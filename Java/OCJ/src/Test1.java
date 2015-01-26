

public class Test1 {
	
	public static void main(String[] args) {	
		
		new TestArrayList();
		
			
		outerLoop:
			for (int j = 0; ; j += 100) {
				for (int i = 0; i < 5; i++) {
					//Je¿eli reszta z dzielenia przez 2 jest rowna jeden to dana iteracja jest odrzucana
					if ((i + j) % 2 == 1)
						continue;

						if (j > 100)
							break outerLoop; // przerywa wykonanie tak¿e pêtli zewnêtrznej

					System.out.println(i + j + " jest liczb¹ parzyst¹");
				}
			}
	}
}


