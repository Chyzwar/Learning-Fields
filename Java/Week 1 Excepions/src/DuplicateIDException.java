
public class DuplicateIDException extends Exception {

	public DuplicateIDException(String dup){
		super("Duplicate id = " + dup);
	}
}
