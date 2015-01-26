
public class SharedObject {
	private int n;
	private boolean empty;
	private boolean done;
	
	public SharedObject () {
		n=0;
		empty = true;
		done = false;
	}
	
	//while no number
	//		wait.
	//when waiting over, get number
	//set empty to true and notify waiting methods
	public synchronized int get() {
		while (empty) {
			try { wait(); }
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Got: " + n);
		empty = true;
		notifyAll();
		return n;
	}
	
	//while number  there
	//		wait.
	//when waiting over, put number
	//set empty to false and notify waiting methods
	public synchronized void put(int n) {
		
		while (!empty) {
			try { wait(); }
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Put: " + n);
		empty = false;
		notifyAll();
		this.n = n;
	} 
	
	public void setDone() {
		done = true;
	}
	
	public boolean getDone() {
		return done;
	}
}
