package dateUtils;
import java.util.ArrayList;

public class DateList {

	private ArrayList<MyDate> dateList;
	
	public DateList() {
		dateList = new ArrayList<MyDate>();
	}
	
	public void add(MyDate d) {
		dateList.add(d);
	}
	
	/**
	 * Returns a date object from the list that matches the input date
	 * @param ddmmyyyy date in format dd/mm/yyyy
	 * @return the matching date object from the list if found. Returns null otherwise.
	 */
	public MyDate find(String ddmmyyyy) {
		for (MyDate date : dateList) {
			if (date.getDDMMYYYY().equals (ddmmyyyy)) {
				return date;
			}
		}
		return null;
	}
		
	

}
