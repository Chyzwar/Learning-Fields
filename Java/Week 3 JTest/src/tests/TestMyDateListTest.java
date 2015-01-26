package tests;

import dateUtils.*;
import static org.junit.Assert.*;

import org.junit.*;


public class TestMyDateListTest {
	
	private DateList dateList;
	
	@Before
	public void setUp() {
		dateList = new DateList();
		dateList.add(new MyDate(31,12,2009));
		dateList.add(new MyDate(31,1,2009));
		dateList.add(new MyDate(30,12,2009));
		dateList.add(new MyDate(31,12,2004));
	}
	
	//not strictly necessary since setUp starts a new list
	@After
	public void tearDown() {
		dateList = new DateList();
	}
	
	@Test
	public void testFind() {
		
	} 

}
