package restaurant.orderstests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import restaurant.orders.BadQuantityException;
import restaurant.orders.DishOrder;
import restaurant.orders.OrderCollection;

public class TestOrderCollection
{
	private DishOrder dish1, dish2, dish3, dish4, dish5;
	private OrderCollection orderSetTest;

	/**
	 * Setting up the tests with one default main dish
	 */
	@Before
	public void setUp() {
		orderSetTest = new OrderCollection();
		dish1 = new DishOrder(1,"Tikka Mane", 2, 1);
		dish2 = new DishOrder(2,"Tikka Mane", 1, 2);
		dish3 = new DishOrder(1,"Tikka Hot", 2, 3);
		dish4 = new DishOrder(3,"Tikka Hot", 3 ,4);
		dish5 = new DishOrder(4,"Tikka Hot", 3, 5);
		orderSetTest.add(dish1);
		orderSetTest.add(dish2);
		orderSetTest.add(dish3);
		orderSetTest.add(dish4);
		orderSetTest.add(dish5);
	}


	/**Test if Exception BadQuantityException, 
	 * is thrown for quantity >10
	 */
	@Test
	public void invalidQuantitySupplied()
	{
		try
		{
			DishOrder dish6 = new DishOrder(4,"Tikka Hot", 12, 5);
			orderSetTest.add(dish6);
			fail("Invalid quantity supplied - should throw exception");
		}
		catch (BadQuantityException e)
		{
			assertTrue(e.getMessage().contains("5"));
		}
	}
	
	
	/**Test method getFrequencyForDish()
	 * This method for given order should return freq 
	 */
	@Test
	public void test1GetFrequencyForDish()
	{
		int test = orderSetTest.getFrequencyForDish("Tikka Hot");
		assertTrue(test == 3);
	}
	
	
	/**Test method getFrequencyForDish()
	 * check for different dishname
	 */
	@Test
	public void test2GetFrequencyForDish()
	{
		int test = orderSetTest.getFrequencyForDish("Tikka Mane");
		assertTrue(test == 2);
	}
	
	/**Test method getFrequencyForDish()
	 * check dishName frequency that is not in the list
	 */
	@Test
	public void test3GetFrequencyForDish()
	{
		int test = orderSetTest.getFrequencyForDish("Tikka");
		assertTrue(test == 0);
	}
	
	
	/**Test of frequencyReport()
	 * Check if method produce correct string
	 */
	@Test
	public void test1frequencyReport()
	{
		
		String expected ="FREQUENCY REPORT" + "\n"+
				"Tikka Hot                          3" + "\n" +
				"Tikka Mane                         2" + "\n";
														

		String actual = orderSetTest.frequencyReport();
		
		assertEquals(expected, actual);

	}
	
}
