// Lecture 7: Threading
// Incrementer/Decrementer example, now with tunable lo- and hi-marks
// This demonstrates how to indirectly pass an argument to the Incremener/Decrementer methods
// -----------------------------------------------------------------------------

using System.Threading;
using System;

namespace Threads1 {

  class Tester {
    static void Main (string[] args) {
      if (args.Length != 2) { // expect 2 args: a low-mark and a high-mark
	System.Console.WriteLine("Usage: <prg> <loMark> <hiMark>");
      } else {    
	Tester t = new Tester();
	t.DoTest(args);
      }
    }

    public void DoTest(string[] args) {
      Thread[] myThreads = {
	new Thread( new ThreadStart(Decrementer)), // arg is a function from void to void
	new Thread( new ThreadStart(Incrementer))  // arg is a function from void to void
      };

      int n = 1;
      // init the marks and make them available to Incrementer/Decrementer
      // NB: this must be done *before* launching the threads, otw we have a race condition!
      loMark = Convert.ToInt32(args[0]);
      hiMark = Convert.ToInt32(args[1]);
      // start all threads
      foreach (Thread myThread in myThreads) {
	myThread.IsBackground = true;
	myThread.Name = "Thread"+n.ToString();
	Console.WriteLine("Starting thread {0}", myThread.Name);
	myThread.Start();
	n++;
	// add a delay in starting all threads
	Thread.Sleep(500);
      }
      // wait for all threads to end
      foreach (Thread myThread in myThreads) {
	myThread.Join();
      }
      // after all threads end print this message
      Console.WriteLine("All my threads are done");
    }

    private int hiMark;       // never increment beyond this mark
    private int loMark;       // never decrement below this mark
    private int counter = 0;

    public void Decrementer() {
      try {
	Console.WriteLine("[{0}] Decrementer finds a loMark of {1}.",
			    Thread.CurrentThread.Name, loMark);
	// (1) synchronise this area
	Monitor.Enter(this);

	while (counter < loMark ) { // now, this uses the loMark 
	  Console.WriteLine("[{0}] In Decrementer. Counter: {1}. Waiting...",
			    Thread.CurrentThread.Name, counter);
	  Monitor.Wait(this);
	}

	while (counter > 0) {
	  int temp = counter;
	  temp--;
	  Thread.Sleep(1);
	  counter = temp;
	  Console.WriteLine("[{0}] In Decrementer. Counter: {1}.",
			    Thread.CurrentThread.Name, counter);
	}
      } finally {
	Monitor.Exit(this);
      }
    }
    public void Incrementer() {
      try {
	Console.WriteLine("[{0}] Incrementer finds a hiMark of {1}.",
			    Thread.CurrentThread.Name, hiMark);
	// (1) synchronise this area
	// Monitor.Enter(this);

	while (counter < hiMark) { // now, this uses the hiMark 
	  // (2) more fine-grained control like this:
          Monitor.Enter(this);
	  int temp = counter;
	  temp++;
	  Thread.Sleep(1);
	  counter = temp;
	  Console.WriteLine("[{0}] In Incrementer. Counter: {1}.",
			    Thread.CurrentThread.Name, counter);
	  // (2) more fine-grained control like this:
	  Monitor.Pulse(this); // inform waiting threads of the change
	  Monitor.Exit(this);  // leave monitor
	  Thread.Sleep(1);     // give other threads time to work
	}
	//Monitor.Pulse(this); // (1) release lock after all increments!
      } finally {
	Console.WriteLine("[{0}] Exiting ...",
			  Thread.CurrentThread.Name);
	Monitor.Exit(this);
      }
    }
  }
}
