package restaurant.orderstests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import restaurant.orders.ManagerClass;

public class TestManagerClass {
	ManagerClass myManager;

	/**
	 * Set up ManagerClass
	 */
	@Before
	public void setUp() {
		 myManager = new ManagerClass();
	}

	

	
	/**Test getTotal to calculate the discount and discounted Total with input Total
	 * getTotal will return an ArrayList with discount and discounted total
	 * If total is more than 150 but less than 300  apply discount 5%
	 * If total is equal to and more than 300 apply discount 10%
	 * otherwise no discount
	 */
	@Test
	public void testEquals() {
		ArrayList<Double> testDiscountTotal1 = new ArrayList<Double>();
		testDiscountTotal1.add(32.0);
		testDiscountTotal1.add(288.0);
		//For total 320 should have discount = 32 and discounted total = 288
		assertEquals("Total 320 must have 10 % discount", testDiscountTotal1, myManager.getTotal(320));
		
		ArrayList<Double> testDiscountTotal2 = new ArrayList<Double>();
		testDiscountTotal2.add(20.0);
		testDiscountTotal2.add(180.0);
		//test return false because discount in not 10% if less than 300
		assertFalse(testDiscountTotal2.equals(myManager.getTotal(200)));
		
		ArrayList<Double> testDiscountTotal3 = new ArrayList<Double>();
		testDiscountTotal3.add(0.0);
		testDiscountTotal3.add(100.0);
		//test return true because total = 100 will have no discount
		assertTrue(testDiscountTotal3.equals(myManager.getTotal(100)));
		
		ArrayList<Double> testDiscountTotal4 = new ArrayList<Double>();
		testDiscountTotal4.add(8.0);
		testDiscountTotal4.add(152.0);
		//test return true because total = 160 will have 5% discount
		assertTrue(testDiscountTotal4.equals(myManager.getTotal(160)));
		
	}
	
	
	
}
