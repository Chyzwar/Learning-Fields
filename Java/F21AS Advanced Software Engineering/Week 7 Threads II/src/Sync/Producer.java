package Sync;


public class Producer implements Runnable {
	private SharedObject so;
	
	public Producer (SharedObject so) { this.so = so;  }
	
	//version 1, 8 times, sleeps then gets the number
	public void run() {
		for (int i = 0; i < 8; i++) {
			//sleep first
			try { Thread.sleep(50);  }
			catch (InterruptedException e) { }
			//puts the number
			so.put(i);
		}
	}
}
