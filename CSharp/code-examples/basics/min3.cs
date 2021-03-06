// Example on nullable types
// Object-oriented Programming in C# for C and Java programmers
// Kurt N�rmark �
// Department of Computer Science, Aalborg University, Denmark
// Sec 14.9
// http://www.cs.aau.dk/~normark/oop-csharp/html/notes/more-classes_themes-value-types-sect.html#more-classes_nullable-types_title_1

using System;

// Program 14.18    An integer sequence with Min and Max operations - with int?. 	
// modified for non o-o sequences to use it as example before classes

class IntSequenceClient{

  public static int? Min(int[] sequence){
    int theMinimum;
    if (sequence.Length == 0)
      return null;
    else {
      theMinimum = sequence[0];
      foreach(int e in sequence)    
        if (e < theMinimum)
           theMinimum = e;
    }
    return theMinimum;
  }

  public static int? Max(int[] sequence){
    int theMaximum;
    if (sequence.Length == 0)
      return null;
    else {
      theMaximum = sequence[0];
      foreach(int e in sequence)    
        if (e > theMaximum)
           theMaximum = e;
    }
    return theMaximum;
  }

  public static void ReportMinMax(int[] sequence){
    if (Min(sequence).HasValue && Max(sequence).HasValue)
      Console.WriteLine("Min: {0}. Max: {1}", 
                         Min(sequence), Max(sequence));
    else
      Console.WriteLine("Int sequence is empty");
  }

  public static void Main(){
    int[] is1 = new int[] { -5, -1, 7, -8, 13};
    int[] is2 = new int[] { };

    ReportMinMax(is1);
    ReportMinMax(is2);     
  }

}