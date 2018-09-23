/**
 * maintains a list of Staff objects as an ArrayList
 * with methods to add objects, find size, various reports 
 */
import java.util.ArrayList;
import java.util.Collections;
public class StaffList
{
    private ArrayList <Staff> staffList;

    public StaffList()
    {
        staffList = new ArrayList<Staff>() ;
    }
    
    /**
     * @return the Staff object at a given index position
     */
    public Staff getByIndex(int n) {
    	return staffList.get(n);
    }
    
    /**
     * @return the number of items in the list
     */
    public int getSize() {
    	return staffList.size();
    }
    
    /**
     * Look up an id and return the
     * corresponding staff details.
     * @param idThe id  to be looked up.
     * @return The details corresponding to the id, null if none
     */
    public Staff findById(String id)
    {
    	for (Staff s : staffList)
    	{
    		if (s.getId().equals(id))
    		{
    			return s;
    		}
    	}
    	return null;
    }
    
    /**
     * Add a new set of details to the list
     * assumes that details are correct and not already there
     * @param details The details of the staff
     */
    public void addDetails(Staff details) 
    {
		staffList.add(details);
    }
    
    /**
     * @return The number of entries currently in the
     *         address book.
     */
    public int getNumberOfEntries()
    {
        return staffList.size();
    }

    /**
     * @return All the staff details
     */
    public String listDetails()
    {
    	StringBuffer allEntries = new StringBuffer();
        for(Staff details : staffList) {
            allEntries.append(details);
            allEntries.append('\n');
        }
        return allEntries.toString();
    }
    
    /**
     * @return All the staff details in name order
     */
    public String listByName()
    {
    	Collections.sort(staffList, new StaffNameComparator());
    	return listDetails();
    }
    
    /**
     * @return All the staff details in id order
     */
    public String listByID()
    {
    	Collections.sort(staffList);
    	return listDetails();
    }
}
