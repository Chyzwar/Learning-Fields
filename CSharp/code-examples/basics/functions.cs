// Examples from Lecture 3: C# Basics

class Functions {
   static void Main() {
      int[] arr = {0,1,2,3,4,5,6,7,8,9};
      int x = 0;
      int n = 3;
      int n1 = 5;
      int n2 = 7;
      int n3 = 9;
      System.Console.WriteLine("Testing array operations on this array: " + showArr(arr));
      System.Console.WriteLine("Get of {0}-th elemnt = {1}", n, Get(arr,n));
      System.Console.WriteLine("Setting the {0}-th elemnt to {1}", n1, x);
      Set(arr,n1,x);
      System.Console.WriteLine("Modified array: " + showArr(arr));
      System.Console.WriteLine("SetSteping the {0}-th elemnt to {1}", n2, x);
      SetStepBroken(arr,n2,x);
      System.Console.WriteLine("Modified array: " + showArr(arr));
      System.Console.WriteLine("Index = {0}", n2);
      System.Console.WriteLine("SetSteping the {0}-th elemnt to {1}", n3, x);
      SetStep(arr,ref n3,x);
      System.Console.WriteLine("Modified array: " + showArr(arr));
      System.Console.WriteLine("Index = {0}", n3);
   }      

   static int Get (int[] arr, int n) {
     return arr[n];
   }
      
   static void Set (int[] arr, int n, int x) {
     arr[n] = x;
   }
      
   static void SetStepBroken (int[] arr, int n, int x) {
     arr[n] = x;
     n +=1 ;
   }
      
   static void SetStep (int[] arr, ref int n, int x) {
     arr[n] = x;
     n +=1 ;
   }
      
   static string showArr(int[] arr) {
     string s = "";
     foreach (int i in arr) {
      if (s!="") {
        s += ',';
      }
      s += i.ToString();
     }
     return s;
   }
}
