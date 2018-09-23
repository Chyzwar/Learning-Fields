package restaurant.orderstests;

import restaurant.orders.DishOrder;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDishOrder {

	DishOrder dishOrderMain, dishOrderEqual, dishOrderNotEqual, dishOrderTableIDNotEqual, dishOrderNameNotEqual, dishOrderQuanityNotEqual, dishOrderSequenceNotEqual;
	
	/**
	 * Setting up the tests with one default main dish order
	 */
	@Before
	public void setUp() {
		dishOrderMain = new DishOrder(1, "Tikka Masala", 3, 2);
	}

	/**
	 * Testing if it returns true when dish orders are equal
	 */
	@Test
	public void testEqualsTrue() {
		dishOrderEqual = new DishOrder(1, "Tikka Masala", 3, 2);
		assertTrue(dishOrderMain.equals(dishOrderEqual));
	}
	
	/**
	 * Testing if it returns false if no parameters are equal
	 */
	@Test
	public void testEqualsAllFalse() {
		dishOrderNotEqual = new DishOrder(5, "Korma", 5, 5);
		assertFalse(dishOrderMain.equals(dishOrderNotEqual));
	}
	
	/**
	 * Testing if it returns false if table ID's are not equal
	 */
	@Test
	public void testEqualsTableIDFalse() {
		dishOrderTableIDNotEqual = new DishOrder(5, "Tikka Masala", 3, 2);
		assertFalse(dishOrderMain.equals(dishOrderTableIDNotEqual));
	}
	
	/**
	 * Testing if it returns false if dish names are not equal
	 */
	@Test
	public void testEqualsNamesFalse() {
		dishOrderNameNotEqual = new DishOrder(1, "Korma", 3, 2);
		assertFalse(dishOrderMain.equals(dishOrderNameNotEqual));
	}
	
	/**
	 * Testing if it returns false if quantity are not equal
	 */
	@Test
	public void testEqualsQuantityFalse() {
		dishOrderQuanityNotEqual = new DishOrder(1, "Tikka Masala", 5, 2);
		assertFalse(dishOrderMain.equals(dishOrderQuanityNotEqual));
	}
	
	/**
	 * Testing if it returns false if order sequences are not equal
	 */
	@Test
	public void testEqualsOrderSequenceFalse() {
		dishOrderSequenceNotEqual = new DishOrder(1, "Tikka Masala", 3, 5);
		assertFalse(dishOrderMain.equals(dishOrderSequenceNotEqual));
	}
}
