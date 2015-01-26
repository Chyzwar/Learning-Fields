package Wait;

//http://java.sun.com/docs/books/tutorial/essential/concurrency/guardmeth.html
public class Consumer implements Runnable {
	private SharedObject so;
	
	public Consumer  (SharedObject so) {
		this.so = so;
	}
	
	public void run() {
		while(!so.getDone()) {
			try { Thread.sleep(100);  }
			catch (InterruptedException e) {}
			int number = so.get();
			
		}
	}
}
