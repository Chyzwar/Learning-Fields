// From "Learning C# 3.0", Jess Liberty

// Examples 21-1 to 21-5

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Book { 
  public string Title { get ; set; }
  public string Author { get ; set; }
  public string Publisher { get ; set; }
  public int Year { get ; set; }
}

public class PurchaseOrder {
  public int OrderNumber { get; set; }
  public string Title    { get; set; }
  public int Quantity    { get; set; }
}

public class Tester {

  public static void Main (string []args) {
     if (args.Length < 0) { // expect 1 args: filename
       System.Console.WriteLine("Usage: linq1 <listlen>");
     } else {
       //int n = Convert.ToInt32(args[0]);
       List<Book> booklist = new List<Book> {
	 new Book { Title = "Learning C#"
	          , Author = "Jesse Liberty"
	          , Publisher = "O'Reilly"
	          , Year = 2008
	          },
	 new Book { Title = "Programming C#"
	          , Author = "Jesse Liberty"
	          , Publisher = "O'Reilly"
	          , Year = 2008
	          },
	 new Book { Title = "Programming PHP"
	          , Author = "Rasmus Lerdorf, Kevin Tatroe"
	          , Publisher = "O'Reilly"
	          , Year = 2006
	          },
	 new Book { Title = "PHP Pocket Reference"
	          , Author = "Rasmus Lerdorf"
	          , Publisher = "O'Reilly"
	          , Year = 2008
	          },
	 new Book { Title = "Perl Cookbook"
	          , Author = "Tom Christiansen, Nathan Torkington"
	          , Publisher = "O'Reilly"
	          , Year = 2001
	          },
       };
       List<PurchaseOrder> purchaselist = new List<PurchaseOrder> {
	 new PurchaseOrder { OrderNumber = 1
	                   , Title = "Programming C#"
                           , Quantity = 2
                           }
	 ,
	 new PurchaseOrder { OrderNumber = 2
	                   , Title = "Perl Cookbook"
                           , Quantity = 1
                           }
	 ,
	 new PurchaseOrder { OrderNumber = 3
	                   , Title = "Programming C#"
                           , Quantity = 5
                           }
       };

       // non LINQ:
       Console.WriteLine("non LINQ ...");
       foreach (Book b in booklist) {
	 if (b.Author ==  "Jesse Liberty") { 
	   Console.WriteLine(b.Title + " by " + b.Author);
	 }
       }
	   
       // LINQ query: find by author
       IEnumerable<Book> resultsAuthor =
	 from b in booklist
	 where b.Author == "Jesse Liberty"
	 select b;

       Console.WriteLine("LINQ query: find by author ...");
       // process the result
       foreach (Book r in resultsAuthor) {
	 Console.WriteLine(r.Title + " by " + r.Author);
       }


       // LINQ query: avoid returning entire books; uses an anonymous type
       var resultsAuthor1 =                // NB: this needs to infer the type, which is anonymous!
	 from b in booklist
	 where b.Author == "Jesse Liberty"
	 select new { b.Title, b.Author} ; // NB: anonymous type here!

       Console.WriteLine("LINQ query: avoid returning entire books; uses an anonymous type ...");
       // process the result
       foreach (var r in resultsAuthor1) {
	 Console.WriteLine(r.Title + " by " + r.Author);
       }


       // LINQ: lambda expressions
       var resultsAuthor2 =
	 booklist.Where(bookEval => bookEval.Author == "Jesse Liberty");

       Console.WriteLine("LINQ: lambda expressions ...");
       // process the result
       foreach (var r in resultsAuthor2) {
	 Console.WriteLine(r.Title + " by " + r.Author);
       }

       // LINQ query: order by author
       var resultsAuthor3 =
	 from b in booklist
	 orderby b.Author
	 select new { b.Title, b.Author} ; // NB: anonymous type here!

       Console.WriteLine("LINQ query: order by author ...");
       // process the result
       foreach (var r in resultsAuthor3) {
	 Console.WriteLine(r.Title + " by " + r.Author);
       }

       // LINQ: join
       var resultList4 = 
         from b in booklist
         join p in purchaselist on b.Title equals p.Title
         where p.Quantity >=2
	 select new { b.Title, b.Author, p.Quantity } ; 
        
       Console.WriteLine("LINQ query: oreder by author ...");
       // process the result
       foreach (var r in resultList4) {
	 Console.WriteLine(r.Quantity + " items of " + r.Title + " by " + r.Author);
       }

     }
  }
}
