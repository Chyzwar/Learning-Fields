using System;
using WebBrowser_OuterSpace.models;

namespace WebBrowser_OuterSpace.controllers
{
    /// <summary>
    /// Controller Class resposible for making request and managing browser tabs
    /// </summary>
    public class BrowsingController
    {

        /// <summary>
        /// Get a Web page based on URL and TabID
        /// </summary>
        /// <param name="tabID"></param>
        /// <param name="urlAdress"></param>
        /// <returns></returns>
        public String[] getWebPage(int tabID , String urlAdress)
        {
            WebDocumentsCache cache = WebDocumentsCache.Instance;
            return cache.getWebDocument(urlAdress, tabID); 
        }


        /// <summary>
        ///  Get tab content if possible if not will fetch home Page
        /// </summary>
        /// <param name="tabID"></param>
        /// <param name="homeURL"></param>
        /// <returns></returns>
        public String[] getTabContent(int tabID, String homeURL)
        {
            WebDocumentsCache cache = WebDocumentsCache.Instance;
            return cache.getTabDocument(tabID,homeURL);
        }


        /// <summary>
        /// Create new tab. It will create new object of class WebDocument
        /// </summary>
        public void createNewTab(String homeURL)
        {
               
             WebDocumentsCache cache = WebDocumentsCache.Instance;
             cache.createNewTab(homeURL);

        }


    }
}