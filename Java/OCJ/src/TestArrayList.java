import java.util.ArrayList;
import java.lang.Float;


public class TestArrayList 
{
	private ArrayList<Float> fishLengthList;

	public TestArrayList()
	{
	fishLengthList = new ArrayList<Float>();
	run();
	}
	
	
	public void run()
	{
		populate();
		searchForLenght();
		
	}
	public void  add(Float F)
	{
		fishLengthList.add(F);
		
		
	}
	
	private void populate()
	{
		float fish1;
		fish1 = new Float(10.0);
		add(fish1);
		
		float fish2;
		fish2 = new Float(15.5);
		add(fish2);
		
		float fish3;
		fish3 = new Float(18.0);
		add(fish3);
		
		float fish4;
		fish4 = new Float(29.5);
		add(fish4);
		
		float fish5;
		fish5 = new Float(45.0);
		add(fish5);
	}
	
	public float getLenght(Float F)
	{
		return F.floatValue();
	}
	
	private void searchForLenght()
	{
		int i = 0;
		for(Float T : fishLengthList)
		{
			
			if(getLenght(T)>=28)
			;
				
			i++;
		}
		System.out.println("This many fishes have appropate lenght "+ i );
	}
	
}

