// see: http://msdn.microsoft.com/en-us/library/chfa2zb8.aspx
// in-place Swap

// compile with: /unsafe
using System;

class UnsafeTest
{
  // Unsafe method
  unsafe static void Swap(int* x, int *y) {
    int z = *x;
    *x = *y;
    *y = z;
  }

  unsafe static void Main() {
     int x = 5;
     int y = 7;
     // Unsafe method: it uses j
     Console.WriteLine("Before swap: x = {0}; y = {1}", x, y);
     Swap(&x,&y);  // passes pointers to the locations of the variables
     Console.WriteLine("After Swap:  x = {0}; y = {1}", x, y);
   }
}
// Output: 15


