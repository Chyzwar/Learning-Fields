/**
 * F21AS Collections
 * Demonstrates adding words (Strings) to a HashSet
 * Then using a treeset to order them
 * 
 * Then creating a HashSet of PersonTel objects
 * Printing all the names
 * Printing names and hashcodes
 * Creates and prints an ordered list of landlines with duplicates eliminated
 * @author monica
 *
 */
import java.util.*;
public class DemoiSet1 {

	public static void main(String[] args) {

		//instantiate a HashSet with a String key      
		Set<String> s = new HashSet<String>(); 
		//for each word (argument), add and check
		for (String a : args) {
			if (!s.add(a)) {	//add and check if fails  
				System.out.println("Duplicate found: " + a); 
			}
		}
		//use size() to find out how many words in the set
		//printing ‘s’ invokes the toString method 
		//  which prints out the set
		System.out.println(s.size() + " distinct words: " 
				+ s); 
		
		//convert  to tree to order
		TreeSet<String> t = new TreeSet<String> (s);
		System.out.println(t);
		//////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////
		
		//add PersonTel objects to a set
		PersonTel pt1 = new PersonTel("Tim", "411-0914", "077871234");
		PersonTel pt2 = new PersonTel("Jo", "411-0210", "077871234");
		PersonTel pt3 = new PersonTel("Jack", "131-9873", "077871234");
		PersonTel pt4 = new PersonTel("Ann", "411-0210", "077871234");
		PersonTel pt5 = new PersonTel("Ann", "411-0210", "077871234");
		HashSet <PersonTel> phonebook = new HashSet<PersonTel> ();
		phonebook.add(pt1);  
		phonebook.add(pt2);
		phonebook.add(pt3);
		phonebook.add(pt4);
		phonebook.add(pt5);	
	
		//print names
		for (PersonTel pt : phonebook) {
			System.out.println(pt.getName());  //nb only one ann
		}
		
		//////////////////////////////////////////////////////////
		//print names and hashcode
		for (PersonTel pt : phonebook) { //order not reliable!!
			System.out.println(pt.getName() + pt.hashCode());
		}
		
		//////////////////////////////////////////////////////////
		//produce a sorted unique ordered list of landlines
		TreeSet<String> landlines = new TreeSet<String> ();
		for (PersonTel pt : phonebook) {
			landlines.add(pt.getLandline());
		}
		for (String phone :landlines) {
			System.out.println(phone);
		}
	} 


}


