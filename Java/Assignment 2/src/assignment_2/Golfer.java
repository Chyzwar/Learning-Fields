package assignment_2;
import java.util.Arrays;


/**The Golfer class is a subclass of competitor which contains about infomation about Golfer.
 * 
 * @author Kamontorn Khamrun H00144659
 *
 */
public class Golfer extends Competitor
{
	private String competitorLevel;
	private int competitorAge;
	private String competitorCountry;
	
	
	
	
	/**Constructor
	 * @param cNumber competitor number
	 * @param cName competitor number
	 * @param cScores array of scores
	 * @param cLevel competitor level
	 * @param cAge competitor age
	 * @param cCountry competitor country
	 */
	public Golfer(int cNumber, Name cName, int[] cScores, String cLevel, int cAge, String cCountry){
		super(cNumber,cName,cScores);
	
		competitorLevel = cLevel;
		competitorAge = cAge;
		competitorCountry = cCountry;
		
		
	}
	
	
	/**This method is to return a competitor level.
	 * @return competitor level
	 */
	public String getCompetitorLevel()
	{
		//returns the capitalized in the first char 
		return Character.toUpperCase(competitorLevel.charAt(0)) + competitorLevel.substring(1);
	}

	
	/**This method is to return a competitor age.
	 * @return competitor age
	 */
	public int getCompetitorAge()
	{
		return competitorAge;
	}

	/**This method is to return a competitor country.
	 * @return competitor country
	 */
	public String getCompetitorCountry()
	{
	return competitorCountry;
	}
	
	
	/**This method is to return all scores in String format e.g. 3 4 1 3 4
	 * @return score
	 */
	public String getAllScore(){
		//create a format of array score to display e.g. 3 4 1 3 4
				String score ="";
				for(int index=0; index < listOfScores.length ; index++)
				{
					if(index != listOfScores.length-1)
					{
						score += listOfScores[index]+" ";
					}
					else
					{
						score += listOfScores[index];
					}
				}
				return score;
				
	}
	
	
	
	/**This method is to return an array of competitor scores
	 * @return an array of competitor scores
	 */
	public int[] getScore() {
		return listOfScores;
	}
	
	
	/** This method overrides from a superclass to calculate and return
	 *  an overall score by average of top n scores where n is the level number Rookie (n=1), Amateur (n=2), Pro (n=3)
	 * @return overallscore
	 */
	
	public double getOverallScore() {
		
		double overallScore = 0;
		int level =0;
		int total = 0;
		int[] sortedScores =  new int[5];
		
		
		for(int index=0; index < listOfScores.length ; index++)
		{
			//Copy listOfScores to sortedScores
			sortedScores[index] = listOfScores[index];
		}
		
		Arrays.sort(sortedScores); //sort all scores into ascending order
		if (competitorLevel.equals("rookie"))
		{
			level = 1;
		}
		else if (competitorLevel.equals("amateur"))
		{
			level = 2;
		}
		else if (competitorLevel.equals("pro"))
		{
			level = 3;
		}
		
		
		
		for(int scoreIndex = sortedScores.length - 1 ; scoreIndex  >=  sortedScores.length - level ; scoreIndex--)
		{
			//adding top n scores where n is the level number
			//starting of the last index (the highest score) then index-1 continue n loops, n = level.
			total += sortedScores[scoreIndex];
			
		}
		//compute an overall score
		overallScore = (double)total/level;
		
		return overallScore;
	}
	
	
	/** This method overrides from the superclass to return a String of full competitor details
	 * @return full details
	 */
	public String getFullDetails() {
		//create a format of array scores to display e.g. 3,4,1,3,4
		String Score ="";
		for(int index=0; index < listOfScores.length ; index++)
		{
			if(index != listOfScores.length-1)
			{
				Score += listOfScores[index]+",";
			}
			else
			{
				Score += listOfScores[index];
			}
		}
		
		
		return "Full details for " + getId() + ":\r\nCompetitor number " + getId() + ", name " + getName() + ".\n"
				+ competitorName.getShortName() + " is a " + this.getCompetitorLevel() + " aged " + competitorAge
				+ " from " + competitorCountry + " and received these scores : "+ Score+ " which has an overall score of " + getOverallScore() + ".";
	}
	

}
