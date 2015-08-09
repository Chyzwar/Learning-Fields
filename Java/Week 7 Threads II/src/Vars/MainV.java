
public class MainV {

		public static void main(String args[]) {
			SharedObject so  = new SharedObject();
			Thread producerThread = new Thread (new Producer(so));
			producerThread.start();
			Thread consumerThread = new Thread (new Consumer(so));
			consumerThread.start();

		}

}
