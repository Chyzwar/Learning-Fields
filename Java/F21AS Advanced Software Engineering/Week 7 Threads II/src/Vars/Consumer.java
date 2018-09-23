
public class Consumer implements Runnable {
	private SharedObject so;
	
	public Consumer  (SharedObject so) {  this.so = so;  }
	
	public void run() {
		boolean done = false;
		while(!done) {
			//sleep first
			try { Thread.sleep(50);  }
			catch (InterruptedException e) { }
			//check to see if last number
			if (so.getDone()) {  done = true;  }
			//get the number
			int number = so.get();
			//check to see if got the number
			if (number == -1) { 	System.out.println("Didn't get the number");  }
		}
	}
}
