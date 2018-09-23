package restaurant.orders;

/**Dish Class is representing dish
 * @author Kamontorn Khamrun
 *
 */
public abstract class Dish implements Comparable<Dish> 
{
	protected String dishName;

	/**Constructor for Dish throw exception if params are invalid
	 * @param dishName
	 */
	public Dish(String dishName)
	{
		if(dishName.length() ==0)
        {
	          throw new IllegalStateException(
	             "Cannot have black dish name");
	        
        }
		this.dishName = dishName;
	}

	/** This is an abstract class to compare object Dish
	 * @param other
	 * @return true if objects are equal otherwise false
	 */
	public abstract boolean equals(Dish other);

	
	/** For comparable
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Dish other)
	{
		return  dishName.compareTo(other.getName());
		
	}
	/**getName method for getting dishName
	 * @return dishName
	 */
	public String getName() 
	{
		return dishName;
	}
}