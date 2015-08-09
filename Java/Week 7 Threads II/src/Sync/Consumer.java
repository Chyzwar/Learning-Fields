
public class Consumer implements Runnable {
  private SharedObject so;

  public Consumer  (SharedObject so) {this.so = so;}

  //version 1, 8 times, sleeps then gets the number
  //doesn't do anything with it (a very small example)
  public void run() {
   for (int i = 0; i < 8; i++) {
		//sleep first
      try { Thread.sleep(100); }
      catch (InterruptedException e) {}
      //get the number
      so.get();  
   }
 }
}