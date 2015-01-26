// Example using private fields with explicit get function
// From Lecture 3: C# Basics
// -----------------------------------------------------------------------------

using System;
class Point{
        // private access modifier: only visible in this class
	private int x;
	private int y;

        // constructor with initialisation
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
        // methods for read-access to these values
	public int GetX() {return(x);}
	public int GetY() {return(y);}
}
class Test{
	public static void Main(){
	        // NB: because we don't have methods for write-access, 
                //     x and y won't change after initialisation, i.e. they are immutable
		Point point1 = new Point(5,10);
		Point point2 = new Point(20, 15);
		Console.WriteLine("Point1({0}, {1})", point1.GetX(), point1.GetY());
		Console.WriteLine("Point2({0}, {1})", point2.GetX(), point2.GetY());
		// NB: this doesn't work, because x and y are private in the Point class; try it!
		// Console.WriteLine("Point1({0}, {1})", point1.x, point1.y);
		// Console.WriteLine("Point2({0}, {1})", point2.x, point2.y);
	}
}
