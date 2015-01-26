/* Sequential verion:
int n = ...
for (int i = 0; i < n; i++)
{
   // ... 
}

The parallel version has this signature:

Parallel.For(int fromInclusive, 
             int toExclusive, 
             Action<int> body);

*/

using System;
using System.Threading.Tasks;
// using Parallel;

class ParallelLoops {

  private static int Fib(int n) {
    if (n==0) { return 1; } 
    else if (n==1) { return 1; }
    else {
      int n1 = Fib(n-1);
      int n2 = Fib(n-2);
      return n1+n2;
    }
  }

  private int SomeComputation(int i) {
    return Fib(i);
  }

  private delegate void WorkerDelegate();

  private static void TimeIt(WorkerDelegate worker) {
       DateTime startTime = DateTime.Now;
       DateTime stopTime = DateTime.Now;
       worker();
       TimeSpan duration = stopTime - startTime;
       Console.WriteLine("Elapsed time: {0}", duration.ToString());
  }

  public static void Main(string []args) {
     if (args.Length != 3) { // expect 1 arg: value to double
       System.Console.WriteLine("Usage: <prg> <k> <m> <n>");
       System.Console.WriteLine("k ... number of cores to use");
       System.Console.WriteLine("m, n ... the range of values to apply Fib to; a good range isP: 35 39");
     } else {    
       int k = Convert.ToInt32(args[0]);
       int m = Convert.ToInt32(args[1]);
       int n = Convert.ToInt32(args[2]);
       int[] fibs = new int[n];

       /* Parallel version, using only 2 tasks */
       var options = new ParallelOptions() { MaxDegreeOfParallelism = k};
       TimeIt(() => {
       Parallel.For(m, n, options, i =>
	  {
	    fibs[i] = ParallelLoops.Fib(i);
	  });
	 });
       for (int j = m; j < n; j++) {
	 Console.WriteLine("Fib({0}) = {1}", j, fibs[j]);
       }

     }
  }
}