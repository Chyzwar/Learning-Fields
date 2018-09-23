import java.util.Comparator;

/**This class is needed to compare competitors object based on Name
 * Provide correct compare method for sort method.
 * @author Marcin Kopacz
 *
 */
public class CompetitorNameComparator implements Comparator<Competitor>
{


	/**Compare two names of competitor object. 
	 * Provide "natural" order for listByName()
	 */
	public int compare(Competitor s1, Competitor s2) 
	{
		return s1.getName().compareTo(s2.getName());
	}
}