package restaurant.orders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**OrderCollection class store collection of restaurant orders in HashSet
 * Use static orderSequence for DishOrder 
 * Class have also methods specific only for OrderCollection
 * Naming convention could be better, but specification was already done
 * @author Marcin Kopacz
 *
 */


public class OrderCollection 
{
	private HashSet<DishOrder> orderSet;
	private static int orderSequence = 0;

	/**Constructor for OrderCollection object
	 * Initiate HashSet orderSet
	 */
	public OrderCollection() 
	{
		orderSet = new HashSet<DishOrder>();
	}

	/** Adds DishOrder item to HashMap 
	 * if quantity number is >10 throw Exception
	 * @param order
	 */
	public void add(DishOrder order) 
	{
		if (order.getQuantity() > 10)
		{
			throw new BadQuantityException(order.getOrderSequence());
		}
		else
		{
			orderSet.add(order);
		}
		
	}


	/** Reads file with given name, extracting line from file
	 * Blank lines are skipped
	 * Catch for missing file
	 * @param filename the name of the input file
	 */
	public void readFileOrders(String aFilename)
	{
		try
		{
			File f = new File(aFilename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine())
			{
				//read first line and process it
				String inputLine = scanner.nextLine(); 
				if (inputLine.length() != 0) 
				{//ignored if blank line
					processLineOrders(inputLine);
				}
			}
			scanner.close();

		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf)
		{
			System.out.println( aFilename + " not found ");
			System.exit(0);
		}
	}


	/**
	 * Processes line, extracts data, creates DishOrder objects
	 * adds DishItem to HashSet , 
	 * non-numeric marks and missing items
	 * Will still crash if name entered without a space
	 * @param line the line to be processed
	 */
	public void processLineOrders(String aLine) 
	{
		orderSequence = orderSequence + 1;
		try 
		{
			String parts [] = aLine.split(",");

			String tableIDString = parts[0];
			int tableID = Integer.parseInt(tableIDString);

			String dishName = parts[1];
			dishName = dishName.trim();

			String quantityString = parts[2];
			int quantity = Integer.parseInt(quantityString);

			String category = parts[2];
			category = category.trim();


			DishOrder c = new DishOrder(tableID,dishName, quantity, orderSequence);
			this.add(c);

		}

		//for these two formatting errors, ignore lines in error and try and carry on
		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) 
		{
			String error = "Number conversion error in '" + aLine + "'  - " 
					+ nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air)
		{
			String error = "Not enough items in  : '" + aLine
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
		catch (BadQuantityException dqe)
		{
			String error = dqe.getMessage();
			System.out.println(error);
		}

	}

	
	/**Take HashSet of orders, put names into list
	 * Put list into hashSet and get frequency based on that list
	 * @return string frequency report
	 */
	public String frequencyReport()  
	{
		String freqReport = "FREQUENCY REPORT" + "\n";
		
		ArrayList<String> dishNamesList = new ArrayList<String>();

		for (DishOrder d : orderSet) 
		{
			String dishName = d.getName();
			dishNamesList.add(dishName);
		}

	
		Set<String> uniqueSet = new HashSet<String>(dishNamesList);
		for (String dishName : uniqueSet) 
		{
			
			freqReport += String.format("%-35s",dishName);
			freqReport += Collections.frequency(dishNamesList, dishName);
			freqReport += "\n";
		}
		return freqReport;
	}

	
	/**Calculate frequency orders for a dish with given name
	 * @param d(dishName)
	 * @return 
	 */
	public int getFrequencyForDish(String d)
	{
	int freq = 0;
	ArrayList<String> dishNamesList = new ArrayList<String>();

	for (DishOrder p : orderSet) 
	{
		String dishName = p.getName();
		dishNamesList.add(dishName);
	}
	freq = Collections.frequency(dishNamesList,d);
	return freq;
	}
	
	
	/**Return collection of orders in HashSet
	 * @return HashSet
	 */
	public HashSet<DishOrder> getHashSetOfOrders()
	{
		return orderSet;
	}
	
}