using System;
using System.Windows.Forms;
using WebBrowser_OuterSpace.controllers;
using WebBrowser_OuterSpace.models;

namespace WebBrowser_OuterSpace
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            //Initalise Homepage Controler
            HomePageController hp = new HomePageController();
            hp.setUpHomePageConfig();

            //Initialise BrowsingController
            BrowsingController bc = new BrowsingController();


            //HistoryController
            HistoryController hc = new HistoryController();
            hc.loadHistory();

            //FavouritiesController
            FavouritesController fc = new FavouritesController();
            fc.loadFavourites();

            Application.Run(new BrowserWindowMain(bc, hp, hc, fc));
        }
    }
}
