// Example points with properties to access fields
// From Lecture 3: C# Basics
// -----------------------------------------------------------------------------

using System;
class Point
{
    // private access modifier: only visible in this class
    private int x;
    private int y;

    // using properties to get and set the contents of x and y
    // Q: is there a shorter way to write this code?
    public int PointX
    {
        get
        {
            return x;
        }
        set
        {
            this.x = value;
        }
    }
    public int PointY
    {
        get
        {
            return y;
        }
        set
        {
            this.y = value;
        }
    }

    // constructor with initialisation
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
class Test
{
    public static void Main()
    {
        Point point1 = new Point(5, 10);
        Point point2 = new Point(20, 15);
        // this doesn't work, because x and y are private in the Point class; try it!
        // Console.WriteLine("Point1({0}, {1})", point1.x, point1.y);
        // Console.WriteLine("Point2({0}, {1})", point2.x, point2.y);
        Console.WriteLine("Point1({0}, {1})", point1.PointX, point1.PointY);
        Console.WriteLine("Point2({0}, {1})", point2.PointX, point2.PointY);
        point1.PointX += 10;
        Console.WriteLine("Increasing Point1's x val by 10 ...");
        Console.WriteLine("Point1({0}, {1})", point1.PointX, point1.PointY);

    }
}
