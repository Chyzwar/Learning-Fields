using System;

class Tester {
   unsafe static void Main()
   {
     // string str = "";
      string str = "test";
      string str0 = "";
      fixed (char *p = str) {
	fixed (char *q = str0) {
	  // FixedBuffer fb = new FixedBuffer(test);
	  for (int i = 0; i<str.Length; i++) {
	    *q++ = *p++;
	  }
	}
      }
      // FixedBuffer.strcpy(p);
      Console.WriteLine("test str: "+str);
      Console.WriteLine("copied str: "+str0);
   }
}

/*
unsafe class FixedBuffer {
  // contents of the buffer
  public struct MyArray {
    public fixed char name[30];
  }

  public FixedBuffer (string s) {
    MyArray.name = s;
  }

  public void strcpy (string toString) {
    char *c;
    char *d;
    for (c = &MyArray.name; c != '\0'; c++) {
      *d = *c;
    }
  }
}      
*/   
