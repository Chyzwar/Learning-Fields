
public class SharedObject {
	private int n;
	private boolean empty;
	private boolean done;
	
	public SharedObject () {
		n=0;
		empty = true;
		done = false;
	}
	
	synchronized int get() {
		if (empty) {
			return -1;
		}
		System.out.println("Got: " + n);
		empty = true;
		return n;
	}
	synchronized boolean put(int n) {
		
		if (!empty) {
			return false;
		}
		System.out.println("Put: " + n);
		empty = false;
		this.n = n;
		return true;
	} 
	
	public void setDone() {
		done = true;
	}
	
	public boolean getDone() {
		return done;
	}
}
