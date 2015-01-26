import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class ClockDisplayGUI extends JFrame
{			
	public ClockDisplayGUI()
	{	
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(160,220);
		this.setVisible(true);

	}
	
	public void addCenter(JPanel panel) {
		this.add(panel, BorderLayout.CENTER);
	}
	
	public void addSouth(JPanel panel) {
;
		this.add(panel, BorderLayout.SOUTH);
	}
}

