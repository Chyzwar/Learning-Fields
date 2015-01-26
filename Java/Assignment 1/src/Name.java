//Names of teams are made of many parts. I can't implement the same class as you did.
//I assume that Team name can be made on maximum 5 words
public class Name {
  private String firstName;
  private String middleName;
  private String lastName;
  
  public Name(String fName, String lName) {
		firstName = fName;
		middleName = "";
		lastName = lName;
  }
  
  public Name(String fName, String mName, String lName) {
		firstName = fName;
		middleName = mName;
		lastName = lName;
}
  
  
  public Name (String fullName) {
	  int spacePos1 = fullName.indexOf(' ');
	  firstName = fullName.substring(0, spacePos1+1);
	  int spacePos2 = fullName.lastIndexOf(' ');
	  if (spacePos1 == spacePos2)
		  middleName = "";
	  else 
		  middleName = fullName.substring(spacePos1+1, spacePos2);
	  lastName = fullName.substring(spacePos2 + 1);
  }
  
 //method return full name.
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
  
 //method return initials
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