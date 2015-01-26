// Author: rajeshvs@msn.com
using System;

class MyClass {
  public unsafe void Tester() {
    int[] iArray = new int[10];
    int[] jArray = new int[10];
    Console.WriteLine("Initialising iArray with i^2 for i = 0..9");
    for (int count=0; count < 10; count++){
      iArray[count] = count*count;
    }
    fixed (int *fromPtr = iArray) {
    fixed (int *toPtr   = jArray) {
      Console.WriteLine("iArray = {0}", showArr(fromPtr, 10));
      Console.WriteLine("jArray = {0}", showArr(toPtr, 10));
      memcpy(fromPtr, toPtr, 10);
      Console.WriteLine("After copying:");
      Console.WriteLine("jArray = {0}", showArr(toPtr, 10));
      Display(toPtr,10);
      Console.WriteLine("4-th element of jArray = {0}", *(toPtr+4));
    }}
  }

  public unsafe static void memcpy (int *p1, int *p2, int n) {
    int *p = p1;
    int *q = p2;
    for (int i = 0; i<n; i++) {
        *q++ = *p++;
      }
  }
  public unsafe static string showArr(int *p, int n) {
     string s = "";
     int *q = p;
     for (int i = 0; i<n; q++, i++) {
      if (s!="") {
        s += ',';
      }
      s += (*q).ToString();
     }
     return s;
  }
  public unsafe void Display(int *pt, int n) {
    for (int i=0; i < n;i++) {
      Console.WriteLine(*(pt+i));
    }
  }
}

class MyClient {
  public static void Main() {
    MyClass mc = new MyClass();
    mc.Tester();
  }
}
