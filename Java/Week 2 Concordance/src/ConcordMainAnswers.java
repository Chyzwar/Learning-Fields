import javax.swing.JOptionPane;
public class ConcordMainAnswers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcordAnswers c = new ConcordAnswers();
		c.process("in.txt");
		
		c.printWordsOnly();
		
		c.printLineNumbersForWord("JAVA");
		
		c.printLineNumbersForWord("ARE");
	}

}
