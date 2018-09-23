package restaurant.orders;

@SuppressWarnings("serial")
/**New checked exception for dish names in menuSet
 * @author Marcin
 *
 */
public class DuplicateNameException extends Exception {

	
	/**Constructor for exception
	 * pass name that can used in getMessege in Exception class
	 * @param dup
	 */
	public DuplicateNameException(String dup){
		super("Duplicate dih name = " + dup);
	}
}

