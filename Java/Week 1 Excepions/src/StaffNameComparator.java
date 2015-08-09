//defines an ordering on Staff objects on the name

import java.util.Comparator;
public class StaffNameComparator 
               implements Comparator<Staff>
{
	public int compare(Staff s1, Staff s2) {
		return s1.getName().compareTo(s2.getName());
	}
}
