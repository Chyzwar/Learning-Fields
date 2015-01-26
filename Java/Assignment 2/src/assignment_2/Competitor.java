package assignment_2;

/**Class for Competitor object. 
 * Contain different methods to extract data about competitors.
 * Have abstract methods declaration.
 * Implements Comparable for sorting purpose.
 * It is super class for Bowler, Golfer, Soapbox.
 * 
 * @authors Group
 */

abstract public class Competitor  implements Comparable<Competitor>
{
	private int id;
	protected Name competitorName;
	protected int[] listOfScores ;


	/**Create Competitor object with values specified in parameters
	 * 
	 * @param id, unique integer
	 * @param competitorName as Name
	 * @param listOfScores as [] integer
	 */
	public Competitor(int id,Name competitorName,int [] listOfScores)
	{
		this.id = id;
		this.competitorName= competitorName;
		this.listOfScores = listOfScores;
	}


	/**Abstract method for getOverallScore() 
	 * all subclasses need to have getOverallScore methods
	 * corresponding methods in subclasses return double
	 * @return double 
	 */
	public abstract double getOverallScore();


	/**Abstract method for getFullDetails() 
	 * all subclasses need to have getFullDetails() methods
	 * corresponding methods in subclasses return String
	 * @return String
	 */
	public abstract String getFullDetails();


	/** Method return id of single competitor as integer value
	 *
	 * @return id
	 */
	public int getId() 
	{  
		return id; 
	}


	/**
	 * Compare this Staff object against another, for the purpose
	 * of sorting. The fields are compared by id ints.
	 * @param otherDetails The details to be compared against.
	 * @return the value 0 if x == y; a value less than 0 if x < y; and a value greater than 0 if x > y
	 */
	public int compareTo(Competitor otherDetails)
	{
		return  Integer.valueOf(id).compareTo(otherDetails.getId());
	}    


	/**Return full name from Name object 
	 * Use method getFullName() in Name class
	 * @return full name as string String 
	 */
	public String getName() 
	{
		return competitorName.getFullName(); 
	}


	/**Return list of scores as integer array
	 * 
	 * @return [] listOfScore
	 */
	public int[] getListOfScores()
	{	
		return listOfScores;
	}


	/**Create short report on single competitor
	 * @return String shortDetails
	 */
	public String getShortDetails()
	{
		int competitorNumber= getId();
		String shortDetails = "Short details for " + competitorNumber + ":\r\nCN " + competitorNumber + " (" + competitorName.getShortName() + ") has overall score " + getOverallScore();
		return shortDetails;
	}


	/**Update listOfScores based on scores obtained by QGUI
	 * @param scores
	 */
	public void setScores(int[] scores)
	{
		listOfScores = scores;
	}
}
