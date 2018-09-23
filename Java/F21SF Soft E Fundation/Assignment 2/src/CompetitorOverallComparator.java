import java.util.Comparator;


/**This class is needed to compare competitors object based on Overallscore
 * Provide correct compare method for sort method.
 * @author Marcin Kopacz
 *
 */
public class CompetitorOverallComparator  implements Comparator<Competitor>
{


	/**Compare two competitor based on OverallScore. 
	 * Provide "natural" order for listByOverall()
	 */
	public int compare(Competitor s1, Competitor s2) 
	{
		return  Double.valueOf(s1.getOverallScore()).compareTo(s2.getOverallScore());
	}
}