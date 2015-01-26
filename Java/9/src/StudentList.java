import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//demonstrates using an ArrayList
public class StudentList {

	private ArrayList<Student> studentList;
	
	//create an empty arraylist
	public StudentList() {
		studentList = new ArrayList<Student> ();
	}
	
	public void add(Student s) {
		studentList.add(s);
	}
	
	//returns a report with one line per person
	//demonstrates traversing the array,
	//getting one element at a time
	public String getAllStudents()
	{
		String report = "";
		for (Student s  : studentList){
			report += String.format("%-6s", s.getId());
			report += String.format("%-15s", s.getName().getFullName() );
			report += String.format("%-4d", s.getYear());
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
	
	/**
	 * writes supplied text to file
	 * @param filename the name of the file to be written to
	 * @param report the text to be written to the file
	 */
	public  void writeToFile(String filename, String report) {
	
		 FileWriter fw;
		 try {
		    fw = new FileWriter(filename);
		    fw.write("The report\n");
		    fw.write(report);
		 	fw.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filename + " not found ");
			 System.exit(0);
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}
	
	/** reads file with given name, extracting student data, creating student objects
	 * and adding them to the list of students
	 * Blank lines are skipped
	 * Validation for integer year, missing items
	 * @param filename the name of the input file
	 */
	public void readFile(String filename) {
		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				//read first line and process it
				String inputLine = scanner.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					processLine(inputLine);
				}

			}
		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf){
			 System.out.println( filename + " not found ");
			 System.exit(0);
		 }
	}
	
	/**
	 * Processes line, extracts data, creates Student object
	 * and adds to list
	 * Checks for non-numeric year and missing items
	 * Will still crash if name entered without a space
	 * @param line the line to be processed
	 */
	private void processLine(String line) {
		try {
			String parts [] = line.split(",");
			Name name = new Name(parts[1]);
			String id = parts[0];
			String yearNum = parts[2];
			yearNum = yearNum.trim();  //remove any spaces
			int year = Integer.parseInt(yearNum);
			
			//the qualifications are at the end of the line
			int qualLength = parts.length - 3;
			String quals[] = new String[qualLength];
			System.arraycopy(parts, 3, quals, 0, qualLength);
			
			//create Student object and add to the list
			Student s = new Student(id,name, quals, year);
			this.add(s);
		}

		//for these two formatting errors, ignore lines in error and try and carry on
		
		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "'  - " 
			                  + nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in  : '" + line
			                        + "' index position : " + air.getMessage();
			System.out.println(error);
		}

	}
}
