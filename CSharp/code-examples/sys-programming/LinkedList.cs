// Based on on-line code at:
//  http://forums.asp.net/p/375039/377882.aspx#377882
// low-level use of references in C#

using System;
 
public class LinkedListNode {
 LinkedListNode next;
 LinkedListNode prev;
 private int data;

 public int MyData() { // {get{...}set{...}}
  return this.data;
 }

 public void Insert(LinkedListNode node) {
   LinkedListNode nextNode = this.next;
   this.next = node;
   node.prev = this;
   node.next = nextNode;
   if (nextNode != null) { // pitfall
     nextNode.prev = node;
   }
 }

 public void RemoveBuggy() {
  // beware: this.prev and this.next must not be null
  this.prev.next = next;
  this.next.prev = prev;

  //note the nulls are put here to insure stability incase someone has a reference to this node,
  //it'll let the other linked nodes die and keep the user from skipping into a node that is no
  //longer the "next" node.
  this.next = null;
  this.prev = null;
 }

 public void Remove() {
  if (this.prev != null) { this.prev.next = next; }
  if (this.next != null) { this.next.prev = prev; }

  //note the nulls are put here to insure stability incase someone has a reference to this node,
  //it'll let the other linked nodes die and keep the user from skipping into a node that is no
  //longer the "next" node.
  this.next = null;
  this.prev = null;
 }

 public void ShowList () {
   Console.WriteLine("{0} ",this.MyData());
   if (this.next == null) {
     return; 
   } else {
     this.next.ShowList();
   }
 }

 public void ShowListReverse () {
   Console.WriteLine("{0} ",this.MyData());
   if (this.prev == null) {
     return; 
   } else {
     this.prev.ShowListReverse();
   }
 }

 public LinkedListNode GetNext() {
  return next;
 }

 public LinkedListNode (int data) {
  this.data = data;
  // init references
  this.next = null;
  this.prev = null;
 }
}

public class Tester {
  public static void Main() {
    LinkedListNode n1 = new LinkedListNode(1);
    Console.WriteLine("Expect a 1 element list with 1...");
    n1.ShowList();
    // adding 3 at the end
    LinkedListNode n3 = new LinkedListNode(3);
    n1.Insert(n3);
    Console.WriteLine("Inserting 3 after 1 ...");
    Console.WriteLine("Expect a 2 element list with 1 3 ...");
    n1.ShowList();
    Console.WriteLine("Testing showListReverse; expect a 2 element list with 3 1 ...");
    n3.ShowListReverse();
    // adding 2 btw 1 and 2
    LinkedListNode n2 = new LinkedListNode(2);
    n1.Insert(n2);        
    Console.WriteLine("Inserting 2 after 1 ...");
    Console.WriteLine("Expect a 3 element list with 1 2 3 ...");
    n1.ShowList();
    Console.WriteLine("Testing showListReverse; expect a 3 element list with 3 2 1 ...");
    n3.ShowListReverse();
    // removing a node
    n2.Remove();        
    Console.WriteLine("Removing 2 ...");
    Console.WriteLine("Expect a 2 element list with 1 3 ...");
    n1.ShowList();
    Console.WriteLine("Testing showListReverse; expect a 2 element list with 3 1 ...");
    n3.ShowListReverse();
    try {
      n3.RemoveBuggy();        
    } catch (NullReferenceException e) {
      Console.WriteLine("RemoveBuggy didn't check for null pointer, hence this exception: {0}", e.Message);
    }
    n3.Remove();        
    Console.WriteLine("Removing 3 ...");
    Console.WriteLine("Expect a 1 element list with 1 ... ");
    n1.ShowList();
  }
}

