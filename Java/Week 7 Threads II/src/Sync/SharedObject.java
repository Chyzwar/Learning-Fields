package Sync;

public class SharedObject {
 private int n;  //a number

 //start number at 0
 public SharedObject () {  n=0; }

 //called by consumer, gets and prints the value of n
 public synchronized int get() {
   System.out.println("Got: " + n);
   return n;
 }

 //by producer, puts and prints the value of n
	public synchronized void put(int n) {
		this.n = n;
		System.out.println("Put: " + n);
	} 
}