//import all the GUI classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Simple GUI for StaffList application
 */
public class StaffListGUI extends JFrame implements ActionListener
{
    // The staff list to be searched.
    private StaffList staffList;
    
    //GUI components
    JTextField result;
    JTextField searchField;
    JButton search;
    JScrollPane scrollList;
    JButton showListById, showListByName;
    JTextArea displayList;
    
    /**
     * Create the frame with its panels.
     * @param list	The staff list to be searched.
     */
    public StaffListGUI(StaffList list)
    {
        this.staffList = list;
        
        //set up window title
        setTitle("StaffList");
        //ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        //search panel contains label, text field and button
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,3));
        searchPanel.add(new JLabel("Enter ID"));   
        searchField = new JTextField(5);
        searchPanel.add(searchField);   
        search = new JButton("Search");  
        searchPanel.add(search);    
        //specify action when button is pressed
        search.addActionListener(this) ;
        
        //Set up the area where the results will be displayed.
        result= new JTextField(25);     
        result.setEditable(false);
        
        //set up south panel containing 2 previous areas
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,1));
        southPanel.add(searchPanel);
        southPanel.add(result);
        
        //add south panel to the content pane
        Container contentPane = getContentPane();
        contentPane.add(southPanel, BorderLayout.SOUTH);
        
        //add north panel containing some buttons
        JPanel northPanel = new JPanel();
        showListById = new JButton("List By ID");
        showListById.addActionListener(this);
        showListByName = new JButton("List By Name");
        showListByName.addActionListener(this);
        northPanel.add (showListById);
        northPanel.add(showListByName);
        contentPane.add(northPanel, BorderLayout.NORTH);
        
        displayList = new JTextArea(15,20);
        displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayList.setEditable(false);
        scrollList = new JScrollPane(displayList);
        contentPane.add(scrollList,BorderLayout.CENTER);
        
        //pack and set visible
        pack();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) 
    { 
    	if (e.getSource() == search) {
	    	//get search text and search staff list
	    	//setting result text if found
	        String searchString = searchField.getText().trim();
	        if(searchString.length() > 0) {
	            Staff person = staffList.findById(searchString);
	            if (person != null )
	            	result.setText(person.toString());
	            else
	            	result.setText("not found");
	        }
    	}
    	else if (e.getSource() == showListById) {
    		displayList.setText(staffList.listByID());
    	}
    	else if (e.getSource() == showListByName ) {
    		displayList.setText(staffList.listByName());
    	}
    }  

}
