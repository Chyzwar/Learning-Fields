/* Goldbach conjecture:
   Every even integer greater than 2 can be expressed as the sum of two primes.
 Usage: goldbach <int>

 Setup: 
 ~/OPT/x86_64-unknown-linux/bin/dmcs --version
 Mono C# compiler version 2.10.2.0

 Compile:  ~/OPT/x86_64-unknown-linux/bin/dmcs  -optimize+ -out:goldbach_par.exe goldbach_par.cs
 Run:      time ~/OPT/x86_64-unknown-linux/bin/mono  goldbach_par.exe 65536

 ----------------------------------------------------------------------------- */

using System;
using System.Collections;
using System.Collections.Generic;    // for List<T>

// for parallelism:
using System.Collections.Concurrent; // partitioner
using System.Threading.Tasks; // parallel patterns

class Primes : IEnumerable {
  private static List<int> all_primes;
  private int ctr = 0;

  // enumerator
  public IEnumerator<int> GetEnumerator() {
    foreach (int n in all_primes) {
      yield return n;
    }
  }
  // required to fulfill IEnumerable
  System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator(){
    throw new NotImplementedException();
  }

  // Build a list of all primes
  public Primes(int m) {
    bool is_prime;
    all_primes = new List<int>();
    all_primes.Add(2);  ctr++;
    for (int n = 3; n<m; n+=2) {
      is_prime = true;
      foreach (int p in all_primes) {
	if (n % p == 0) {
	  is_prime = false;
	  break;
	}
      }
      if (is_prime) { all_primes.Add(n);  ctr++; }
    }
  }
}

public class Goldbach {

  public static void Main (string []args) {
     if (args.Length < 1) { // expect 1 args: x
       System.Console.WriteLine("Usage: goldbach <int>");
     } else if (args.Length == 2) {
       int p = Convert.ToInt32(args[0]);
       int x = Convert.ToInt32(args[1]);
       System.Console.WriteLine("Parallel Goldbach conjecture up to {0} is {1} on {2} processors", x, p, goldbach_par(x,p));
     } else {
       int x = Convert.ToInt32(args[0]);
       System.Console.WriteLine("Goldbach conjecture up to {0} is {1}", x, goldbach_naive(x));
     }
  }

  public static bool goldbach_naive (int m) {
    Primes primes = new Primes(m);
    bool found = false;

    for (int n = 4; n<m; n+=2) {
      found = false;
      foreach (int p in primes) { 
	foreach (int q in primes) { 
	  if (n == p+q) {
	    found = true;
	  }
	}
	if (found) break;
      }
      if (!found) break;
    }
//     if (!found) 
//       System.Console.WriteLine("False: no Goldbach pair found for {0}", m); 
    return found;
  }

  public static bool goldbach_par (int m, int proc) {
    // initialise list of primes eagerly
    Primes primes = new Primes(m);
    Object foundLock = new Object();
    bool globalFound = false;

    /* Parallel version, using only k tasks */
    var options = new ParallelOptions() { MaxDegreeOfParallelism = proc};

    // for (int n = 4; n<m; n+=2) {
    Parallel.For(4, m, options, (n, loopState) => {
      bool found = false; // thread-local
      foreach (int p in primes) { 
	foreach (int q in primes) { 
	  if (n == p+q) {
	    found = true;
	  }
	}
	if (found) { lock (foundLock) { globalFound = true; } ;  loopState.Break(); }
      }
      if (!found) loopState.Break();
      n++; n++; 
      });
//     if (!found) 
//       System.Console.WriteLine("False: no Goldbach pair found for {0}", m); 
    return globalFound;
  }
}
