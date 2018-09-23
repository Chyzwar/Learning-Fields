import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**This QGUI class is a GUI for search, view and modify competitor score.
 * @author Kamontorn Khamrun H00144659
 *
 */
@SuppressWarnings("serial")
public class QGUI extends JFrame implements ActionListener{

	JButton search,updateScore,close;
	JTextField searchField, id, fullName, level,score1,score2,score3,score4,score5, overallScore;
	JTextArea displayDetail;
	private Competitor c;
	private CompetitorList competitorList;

	/**Constructor to prepare GUI panels and get competitorList for searching
	 * @param list the competitor list
	 */
	public QGUI(CompetitorList list) {
		competitorList = list;
		setTitle("View and alter scores");
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();
		pack();
		setSize(300,200);

	}

	
	/**This method is to set up the north panel that contains search menu.
	 * 
	 */
	private void setupNorthPanel() {      

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(1,3));
		searchPanel.add(new JLabel("Enter ID"));   
		searchField = new JTextField(5);
		searchPanel.add(searchField);   
		search = new JButton("Search");  
		searchPanel.add(search);    	        
		//specify action when button is pressed
		search.addActionListener(this) ;
		this.add(searchPanel, BorderLayout.NORTH);
	}

	
	/**This method is to set up the center panel for showing the details of competitor and can modify scores.
	 * 
	 */
	private void setupCenterPanel() {
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(5,2));
		displayPanel.add(new JLabel("ID :"));   
		id = new JTextField();
		id.setEditable(false);
		displayPanel.add(id);
		displayPanel.add(new JLabel("Full Name :"));   
		fullName = new JTextField();
		fullName.setEditable(false);
		displayPanel.add(fullName);
		displayPanel.add(new JLabel("Scores :"));   
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(1,5));
		//Display each score in each text field
		score1 = new JTextField();
		scorePanel.add(score1);
		score2 = new JTextField();
		scorePanel.add(score2);
		score3 = new JTextField();
		scorePanel.add(score3);
		score4 = new JTextField();
		scorePanel.add(score4);
		score5 = new JTextField();		
		scorePanel.add(score5);
		displayPanel.add(scorePanel);
		displayPanel.add(new JLabel(""));   
		updateScore = new JButton("Update Scores");
		updateScore.setEnabled(false);
		//add button for updating score
		displayPanel.add(updateScore);
		//specify action when button is pressed
		updateScore.addActionListener(this);
		displayPanel.add(new JLabel("Overall score :"));   
		overallScore = new JTextField();
		overallScore.setEditable(false);
		displayPanel.add(overallScore);
		this.add(displayPanel,BorderLayout.CENTER);
	}

	
	
	/**This method is to set up the south panel that contains a close button.
	 *South panel contains close button  
	 */
	private void setupSouthPanel() {
		close = new JButton("Close");
		close.addActionListener(this);
		this.add(close,BorderLayout.SOUTH);

	}
	
	
	/** This method will be called when each button is clicked. When search button is click, seach() is called.
	 * When updateScore is click. All updated scores are validated and then calculate the new overall score which will be displayed in the text field
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == search) {
			search();
		}
		else if(e.getSource()== updateScore)
		{
			boolean add = true;
			int[] listOfScores = new int[5]; //the array for updated scores
			try{
				//get new scores from text fields
				listOfScores[0] = Integer.parseInt(score1.getText());
				listOfScores[1] = Integer.parseInt(score2.getText());
				listOfScores[2] = Integer.parseInt(score3.getText());
				listOfScores[3] = Integer.parseInt(score4.getText());
				listOfScores[4] = Integer.parseInt(score5.getText());



				//To validate range of scores
				for(int index = 0; index < listOfScores.length; index++)
				{

					int x = listOfScores[index];
					//validate new scores are in the range 0-5 or not
					if((x<0)||(x>5))
					{
						add = false;
						JOptionPane.showMessageDialog(null, "A score is out of range 0-5","Invalid input", JOptionPane.ERROR_MESSAGE);

					}

				}				


				if(add)
				{		

					//update new scores into competitor
					c.setScores(listOfScores);
					//JOptionPane.showMessageDialog(null, "The updated overall score is" + c.getOverallScore()+"");

					overallScore.setText(String.format("%.1f",c.getOverallScore()));
				}

			}//Catch if input is not a number
			catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Please input a number","Invalid input", JOptionPane.ERROR_MESSAGE);
				add = false;
			}


		}
		else if(e.getSource()== close)
		{
			this.setVisible(false);

		}

	}
	
	
	/**This method is to search by calling searchById(int id) from competitorList Class and
	 * display results in text fields
	 * 
	 */
	private void search() {
		try{
			String searchString = searchField.getText().trim();
			if(searchString.length() > 0)
			{
				//get competitor by using method searchById 
				c = competitorList.searchById(Integer.parseInt(searchString)); 
				if (c!= null){
					//display results in each field
					id.setText(c.getId()+"");
					fullName.setText(c.getName());
					int[] scoresList = c.getListOfScores();
					score1.setText(scoresList[0]+"");
					score2.setText(scoresList[1]+"");
					score3.setText(scoresList[2]+"");
					score4.setText(scoresList[3]+"");
					score5.setText(scoresList[4]+"");
					overallScore.setText(String.format("%.1f",c.getOverallScore()));
					updateScore.setEnabled(true);

				}
				else{
					JOptionPane.showMessageDialog(null, "Not found. Try again!","Invalid input", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Please input a number","Invalid input", JOptionPane.ERROR_MESSAGE);
		}


	}

}
