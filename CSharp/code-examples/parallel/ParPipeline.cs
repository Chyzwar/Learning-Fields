/* 
From: 
Parallel Programming with Microsoft® .NET
Design Patterns for Decomposition and Coordination on Multicore Architectures
By Colin Campbell, Ralph Johnson, Ade Miller, Stephen Toub
Publisher: Microsoft Press
Released:  August 2010 
On-line: http://msdn.microsoft.com/en-us/library/ff963547.aspx

Chapter on Pipelines

Simplified example from the book.
*/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Concurrent;
using System.Threading.Tasks;

class Pipeline<T> where T:IComparable {

  public delegate T IncDelegate (T x);

  private static void mkSequence(List<T> seq, T m, T n, IncDelegate inc) {
    if (m.CompareTo(n)>0) { // m>n
      return;
    } else {
      T m1 = inc(m);
      seq.Add(m);
      mkSequence(seq, m1, n, inc);
      return;
    }
  }

  public delegate T ProducerDelegate(T x, T y);

  public static void Producer(BlockingCollection<T> output,
			      T from, T to, IncDelegate inc
			      /* int seed */)
  {
    // System.Console.WriteLine("Producer running ... ");
    List<T> items = new List<T>();
    mkSequence(items, from, to,  inc);
    try
      {
 	foreach (T item in items) {
 	  output.Add(item);
 	}
      }
    finally
      {
	output.CompleteAdding();
      }
  }

  public delegate T ConsumerDelegate(T x);

  public static void Consumer(BlockingCollection<T> input,
			      ConsumerDelegate worker,
			      BlockingCollection<T> output) {
    // System.Console.WriteLine("Consumer running ... ");
    try
      {
	foreach (var item in input.GetConsumingEnumerable())
	  {
	    var result = worker(item);
	    output.Add(result);
	  }
      }
  finally
    {
      output.CompleteAdding();
    }
  }


  // public static string result_str = "";

  public static string LastConsumer(BlockingCollection<T> input, 
				    string str)
  {
    foreach (var item in input.GetConsumingEnumerable()) {
      str += " "+item.ToString();
    }
    return str;
  }
}

public class Tester {
  public static void Main(string []args) {
     if (args.Length != 3) { // expect 1 arg: value to double
       System.Console.WriteLine("Usage: <prg> <k> <m> <n>");
       System.Console.WriteLine("k ... number of cores to use");
       System.Console.WriteLine("m, n ... the range of values to apply square function on ");
     } else {    
       int k = Convert.ToInt32(args[0]); // unused
       int m = Convert.ToInt32(args[1]);
       int n = Convert.ToInt32(args[2]);
       int sum = 0;
       object lockObject = new { }; // any non-null object will do as lock
       // generates a range from input values; slightly artificial
       // List<int> seq = new List<int>();
       // mkSequence(seq, m, n);
       Pipeline<int>.IncDelegate inc = new Pipeline<int>.IncDelegate(x => x+1);

       /* Parallel version, using only 2 tasks */
       System.Console.WriteLine("Generating a list of squares, using a pipeline: {0} .. {1}", m, n);

       try {
	   int limit = 10; // buffer size
	   string str = "";
	   string result_str = "";
	   var buffer1 = new BlockingCollection<int>(limit);
	   var buffer2 = new BlockingCollection<int>(limit);
	   
	   var f = new TaskFactory(TaskCreationOptions.LongRunning,       
				   TaskContinuationOptions.None);
	   System.Console.WriteLine("Starting Producer writing to buffer1 ... ");
	   var task1 = f.StartNew(() => Pipeline<int>.Producer(buffer1, m, n, inc)); // mkSequence(seq,m,n)));
	   System.Console.WriteLine("Starting Consumer, reading from buffer1 writing to buffer2 ... ");
	   var task2 = f.StartNew(() => Pipeline<int>.Consumer(buffer1, 
							       new Pipeline<int>.ConsumerDelegate(x => x*x),
							       buffer2));
	   System.Console.WriteLine("Starting LastConsumer reading from buffer2 ... ");
	   var task3 = f.StartNew(() => { result_str = Pipeline<int>.LastConsumer(buffer2, str); });

	   System.Console.WriteLine("Waiting for all results ... ");
	   Task.WaitAll(task1, task2, task3);
	   System.Console.WriteLine("Result is: {0} ", result_str);
       } finally {
	   // ... release handles to unmanaged resources ...
       }
     }
  }
}
