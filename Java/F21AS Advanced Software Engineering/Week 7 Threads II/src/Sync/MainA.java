package Sync;

//http://java.sun.com/docs/books/tutorial/essential/concurrency/guardmeth.html
public class MainA {
	public static void main(String args[]) {
		//create the shared object
		SharedObject so  = new SharedObject();
		//create a producer who knows about the shared object
		Producer p = new Producer(so);
		//create a producer thread and start it
		Thread producerThread = new Thread (p);
		producerThread.start();

		//create a consumer who knows about the shared object
		Consumer c = new Consumer(so);
		//create a consumer thread and start it
		Thread consumerThread = new Thread (c);
		consumerThread.start();
	}
} 