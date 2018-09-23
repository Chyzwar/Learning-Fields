package restaurant.orders;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JOptionPane;


/**The manager class in the interface class that creates OrderCollection and MenuSet
 * and run the program via the run method to take a user input , show summary, read and write file
 * @author Kamontorn, Marcin, Stien
 */
public class ManagerClass {
	private OrderCollection allOrders;
	private MenuSet fullMenu;
	private String report;
	private static int userSelection;
	private double grandTotal;

	/**Constructor for object ManagerClass 
	 */
	public ManagerClass(){
		allOrders = new OrderCollection();
		fullMenu = new MenuSet();
	}
	/**Initialise the program and start a pop up for a user selection
	 * Starts GUI based on user selection
	 * Calls methods needed to create report
	 * Create full report and call method to write it to file
	 */
	public void run()
	{

        String message = "";
		try{
		allOrders.readFileOrders("tableOrder.csv");
		fullMenu.readFileMenu("restaurantMenu.csv");
		getUserSelection();
		GrandTotalGUI SumGUI = new GrandTotalGUI(singleTableSummary(userSelection));
		SumGUI.setVisible(true);

		report = "Full report of Restaurant orders and menu" + "\n" + "\n";
		report += fullMenu.getMenuByCategory() + "\n";
		report += allOrders.frequencyReport()+ "\n";
		report += getNotOrderedItems() +"\n";
		report += getAllTablesSummary() + "\n";
		report += getBestInCategory()+ "\n";
		report += getAvgPerCategory() + "\n";
		report += getAvgPerHeadPerTable() + "\n"; 
		writeToFile("restaurantReport.txt",report);
		}
        //if params invalid
		catch (IllegalStateException e){ 
        	message = e.getMessage() + "\nDetails not added";
        }
		System.out.println(message);

	}


	/**Write report to a file
	 * @param filename the name of the file to be written to
	 * @param report the text to be written to the file
	 */
	public void writeToFile(String filename, String report) 
	{
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			fw.write(report);
			fw.close();
		}

		catch (FileNotFoundException fnf){
			System.out.println(filename + " not found ");
			System.exit(0);
		}

		catch (IOException ioe){
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	
	/**Create a report of all tables in the restaurant by calling singleTableSummary method
	 * @return String report of all tables with grand total.
	 */
	public String getAllTablesSummary() {
		String allTablesSummary = "TABLE SUMMARY" + "\n"+"=============" +"\n";
		grandTotal = 0.00;
		for(int index=0; index<6 ; index++)
		{
			allTablesSummary += singleTableSummary(index+1) + "\n";
		}
		allTablesSummary += String.format("%60s", "=====")+ "\n" ;
		allTablesSummary += "Grand Total : " + String.format("%46.2f",grandTotal) + "\n";
		allTablesSummary += String.format("%60s", "=====")+ "\n" ;
		return allTablesSummary;

	}

	/**Show GUI to ask a user to input a table number
	 * Catch when the user does not input a number
	 */
	public void getUserSelection() 
	{
		try{
			String input = JOptionPane.showInputDialog(null, "Select table calculate summary");
			userSelection = Integer.valueOf(input.trim());

		}
		//catch when trying to convert String to integer
		catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Please input a number","Invalid input", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}


	}

	
	/**Create a table summary report by getting table number
	 * @param aUserSelection is a table number from a user input
	 * @return report of selected table
	 */
	public String singleTableSummary(int aUserSelection) 
	{
		TreeSet<DishItem> menuSet = fullMenu.getTreeSetOfMenu();
		String singleTableSummary ="TABLE " + aUserSelection + "\n";
		Set<DishOrder> dishOrder = new TreeSet<DishOrder>();
		double total = 0.0;
		for (DishOrder o :allOrders.getHashSetOfOrders() )
		{

			if (o.getTableID() == aUserSelection)
			{
				//add dishOrder of a selected table into the set dishOrder
				dishOrder.add(o);
			}
		}
		for (DishOrder d : dishOrder) 
		{
			//create summary report
			singleTableSummary += String.format("%-40s", d.getName()) + " ";
			singleTableSummary += String.format("%-4d", d.getQuantity()) + "*";
			for(DishItem i : menuSet)
			{
				if(i.getName().equals(d.getName()))
				{
					singleTableSummary += String.format("%6.2f", i.getPrice());
					double price = d.getQuantity() * i.getPrice();
					// sum to each dish in to total
					total += price; 
					singleTableSummary += " = " + String.format("%5.2f", price) +"\n";

				}
			}
		}

		singleTableSummary +=String.format("%60s", "=====")+ "\n" ;
		//calculate discount and total
		ArrayList<Double> totals = getTotal(total);
		singleTableSummary += "Total for this table :" + String.format("%38.2f", total) +"\n";
		singleTableSummary += "Discount :" + String.format("%50.2f", totals.get(0)) +"\n";
		singleTableSummary += "Discounted Total :" + String.format("%42.2f", totals.get(1)) +"\n";
		
		//if there is no order on the selected table report no order
		if(dishOrder.size() == 0)
		{
			singleTableSummary = "There is no order in Table " + aUserSelection;
		}

		return singleTableSummary;
	}
	
	
	/**Calculate discounted total if the total is more than 150 but less than 300 discount equals to 5%
	 * if total is more than 300 discount equals to 10%
	 * @param total before discount
	 * @return report of total, discounted and discounted total
	 */
	public ArrayList<Double> getTotal(double total)
	{
		double discount = 0.0;
		double discountedTotal = 0.0;
		ArrayList<Double> discountTotal = new ArrayList<Double>();
		if(total >=300)
		{
			discount = total * 10/100;
			discountedTotal = total - discount;
		}
		else if (total >150)
		{
			discount = total * 5/100;
			discountedTotal = total - discount;
		}
		else
		{
			discountedTotal = total;
		}
		
		discountTotal.add(discount);
		discountTotal.add(discountedTotal);
		grandTotal +=discountedTotal;
		return discountTotal;
	}


	/**Finds dished not ordered,
	 * Create to sets of unique names from both list
	 * use method removeAll 
	 * @return String contain list.
	 */
	public String getNotOrderedItems()
	{
		TreeSet<DishItem> menuSet = fullMenu.getTreeSetOfMenu();
		HashSet<DishOrder> 	allOrd = allOrders.getHashSetOfOrders();
		String report = "DISHES NOT ORDERED" + "\n";
		Set<String> orderedDishNames = new TreeSet<String>();
		Set<String> menuDishNames = new TreeSet<String>();

		for (DishOrder d : allOrd)
		{	
			orderedDishNames.add(d.getName());
		}

		for(DishItem a : menuSet)
		{
			menuDishNames.add(a.getName());
		}

		menuDishNames.removeAll(orderedDishNames);
		for(String r : menuDishNames)
		{
			report += r +"\n";
		}

		return report;
	}

	
	/**Create a TreeMap where categories are keys and dishes are values
	 * @return TreeMap of categories to dishes
	 */
	public TreeMap<String,ArrayList<String>> getTreeMapForCategories()
	{
		TreeSet<DishItem> menuSet = fullMenu.getTreeSetOfMenu();
		Set<String> unique = fullMenu.getUniqueCategories();
		TreeMap<String,ArrayList<String>> dishNamesListByCat = new TreeMap<String,ArrayList<String>>();
		
		//iterate in set of category names
		for (String a : unique)
		{
			//iterate in menuSet
			for (DishItem d : menuSet)
			{ 
				//select DishItems in choose category and insert them to TreeMap
				if(a.equals(d.getCategory()))
				{
					ArrayList<String> byCategory = dishNamesListByCat.get(a);
					if (byCategory == null) 
					{
						byCategory = new ArrayList<String>();
						dishNamesListByCat.put(a, byCategory);
					}
					byCategory.add(d.getName());
				}	
			}
		}
		return dishNamesListByCat;
	}

	
	/**Finds best selling dishes in each category
	 * Use getTreeMapForCategories and processes it
	 * @return String contain summary
	 */
	public String getBestInCategory()
	{
		@SuppressWarnings("unused")
		HashSet<DishOrder> 	allOrd = allOrders.getHashSetOfOrders();
		TreeMap<String,ArrayList<String>> dishNamesListByCat = getTreeMapForCategories();
		TreeMap<String,ArrayList<String>> bestDishesInCat = new TreeMap<String,ArrayList<String>>();
		String bestSummary = "BEST IN CATEGORY \r\n";
		String winnerDish ="";


		for(Map.Entry<String,ArrayList<String>> entry : dishNamesListByCat.entrySet()) 
		{
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
			int freq = 0;
			for(String  a : value)
			{
				if(freq < allOrders.getFrequencyForDish(a))
				{
					freq = allOrders.getFrequencyForDish(a);
					winnerDish = a;

					ArrayList<String> list = bestDishesInCat.get(key);
					if (list == null) 
					{
						list = new ArrayList<String>();
						list.add(0, winnerDish);
						list.add(1, Integer.toString(freq));
					}
					list.set(0, winnerDish);
					list.set(1, Integer.toString(freq));
					bestDishesInCat.put(key, list);
				}
			}
		}


		for(Map.Entry<String,ArrayList<String>> entry : bestDishesInCat.entrySet()) 
		{
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
			bestSummary +=(key + " => " + value) + "\n";  
		}
		return bestSummary;
	}

	
	/**Calculating average spend of each table for each category
	 *Using getTreeMapForCategories method to get categories and dishes then map with dishItem to get price
	 *then get a frequency for each dish. Finally, sum and calculate them.
	 * @return String containing report of average spend per category per table
	 */
	public String getAvgPerCategory()
	{
		TreeSet<DishItem> menuSet = fullMenu.getTreeSetOfMenu();
		TreeMap<String,ArrayList<String>> dishNamesListByCat = getTreeMapForCategories();
		String avgPerCat = "Average spend per category per table " + "\n";
		 avgPerCat += "==================================== " + "\n";
		 int table = 5;
		for(Map.Entry<String,ArrayList<String>> entry : dishNamesListByCat.entrySet()) 
		{
			double price = 0.0;
			double totalPerCat = 0.0;
			String category = entry.getKey();
			ArrayList<String> dishList = entry.getValue();
			for(String d : dishList)
			{
				int freq = allOrders.getFrequencyForDish(d);
				for(DishItem i : menuSet)
				{
					if(i.getName().equals(d))
					{
						price = i.getPrice();
					}
				}
				double totalPerDish = freq*price ;
				totalPerCat +=totalPerDish;
			}
			
			totalPerCat = totalPerCat/table;
			avgPerCat += category + " : " + totalPerCat+ "\n";
			
			
		}
		return avgPerCat;
	}
	
	
	/**Iterates through the table with the table number given as input parameter,
	 * iterates through the spend on the table to get a total price, then gets the
	 * average spend per head from the getTotalAvg method, and returns a string with
	 * table number and average spend per head
	 * 
	 * @param table number of table to be calculated
	 * @return a String containing table number with average spend
	 */
	public String getSingleTableAvg(int table)
	{
		TreeSet<DishItem> menuSet = fullMenu.getTreeSetOfMenu();
		String singleTableAvg ="Table " + table + ": ";
		Set<DishOrder> dishOrder = new TreeSet<DishOrder>();
		double total = 0.0;
		for (DishOrder o :allOrders.getHashSetOfOrders() )
		{
			if (o.getTableID() == table)
			{
				dishOrder.add(o);
			}
		}
		for (DishOrder d : dishOrder)
		{
			for(DishItem i : menuSet)
			{
				if(i.getName().equals(d.getName()))
				{
					double price = d.getQuantity() * i.getPrice();
					total += price;
				}
			}
		}
		
		singleTableAvg += String.format("%.2f", getTotalAvg(total));
		
		return singleTableAvg;
	}
	
	
	/**Calculates eventual discount basted on input parameter, and returns the value divided on 4,
	 * as we assume there are 4 people per table
	 * 
	 * @param total sum to calculate eventual discount
	 * @return the total including discount, divided on 4, as we assume there are 4 people on the table
	 */
	public Double getTotalAvg(double total)
	{
		double discount = 0.0;
		double discountedTotal = 0.0;
		if(total >300)
		{
			discount = total * 10/100;
			discountedTotal = total - discount;
		}
		else if (total >150)
		{
			discount = total * 5/100;
			discountedTotal = total - discount;
		}
		else
		{
			discountedTotal = total;
		}
		
		discountedTotal = discountedTotal/4;
		
		return discountedTotal;
	}
	
	
	/**Iterates through each table to find the average spend per head per table
	 * 
	 * @return String with average spend per head per table 
	 */
	public String getAvgPerHeadPerTable()
	{
		String avgPerHeadPerTable = "AVERAGE SPEND PER HEAD PER TABLE" + "\n"+"================================" +"\n";

		for(int index=1; index<6 ; index++)
		{
			avgPerHeadPerTable += getSingleTableAvg(index) + "\n";
		}

		return avgPerHeadPerTable;
	}
}
