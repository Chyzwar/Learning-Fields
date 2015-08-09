using System;
using System.IO;
using System.Text;

namespace WebBrowser_OuterSpace.models
{   
    /// <summary>
    /// Singleton that control settings of home page
    /// </summary>
    class HomePageConfig
    {
        public String homePageURL { get; set; }
        private static HomePageConfig instance;

        private HomePageConfig()
        {

        }
        /// <summary>
        /// I am possibly overusing Singleton pattern but It make me write this application faster
        /// </summary>
        public static HomePageConfig Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new HomePageConfig();
                }
                return instance;
            }
        }

        /// <summary>
        /// Read from file home page settings
        /// </summary>
        public void readConfig()
        {
            try
            {
                StreamReader sr = new StreamReader("homePageConfig.txt");

                //Read the first line of text where url of home page is
                homePageURL = sr.ReadLine();

                //Close the file
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
        /// Clear file and write new address of home page
        /// </summary>
        /// <param name="homeURL"></param>
        public void writeHomeConfig(String homeURL)
        {
            homePageURL = homeURL;
            try
            {
                //Clear file, clear last homepage settings
                System.IO.StreamWriter file = new System.IO.StreamWriter("homePageConfig.txt");
                file.Write("");
                file.Close();

                //Open the File and write ne home page
                StreamWriter sw = new StreamWriter("homePageConfig.txt", true, Encoding.ASCII);
                sw.WriteLine(homeURL);
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
    }
}
