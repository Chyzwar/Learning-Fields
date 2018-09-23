import java.util.ArrayList;
//demonstrates using an ArrayList
public class StudentList {
	//holds a list of Student objects
	private ArrayList<Student> studentList;
	
	//create an empty arraylist
	public StudentList() {
		studentList = new ArrayList<Student> ();
	}
	
	/**
	 * Adds student to the list, if there is not already one there 
	 * with the same ID
	 * @param s the Student to be added
	 * @return true if student was added to the list, false if already there
	 */
	public boolean addOneStudent(Student s) {
		//gets id of student to be added
		String id = s.getId();
		//see if student with this id is already in the list
		Student inList = this.findById(id);
		//add the student if they are not in the list, and return true
		if (inList == null) {
			studentList.add(s);
			return true;
		}
		//return false if not in the list
		return false;
	}
	
	//populate the array list
	public void populate() {
		String [] quals1 = {"MIC", "ARA" };
		Student s1 = new Student ("0011",new Name("Helen Scott"),
				quals1, 1);
		//an example of checking to see if added successfully
		//(expect this one to be added)
		//(not checked for many other students, assuming correct)
		boolean ok = this.addOneStudent(s1);
		if (!ok) { 
			System.out.println("Duplicate entry " + s1.getId()); 
		}

		String [] quals2 = {"MIC", "FAC1" };
		Student s2 = new Student ("1234",new Name("James Jackson"),
				quals2, 1);
		this.addOneStudent(s2);

		String [] quals3 = {"ARA", "FAC1", "FAC2" };
		Student s3 = new Student ("0267", new Name("Tim Moore"), 
				quals3, 2);
		this.addOneStudent(s3);

		String [] quals4 = {"ARA" };
		Student s4 = new Student ("1356",new Name("Tom Smith"), 
				quals4, 2);
		this.addOneStudent(s4);

		String [] quals5 = {"FAC1", "JBB"};
		Student s5 = new Student ("9876",new Name("Jo Black"), 
				quals5, 2);
		this.addOneStudent(s5);

		String [] quals6 = {"FAC1", "ARA" , "JBB", "FAC2"};	
		Student s6 = new Student ("3434",new Name("Mary Brown"), 
				quals6, 3);
		this.addOneStudent(s6);
		//another example of checking to see if added successfully
		//(this one was not added)
		ok = this.addOneStudent(s6);
		if (!ok) { 
			System.out.println("Duplicate entry " + s6.getId()); 
		}
	}
	
	//returns a report with one line per person
	//demonstrates traversing the array,
	//getting one element at a time
	public String getTableOfStudents()
	{
		String report = "ID    NAME          YEAR   QUALS\n";
		for (Student s  : studentList){
			report += String.format("%-6s", s.getId());
			report += String.format("%-15s", s.getName().getFullName() );
			report += String.format("%-6d", s.getYear());
			report += s.getQualDetails();
			report += "\n";
		}
		return report;
	}
	
	//returns the number of elements in the list
	public int getSize() {
		return studentList.size();
	}
	
	//returns the Staff object at specified index position
	public Student getAtIndex(int index) {
		return studentList.get(index);
	}
	
	//returns the Staff object with a specified id
	//demonstrates searching through the array
	//and stopping by returning when a match is found
    public Student findById(String id)
    {
    	for (Student s : studentList)
    	{
    		if (s.getId().equals(id))
    		{
    			return s;
    		}
    	}
    	return null;
    }
    
    //counts the number of people in a specified year
    //demonstrates making a count with arraylists
    public int getCountOfPeopleAtYear(int year) {
    	int count = 0;  //initialise count to 0
    	for (Student s:studentList) {
    		if (s.getYear()==year) {
    			count++;
    		}
    	}
    	return count;
    }
	

	
	//works out how many people in each year,
	//then creates and returns a report
    //
    //demonstrates calculating a frequency report
    //i.e. how often each year occurs
    //it uses the value of the year as an index
	public String getYearsFrequencyReport() {
		//work out max year
		int maxYear = getMaxYear();
		//work out how many people at each year
		int [] freqYears = new int [maxYear];
		for (Student s : studentList) {
			int y = s.getYear();
			freqYears[y-1]++;
		}
		//create a report
		String report = "NUMBER OF STUDENTS IN EACH YEAR\n";
		for (int i = 0; i < freqYears.length; i++) {
			report += "Year " + (i+1) + " : " + freqYears[i] + "\n";
		}
		return report;
	}
	
	//calculates the maximum year that anyone is in
	//demonstrates finding a max with array lists
	public int getMaxYear() {
		int maxYear = 0;
		for (Student s : studentList) {
			int yr = s.getYear();
			if (yr> maxYear) {
				maxYear= yr;
			}
		}	
		return maxYear;
	}
	

}
