package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import dateUtils.*;


	public class TestMyDate {

	/*
	 * Tests method to return date in format dd/mm/yyyy
	 * numbers < 10 need a leading 0 for day and month
	 */
	@Test
	public void testGetDDMMYYYY() {
		 String expected1 = "31/12/2007";
		 String message1 = "Failed for d = 31, m = 12\n";
		 MyDate date1 = new MyDate(31,12,2007);
	     String actual1 = date1.getDDMMYYYY();
		 assertEquals(message1, expected1, actual1);

		 String expected2 = "01/01/2007";
		 String message2 =  "Failed for d = 1, m = 1\n";
		 MyDate date2 = new MyDate(1,1,2007);
	     String actual2 = date2.getDDMMYYYY();
		 assertEquals(message2, expected2, actual2);
	}
	
	@Test
	public void testIsThisCentury() {
	}
	
	/*
	 * tests that 2 dates are equal. 
	 * Test all components diff, 2 same, all same
	 */
	@Test
	public void testEquals() {	 
	}
	
	@Test
	public void testCompareTo() {
	}
	

	/**
	 * tests that exception is thrown for invalid dates
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public  void invalidDateSupplied1() {
		MyDate date1 = new MyDate(0,1,2009);
	}
	
	@Test
	public  void invalidDateSupplied2() {
		try {
			MyDate date1 = new MyDate(40,1,2009);
			fail("Invalid date supplied - should throw exception");
		}
		catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("40"));
		}
	}

}
