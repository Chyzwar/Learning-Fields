using System;
using System.Collections;

namespace WebBrowser_OuterSpace.models
{
    public class HistoryController
    {
        public void addToHistory(String url, int tabID)
        {
            HistoryCollection hc = HistoryCollection.Instance;
            hc.addToHistoryPerTab(url, tabID);
        }

        public void loadHistory()
        {
            HistoryCollection hc = HistoryCollection.Instance;
            hc.readHistoryConfig();
        }

        public String getHistoryPageURL(int tabID, int deepness)
        {
 
            HistoryCollection hc = HistoryCollection.Instance;
            return hc.getHistoryURL(tabID, deepness);
        }


        public void recordHistory(String url, int tabID)
        {

            HistoryCollection hc = HistoryCollection.Instance;
            hc.writeRecordHistory(url, tabID);
        }


        public ArrayList getHistory()
        {
            HistoryCollection hc = HistoryCollection.Instance;
            return  hc.getFullhistory();

        }
    }
}
