// Lecture 7: Threading

using System.Threading;
using System;

namespace Threads0 {
  class Tester {
    static void Main () {
      Tester t = new Tester();
      t.DoTest();
    }

    private int x = 5;
    private int y = 7;
    // could use this field to narrow down the scope of the lock
    // object swapLock = new { };

    public void DoTest() {
      Thread t1 = new Thread( new ThreadStart(Swap));
      Thread t2 = new Thread( new ThreadStart(Swap));

      t1.Start();
      t2.Start();
      t1.Join();
      t2.Join();
    }

    public void Swap() {
      lock (this) { // or: this.swapLock
	Console.WriteLine("Swap enter: x = {0}, y = {1}", this.x, this.y);
	int z = this.x;
	this.x = this.y;
	this.y = z;
	Console.WriteLine("Swap leave: x = {0}, y = {1}", this.x, this.y);
      }
    }
  }
}
