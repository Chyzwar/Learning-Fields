using System;
using System.Collections;
using System.IO;
using System.Text;
using System.Linq;

namespace WebBrowser_OuterSpace.models
{
    /// <summary>
    /// Singleton Class holding collection of Favourities
    /// </summary>
    class FavouritesList
    {
        private static FavouritesList instance;
        // Agin I will use nested Array
        public ArrayList favouritesCollection = new ArrayList();

        private FavouritesList()
        {

        }
        /// <summary>
        /// Singleton method
        /// </summary>
        public static FavouritesList Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new FavouritesList();
                }
                return instance;
            }
        }

        /// <summary>
        /// Read Favourities from file durring startup
        /// </summary>
        public void readFavouritesConfig()
        {
            String line;
            try
            {
                //Pass the file path and file name to the StreamReader constructor
                StreamReader sr = new StreamReader("webFavourites.txt");

                //Read the first line of text
                line = sr.ReadLine();
                buildFavouritesColection(line);

                //Continue to read until you reach end of file
                while (line != null)
                {
                    //Read the next line
                    line = sr.ReadLine();
                    buildFavouritesColection(line);
                }

                //close the file
                sr.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Exception: " + e.Message);
            }
            finally
            {
                Console.WriteLine("Executing finally block.");
            }
        }

        /// <summary>
        /// Buld ip a collection base of file reading
        /// </summary>
        /// <param name="line"></param>
        private void buildFavouritesColection(string line)
        {
            string[] input = line.Split(',');
            favouritesCollection.Add(new string[2] { input[0], input[1] });
        }

        /// <summary>
        /// Getter for favourities collection
        /// </summary>
        /// <returns></returns>
        public ArrayList getfavouritesCollection()
        {
            return this.favouritesCollection;
        }


        /// <summary>
        /// Add new favourite to list and call method to update file
        /// </summary>
        /// <param name="name"></param>
        /// <param name="url"></param>
        public void addNewFav(string name, string url)
        {
            favouritesCollection.Add(new string[2] { name, url });
            updateFavouritesConfig();

        }

        private void updateFavouritesConfig()
        {
            String line;
            try
            {
                //Clear file
                System.IO.StreamWriter file = new System.IO.StreamWriter("webFavourites.txt");
                file.Write("");
                file.Close();

                StreamWriter sw = new StreamWriter("webFavourites.txt");

                foreach (string[] favorite in favouritesCollection)

                {
                    Console.WriteLine(favorite[0].ToString());
                    Console.WriteLine(favorite[1].ToString());
                    line = favorite[0].ToString() + "," + favorite[1].ToString();
                    sw.WriteLine(line);
                }
                sw.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Exception: " + e.Message);
            }
            finally
            {
                Console.WriteLine("Executing finally block.");

            }
        }

        /// <summary>
        /// Insert updated values for given favourities
        /// </summary>
        /// <param name="name"></param>
        /// <param name="url"></param>
        /// <param name="id"></param>
        internal void makeUpdateFav(string name, string url, int id)
        {   
             string[] updatedFav = new string[2] { name, url } ;
             Console.WriteLine(id);
             favouritesCollection.Insert(id, updatedFav);
             updateFavouritesConfig();

        }

        /// <summary>
        /// Insert updated values for given favourities
        /// </summary>
        /// <param name="name"></param>
        /// <param name="url"></param>
        /// <param name="id"></param>
        public void removeFav(int id)
        {
            favouritesCollection.RemoveAt(id);
            string[] updatedFav = new string[2] { "empty" , "empty" };
            favouritesCollection.Insert(id, updatedFav);
            updateFavouritesConfig();

        }


    }
}
