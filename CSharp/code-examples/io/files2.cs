// example from Lecture 7 on Streams

using System;
using System.IO;

public class FileReadWrite
{
    public static void Main()
    {
        // a safe way of writing to a file
        try
        {
            StreamWriter sw = new StreamWriter("test.txt");
            sw.WriteLine("Hello World!");
            sw.Close();

            // append more text to the file
            StreamWriter sw1 = new StreamWriter("test.txt", true);
            sw1.WriteLine("Hello World again!");
            sw1.Close();
        }
        catch (IOException ex)
        {
            Console.WriteLine(ex.Message);
        }

        // std iteration over the contents of a file
        StreamReader sr = new StreamReader("test.txt");
        string inValue = "";
        while ((inValue = sr.ReadLine()) != null)
            Console.WriteLine(inValue);
    }
}