package restaurant.orders;

/**New unchecked exception for dish prices in Order Collection
 * get thrown when quantity is > 10 in order
 * @author Marcin
 *
 */
@SuppressWarnings("serial")
public class BadQuantityException extends RuntimeException {

	
	/**Constructor for exception
	 * pass name that can used in getMessege in Exception class
	 * @param seq
	 */
	public BadQuantityException(int seq){
		super("Incorrect dish quantity in order number = " + seq);
	}
}