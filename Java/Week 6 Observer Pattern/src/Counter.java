import javax.swing.JOptionPane;
public class Counter implements Observer{
	private int counter ;
	
	public Counter (Subject subject) {
		counter = 0;
		subject.registerObserver(this);
	}
	
	public void update () {
		counter++;
		JOptionPane.showMessageDialog(null, "The clock has been changed " 
				+ counter + " times");
	}

}
