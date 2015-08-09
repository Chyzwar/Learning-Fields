package assignment_2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * Creates a window used to select other functionalities of application
 * Lets to close and save program.
 * @author Group
 *
 */
@SuppressWarnings("serial")
public class StartGUI extends JFrame  implements ActionListener
{
	 private CompetitorList compList;

    JButton startMarcin, startStian,startQ;
    JButton close;
    
    /**
     * Create the frame with its panels.
     * sets all the options for the GUI, and adds the center and north panel to it
     * @param list competitor list to be sorted.
     */
    public StartGUI(CompetitorList list)
    {
      
    	this.compList = list;
       
        setTitle("Access to Group GUIs");
		setupNorthPanel();
		setupCenterPanel();
        pack();
        setVisible(true);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
  
    }
    
    
    /**Setup NorthPanel using border layout
     * Puts buttons on panel
     * Adds buttons to actionListener
     */
    private void setupNorthPanel() 
    {
        
        JPanel northPanel = new JPanel();
        startMarcin = new JButton("Sorting by Marcin");
        startMarcin.addActionListener(this);
        
        startStian = new JButton("Search by Stian");
        startStian.addActionListener(this);
        
        startQ = new JButton("Modification by Q");
        startQ.addActionListener(this);
        
     
        
        northPanel.add (startMarcin);
        northPanel.add(startStian);
        northPanel.add(startQ);
        
        
        
        this.add(northPanel, BorderLayout.NORTH);
        
    }
    
    
    /**Setup CenterPanel using border layout
     * Puts buttons on panel
     * Adds buttons to actionListener
     */
    private void setupCenterPanel() 
    {
    	JPanel centerPanel = new JPanel();

    	
    	close=new JButton("Close & Save");
    	close.addActionListener(this);
    
    	centerPanel.add(close);
    	this.add(centerPanel,BorderLayout.CENTER);
    }
    
    
    /**Adds the functionality for all the buttons
	 * Depend on with button was pressed perform given action
	 */
    public void actionPerformed(ActionEvent e) 
    { 
    
    	if (e.getSource() ==  startMarcin) 
    	{
    		MarcinGUI mk = new MarcinGUI(compList);
    		mk.setVisible(true);
    	}
    	
    	else if (e.getSource() == startStian)
    	{
    		new StianGUI(compList);
    		
    	}
    	
    	else if (e.getSource() == startQ)
    	{
    		QGUI qGUI = new QGUI(compList);
    		qGUI.setVisible(true);	
    	}
    	else if (e.getSource() == close) 
    	{
    		String report = compList.getAllCompetitors();
    		String winner = compList.getWinner();
    	
    		compList.writeToFile("OutputData.txt", report + winner);
    		
    		JOptionPane.showMessageDialog(this, 
    				 "Program will sava a report and close");
    		System.exit(0);
    	}
    	
    }
    	
    }  
  
  



