/**
 * This class creates a list of competitors, and uses  the CompetitorList class to write a full report to a file
 * @author Group
 *
 */
public class CompetitionManager
{
	private CompetitorList allCompetitors;
	
	
	/**Create new instance of CompetitorList object named allCompetitors
	 * CompetitorList store information on competitors in array list
	 */
	public CompetitionManager()
	{
	this.allCompetitors = new CompetitorList();
	
	}
	
	
	/**Create s StartGUI and populate competitorList using data from files
	 * 
	*/
	public void run()
	{
	
		allCompetitors.readAllFiles();
	
		StartGUI s = new StartGUI(allCompetitors);
	    s.setVisible(true);
	}
	
}
