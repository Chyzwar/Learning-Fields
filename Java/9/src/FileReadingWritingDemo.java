public class FileReadingWritingDemo {


	public static void main(String[] args) {
		StudentList sl = new StudentList();
		sl.readFile("StudentList.csv");
		String report = sl.getAllStudents();
		sl.writeToFile("StudentsOut.txt", report);
		
	}
}
