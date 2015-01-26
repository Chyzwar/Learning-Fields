
public class Producer implements Runnable {
	private SharedObject so;
	
	public Producer (SharedObject so) {
		this.so = so;
	}
	
	public void run() {
		for (int i = 0; i < 8; i++) {
			try {
				Thread.sleep(50);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			so.put(i);
		}
		so.setDone();
	}
}
