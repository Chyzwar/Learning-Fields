using System;
using System.Collections;
using WebBrowser_OuterSpace.models;


namespace WebBrowser_OuterSpace.controllers
{
    /// <summary>
    /// Favourites controller
    /// </summary>
    public class FavouritesController
    {
        /// <summary>
        /// Get a list of all favourities
        /// </summary>
        /// <returns></returns>
        public ArrayList getFavouritesList()
        {
            FavouritesList favo = FavouritesList.Instance;
            return favo.getfavouritesCollection();
        }

        /// <summary>
        /// On Application Start load Favourities
        /// </summary>
        public void loadFavourites()
        {
            FavouritesList favo = FavouritesList.Instance;
            favo.readFavouritesConfig();
        }

        /// <summary>
        /// Create new Fav
        /// </summary>
        /// <param name="name"></param>
        /// <param name="url"></param>
        public void createNewFav(String name, String url)
        {
            FavouritesList favo = FavouritesList.Instance;
            favo.addNewFav(name,url);
        }

        /// <summary>
        /// Update Fav
        /// </summary>
        /// <param name="name"></param>
        /// <param name="url"></param>
        /// <param name="id"></param>
        public void updatefav(string name, string url, int id)
        {
            FavouritesList favo = FavouritesList.Instance;
            favo.makeUpdateFav(name , url , id);
        }

        /// <summary>
        /// Remove Favourity website
        /// </summary>
        /// <param name="id"></param>
        public void removefav(int id)
        {
            FavouritesList favo = FavouritesList.Instance;
            favo.removeFav(id);
        }

 
    }
}
