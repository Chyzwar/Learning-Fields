public class ClockExample
{
    public static void main(String[] args)
    {
    	//create clock to keep time
    	Clock clock = new Clock();
 	
    	//create  digital clock display
    	DigitalDisplay dd = new DigitalDisplay(clock);
    	//create a graphic clock displays
    	AnalogDisplay ad = new AnalogDisplay(clock);
 	
    	//create a display for the clocks
    	ClockDisplayGUI display = new ClockDisplayGUI(); 
    	//add all the display clock panels
    	display.addCenter(ad);
    	display.addSouth(dd);
    	
    	//create counter
    	Counter counter = new Counter (clock);
    	
    	//create gui to allow user to set the time
    	SetClockGUI  setTime = new SetClockGUI(clock);
    	setTime.setVisible(true);
    	
    	//now program just waits for user to use the SetClockGUI 
    }
    
}