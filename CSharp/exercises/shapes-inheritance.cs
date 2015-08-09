namespace shapes;




abstract class Shape
{

    public abstract double area();

}

class Square : Shape
{
    public static int no = 0;
    public static double area = 0;
    public static double sideLenght = 10;

    public double area()
    {
        area = sideLenght * sideLenght;
        return area;
    }

}

class Circle : Shape
{
    public static int no = 0;

}