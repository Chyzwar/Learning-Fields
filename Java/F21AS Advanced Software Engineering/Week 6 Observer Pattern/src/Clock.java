//holds the time, with various get and set methods
//subject of observer pattern

import java.util.*;
public class Clock implements Subject
{	
	private final int MINUTES_IN_HR = 60;
	private int minutes;  //the time, stored as minutes since midnight

	//initially, set time to midnight
	public Clock()
	{
		minutes = 0;
	}
	
	//////////////////////////////////////////////////////////////
	//VARIOUS GET METHODS
	//returns hours component of the time
	public int getWholeHours()
	{
		return minutes/MINUTES_IN_HR;
	}
	
	//returns minutes component of the time
	public int getRemMins()
	{
		return minutes%MINUTES_IN_HR;
	}  
	
	
	/** returns value of time period in minutes
	 * @return value of time in minutes
	 */   
	public int getTimeInMins()
	{
		return minutes;
	}
	
	/** creates a phrase 'x hours and y mins'
	 * @return time as a phrease
	 */   
	public String getTimePhrase()
	{
		return this.getWholeHours() + " hours and  " + this.getRemMins()
		+ " minutes";
	}
	
	public String getTime12()
	{
		String time = "";
		String dayPart = "am";
		int wholeHours = this.getWholeHours();
		int minsLeft=this.getRemMins();
		if (wholeHours >=12)
			dayPart = "pm";
		if (wholeHours >12)
			wholeHours = wholeHours - 12;
		if (wholeHours < 9)
			time += "0";
		time += wholeHours + ":";
		if (minsLeft < 9)
			time += "0";
		time  += minsLeft;
		return time + dayPart;
	}
	
	public String getTime24()
	{
		String time = "";
		int wholeHours = this.getWholeHours();
		int minsLeft=this.getRemMins();
		if (wholeHours < 9)
			time += "0";
		time += wholeHours + ":";
		if (minsLeft < 9)
			time += "0";
		time  += minsLeft;
		return time;
	}
	
	/** adds amount in minutes to period of time
	 * @param m time in minutes to be added to period of time
	 */   
	public void addMinutes(int m)
	{
		minutes = minutes+m;
		notifyObservers();
	}
	
	//initialises time from hours and minutes
	public void setTime24(int hour, int min)
	{
		minutes = hour*60 + min;
		notifyObservers();
	}
	////////////////////////////////////////////////////////
	//OBSERVER PATTERN
	//SUBJECT must be able to register, remove and notify observers
	//list to hold any observers
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	//methods to register, remove and notify observers
	public void registerObserver( Observer obs)
	{
		registeredObservers.add( obs);
	}
	
	public void removeObserver( Observer obs)
	{
		registeredObservers.remove( obs);
	}
	
	public void notifyObservers()
	{
		for( Observer obs : registeredObservers)
			obs.update();
	}
	//////////////////////////////////////////////////////// 	
}
