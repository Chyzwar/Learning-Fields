// simple ADO.NET example of database access (using MySQL backend)
using System;
using System.Data;
using MySql.Data.MySqlClient;
 
public class Test
{
    public static void Main(string[] args)
    {
      string connectionString =
          "Server=anubis;" +
          "Database=test;" +
          "User ID=myid;" +
          "Password=mypwd;" +
          "Pooling=false";

       MySqlConnection dbcon;
       Console.WriteLine("Trying to connect to '"+connectionString+"'...");
       try {
	 dbcon = new MySqlConnection(connectionString);
	 dbcon.Open();
       }
       catch (MySql.Data.MySqlClient.MySqlException ex)
	{ // for error codes see: http://navody.kongo.sk/mysql-5/connectors-apis.html
	  switch (ex.Number) {
	  case 0: Console.WriteLine("Error 0: Cannot connect to server"); break;
	  case 1042: Console.WriteLine("Error 1042: ER_BAD_HOST_ERROR: cannot connect to host"); break;
	  case 1045: Console.WriteLine("Error 1045: ER_ACCESS_DENIED_ERROR: cannot log in"); break;
	  default:  Console.WriteLine("Unknown error number "+ex.Number); break;
	  }
	  Console.WriteLine(ex.Message);
	}

       MySqlCommand dbcmd = dbcon.CreateCommand();
       // requires a table of authors like this
       //        CREATE TABLE authors (
       //          A_ID MEDIUMINT,
       //          A_FNAME VARCHAR(80),
       //          A_LNAME VARCHAR(80));
       string sql =
           "SELECT A_ID, A_FNAME, A_LNAME " +
           "FROM authors";
       dbcmd.CommandText = sql;
       MySqlDataReader reader = dbcmd.ExecuteReader();
       while(reader.Read()) {
            string FirstName = (string) reader["A_FNAME"];
            string LastName = (string) reader["A_LNAME"];
            Console.WriteLine("Name: " + FirstName + " " + LastName);
       }
       // clean up
       reader.Close();
       reader = null;
       dbcmd.Dispose();
       dbcmd = null;
       dbcon.Close();
       dbcon = null;
    }
}