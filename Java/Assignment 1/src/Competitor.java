
public class Competitor 

{
	//Competitor unique id 0-100
	private int id;
	//team name
    private Name name;
    //Array storing marks given by 5 judges
    private static final int NUM_MARKS = 5;
    private int[] marks ;
    //Measurement of audience reaction 1-100
    private int pub;
    private int lvl;
    //constructor for competitor object
    public Competitor (int id, Name n, int [] ma,int pub,int lvl)
    {
    	this.id = id;
    	name = n;    
    	marks = ma;
    	this.pub = pub;
    	this.lvl= lvl;
    }
   
    //method to get id
	public int getId() 
	{  
	return id; 
	}
	
	//method to get team name
	public Name getName() 
	{
	return name; 
	}
	
	//method returning marks as strings
	public String getMarks()
	{	
		String report =" ";
		for (int markIndex = 0; markIndex < NUM_MARKS; markIndex++)
		{
		report += Integer.toString(marks[markIndex]) + " ";
		}
	return report;
	
	}
	
	//method to return audience response
	public String getPub()
	{
	String audience ="";
	audience = Integer.toString(pub);
	return audience;
	}
	
	//return Level of Art
	public int getLvl()
	{
	return lvl;
	}
	//method to return maximum number of marks,
    public static int getMaxNumberOfMarks()
    {
    return NUM_MARKS;	
    }
	
    //Overall as average of marks with audience respond as multiplication
	public double getOverall()
	{
	int total = 0;
	for (int markIndex = 0; markIndex <NUM_MARKS; markIndex++)	
		{
		total += marks[markIndex];
		}
	return (double) total/(NUM_MARKS + 1) * pub/100;
	}
}
