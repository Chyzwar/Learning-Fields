import java.util.Arrays;


/**The Soapbox class is a subclass of competitor which contains infomation about Soapbox teams.
 * Class have also methods specific only for Soapbox
 * @author Marcin Kopacz
 *
 */
public class Soapbox extends Competitor

{

	private int pub;
	private int lvl;
	private static int numOfScores =5;

	
	/**
	 * This constructor creates a Soapbox object based on the following parameters,
	 * @param id is competitor number for competitor
	 * @param competitorName store name of competitor
	 * @param listOfScores the list of scores for the competitor
	 * @param pub is audience response
	 * @param lvl of art of Soapbox team
	 */
	public Soapbox (int id,Name competitorName,int [] listOfScores,int pub,int lvl)
	{
		super(id,competitorName,listOfScores);
		this.pub = pub;
		this.lvl= lvl;

		
	}


	/**return audience response
	 * Convert integer to string
	 * @return audience response as string
	 */
	public String getPub()
	{
		String audience ="";
		audience = Integer.toString(pub);
		return audience;
	}

	
	/**Return Level of Art
	 * @return lvl- art level of competitor
	 */ 
	public int getLvl()
	{
		return lvl;
	}

	
	/**Return maximum number of Scores,
	 * This is used by procesLineSoapbox() 
	 * @return numOfScores
	 */
	public static int getMaxNumberOfScores()
	{
		return numOfScores;	
	}

	
	/**Calculate Overall as average of marks then multiplies by audience respond 
	 * @return double value
	 */
	public double getOverallScore()
	{
		int total = 0;
		for (int scoreIndex = 0;scoreIndex <numOfScores; scoreIndex++)	
		{
			total += listOfScores[scoreIndex];
		}
		return (double) total/(numOfScores) * pub/100;
	}

	
	/**Method to display full details of Soapbox team
	 *@return fullDeatils string containing details
	 */
	public String getFullDetails()
	{
		String fullDetails = "Full details for " + getId() + ":\r\nCompetitor #" + getId() + " is " + competitorName.getFullName() + ".\r\n" +
				competitorName.getFullName() + " have level of art: " + String.valueOf(getLvl()) + " and responce from audience " + String.valueOf(getPub())  + "% , and received these scores: " + Arrays.toString(getListOfScores()).replace("[", "").replace("]", "") + ".\n" +
				"This gives an overall score of " + getOverallScore();
		return fullDetails;
	}


}




