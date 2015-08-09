//F21SF Lecture 3
public class TestName {

	public static void main(String[] args) {
		
		Name name2 = new Name("Jane","Jo", "Jones");
		String last = name2.getLastName();
		System.out.println
		      ("Last name is " + last);
		name2.setLastName("Smith"); 
		String newLast = name2.getLastName();
		System.out.println
		      ("Last name is " + newLast);
		System.out.println();
				
		Name n2 = new Name("Mary", "Ann", "Smith");
		System.out.print("First name and last name  : "  );	
		System.out.println(n2.getFirstAndLastName());
		System.out.print("Surname, comma, firstname : ");
		System.out.println(n2.getLastCommaFirst());
		System.out.println();
		
		System.out.println(n2.getInitPeriodLast());

	}
		
}
