package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Clock;
import views.ClockGUI;

//Handles interaction with users
//calls view and model as needed

public class ClockController {

	private ClockGUI view;  //GUI to allow user to set the time

	private Clock clock;       //clockmodel stores the time
	
	public ClockController(ClockGUI view, Clock clock) {
		this.clock = clock;
		this.view = view;
		//specify the listener for the view
		view.addSetListener(new SetListener());
	}
	
	//inner class SetListener responds when user sets the time
	public  class SetListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			int hour = Integer.parseInt(view.getHours());
			int min = Integer.parseInt(view.getMins());
			clock.setTime24(hour, min);
		}
	}

}
