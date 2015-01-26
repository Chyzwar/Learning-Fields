
public class Student
{
	private String id;        //unique id, e.g. 0099
    private Name name;
    private int year;        //year of study  e.g. 1,2,3 etc
    private String  [] prevQuals;   //previous qualifications

    private static final int NUM_MARKS = 3;
    private int [] marks;    //marks for the module - 5 assignments
    
    //qual array is provided as a parameter
    //marks array is initialised to a fixed size
    public Student (String id, Name n,  String [] pq, int y)
    {
    	this.id = id;
    	name = n;   
    	prevQuals = pq;
    	year = y;
    	marks = new int [NUM_MARKS];   // initially all 0
    }

    //a few getters and setters
    //the others are not required in this example
	public String getId() {  return id;  }
	public Name getName() {  return name;  }
	public int getYear() {  return year;  }

    //add a mark for a given assignment
    //assumes that assignment number is correct
	//demonstrates how users think numbers start at 1,
	// but a program starts arrays from 0
    public void addMark(int value, int ass ) {
    	int index = ass-1;
    	marks[index] = value;
    }
    
    
    //traverse list of qualifications, returning details as a String 
    //demonstrates traversing array using array length
    //and adding details of each array element to a String
    public String getQualDetails() {
    	String report = "" ;
    	for (int qualsIndex = 0; qualsIndex < prevQuals.length; qualsIndex++) {
    		report += prevQuals[qualsIndex] + "   ";
    	}
    	return report;
	}
    
	//returns the maximum mark
	//ALGORITHM
	//assumes first mark is the maximum
	//for each mark, 
	//  if it is greater than the maximum
    //     make it the maximum
	public int getMaxMark()  {
		int max = marks[0];
		for (int marksIndex = 1; marksIndex < marks.length; marksIndex++) {
			if (marks[marksIndex] > max) {
				max = marks[marksIndex];
			}
		}
		return max;
	}
	
	//finds the average mark
	//ALGORITHM
	//add up all the marks, then divide by number of marks
	//demonstrates forcing double division
	//assumes that all marks entered, or, if not, ok to use 0 for calculation
	public double getAveMark()  {
		int total = 0;
		for (int marksIndex = 0; marksIndex < marks.length; marksIndex++) {
			total += marks[marksIndex];
		}
		return (double) total/marks.length;
	}
 

	//returns true if the person has the specified qualification, false otherwise
	//can stop as soon as it is found,
	//but might get to end and not find it at all.
	//demonstrates searching and returning as soon as condition is met.
	public boolean hasQual(String searchQual) {
    	for (int qualsIndex = 0; qualsIndex < prevQuals.length; qualsIndex++) {	
    		String prevQual = prevQuals[qualsIndex];
    		if (prevQual.equals(searchQual) ) {
    			return true;
    		}
    	}	
    	return false;
	}
	
	//same as previous method  - different style
	public boolean hasQualification(String qual) {
		int qualsIndex = 0;
		boolean found = false;
		while (qualsIndex < prevQuals.length && !found) {
    		if (prevQuals[qualsIndex].equals(qual) ) {
    			found = true;
    		}
    		qualsIndex++;
    	}
    	return found;
	}		

    
}

