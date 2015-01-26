using System;
using System.Collections;
using System.IO;
using System.Text;

namespace WebBrowser_OuterSpace.models
{
    /// <summary>
    /// Manage history of Browser
    /// </summary>
    class HistoryCollection
    {
        /// <summary>
        /// Nested ArrayList will represent history for each tab
        /// HashMaps could be possible a bit better but this will do
        /// </summary>
        private ArrayList historyCollection = new ArrayList();

        private static HistoryCollection instance;

        private HistoryCollection()
        {

        }
        /// <summary>
        /// Singleton method
        /// </summary>
        public static HistoryCollection Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new HistoryCollection();
                }
                return instance;
            }
        }
        /// <summary>
        /// Add new entry to Browser history
        /// </summary>
        /// <param name="url"></param>
        /// <param name="tabID"></param>
        public void addToHistoryPerTab(String url, int tabID)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Load history from file on startup
        /// It is used in Program.cs
        /// </summary>
        public void readHistoryConfig()
        {
            String line;
            try
            {
                //Pass the file path and file name to the StreamReader constructor
                StreamReader sr = new StreamReader("webHistory.txt");

                //Read the first line of text
                line = sr.ReadLine();
                buildHistoryColection(line);

                //Continue to read until you reach end of file
                while (line != null)
                {
                    buildHistoryColection(line);
                    //Read the next line
                    line = sr.ReadLine();
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
        /// Helper method for ReadHistoryConfig
        /// Pupulate ArrayLists
        /// </summary>
        /// <param name="line"></param>
        private void buildHistoryColection(String line)
        {
            string[] input = line.Split(',');
            int tabID = 0;

            try { 
                tabID = Convert.ToInt32(input[0]);
            }
            catch (FormatException e)
            {
                Console.WriteLine(e.Message);
            }

            //Init I set limitation on maximum 25 tabs. above that history will be not holded
            for (int i = 0; i < 25; i++)
			{
			 historyCollection.Add(new ArrayList());
			}

            ArrayList tabIDArrayList = (ArrayList)historyCollection[tabID];
            tabIDArrayList.Add(input[1]);

        }

        /// <summary>
        /// Write new record to file and add entry to History List in heap
        /// </summary>
        /// <param name="url"></param>
        /// <param name="tabID"></param>
        public void writeRecordHistory(string url, int tabID)
        {

            string line = tabID.ToString() + "," + url;
            ArrayList tabIDArrayList = (ArrayList)historyCollection[tabID];
            tabIDArrayList.Add(url);

            try{
                StreamWriter sw = new StreamWriter("webHistory.txt", true, Encoding.ASCII);
                sw.WriteLine(line);
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
        /// Get Historical URL for next and prev functionality
        /// Use deepness to manage wiith history link per tab to retrieve
        /// </summary>
        /// <param name="tabID"></param>
        /// <param name="deepness"></param>
        /// <returns></returns>
        public String getHistoryURL(int tabID, int deepness){

            ArrayList tabIDArrayList = (ArrayList)historyCollection[tabID];
            int ArrayLenght = tabIDArrayList.Count;
            if(ArrayLenght > (-deepness) && deepness < 0 ){
                int index = ArrayLenght + (deepness - 1);
                return tabIDArrayList[index].ToString();
            }
            return "No history for this index";
        }

    
        /// <summary>
        /// Flaten Historry nested Array to simple Array of links
        /// </summary>
        /// <returns></returns>
        public ArrayList getFullhistory()
        {
            ArrayList totalHistory = new ArrayList();

            foreach (ArrayList perTabHistory in historyCollection)
            {
                foreach (var singleItem in perTabHistory)
                {
                    totalHistory.Add(singleItem);
                }
            }
            return totalHistory;
              
        }
    }
}
