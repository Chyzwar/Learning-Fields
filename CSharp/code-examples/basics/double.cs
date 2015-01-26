// Hello-world-like example program
// DOS

class TestClass {
  public static void Main () {
    MyClass m = new MyClass();
    System.Console.WriteLine("99*2 = {0}", m.Double(99));
  }
}

class MyClass {
  public int Double(int val) {
    return val*2;
  }
}