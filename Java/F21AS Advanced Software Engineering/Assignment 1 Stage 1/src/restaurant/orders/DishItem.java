package restaurant.orders;

/**This class is a subclass of Dish class, and represents a dish item
 * 
 * @author Stian Dalviken
 *
 */
public class DishItem extends Dish 
{
	private double price;
	private String category;
	
	/**Dish item constructor for DishItem object throw exception if params are invalid
	 * 
	 * @param dishName
	 * @param price
	 * @param category
	 */
	public DishItem(String dishName, double price, String category)
	{
		super(dishName);
		if(price <=0)
        {
	          throw new IllegalStateException(
	             "Price is invalid");
	        
        }
		if(category.length() ==0)
        {
	          throw new IllegalStateException(
	             "Cannot have blank category");
	        
        }
		this.price = price;
		this.category = category;
	}

	/**Comparing two DishOrder objects and test for equality
	 * 
	 * @param other Dish object
	 * @return returns true if objects are equal otherwise false
	 */
	public boolean equals(Dish other) 
	{
		DishItem otherItem = (DishItem) other;
		if (dishName.equals(otherItem.dishName) && price == otherItem.price && category == otherItem.category)
		{
			return true;
		}
		return false;
	}

	/**Returns the category of the DishItem object
	 * 
	 * @return category
	 */
	public String getCategory() 
	{
		return this.category;
	}
	
	/**Returns the price of the DishItem object
	 * 
	 * @return price
	 */
	public double getPrice()
	{
		return this.price;
	}
}