
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**CompetitorList Class storing competitors objects in arraylist
 * 
 * @author Marcin Kopacz
 *
 */

public class CompetitorList
{
	
	int number =CompetitionManager.readIntValue();
	private ArrayList<Competitor> competitorList;
	
	
	/**Create ArrayList storing objects of type Competitor
	 * @param number the Id of competitor with we want to have more details from user selction
	 */
	public CompetitorList()
	{
	competitorList = new ArrayList<Competitor>();
	}
	
	/** add Competitor and the end of ArrayList
	 */
	public void add(Competitor c)
	{
		competitorList.add(c);
	}
	
	/** returns details of all Competitors stored in ArrayList
	 * 
	 * @return report
	 */
	public String getAllCompetitors()
	{
		String space = "    ";
		String report = " Full raport of competitors"+"\r\n";
		report +="Id    " + "Team name                " + "Marks from Judges"+ "       Audience Responce %" +"  Overall"+ "     Level"+"\r\n";
		for (Competitor c  : competitorList)
		{
			
			report += String.format("%-6s", c.getId());
			report += String.format("%-25s", c.getName().getFullName());
			report += String.format("%-35s",c.getMarks());
			report += String.format("%-6s",c.getPub());
			report += space;
			report += String.format("%1$,.1f", c.getOverall())+" ";
			report += space+space +String.format("%-10s", String.valueOf(c.getLvl()));
			report += "\r\n";
			
		}
		return report;

	}
	
	/** return details of single competitor seleced by variable
	 * 
	 * @return single
	 */
	public String getSingleCompetitor()
	{
	
		 
		String single ="\r\n" + "Full details for "+ number + "\r\n" + "Team name is ";
		
			Competitor p = competitorList.get(number);
			String name = p.getName().getFullName();
			single +=String.format("%-8s", name)+ ".";
		    single +="\r\n" + "Judges gives them following marks: ";
		    single += String.format("%-35s",p.getMarks());
		    single += "\r\n" + "Audience responce was: ";
		    single += String.format("%-2s",p.getPub());
		    single += "%.";
		    single +="\r\n" + "This would give them Overall Score: " + String.format("%1$,.1f", p.getOverall());
		    single +="  Level artism: "+String.valueOf(p.getLvl())+"\r\n";
	return single;
	}

	/** Search and return a winner by looking for competitor with highest Overall Score
	 * 
	 * @return winner
	 */
	public String getWinner()
	{
		String winner="\r\n" +"Winner of Soapbox competition is: "+"\r\n";
		double maxOverall = 0;
		for (Competitor s : competitorList) 
		{
			double overall = s.getOverall();
			if (overall> maxOverall) 
			{
				maxOverall= overall;
			}
		}
		for (Competitor s : competitorList ) 
		{
			double overall = s.getOverall();
			if(overall >= maxOverall)
			{
			winner +="Team name is ";
			String name = s.getName().getFullName();
			winner +=String.format("%-8s", name)+ ".";
		    winner +="\r\n" + "Judges gives them following marks: ";
		    winner += String.format("%-35s",s.getMarks());
		    winner += "\r\n" + "Audience responce was: ";
		    winner += String.format("%-2s",s.getPub());
		    winner += "%.";
		    winner +="\r\n" + "This would give them Overall Score: " + String.format("%1$,.1f", s.getOverall());
		    winner +="  Level of art: "+ String.valueOf(s.getLvl())+"\r\n";
			}
		}
	return winner;
	}
	
	/** return short details of selected competitor
	 * 
	 * @return shorty
	 */
	public String getShortDetails()
	{
	String shorty ="\r\n"+"Short details for " + number;
	shorty +="\r\n" ; 
	Competitor y = competitorList.get(number);
	String name = y.getName().getShortName();
	shorty+="Id "+number +" ";
	shorty +="("+String.format(name)+") "+ "has overall score "+String.format("%1$,.1f", y.getOverall()) ;
	shorty +="\r\n";
	return shorty;
	}
	
	/** Calculate number of competitors in Comptetition
	 * 
	 * @return county
	 */
	public String getCountOfTeams() 
		{
		String county="\r\n"+"Total number of teams in this year competition is :";	
	    int count = 0;
	    	for (@SuppressWarnings("unused") Competitor s: competitorList) 
	    	{
	    	count++;
	    	}
	    	county+=Integer.toString(count)+"\r\n";
	    	return county;
	    }

	/**Look for competitor with maximum Audience Response
	 * 
	 * @return max
	 */
	public String getMaxPub()
	{
	String max="Audience have highest respond of:  ";
	int maxe =0;
	
	for (Competitor s : competitorList) 
	{
		String maxi = s.getPub();
		int maxiInt = Integer.parseInt(maxi);
		if (maxe< maxiInt) 
		{
			maxe=maxiInt;
			@SuppressWarnings("unused")
			String Maxe = Integer.toString(maxe);
		}
		
	}
	max+=maxe+"\r\n";
	return max;
	}
	
	/**Calculate average Level of Art
	 * 
	 * @return average
	 */
	public String getAveregeLvl()
	{
		String averege = "\r\n" + "Averege Level of Art is: ";
		int ave = 0;
		int count =0;
		double aved=0;
		for (Competitor s : competitorList) 
			{
			ave += s.getLvl();
			count++;
			}
		aved+=ave/count;
	averege+= String.format("%1$,.1f", aved);
	return averege;
	}
	
	/** Make frequency report on Level of Art
	 * Method have misleading name.
	 * @return reportArt
	 */
	public String getMaxPubFrequencyReport() 
	{
			
	int maxArt = 6;
	int [] freqYears = new int [maxArt];
	
			for (Competitor s : competitorList) 
			{
			int y = s.getLvl();
			freqYears[y-1]++;
			}
			
			String reportArt = "How offen given Level of Art was used \r\n";
			for (int i = 0; i < freqYears.length; i++)
			{
				reportArt += " Level of Art  " + (i+1) + " : " + freqYears[i] + "\r\n";
			}
	return reportArt;
	}
	
	/** reads file with given name, extracting competitor data, creating competitor objects
	 * and adding them to the list of competitors
	 * Blank lines are skipped
	 * Validation for integer, missing items
	 * @param filename the name of the input file
	 */
	public void readFile(String filename) 
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
				processLine(inputLine);
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
	private void processLine(String line)
	{

	// create and initiate local variables for some loops
	int [] marks;
	int maxNumberOfMarks = Competitor.getMaxNumberOfMarks();
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
		Competitor c = new Competitor(idNumber,name, marks,pubNumber,lvl);
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
	// Do a catch or write if before ?? 
	//	catch (if index > maxNumberOfMarks)
	//	{
		
	//	}
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
			    fw.write(getSingleCompetitor());
			    fw.write(getShortDetails());
			    fw.write(getWinner());
			    fw.write(getCountOfTeams());
			    fw.write(getMaxPub());
			    fw.write(getMaxPubFrequencyReport());
			    fw.write(getAveregeLvl());
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
