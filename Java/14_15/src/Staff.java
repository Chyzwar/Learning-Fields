//a simple class to contain and manage Staff details
//(id, name, phone, level)
public class Staff implements Comparable<Staff>
{
	private String id;
    private Name name;
    private String phone;
    private int qualLevel;

    /**
     * Set up the contact details. All details are trimmed to remove
     * trailing white space.
     * @param name The name.
     * @param phone The phone number.
     */
    public Staff(String id, Name name, String phone, int level)
    {   
    	//id and name MUSt be provided
        if( name==null || id.trim().length()== 0)    
        {
          throw new IllegalStateException(
             "Cannot have blank id or name");
        }
        this.id =id.trim();
        this.name = name;
        this.phone = phone.trim();
        this.qualLevel = level;
    }
    
 
  
    public String getId()  {  return id;  } 
    public Name getName() { return name; }
    public String getPhone() { return phone; }
    public int getLevel() { return qualLevel; }
    
    public void setID(String id) {this.id = id; }
    public void setName(Name name) {this.name = name;}
    public void setPhone(String phone){this.phone = phone; }
    public void setLevel(int level) {this.qualLevel = level; }

    
    /**
     * Test for content equality between two objects.
     * @param other The object to compare to this one.
     * @return true if the argument object has same id
     */
    public boolean equals(Object other)
    {
        if(other instanceof Staff) {
            Staff otherStaff = (Staff) other;
            return id.equals(otherStaff.getId());
        }
        else {
            return false;
        }
    }

    /**
     * Compare this Staff object against another, for the purpose
     * of sorting. The fields are compared by id.
     * @param otherDetails The details to be compared against.
     * @return a negative integer if this id comes before the parameter's id,
     *         zero if they are equal and a positive integer if this
     *         comes after the other.
     */

    public int compareTo(Staff otherDetails)
    {
        return id.compareTo(otherDetails.getId());
    }    

    /**
     * @return A multi-line string containing the name, phone, and address.
     */
    public String toString()
    {
        return String.format("%-5s", id ) + String.format("%-20s", name.getLastCommaFirst()) + phone ;
    }

}
