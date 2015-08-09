using System;
using System.Linq;
using System.Collections;

namespace WebBrowser_OuterSpace.models
{   
    /// <summary>
    /// Singleton class dealing with browsing and getting a webpages
    /// </summary>
    class WebDocumentsCache
    {
        private static WebDocumentsCache instance;
        private ArrayList cachedTabs = new ArrayList();

        /// <summary>
        /// Constructor used only in Program.cs
        /// </summary>
        private WebDocumentsCache()
        {

        }
        
        /// <summary>
        /// Function that return referecnce to this singleton
        /// </summary>
        public static WebDocumentsCache Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new WebDocumentsCache();
                }
                return instance;
            }
        }

        /// <summary>
        /// Get web document, depends on tabID it will create a new intsance of tab or use existing one
        /// </summary>
        /// <param name="url"></param>
        /// <param name="tabID"></param>
        /// <returns></returns>
        public String[] getWebDocument(String  url, int tabID)
        {
            if(cachedTabs.Capacity >= tabID+1)
            {
                WebDocumenTab cachedDocument = (WebDocumenTab)cachedTabs[tabID];
                return cachedDocument.makeRequest(url);

            }else{
                 WebDocumenTab newDocument = createNewTab(url);
                 return newDocument.makeRequest(url);
            }

        }

        /// <summary>
        /// Get content of given Browser Tab
        /// </summary>
        /// <param name="tabID"></param>
        /// <param name="homeURL"></param>
        /// <returns></returns>
        public String[] getTabDocument(int tabID, String homeURL)
        {
            if (cachedTabs.Count >= tabID + 2)
            {
                WebDocumenTab cachedDocument = (WebDocumenTab)cachedTabs[tabID];
                return cachedDocument.retrieveDocument();

            }
            else
            {   
                WebDocumenTab newDocument = createNewTab(homeURL);
                newDocument.makeRequest(homeURL).ToList().Add(homeURL);
                return  newDocument.retrieveDocument();
            }
        }
 
        /// <summary>
        /// Create new Browsertab and to ArrayList for later retrieval
        /// </summary>
        /// <param name="url"></param>
        /// <returns></returns>
        public WebDocumenTab createNewTab(String url)
        {
            WebDocumenTab newTab = new WebDocumenTab(url);
            cachedTabs.Add(newTab);
            newTab.runFetchThread();
            return newTab;

        }



    }
}
