package restaurant.orders;


/**This Subclass of Dish class is representing dish orders.
 * Class have also methods specific only for DishOrder
 * Naming convention could be better, but specification was already done
 * @author Marcin Kopacz
 *
 */
public class DishOrder extends Dish {
	private int tableID;
	private int quantity;
	private Integer orderSequence;


	/**Constructor for DishOrder object throw exception if params are invalid
	 * 
	 * @param dishName - name of dish
	 * @param tableID - integer : id of table 
	 * @param quantity - number of dishes in one order
	 * @param orderSequence - sequence number of order
	 */
	public DishOrder(int tableID, String dishName, int quantity, int orderSequence)
	{
		super(dishName);
		if(tableID <=0)
        {
	          throw new IllegalStateException(
	             "table id is invalid");
	        
        }
		if(quantity <=0)
        {
	          throw new IllegalStateException(
	             "quantity is invalid");
	        
        }	
		this.tableID = tableID;
		this.quantity = quantity;
		this.orderSequence =orderSequence;

	}



	/**Compare two DishOrder object and test for equality
	 * 
	 * @param other object with is DishOrder 
	 * @return true if objects are equal otherwise false
	 */
	public boolean equals(Dish other) 
	{
		if(other instanceof DishOrder)
		{
			DishOrder otherOrder = (DishOrder) other;
			if (dishName.equals(otherOrder.dishName) && orderSequence == otherOrder.orderSequence && tableID == otherOrder.tableID && quantity == otherOrder.quantity)
			{
				return true;
			}
		}
		return false;
	}

	
	/**Return tableID of DishOrder object
	 * @return tableID 
	 */
	public int getTableID() 
	{
		return this.tableID;
	}


	/**Return quantity of DishOrder object
	 * @return quantity
	 */
	public int getQuantity() 
	{
		return this.quantity;
	}

	/**hasCode function on orderSequence
	 * This create index to access elements in HashSet
	 */
	public int hashCode() 
	{
		return orderSequence.hashCode();
	}
	
	
	/**Return orderSequence of DishOrder object
	 * @return orderSequence
	 */
	public int getOrderSequence()
	{
		return this.orderSequence;
	}
	
	
}