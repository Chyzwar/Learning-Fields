// Lecture 6: Advanced C# Constructs: Delegates

using System;

// simple higher-order example, using delegates
// this class takes an int -> int function and applies it twice
public class Twice {
  // delegate, specifying the type of the function argument
  public delegate int Worker(int i);

  // the higher-order function twice applies the
  // worker function twice
  public static int twice(Worker worker, int x) {
    return worker(worker(x));
  }
}

class TestClass {
  public static int Double(int val) {
    return val*2;
  }

  public static void Main(string []args) {
     if (args.Length != 1) { // expect 1 arg: value to double
       System.Console.WriteLine("Provide an int value as argument");
     } else {    
       int x = Convert.ToInt32(args[0]);
       System.Console.WriteLine("Applying double once on {0} gives {1}", x, TestClass.Double(x));
       System.Console.WriteLine("Applying double twice, using class Twice, on {0} gives {1}", x, Twice.twice(Double, x));
     }
  }
}

