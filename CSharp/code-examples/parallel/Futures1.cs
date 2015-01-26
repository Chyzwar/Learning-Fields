/* 
From: 
Parallel Programming with Microsoft® .NET
Design Patterns for Decomposition and Coordination on Multicore Architectures
By Colin Campbell, Ralph Johnson, Ade Miller, Stephen Toub
Publisher: Microsoft Press
Released:  August 2010 
On-line: http://msdn.microsoft.com/en-us/library/ff963547.aspx

Chapter on Futures

*/

using System;
using System.Threading.Tasks;
// using Parallel;
using System.Collections.Generic;    // for List<T>
using System.Collections.Concurrent; // for Partitioner

class Futures {

  private static int Fib(int n) {
    if (n==0) { return 1; } 
    else if (n==1) { return 1; }
    else {
      int n1 = Fib(n-1);
      int n2 = Fib(n-2);
      return n1+n2;
    }
  }

  private static int F1(int n) { 
    return Fib(n);
  }

  private static int F2(int n) { 
    return n+1; // Next(n);
  }

  private static int F3(int n) { 
    return Fib(n);
  }

  private static int F4(int m, int n) { 
    return m+n;
  }

  private static int seq_code(int a) { 
    int b = F1(a); 
    int c = F2(a); 
    int d = F3(c); 
    int f = F4(b, d);
    return f;
  }

  private static int par_code(int a) { 
    // constructung a future generates potential parallelism
    Task<int> futureB = Task.Factory.StartNew<int>(() => F1(a));
    int c = F2(a); 
    int d = F3(c); 
    int f = F4(futureB.Result, d);
    return f;
  }

  public static void Main(string []args) {
     if (args.Length != 2) { // expect 1 arg: value to double
       System.Console.WriteLine("Usage: <prg> <parallel?> <n>");
       // System.Console.WriteLine("k ... number of cores to use");
       System.Console.WriteLine("n ... input to Fib");
     } else {    
       // int k = Convert.ToInt32(args[0]);
       // int m = Convert.ToInt32(args[1]);
       int p = Convert.ToInt32(args[0]);
       int n = Convert.ToInt32(args[1]);
       // int sum = 0;
       if (p==0) {
	 System.Console.WriteLine("Sequential computation of Fib({0}) + Fib({0}+1):", n, seq_code(n));
       } else {
	 System.Console.WriteLine("Parallel computation of Fib({0}) + Fib({0}+1) using futures:", n, par_code(n));
       }
     }
  }
}
       
