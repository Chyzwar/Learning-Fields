import java.util.*;
public class DemoMap {

	

	public static void main(String[] args) {
		//create a treemap with key = name (String) and value = phone (String)
		TreeMap <String, String> phoneBook1 = new TreeMap <String, String> ();
		//add a few key/value pairs
		phoneBook1.put("Tim", "411-0914");
		phoneBook1.put("Jo", "411-0210");
		phoneBook1.put("Jack", "131-9873");
		phoneBook1.put("Ann", "411-0210");  //jo and ann share a phone

		System.out.println("There are " +Integer.toString(phoneBook1.size()) + " numbers in phonebook");
		
		
		//get a value (phone) for a given key (name)
		String phoneNumber = phoneBook1.get("Jack");
		System.out.println("Jack's number is "
				+ phoneNumber);
		System.out.println();
		
	
		//use the TreeMap toString method to print the entries
		//(prints key=value)
		System.out.println(phoneBook1);
		System.out.println();
		phoneBook1.remove("Jack");
		System.out.println("Jack has been removed");
		System.out.println(phoneBook1);
		
		
		//print all the keys (names)
		//(keyset returns a set of the keys)
		System.out.println("Names listed");
		for (String name : phoneBook1.keySet() ) {
			System.out.println(name);
		}
		System.out.println();
		
		//print details from each value
		//(values returns a set of the values)
		System.out.println("Phone numbers suffix, from the values");
		for (String phone : phoneBook1.values() ) {
			//just shows you can use a method on the value
			//(and would have to if the value was an object)
			System.out.println(phone.substring (4) );
		}
		System.out.println();
		
		//print details of key and value
		//(entrySet returns a set of entries (key/value pairs), type Map.Entry<K,V>  )
		//use getKey, getValue on the entry
		for (Map.Entry<String, String> entry : phoneBook1.entrySet() ) {
			System.out.println(entry.getKey() + " has extension " 
					+ entry.getValue().substring(4));
		}
		System.out.println();
		
		//get list of phone numbers without duplicates
		System.out.println("Phone numbers, no duplicates");
		TreeSet <String> phones = new TreeSet<String> (phoneBook1.values());
		for (String phone : phones) {
			System.out.println(phone);
		}
		
	} 

	
}

