// Example points with public fields
// From Lecture 3: C# Basics
// -----------------------------------------------------------------------------

using System;
class Point{
        // public access modifier: no restrictions on access 
	public int x;
	public int y;

        // constructor with initialisation
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Test{
	public static void Main(){
		Point point1 = new Point(5,10);
		Point point2 = new Point(20, 15);
		Console.WriteLine("Point1({0}, {1})", point1.x, point1.y);
		Console.WriteLine("Point2({0}, {1})", point2.x, point2.y);
	}
}
