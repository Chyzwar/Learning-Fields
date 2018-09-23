package dateUtils;

public class MyDate {
	private int day;
	private int month;
	private int year;  //should be 4 figures
	
	public MyDate(int d, int m, int y){
		if (d<1 || d >31 )
			throw new IllegalArgumentException("Day" + d);
		day = d;
		month = m;
		year = y;
	}

	public String getDDMMYYYY() {
		 return day + "/" + month + "/" + year;
	}
	
	public boolean isThisCentury() {
		return false;
	}
	
	public boolean equals (MyDate other) {
		return true;
	}
	
	//returns 0 if dates equal, -1 if this date < other date
	//returns +1 if this date after other date
	public int compareTo(MyDate other) {
		return 100;	
	}
}
