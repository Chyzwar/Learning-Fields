
public class Producer implements Runnable {
	private SharedObject so;
	
	public Producer (SharedObject so) {  this.so = so;  }
	
	public void run() {
		int i = 0;
		while ( i < 8){
			//sleep
			try {  Thread.sleep(80);  }
			catch (InterruptedException e) { }
			//try to put number
			boolean success = so.put(i);
			//increase counter if successful
			if (success) {  i++;  }
			//record that there will be no more
			//otherwise consumer will never stop looking
			if (i == 8) {  so.setDone();  }
		}
	}
}
