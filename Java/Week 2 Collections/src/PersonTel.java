/**
 * F21AS Collections
 * Object stored in various sets and maps
 * @author monica
 *
 */
public class PersonTel implements Comparable<PersonTel> {
	private String name;
	private String landline;
	private String mobile;
	
	public PersonTel(String n, String l, String m) {
		name = n;
		landline = l;
		mobile = m;
	}
	
	public int compareTo(PersonTel other) {
		return name.compareTo(other.name);
	}
	
	public boolean equals(Object other) {
		if (other instanceof PersonTel) {
			PersonTel otherPerson = (PersonTel) other;
			if (name.equals(otherPerson.name)) 
				return true;
		}
		return false;
	}
	
	public int hashCode() { return name.hashCode();}
	
	public String getName() { return name;}
	public String getLandline() { return landline;}
	public String getMobile() {return mobile;}
}
