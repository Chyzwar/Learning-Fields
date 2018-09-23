package restaurant.orderstests;

import restaurant.orders.DishItem;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDishItem {

	DishItem dishMain, dishEqual, dishNotEqual, dishPriceNotEqual, dishNameNotEqual, dishCatNotEqual;
	
	/**
	 * Setting up the tests with one default main dish
	 */
	@Before
	public void setUp() {
		dishMain = new DishItem("Tikka Masala", 6.29, "Indian");
	}
	
	/**
	 * Testing if it returns true when dishes are equal
	 */
	@Test
	public void testEqualsTrue() {
		dishEqual = new DishItem("Tikka Masala", 6.29, "Indian");
		assertTrue(dishMain.equals(dishEqual));
	}
	
	/**
	 * Testing if it returns false if no parameters are equal
	 */
	@Test
	public void testEqualsAllFalse() {
		dishNotEqual = new DishItem("Korma", 5.50, "Chinese");
		assertFalse(dishMain.equals(dishNotEqual));
	}
	
	/**
	 * Testing if it returns false if dish names are not equal
	 */
	@Test
	public void testEqualsNamesFalse() {
		dishNameNotEqual = new DishItem("Korma", 6.29, "Indian");
		assertFalse(dishMain.equals(dishNameNotEqual));
	}
	
	/**
	 * Testing if it returns false if prices are not equal
	 */
	@Test
	public void testEqualsPricesFalse() {
		dishPriceNotEqual = new DishItem("Tikka Masala", 5.50, "Indian");
		assertFalse(dishMain.equals(dishPriceNotEqual));
	}
	
	/**
	 * Testing if it returns false if categories are not equal
	 */
	@Test
	public void testEqualsCategoriesFalse() {
		dishCatNotEqual = new DishItem("Tikka Masala", 6.29, "Chinese");
		assertFalse(dishMain.equals(dishCatNotEqual));
	}
}
