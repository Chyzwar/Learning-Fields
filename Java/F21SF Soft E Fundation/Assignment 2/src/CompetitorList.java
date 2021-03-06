
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;


/**The CompetitorList class to read the input file 
 * and extract data to an ArrayList of different Competitors objects for producing a competitor report.
 * @author group work
 *
 */
public class CompetitorList
{
	ArrayList<Competitor> competitorList;


	/**Create empty ArrayList that can store Competitor objects
	 */
	public CompetitorList()
	{
		competitorList = new ArrayList<Competitor>();
	}


	/** Add Competitor at the end of competitorList ArrayList
	 */
	public void add(Competitor c)
	{
		competitorList.add(c);

	}


	/**Read all input files and process them
	 * 
	 */
	public void readAllFiles()
	{
		readFromFileBowler("inputBowler.txt");
		readFileGolfer("inputGolfer.txt");
		readFileSoapbox("inputSoapbox.csv");
	}


	/**return short and long details on selected competitor number from all competitors
	 * 
	 * @return singleCompetitor string
	 */
	public String singleCompetitorInfo()
	{
		int competitorNumber= CompetitorList.getUserInput();
		String singleCompetitor ="\r\n";
		for (Competitor s : competitorList)
		{
			int w= s.getId();
			if (w == competitorNumber)
			{
				singleCompetitor+=s.getShortDetails();
				singleCompetitor+="\r\n";
				singleCompetitor+="\r\n"+s.getFullDetails();
				singleCompetitor+="\r\n";
				return singleCompetitor ;
			}

		}

		return null;
	}


	/**Method sort competitorList by id
	 * 
	 * @return getAllCompetitors() sorted by list
	 */
	public String listByID()
	{
		Collections.sort(competitorList);
		return getAllCompetitors();
	}


	/**Method sort competitorList by Overall Score
	 * 
	 * @return getAllCompetitors() sorted by Overall Score
	 */
	public String listByOverall()
	{
		Collections.sort(competitorList, new CompetitorOverallComparator());
		return getAllCompetitors();
	}

	
	/**Search competitor list for specific id
	 * 
	 * @param competitor object with id
	 * @return null
	 */
	public Competitor searchById(int id)
	{
		for(Competitor g:competitorList)
		{
			if(g.getId()==id)
			{
				return g;
			}
		}
		return null;
	}

	
	/**method return Competitor list sorted by name
	 * 
	 * @return sorted by list
	 */
	public String listByName()
	{
		Collections.sort(competitorList, new CompetitorNameComparator());
		return getAllCompetitors();
	}

	
	/**Clear competitorList arrayList
	 * 
	 */
	public void clear()
	{
		competitorList.clear();
	}

	
	/**Find winner by looking for competitor with highest OverallScore
	 * to different loops not very beautiful
	 * @return winner report consisting of getFullDetails()
	 */
	public String getWinner()
	{
		String winner="\r\n" +"Competitor with highest overall score is: "+"\r\n";
		double maxOverall = 0;
		for (Competitor s : competitorList) 
		{
			double overall = s.getOverallScore();
			if (overall> maxOverall) 
			{
				maxOverall= overall;
			}
		}
		for (Competitor s : competitorList ) 
		{
			double overall = s.getOverallScore();
			if(overall >= maxOverall)
			{
				winner +=s.getFullDetails();
			}
		}
		return winner;
	}
	
	
	/** Returns details of all Competitors stored in ArrayList
	 * 
	 * @return report
	 */
	public String getAllCompetitors()
	{

		String report = " Full report of competitors"+"\r\n";
		report +="Id    " + "Name of Competitor      " +"Overall"+"\r\n";
		for (Competitor c  : competitorList)
		{

			report += String.format("%-6s", c.getId());
			report += String.format("%-25s", c.getName());


			report += String.format("%1$,.1f", c.getOverallScore())+" ";

			report += "\r\n";

		}
		return report;

	}


	/** reads file with given name, extracting competitor data, creating competitor objects
	 * and adding them to the list of competitors
	 * Blank lines are skipped
	 * Validation for integer, missing items
	 * @param filename the name of the input file
	 */
	public void readFileSoapbox(String filename) 
	{
		try
		{
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine())
			{
				//read first line and process it
				String inputLine = scanner.nextLine(); 
				if (inputLine.length() != 0) 
				{//ignored if blank line
					processLineSoapbox(inputLine);
				}
			}
			scanner.close();

		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf)
		{
			System.out.println( filename + " not found ");
			System.exit(0);
		}
	}


	/**
	 * Processes line, extracts data, creates competitor object
	 * and adds to list
	 * non-numeric marks and missing items
	 * Will still crash if name entered without a space
	 * @param line the line to be processed
	 */
	private void processLineSoapbox(String line)
	{

		// create and initiate local variables for some loops
		int [] marks;
		int maxNumberOfMarks = Soapbox.getMaxNumberOfScores();
		marks = new int [maxNumberOfMarks];
		int markN = 0;
		int index = 0;
		String mark = " ";


		try 
		{
			String parts [] = line.split(",");
			String id = parts[0];
			id = id.trim();
			int idNumber = Integer.parseInt(id);
			Name name = new Name(parts[1]);

			//marks position should be 2,3,4,5,6 this loop is to put them into array
			for (int marksLength  = 2 ; marksLength <= parts.length-3; marksLength++ )
			{
				mark = parts[marksLength];
				mark = mark.trim();
				markN = Integer.parseInt(mark);
				index = marksLength -2;
				marks[index] = markN;
			}
			String pub = parts[parts.length-2];
			pub = pub.trim();
			int pubNumber = Integer.parseInt(pub);

			String lvls = parts[parts.length-1];
			lvls=lvls.trim();
			int lvl = Integer.parseInt(lvls);

			//create competitor team  and add to the list of objects in arraylist
			Soapbox c = new Soapbox(idNumber,name, marks,pubNumber,lvl);
			this.add(c);
		}

		//for these two formatting errors, ignore lines in error and try and carry on
		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) 
		{
			String error = "Number conversion error in '" + line + "'  - " 
					+ nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air)
		{
			String error = "Not enough items in  : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}

	}



	/**This method is taken from the example code in the lecture note about File I/O and exceptions to 
	 * read file with given name, extract golfer data, create golfer objects
	 * and adding them to the list of golfers.
	 * Blank lines are skipped.
	 * Validation for range of scores, full name without a space, missing items, numeric inputs (competitor number,scores,age).
	 * @param filename the name of the input file
	 */
	public void readFileGolfer(String filename) {
		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) 
			{
				//read first line and process it
				String inputLine = scanner.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					processLineGolfer(inputLine);
				}

			}
			scanner.close();
		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf)
		{
			System.out.println( filename + " not found ");
			System.exit(0);
		}
	}


	/**Validates level of golfer. It should only be Rookie, Amateur, and Pro.
	 * @param level the level of a golfer to be checked
	 * @return true if it matches with one of levels, false otherwise.
	 */
	public boolean isLevelValid(String level){
		if (level.equals("rookie"))
		{
			return true;
		}
		else if (level.equals("amateur"))
		{
			return true;
		}
		else if (level.equals("pro"))
		{
			return true;
		}
		else
		{
			//Print out an invalid level
			System.out.println("Invalid level : "+ level);
			return false;
		}


	}


	/**This method was developed based on the example code in the lecture note about File I/O and exceptions to
	 * process line, extract data, create Golfer object
	 * and add to list.
	 * Checks for range of scores, full name without a space, missing items, numeric inputs (competitor number,scores,age).
	 * Lines with errors are ignored.
	 * @param line the line to be processed
	 */
	private void processLineGolfer(String line) {
		try {
			String parts [] = line.split(",");

			/* Process each line to create Golfer object e.g. 100, Keith John Talbot, Amateur, 42, USA, 5,4,5,4,3
			 * Use trim() to remove any spaces 
			 * */
			boolean add = true; //will use this to check whether input error and ensure that we can add an item to the list
			int number = Integer.parseInt(parts[0].trim());
			Name name = new Name(parts[1].trim());						
			String level = parts[2].trim().toLowerCase();//make it all lower cases for validation.
			add = isLevelValid(level); //Check whether the level value is valid or not
			int age = Integer.parseInt(parts[3].trim());
			String country = parts[4].trim();

			//the scores are at the end of the line
			int scoreLength = parts.length - 5;
			String s[] = new String[scoreLength];
			//copy to a new array
			System.arraycopy(parts, 5, s, 0, scoreLength);
			int score[] = new int[scoreLength];

			for(int index = 0; index < s.length; index++){
				//convert a String to an integer
				int x = Integer.parseInt(s[index].trim());

				//check whether scores are in the range 0-5 or not
				if((x<0)||(x>5))
				{
					System.out.println("A score is out of range 0-5 in line :'"+ line +"'" );
					add = false;//a line contains an error
				}
				else
				{
					//add x in the array scores
					score[index] = x;
				}
			}		
			//if there is no error (add=true) then create Golfer object and add to the list 
			if(add){
				Golfer g = new Golfer(number, name, score, level, age, country);
				this.add(g);}
		}

		//for these formatting errors, ignore lines in error and try and carry on
		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "'  - " 
					+ nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
		//This also catches if there is no space between a full name
		catch
		(StringIndexOutOfBoundsException sir) {
			String error = "No space between a fullname in line : '" + line
					+ " '" + sir.getMessage();
			System.out.println(error);

		}
	}


	/**
	 * Reading through the competitor Bowlers list file. For each read line in the file, the line is sent as a parameter to the addCompetitorsToList method
	 * @return line red from file
	 */
	public String readFromFileBowler(String filename)
	{
		String line = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));

			try {
				while ((line = reader.readLine()) != null) {
					processLineBowler(line);
				}
				reader.close();
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found\n" + e.getMessage());
		}
		return line;
	}


	/**
	 * Adding competitors to the competitor list based on a line read from a specific file, given as the parameter.
	 * It splits the line up to extract the right information, passing that information to the Bowler constructor to create a Bowler object
	 * based on the given information, and then adding the object to the list of competitors
	 * @param line line read from file
	 */
	public void processLineBowler(String line)
	{
		/*
		 * Trying to split the line that's being passed through. If an error occurs, the line is ignored
		 */
		try{
			/*
			 * Splitting up the line on each comma, and creating an array of splitted Strings
			 */
			String[] split = line.split(",");

			/*
			 * As the line that has been passed through is in a certain format, the strings in each index of the splitted array is known.
			 * Below, each String from the splitted array is divided up nicely, and is being parsed correctly to be sent to the Bowler class constructor
			 */
			int id = Integer.parseInt(split[0]);
			Name competitorName = new Name(split[1]);
			int[] listOfScores = { Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]) };
			String competitorLevel = split[7];
			String competitorCountry = split[8];


			/*
			 * Creating a Bowler object based on the information that got splitted up in the line that came through from the line read from a specific file
			 */

			Bowler b = new Bowler(id, competitorName, listOfScores, competitorLevel, competitorCountry);

			/*
			 * Adding the created Bowler object to the list of bowlers/competitors
			 */
			this.add(b);

		} catch (NumberFormatException nfe){
			System.out.println("ERROR: " + nfe.getMessage() + "\nIgnoring line: " + line);
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("ERROR: " + aioobe + "\nIgnoring line: " + line);
		}
	}

	
	/**
	 * Opens a dialog box asking to input a competitor number, and returns the short details for the bowler with that competitor number in the list of bowlers
	 * @return short details from a specific bowler
	 */
	public static int getUserInput()
	{	
		int usrInput = 0;
		String input = JOptionPane.showInputDialog(null, "Please enter a competitor number:");

		try{
			usrInput = Integer.parseInt(input);
		}
		catch(NumberFormatException n)
		{
			System.out.println("You did not enter a valid number.");
		}

		return usrInput;
	}

	
	/**
	 * writes supplied text to file
	 * @param filename the name of the file to be written to
	 * @param report the text to be written to the file
	 */
	public  void writeToFile(String filename, String report) 
	{

		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			fw.write("The report\n");
			fw.write(getAllCompetitors());
			fw.write(getWinner());
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

}
