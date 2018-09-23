import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**This MarcinGUI class is providing GUI for different sorting types.
 * Instead of MarcinGUI better name would be SortGUI but for our group work we decided that this way would be best
 * @author Marcin Kopacz 
 *
 */
@SuppressWarnings("serial")
public class MarcinGUI extends JFrame  implements ActionListener
{
   
  
	/**
	 * Constructor for the GUI that gets a list of competitors and sets the class variable compList,
	 * sets all the options for the GUI, and adds the center and north panel to it
	 * @param  compList of competitors from CompetitorList
	 */
	private CompetitorList compList;
    
    JTextField result;
    JScrollPane scrollList;
    JButton showListById, showListByName,showListByType,showListByOverall;
    JTextArea displayList;
    
 
 
    /**
     * Create the frame with its panels.
     * @param list competitor list to be sorted.
     */
    public MarcinGUI(CompetitorList list)
    {
        this.compList = list;
        
       
        setTitle("All types of Competitor listed");
		setupNorthPanel();
		setupCenterPanel();
        pack();
        setVisible(true);
        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    
    /**Setup CenterPanel using border layout
     * Puts buttons on panel
     * Adds buttons to actionListener
     */
    private void setupCenterPanel() {
        displayList = new JTextArea(15,20);
        displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayList.setEditable(false);
        scrollList = new JScrollPane(displayList);
        this.add(scrollList,BorderLayout.CENTER);
    }
    
    
    /**Setup NorthPanel using border layout
     * Puts buttons on panel
     * Adds buttons to actionListener
     */
    private void setupNorthPanel() {
   
        JPanel northPanel = new JPanel();
        showListById = new JButton("List By ID");
        showListById.addActionListener(this);
        
        showListByName = new JButton("List By Name");
        showListByName.addActionListener(this);
        
        showListByType = new JButton("List By Competitor Type");
        showListByType.addActionListener(this);
        
        showListByOverall = new JButton("List By Overall Score");
        showListByOverall.addActionListener(this);
        
        northPanel.add (showListById);
        northPanel.add(showListByName);
        northPanel.add(showListByType);
        northPanel.add(showListByOverall);
        
        this.add(northPanel, BorderLayout.NORTH);
    }
    
    
    /**Adds the functionality for all the buttons
   	 * Depend on with button was pressed perform given action
   	 */
    public void actionPerformed(ActionEvent e) 
    { 
    
    	if (e.getSource() == showListById) 
    	{
    		displayList.setText(compList.listByID());
    	}
    	
    	else if (e.getSource() == showListByName )
    	{
    		displayList.setText(compList.listByName());
    	}
    	else if (e.getSource() == showListByOverall )
    	{
    		displayList.setText(compList.listByOverall());
    	}
    	else if (e.getSource() == showListByType) 
    	{
    	compList.clear();
    	compList.readAllFiles();
    	displayList.setText(compList.getAllCompetitors());
    	
    	}
    	
    }  
  
  

}

