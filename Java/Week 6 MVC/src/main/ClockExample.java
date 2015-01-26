package main;
import controllers.ClockController;
import model.Clock;
import views.ClockGUI;


//Applies MVC pattern to the SetClockGUIView window.
//the other views are for display only and are updated using the observer pattern
public class ClockExample
{
    public static void main(String[] args)
    {
    	//ClockModel maintains the time and broadcasts changes
    	Clock model = new Clock();
    	
        //This view displays the time, updating whenever changed
    	//AND incorporates the GUI to change the time
    	ClockGUI view = new ClockGUI(model); 
    	
    	//this controller responds when the time is changed in the view
    	//it needs to know about teh view and the model
    	ClockController controller = new ClockController (view, model);
    	
    	//now all is displayed, wait for user to interact
 
    } 
}