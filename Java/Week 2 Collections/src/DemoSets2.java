/** Demo Set 2
 * First stores words in TreeSet instead of HashSet
 * Then demonstrates using treeset to sort numbers
 */
import java.util.*;
public class DemoSets2 {

	public static void main(String[] args) {

		//instantiate a TreeSet with a String key      
		Set<String> s = new TreeSet<String>(); 
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
		
		ArrayList <Integer> numbers = new ArrayList<Integer>();
		//use ArrayList add method to add a few numbers
		numbers.add(5);  
		numbers.add(2);  
		numbers.add(8);  
		numbers.add(1);  
		TreeSet<Integer> treeNums = new TreeSet<Integer> (numbers);
		//treeNums.addAll(numbers);
		System.out.println( treeNums);
	} 


}


