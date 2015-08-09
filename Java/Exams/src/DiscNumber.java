

public class DiscNumber { 

	int [] digits = {10,2,3,4,5,6,7,8,9,2,0,5,6,7,8};
	static int a = 10;
	public static void main(String[] args) 
	{
		
		
	gowno();
		
	}	
	
	public DiscNumber(int [] d) 
	{ 
		digits = d; 
		
	} 
	public static void gowno()
	{
		validate(a);
	}
	
	public boolean  validate(int num) 
	{ 
		for (int x = 0; x < 15; x++) 
		{ 
			if (digits[x] <0 || digits[x] >=num ) 
				return false; 
		} 
		int c = 0; 
		for (int x = 0; x < 15; x++) 
		{ 
			c += digits[x]; 
		} 
		int r = c%10; 
		boolean b = digits[15] == r; 
		return b; 
	} 
}
