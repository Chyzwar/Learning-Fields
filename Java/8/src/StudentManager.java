import javax.swing.JOptionPane;


public class StudentManager {
//demonstrates using a class containing an ArrayList of objects.
	private StudentList allStudents;
	public StudentManager () {
		allStudents = new StudentList();
	}
	public void run() {
		//fill the list with some students
        allStudents.populate();
        
        //print them
		System.out.println(allStudents.getTableOfStudents());
		System.out.println();
		
		//try out the count of  people method
		System.out.println("There are " + allStudents.getCountOfPeopleAtYear(2)
				+ " people who are in year 2\n");
		
		//print a frequency report
		System.out.println(allStudents.getYearsFrequencyReport());
		
		//print report using getAtIndex() method
		//for each index
		//   get student at that index
		//   get some details about the student
		//   print these details
		String report = "LIST OF NAMES \n";
		for (int i = 0; i < allStudents.getSize(); i++) {
			Student p = allStudents.getAtIndex(i);
			String firstName = p.getName().getFirstName();
			report +=String.format("%-8s", firstName) + "\n";
		}
		System.out.println(report);
		
		//try out the search, give 3 tries
		//just use this line to get input for the moment
		//  it is explained in more detail in lectures on GUIs
		boolean ok = false;
		int count = 0;
		while (!ok && count <3) {
			String id = JOptionPane.showInputDialog(null, "Enter id");
			Student s = allStudents.findById(id);
			count++;
			if (s!=null) {
				String firstName = s.getName().getFirstName();
				System.out.println("The person with id " + id + " is called " + firstName);
				ok = true;
			}
			else  {
				System.out.println("Incorrect id");
				if (count <= 3) {
					System.out.println("Try again");
				}
			}
		}
	}
}	
