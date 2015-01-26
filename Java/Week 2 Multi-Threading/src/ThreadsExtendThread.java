
public class ThreadsExtendThread extends Thread {
	int id;

	public ThreadsExtendThread(int i) { id = i; }

	public void run()
	{ 
		int i = 0;
		while (true)
		{
			System.out.print(id); 
			i++;
			if (i>100)
			{
				continue;
			}	
		}
		
	}
		public static void main(String args[]) {
			new ThreadsExtendThread(1).start();
			new ThreadsExtendThread(2).start();
		}
	}