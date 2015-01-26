import java.awt.*;
import javax.swing.*;
//using observer pattern
public class DigitalDisplay extends JPanel implements Observer
{
	private Clock clockdata;
	private JTextField timeText = new JTextField(10);

	//sets up general gui
	public DigitalDisplay (Clock clock)
	{	
		this.clockdata = clock;	
		clock.registerObserver(this);
		this.add(timeText);	
		timeText.setEditable(false);
		timeText.setHorizontalAlignment(JTextField.CENTER);
		Font timeFont = new Font("SansSerif", Font.BOLD, 14);
		timeText.setFont (timeFont);
		update();
	}	
	//update method gets and stores time in 24 hour hh:mm format
	public void update()
	{
		String text =  clockdata.getTime24();
		timeText.setText(text);
	}
}
