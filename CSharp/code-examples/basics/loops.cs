// Simple examples using loops over arrays

class Loops {
   static void Main() {
      int[] arr = {1,2,3,4,5,6,7,8,9,10};
      System.Console.WriteLine("Testing some loops now...");
      Loops.Run(10);
      Loops.SumArr(arr);
      int[] arrr = new int[10];
      int i;
      for (i=0; i<10; i++) { // beware of indices
        arrr[i]=i-1;
      }
      Loops.SumArr(arrr);
   }
   static void Run(int n) {
     int i = 0, s = 0;
     while (i<=n) {
       s += i;
       i++;
     }
     System.Console.WriteLine("Sum from 0 to "+n+" = "+s);

     s=0;
     for (i=0; i<=n; i++) {
       s += i;
     }
     System.Console.WriteLine("Sum from 0 to "+n+" = "+s);
   }
   static void SumArr(int[] arr) {
     int i, s = 0;
     for (i=0; i<arr.Length; i++) {
       s+=arr[i];
     }
     System.Console.WriteLine("SumArr = "+s);
     s = 0; 
     foreach (int j in arr) { // need different variable here
       s+=j;
     }
     System.Console.WriteLine("SumArr = "+s);
   }
}
