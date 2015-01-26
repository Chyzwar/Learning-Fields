// Example from Lecture 7: Streams
// remove all punctuation symbols from a text file

using System;
using System.IO;
using System.Collections;
using System.Collections.Generic;

public class DotKill
{
    // main worker function, removing all punctuation symbols in file
    public static string removePunctuation(string file)
    {
        string file0 = file + "0";

        if (File.Exists(file))
        {
            using (StreamReader sr = new StreamReader(file))      // open file
            {
                using (StreamWriter sw = new StreamWriter(file0))   // open file
                {
                    string str = "";
                    string str0  = "";
                    while ((str = sr.ReadLine()) != null) // iterate over all lines
                    {
                        str0 = "";
                        foreach (char c in str)
                        {
                            if (Char.IsPunctuation(c))
                            {
                                // nothing
                            }
                            else
                            {
                                str0 += c;
                            }
                        }
                        sw.WriteLine(str0.ToLower());
                    }
                }
            }
        }
        return file0;
    }

    public static void Main (string[] args)
    {
        if (args.Length != 1)   // expect 1 args: filename
        {
            System.Console.WriteLine("Usage: files4 <filename>");
        }
        else
        {
            string file = args[0];
            DotKill.removePunctuation(file);
            System.Console.WriteLine("Result written to {0}0", file);
        }
    }
}