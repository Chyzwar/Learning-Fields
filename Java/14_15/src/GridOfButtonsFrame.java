import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GridOfButtonsFrame extends JFrame
                  implements ActionListener
{ 
	//assumes a max list size of 9
	private static final int MAX_BUTTONS = 9;
	private static final int ROW_SIZE = 3;
	private StaffList allStaff;
	JButton buttons[] =new JButton[MAX_BUTTONS];

	public GridOfButtonsFrame(StaffList s) {
		allStaff = s;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300,200);
        this.setSize(300,300);
		this.setLayout(new BorderLayout());
		
		JPanel gridP = this.createGridPanel(allStaff);
		this.add(gridP, BorderLayout.CENTER);	
	}

	//loop through staff list
	//for each staff object, get id and create button with first name as label
	private JPanel createGridPanel(StaffList stafflist) {
		JPanel panel = new JPanel(new GridLayout(ROW_SIZE, ROW_SIZE));
		for (int i = 0; i < stafflist.getSize(); i++) {
			Staff s = stafflist.getByIndex(i);
			String label = s.getName().getFirstName();
			buttons[i] = new JButton(label);
			panel.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		return panel;
	}
	
	//Loop through buttons until the one that was clicked is found
	//then use that index to get the corresponding Staff object 
	//and display the name
	//ASSUMES LIST IS NEVER ALTERED
	//If list is ever altered, need to search by name instead
	//  assuming name is unique, or put id on label, or....
	public void actionPerformed (ActionEvent e) {
		for (int i = 0; i < MAX_BUTTONS; i++) {
			if (e.getSource()== buttons[i]) {
				Staff s = allStaff.getByIndex(i);
				String name =  s.getName().getFirstAndLastName();
				JOptionPane.showMessageDialog(this, name);
			}
		}
	}
	
	//disables button with name - assumes names are unique
	//Demonstrates disabling - not a very logical thing to do in this situation
	public void disableButton (String firstname) {
		for (int i = 0; i < allStaff.getSize(); i++ ) {
			if (buttons[i].getText().equals((firstname)))  {
				buttons[i].setEnabled(false);
			}
			else {
				buttons[i].setEnabled(true);
			}
		}
	}
}
