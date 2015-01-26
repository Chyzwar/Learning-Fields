
public class NoMatchingIDExeption extends Exception
{
	public NoMatchingIDExeption(String dup)
	{
		super("No matching ID = " + dup);
	}
}
