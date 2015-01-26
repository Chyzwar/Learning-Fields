public class Test
{
    private int x;
    private int y;

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
}

public class Runny
{
    public static void Main ()
    {

        Test test1 = new Test();

        test1.PointX = 1;

        System.Console.WriteLine(test1.PointX);

    }
}