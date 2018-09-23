package restaurant.orders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Set;



import restaurant.orders.DishItem;

public class MenuSet 
{
	private TreeSet<DishItem> menuSet;
	private Set<String> uniqueCategories;

	/**
	 * 
	 */
	public MenuSet() 
	{
		menuSet = new TreeSet<DishItem>();
		uniqueCategories = new TreeSet<String>();
	}


	/** Add DishItem to TreeSet 
	 * @param item 
	 */
	public void add(DishItem item) throws DuplicateNameException
	{
		String name= item.getName();
		if (findByname(name) != null)
		{
			//menuSet cannot have two DishItems with same name
			throw new DuplicateNameException(name);
		}
		else
		{
			menuSet.add(item);
		}

	}

	/**Search menuSet for dishItem with given name
	 * Return this dish otherwise return null
	 * @param name
	 * @return
	 */
	public DishItem findByname(String name)
	{
		for(DishItem s : menuSet)
		{
			if(s.getName().equals(name))
			{
				return s;
			}
		}
		return null;

	}




	public void add(String category)
	{
		uniqueCategories.add(category);
	}

	/** Reads file with given name, extracting line from file
	 * Blank lines are skipped
	 * Catch for missing file
	 * @param filename the name of the input file
	 */
	public void readFileMenu(String aFilename)
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
					processLineMenu(inputLine);
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
	 * Processes line, extracts data, creates DishItem object
	 * adds DishItem to TreeSet, category to Set
	 * non-numeric marks and missing items
	 * Will still crash if name entered without a space
	 * @param line the line to be processed
	 */
	public void processLineMenu(String aLine) 
	{
		try 
		{
			String parts [] = aLine.split(",");
			String dishName = parts[0];
			dishName = dishName.trim();

			String priceString = parts[1];
			double price = Double.parseDouble(priceString);

			String category = parts[2];
			category = category.trim();


			DishItem c = new DishItem(dishName,price, category);
			this.add(c);
			this.add(category);
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
		catch (DuplicateNameException dne)
		{
			String error = "This dish name is already on list  : '" + aLine;
			System.out.println(error);

		}


	}



	public TreeSet<DishItem> getTreeSetOfMenu()
	{
		return menuSet;
	}

	/**
	 * Iterates through each category in the uniqueCategory TreeSet and adds each dish to the 
	 * respective category by adding (concatenate) the name of the dish to the menuByCat String
	 * 
	 * @return String with all dishes listed by categories
	 */
	public String getMenuByCategory() 
	{
		String menuByCat = "MENU BY CATEGORY:\n";

		for (String category : uniqueCategories)
		{
			menuByCat += "\n" + category.toString() + "s\n";
			menuByCat += "======================\n";

			for(DishItem dish : menuSet)
			{
				if (category.toString().equals(dish.getCategory())) {
					menuByCat += dish.dishName + "\n";
				}
			}

			menuByCat += "---------------------\n";
		}

		return menuByCat;
	}

	/**get categories in menu as set of string
	 * @return Set 
	 */
	public TreeSet<String> getUniqueCategories()
	{
		return (TreeSet<String>) uniqueCategories;
	}

}