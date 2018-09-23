/**
 * This class is creating a name object 
 * Name class also include methods to access specific parts of name
 * @author Group
 *
 */
public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	/**
	 * This constructor is used to setting up the name-object based on first and last name
	 * @param firstName first name of the person/team
	 * @param middleName as empty string 
	 * @param lastName last name of the person/name
	 */
	public Name(String fName, String lName) 
	{
		firstName = fName;
		middleName = "";
		lastName = lName;
	}


	/** This constructor is used to setting up the name-object based on first-, middle- and last name
	 * @param fName
	 * @param mName
	 * @param lName
	 */
	public Name(String fName, String mName, String lName) 
	{
		firstName = fName;
		middleName = mName;
		lastName = lName;
	}


	/**This constructor is used to setting up the name-object based fullname only.
	 * Based on fullname It will get first, middle and last name
	 * @param fullName, long string containing all names together separated by ' '
	 */

	public Name (String fullName) 
	{
		int spacePos1 = fullName.indexOf(' ');
		firstName = fullName.substring(0, spacePos1+1);
		int spacePos2 = fullName.lastIndexOf(' ');
		if (spacePos1 == spacePos2)
			middleName = "";
		else 
			middleName = fullName.substring(spacePos1+1, spacePos2);
		lastName = fullName.substring(spacePos2 + 1);
	}



	/**
	 * Returning the full name of the person/team . If the person doesn't have a middle name, and the middle name is set to "",
	 * then it will only return the first and last name
	 * @return full name of the person/team
	 */
	public String getFullName() 
	{
		String result = firstName + " ";
		if (!middleName.equals("")) 
		{
			result += middleName + " ";
		}
		if (!lastName.equals("")) 
		{
			result += lastName;
		}

		return result;	  
	}


	/**
	 * Only returns the initials for the first, middle and last name. If the person doesn't have a middle name,
	 * it only returns the initial for the first and last name
	 * @return first, middle and last name initials
	 */
	public String getShortName()
	{
		String shorts = "";
		if (firstName !="")
		{
			shorts+=firstName.substring(0,1);
		}
		if (middleName!="")
		{
			shorts+=middleName.substring(0,1);
		}
		if(lastName!="")
		{
			shorts+=lastName.substring(0,1);
		}

		return shorts;
	}


}