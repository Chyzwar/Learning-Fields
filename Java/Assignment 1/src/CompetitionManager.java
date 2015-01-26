import java.util.InputMismatchException;
import java.util.Scanner;


public class CompetitionManager
{

private CompetitorList allCompetitors;
	
	public CompetitionManager()
	{
	allCompetitors = new CompetitorList();
	
	}
	
	public void run()
	{
	
		 
	allCompetitors.readFile("InputData.csv");
	String report = allCompetitors.getAllCompetitors();
	String single = allCompetitors.getSingleCompetitor(); 
	String shorty = allCompetitors.getShortDetails();
	String winner = allCompetitors.getWinner();
	String county = allCompetitors.getCountOfTeams();
	String maxiPub = allCompetitors.getMaxPub();
	String reportArt = allCompetitors.getMaxPubFrequencyReport();
	String averegeLvl =allCompetitors.getAveregeLvl();
	allCompetitors.writeToFile("OutputData.txt", report + single + shorty+winner+county+maxiPub+reportArt+averegeLvl);
	//allCompetitors.writeToFile("OutputData.txt", single);
	}
	
	//let user select number of competitor
	public static int readIntValue() throws InputMismatchException
	{
	    @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);  
	    int integer;
	    System.out.println("Enter an integer value: ");
	    integer = in.nextInt();
	   
	    return integer;
	   
	}
	
	
	
	
}
