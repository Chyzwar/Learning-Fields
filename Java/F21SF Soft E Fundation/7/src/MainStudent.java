
public class MainStudent {

	public static void main(String[] args) {
		//example of creating an array with known values
		String [] quals1 = {"MIC", "ARA" };
		//create Student with this array of qualifications
		Student s1 = new Student ("0011",new Name("Helen Scott"),
				quals1, 1);
		//add their marks
		s1.addMark(8,1);
		s1.addMark(7,2);		
		s1.addMark(4,3);
		
		//print out some details
		System.out.println(s1.getName().getFullName() + " has an average mark of " 
				+ String.format ("%.1f", s1.getAveMark())
				+ " and a highest mark of " + s1.getMaxMark() );
		System.out.println("Previous qualifications: " + s1.getQualDetails());
		
		//find out whether they have the qualification "ABC"
		String q = "ABC";
		boolean hasQ = s1.hasQual(q);
		//print out message
		System.out.print("This student does ");
		if (!hasQ) {  //N.B. this is a better way than writing if (hasQ == false)
			System.out.print ("not ");
		}
		System.out.println ("have the " + q + " qualification");
		
		

	}
}
