// very simple array functions

class Arrays {
   static void Main() {
      int[] arr1 = {0,1,2,3,4,5,6,7,8,9};
      int[] arr2 = new int[10];

      for (int i = 0; i<arr2.Length; i++) {
	arr2[i] = i;
      }
      System.Console.WriteLine("arr1 = " + showArr(arr1));
      System.Console.WriteLine("arr2 = " + showArr(arr2));
      if (eqArr(arr1,arr2)) {
	System.Console.WriteLine("Both arrays are equal!");
      } else {
	System.Console.WriteLine("The arrays are NOT equal!");
      }
  }      

  static bool eqArr(int[] arr1, int[] arr2) {
    int n1 = arr1.Length, n2 = arr2.Length;
    if (n1!=n2) { return false; }
    for (int i = 0; i<n1; i++) {
      if (arr1[i]!=arr2[i]) {
	return false;
      }
    }
    return true;
  }

  // useful for testing
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
