package restaurant.orders;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**This GUI shows the summary of selected table from a user input
 * @author Kamontorn Khamrun
 *
 */
@SuppressWarnings("serial")
public class GrandTotalGUI extends JFrame
{
	private String singleTableSummary;
	private JTextArea tableReport;


	/**Set up the GUI to show a report of selected table 
	 * @param tableSummary the report of the table
	 */
	public GrandTotalGUI(String tableSummary) 
	{
		this.singleTableSummary = tableSummary;
		setTitle("Bill");
		setupNorthPanel();
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Set up the north panel to contain a report 
	 */
	public void setupNorthPanel() 
	{
		JPanel northPanel = new JPanel();
		tableReport = new JTextArea();
		tableReport.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		tableReport.setEditable(false);
		northPanel.add(tableReport);
		tableReport.setText(singleTableSummary);
		this.add(northPanel, BorderLayout.NORTH);
	}
}