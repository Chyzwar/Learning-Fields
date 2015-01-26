// Example of multi-threading, passing the value retrieved by one thread to the other thread.
// The operations are: (1) reading from a file (2) displaying the file contents
// -----------------------------------------------------------------------------

using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Text;
using System.IO;
using System.Threading;

class FileData {
  // This class holds the context for one file read operation
  // contents of the data that arrived
  private  static int counter = 0;
  private  string filename = null;
  private  string filecontents = null;
  private  object mainLock = new { }; // any non-null object will do as lock

  public  void setFileName(string fn) { this.filename = fn; }
  // public  void setContents(string cont) { this.filecontents = cont; }
  public  void addContents(string cont) { this.filecontents += cont; }
  
  // public string getFileName() { return this.filename; }
  public  int getCounter() { return FileData.counter; }
  public  string getFileName() { return this.filename; }
  public  string getContents() { return this.filecontents; }

  // constructor
  public  FileData (string filename) {
    this.filename = filename;
    FileData.counter++;
  }
}

class Test {
  // A class for testing concurrent read and display threads

  // booleans, indicating whether data has arrived; needed for synchronisation
  private bool okFileName = false;
  private bool okContents = false;
  private object mainLock = new { }; // any non-null object will do as lock

  public FileData currentFileData; // handle on the context for file ops
  public static LinkedList<FileData> allFileData  = new LinkedList<FileData>(); // list of all file contexts

  // define an exception, triggered by the balance being too low
  public class NoFile  : System.Exception {
      public NoFile(string msg) :base(msg) {
      }
  }

  // constructor; needs the filename to read from/display
  public Test(string filename) {
    currentFileData = new FileData(filename);
    okFileName = true;
    allFileData.AddLast(currentFileData);
  }

 // -----------------------------------------------------------------------------
 // 2 threads co-operate to get the HMTL code:
 // . getFContents   retrieves the contents of the file
 // . displayFile  shows the contents, read by the previous thread

 // could use this to set the filename in a separate thread
 // mulT: lookup the file name from a variable
 /*
 public static void getFName() {
  System.Console.WriteLine("<{0}> setting filename ... ", Thread.CurrentThread.Name);
  if (currentFileData.getArgs().Length == 0) { // expect 1 arg: value to double
    // System.Console.WriteLine("Usage: <prg> <filename>");
    lock (mainLock) { // protect write access to static fields
      currentFileData.setFileName("mulT.cs");  // by default, read the sources for this file
      okFileName = true;
    }
  } else {    
    lock (mainLock) { // protect write access to static fields
      currentFileData.setFileName(currentFileData.getArgs()[0]);
      okFileName = true;
    }
  }
 }
 */

 /* Could use ParameterizedThreadStart, which allows to pass an argument to the method in the thread */

 // requires: File.Exists(filename)
 // summary: retrieve the contents for the current file
 public void getFContents() {
   while (!okFileName) { 
     // Thread.Sleep(1);  // busy wait; better: use Wait and Pulse from within getFilenName
     Monitor.Wait(mainLock);
   }
   System.Console.WriteLine("<{0}> we have the name of the file ... ", Thread.CurrentThread.Name);

   StreamReader sr;

   // assert: not (null filename)
   // lock (mainLock) {
 
   try {
     Monitor.Enter(mainLock); // enter critical region

     System.Console.WriteLine("<{0}> reading file contents ... ", Thread.CurrentThread.Name);

     sr = new StreamReader(currentFileData.getFileName());
     // std iteration over the contents of a file
     string inValue = "";
     while((inValue = sr.ReadLine()) != null) // read line-by-line
       // filecontents += inValue + "\n";
       currentFileData.addContents(inValue + "\n");

     okContents = true;
     Monitor.Pulse(mainLock);
   }
     catch(System.Exception ex) {
	    Console.WriteLine(ex.Message); 
     }	
     finally {
       sr.Close();
       Monitor.Exit(mainLock);
   }
 }

  // summary: show the previously retrieved contents for the current file
 public void displayContents() {
    Monitor.Enter(mainLock);
    while (!okContents) { 
      Console.WriteLine("<{0}> Waiting ...", Thread.CurrentThread.Name);  
      Monitor.Wait(mainLock) ; 
    } 
    System.Console.WriteLine("<{0}> we have the contents of the file ... ", Thread.CurrentThread.Name);
    // else { 
    Console.WriteLine("<{0}> Continuing ...", Thread.CurrentThread.Name);  
    Console.WriteLine("------------------------------------------------------- \n<{0}> Contents of file {1} ({2} files in total): \n{3}", 
		      Thread.CurrentThread.Name, currentFileData.getFileName(), currentFileData.getCounter(), currentFileData.getContents()); 
    Console.WriteLine("<{0}> end of file ------------------------------------------------------- ", 
		      Thread.CurrentThread.Name, currentFileData.getFileName());  
    Monitor.Exit(mainLock);
 }

 public void RunTest(){
   // kicks-off 2 threads: one for reading the file contents, the other to display it
   Thread getT = new Thread(new ThreadStart(getFContents));
   getT.Name = "getContentsThread_for_file_" + currentFileData.getFileName();
   Thread displayT = new Thread(new ThreadStart(displayContents));
   displayT.Name = "displayContents_for_file_" + currentFileData.getFileName();
   //getFN.Start();
   getT.Start();
   displayT.Start();
   //getFN.Join();
   getT.Join();
   displayT.Join();
   System.Console.WriteLine("<{0}> thread terminates.", Thread.CurrentThread.Name);
 }
}

// -----------------------------------------------------------------------------

public class MainClass {
 // list of all threads
 public static LinkedList<Thread> allThreads = new LinkedList<Thread>();
 
 // summary: test wrapper, generating a test for each filename in args
 public static void Main(string []args) {
   Thread.CurrentThread.Name = "Main thread";
   Test t;
   Thread currT;
   foreach (string s in args) {
     // set-up a test for file s
     t = new Test(s);
     // run the test
     currT = new Thread( new ThreadStart(t.RunTest) );
     currT.Name = "Tester_for_file_"+s;
     // add the thread to this list of all threads
     allThreads.AddLast(currT);
     // now, start a new thread for the test
     currT.Start();
   }
   foreach (Thread thr in allThreads) {
     // iterate over all threads and join it with the main one
     thr.Join();
     System.Console.WriteLine("<{0}> shutting down.", thr.Name);
   }
   System.Console.WriteLine("<{0}> thread terminates.", Thread.CurrentThread.Name);
 }
}
