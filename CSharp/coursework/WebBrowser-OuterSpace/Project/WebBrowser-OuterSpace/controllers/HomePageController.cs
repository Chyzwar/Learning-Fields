using System;
using WebBrowser_OuterSpace.models;

namespace WebBrowser_OuterSpace.controllers
{
    /// <summary>
    /// Controller for HomePage Settings
    /// </summary>
    public class HomePageController
    {

        /// <summary>
        /// Read up a config on application load
        /// </summary>
        public void setUpHomePageConfig()
        {
            HomePageConfig hpc = HomePageConfig.Instance;
            hpc.readConfig();
        }

        /// <summary>
        /// Get Currently Selected home page
        /// </summary>
        /// <returns></returns>
        public String getHomePageURL(){

            HomePageConfig hpc = HomePageConfig.Instance;
            return hpc.homePageURL;
        }



        /// <summary>
        /// Save new home page address
        /// </summary>
        /// <param name="url"></param>
        public void setHomePage(string url)
        {
            HomePageConfig hpc = HomePageConfig.Instance;
            hpc.writeHomeConfig(url);
        }
    }
}
