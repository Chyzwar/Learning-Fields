// Container example using advanced C# constructs:
// . indexers
// . generics
// . collections
// . exceptions
// . delegates
// -----------------------------------------------------------------------------

using System;
using System.Collections;
using System.Threading.Tasks;

public class MyContainer<T>: // a collection, generic over element type T
                             IEnumerable, // it implements IEnumerable to use foreach loops etc
                             ICollection,  // it implements ICollection to use Count etc
                             ICloneable   // it implements the Clone method
  where T:IComparable { 
  // T:ICloneable { // T needs to be IComparable, to implement sorting

  private T[] items;                 // contents
  private int ctr = 0;               // first free slot in array
  public const int max_items = 16*256; // hard-wired upper bound
  // unused; see properties below
  // private const bool IsReadOnly = false; 
  // private const bool IsSynchronized = false; 
  private object containerLock = new { }; // any non-null object will do as lock

  // exceptions
  public class ContainerException: System.Exception {
    public ContainerException(string msg): base(msg) { }
  }

  // constructor
  public MyContainer (params T[] inits) { // params allows a list of arguments rather than array
    items = new T[max_items];             // could use inits.CopyTo(this.items, 0) here
    foreach (T t in inits) {
      items[ctr++] = t;
    }
  }

#region ICollection Members
  // -------------------------------------------------------
  // ICollection implementation
  // see http://msdn.microsoft.com/en-us/library/92t2ye13.aspx

  public void Add (T t) {
   if (ctr >= items.Length) {
     throw new ContainerException(String.Format("Reached max length of container: {0}", items.Length));
   } else {
     items[ctr++] = t;
   }
  }

  public void Clear() {
    ctr=0;
  }

  public bool Contains(T x) {
    foreach (T t in this) {
      if (t.CompareTo(x)==0) { // uses method from IComparable
	return true;
      }
    }
    return false;
  }

  public bool Remove(T x) {
    throw new ContainerException("Remove method not implemented for MyContainer");
  }

  public void CopyTo(T[] arr, int arrInd) {
    int i = arrInd;
    foreach (T t in this) {
      arr[i++] = t;
    }
  }

  public void CopyTo(Array arr, int index) {
    int i = index;
    foreach (T t in this) {
      arr.SetValue(t, i++);
    }
  }

  // indexer
  public T this[int index] {
    get {
      if (index<0 || index>=this.Count) {
	throw new ContainerException(String.Format("get: index {0} out of bounds: [{1},{2}[", index, 0, this.Count));
      } else {
        return items[index];
      }
    }
    set {
      if (index >= ctr) {
	throw new ContainerException(String.Format("set: index {0} out of bounds: [{1},{2}[", index, 0, this.Count));
      }  else {
        items[index] = value;
      }
    }
  }

  // number of elements in the collection
  public int Count {
    get { return ctr; }
  }

  public bool IsReadOnly { get { return false; } }

  public bool IsSynchronized { get { return false; } }

  public object SyncRoot { get { return containerLock; } }
#endregion

#region IEnumerator Members
  // -------------------------------------------------------
  // IEnumerator implementation 
  // see http://msdn.microsoft.com/en-us/library/78dfe2yb.aspx

  public IEnumerator GetEnumerator() { 
    return (IEnumerator) new MyEnumerator(this);
  }

  private class MyEnumerator : IEnumerator {
    private int curr_idx;
    private MyContainer<T> this_cont;

    public MyEnumerator (MyContainer<T> cont) {
      this.curr_idx = -1;    // NB: needs to start with -1; 
      this.this_cont = cont;
    }

    public bool MoveNext( ) {
      if (curr_idx>=this.this_cont.ctr-1) {
	return false;
      } else {
	curr_idx++;
	return true;
      }
    }

    public void Reset( ) {
      curr_idx=-1;
    }

    public object Current {
      get { return this_cont[curr_idx]; }
    }
  }
#endregion

  // obsolete:
  // private int GetNumEntries() { return ctr; }

#region ICloneable Members
  public object Clone()        {
    return this.MemberwiseClone();
  }
#endregion

  // -------------------------------------------------------
  // specialised methods

  public void print() {
    // make use of IEnumerable implementation
    IEnumerator iter = this.GetEnumerator();
    Console.Write("Container contains: [");
    /* get ','s right */
    if (iter.MoveNext()) {
      Console.Write("{0}", iter.Current);
    }
    while (iter.MoveNext()) { 
      Console.Write(",{0}", iter.Current);
    }
    Console.WriteLine("]");
  }

  // swap i-th and j-th element of the array
  private void Swap(int i, int j) {
    if (i==j) {
      return;
    } else {
      T tmp = items[i];
      items[i] = items[j];
      items[j] = tmp;
    }
  }

  // -------------------------------------------------------
  // delegates used in this class

  public delegate int CompareDelegate(T s1, T s2);

  public MyContainer<T> Merge(CompareDelegate cmp, MyContainer<T> other) {
    int i = 0;
    int j = 0;
    bool finished = false;
    MyContainer<T> res = new MyContainer<T>();

    while (!finished) {
      if (cmp(this.items[i], other.items[j]) > 0) {
	res.Add(other.items[j]);
	j++;
      } else {
	res.Add(this.items[i]);
	i++;
      }	    
      finished = i>=this.Count || j>=other.Count;
    }
    for(int k = i; k<this.Count; k++) {
      res.Add(this.items[k]);
    }
    for(int k = j; k<other.Count; k++) {
      res.Add(other.items[k]);
    }
    return res;
  }

  public MyContainer<T> MergeFromTo(CompareDelegate cmp, int from, int mid, int to) {
    int i = from;
    int j = mid;
    bool finished = false;
    // TODO: nuke new container; this should be INPLACE!!!!
    MyContainer<T> res = new MyContainer<T>();

    while (!finished) {
      if (cmp(this.items[i], this.items[j]) > 0) {
	res.Add(this.items[j]);
	j++;
      } else {
	res.Add(this.items[i]);
	i++;
      }	    
      finished = i>=mid || j>=to;
    }
    for(int k = i; k<mid; k++) {
      res.Add(this.items[k]);
    }
    for(int k = j; k<to; k++) {
      res.Add(this.items[k]);
    }
    return res;
  }

  public void ParSort(CompareDelegate cmp) {
    int m = this.Count / 2;
    Parallel.Invoke(
                     () => SortFromTo(cmp, 0, m-1),
                     () => SortFromTo(cmp, m, this.Count-1));
  }

  public void Sort(CompareDelegate cmp) {
    SortFromTo(cmp, 0, this.Count-1);
  }

  public void SortFromTo(CompareDelegate cmp, int from, int to) {
    // implements a naive bubble-sort
    bool swapped = false;

    if (from<0 || to>this.Count-1) {
      throw new System.Exception("SortFromTo: out of bounds");
    }
    do {
      swapped = false;
      for (int i = from; i<to; i++) {
	if (cmp(items[i], items[i+1]) > 0) {
	  Swap(i, i+1);
	  swapped = true;
	}
      }
    } while (swapped);
  }

  // an equivalent implementation using interface IComparable rather than delegates
  public void Sort0() {  // no argument; the IComparable interface provides CompareTo
    // implements a naive bubble-sort
    bool swapped = false;
    do {
      swapped = false;
      for (int i = 0; i<=this.Count-2; i++) {
	if (items[i].CompareTo(items[i+1]) > 0) {
	  Swap(i, i+1);
	  swapped = true;
	}
      }
    } while (swapped);
  }

  public bool IsSorted(CompareDelegate cmp) {
    for (int i = 0; i<=this.Count-2; i++) {
      if (cmp(items[i], items[i+1]) < 0) { // error case
	return false;
      }
    }
    return true;
  }

 // From: http://msdn.microsoft.com/en-us/library/ff963551.aspx
 /*
 static void SequentialQuickSort(int[] array, int from, int to)
 {
   if (to - from <= Threshold)
   {
     InsertionSort(array, from, to);
   }
   else
   {
     int pivot = from + (to - from) / 2;
     pivot = Partition(array, from, to, pivot);
     SequentialQuickSort(array, from, pivot);
     SequentialQuickSort(array, pivot + 1, to);
   }
 }
 */
}

public class Tester {
  // instances for ComparerDelegate
  /* not used any more
  private int str_lt (string s1, string s2) { 
    return String.Compare(s1,s2);
  }
  private int str_gt (string s1, string s2) { 
    return (-1)*String.Compare(s1,s2);
  }
  */
  private int int_lt (int i, int j) { 
    if (i==j) {
      return 0;
    } else {
      if (i<j) {
	return -1;
      } else {
	return 1;
      }
    }
  }
  private int int_gt (int i, int j) { 
    return (-1)*int_lt(i,j);
  }

  public static void Main () {
    MyContainer<string> cont = new MyContainer<string>("One", "Two", "Three", "Four");
    Tester tst = new Tester();

    Console.WriteLine("Container of strings initialised, containing {0} items", cont.Count);
    // direct write access
    try {
      cont[4] = "Five";
    } catch (MyContainer<string>.ContainerException e) {
      Console.WriteLine(e.Message);
      Console.WriteLine("Implementation restriction: can only write to an existing field; currently only fields up to {0} exist", cont.Count-1);
    }

    // direct read access
    Console.WriteLine("Printing contents using for loop: ");
    for (int i = 0; i<cont.Count; i++) {
     Console.WriteLine("cont[{0}]: {1}", i, cont[i]);
    }
    // make use of IEnumerable implementation
    Console.WriteLine("Printing contents using foreach loop: ");
    foreach (string s in cont) {
      Console.WriteLine("Value: {0}", s);
    }
    Console.WriteLine("Sorting contents alphabetically (delegate version) ...");
    MyContainer<string>.CompareDelegate cmp_lt = new MyContainer<string>.CompareDelegate(String.Compare); // tst.lt
    cont.Sort(cmp_lt);
    cont.print();
    Console.WriteLine("Reverse sorting contents alphabetically (delegate version) ...");
    MyContainer<string>.CompareDelegate cmp_gt = new MyContainer<string>.CompareDelegate((string s1, string s2) => { return (-1)*String.Compare(s1,s2) ; }); 
    cont.Sort(cmp_gt);
    cont.print();
    Console.WriteLine("Sorting contents alphabetically (IComparable version) ...");
    cont.Sort0();
    cont.print();

    // container with int contents
    int [] int_arr = new int[] { 8, 4, 6, 2 };
    MyContainer<int> int_cont = new MyContainer<int>(int_arr);
    Console.WriteLine("Container of ints initialised, containing {0} items", int_cont.Count);
    Console.WriteLine("Sorting contents ...");
    MyContainer<int>.CompareDelegate cmp_int_lt = new MyContainer<int>.CompareDelegate(tst.int_lt); // tst.lt
    int_cont.Sort(cmp_int_lt);
    int_cont.print();
    Console.WriteLine("Reverse sorting contents ...");
    MyContainer<int>.CompareDelegate cmp_int_gt = new MyContainer<int>.CompareDelegate(tst.int_gt);
    int_cont.Sort(cmp_int_gt);
    int_cont.print();
    Console.WriteLine("Sorting contents (IComparable version) ...");
    int_cont.Sort0();
    int_cont.print();

    // -----------------------------------------------------------------------------
    // Parallelism Example:


    // Generate a sizable list, and sort it in ascending and descending order, in parallel:
    // MyContainer<int>.ComparDelegate[] cmps = new MyContainer<int>.ComparDelegate[2] { cmp_int_lt, cmp_int_gt };
    Random rg = new Random(1701); // fix a seed for deterministic results
    MyContainer<int> ic1 = new MyContainer<int>();
    for (int i = 0; i < MyContainer<int>.max_items; i++) {
      ic1.Add(rg.Next());
    }
    // Console.WriteLine("Input array:");    ic1.print();
    MyContainer<int> ic2 = (MyContainer<int>)ic1.Clone();
    Console.WriteLine("Sorting a random array ...");
    /* sequential code: */
    ic1.Sort(cmp_int_lt);
    ic2.Sort(cmp_int_gt);
    /* parallel code: 
    Parallel.Invoke( // generate two parallel threads
		    () => ic1.Sort(cmp_int_lt),
                    () => ic2.Sort(cmp_int_gt));
    */
    /* parallelised sorting algorithm: 
    ic1.ParSort(cmp_int_lt);
    ic2.ParSort(cmp_int_gt);
    */
    // Console.WriteLine("Sorted (ascending):");    ic1.print();
    Console.WriteLine("Sorted?: {0}", ic1.IsSorted(cmp_int_lt).ToString());
    // Console.WriteLine("Sorted (descending):");    ic2.print();
    Console.WriteLine("Sorted?: {0}", ic2.IsSorted(cmp_int_gt).ToString());
  }
}
