package assignment_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Creates a window used to view a list of all competitors, as well as showing full and short details for a specific competitor
 * based on user input
 * @author Stian Dalviken
 *
 */
@SuppressWarnings("serial")
public class StianGUI extends JFrame implements ActionListener {
	
	private JButton viewAllDetails, showAll, close;
	private JTextArea txtArea;
	private JScrollPane scrollPane;
	private CompetitorList competitorList;
	
	/**
	 * Constructor for the GUI that gets a list of competitors and sets the class variable competitorList,
	 * sets all the options for the GUI, and adds the center and south panel to it
	 * @param list list of competitors from CompetitorList
	 */
	public StianGUI(CompetitorList list) {
		this.competitorList = list;
		
		setGUIOptions();
		
		setCenterPanel();
		setSouthPanel();
		
		this.pack();
	}
	
	/**
	 * sets up the GUI by setting all the options for it
	 */
	private void setGUIOptions() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(230, 100);
		setTitle("View full/short details for competitors");
		setVisible(true);
	}
	
	/**
	 * sets up the text area, populates it with the competitor list using the populateTxtAreaWithList method,
	 * and adds a scrollable pane to the center panel
	 */
	private void setCenterPanel() {		
		txtArea = new JTextArea(20, 100);
		txtArea.setEditable(false);
		txtArea.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		
		populateTxtAreaWithList();
		
        add(txtArea);
        setBackground(Color.WHITE);
        
        scrollPane = new JScrollPane(txtArea);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * sets up the south panel with buttons using a GridLayout, so that the buttons are always
	 * visible if the window is resized, and adds it to the south panel. The buttons are also made
	 * active, using the actionlistener
	 */
	private void setSouthPanel() {
		JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout());
        
        viewAllDetails = new JButton("View details of competitor");
        viewAllDetails.addActionListener(this);
		southPanel.add(viewAllDetails);
		
		showAll = new JButton("Show all");
		showAll.addActionListener(this);
		southPanel.add(showAll);
        
        close = new JButton("Close");
        close.addActionListener(this);
		southPanel.add(close);
		
		this.add(southPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * clears the text area in the center panel before populating it with a list of all competitors
	 */
	private void populateTxtAreaWithList(){		
		txtArea.setText("");
		
		txtArea.append(competitorList.getAllCompetitors());
	}

	/**
	 * adds the functionality for all the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* adds the functionality for the "View details of competitor" button.
		 * Sets new title for the window, gets short and full details from the singleCompetitorInfo method
		 * based on user input, and populates the text area with the details
		 */
		if (e.getSource() == viewAllDetails) {
			setTitle("Full and short details for specific user");
			txtArea.setText("Waiting for user input...");
    		
			String competitorInfo = competitorList.singleCompetitorInfo();
			if(competitorInfo != null)
				txtArea.setText(competitorInfo);
			else
				txtArea.setText("There is no competitor with this competitor number");
		}
		
		/* adds the functionality for the "Show all" button.
		 * Sets a new title for the window and populates the text area with a list of all competitors
		 */
		else if (e.getSource() == showAll) {
			setTitle("View full/short details for competitors");
			populateTxtAreaWithList();
    	}
		
		/* adds the functionality for the "Close" button. If pressed, the window gets closed without stopping the application
		 */
		else if (e.getSource() == close) {
			dispose();
		}
	}
}
