// Lecture 7: Threading

using System.Threading;
using System;

namespace Threads1 {

  class Tester {
    static void Main () {
      Tester t = new Tester();
      t.DoTest();
    }

    public void DoTest() {
      Thread[] myThreads = {
	new Thread( new ThreadStart(Decrementer)),
	new Thread( new ThreadStart(Incrementer)) };

      int n = 1;
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

    private long counter = 0;

    public void Decrementer() {
      try {
	// (1) synchronise this area
	Monitor.Enter(this);

	while (counter < 5 ) {
	  Console.WriteLine("[{0}] In Decrementer. Counter: {1}. Waiting...",
			    Thread.CurrentThread.Name, counter);
	  Monitor.Wait(this);
	}

	while (counter > 0) {
	  long temp = counter;
	  temp--;
	  Thread.Sleep(2);
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
	// (1) synchronise this area
	// Monitor.Enter(this);

	while (counter < 10) {
	  // (2) more fine-grained control like this:
          Monitor.Enter(this);
	  long temp = counter;
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
