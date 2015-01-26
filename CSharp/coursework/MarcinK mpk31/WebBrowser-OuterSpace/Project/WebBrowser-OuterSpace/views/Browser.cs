using System;
using System.Collections;
using System.Windows.Forms;
using WebBrowser_OuterSpace.controllers;
using WebBrowser_OuterSpace.models;

namespace WebBrowser_OuterSpace
{
    public partial class BrowserWindowMain : Form
    {
        
        /// <summary>
        /// Constructor for GUI with DI injection
        /// </summary>
        /// <param name="bc"></param>
        /// <param name="hp"></param>
        public BrowserWindowMain(BrowsingController bc, HomePageController hp, HistoryController hc, FavouritesController fc)
        {
            //start gui elements
            InitializeComponent();

            //inject controllers
            this.browsingController = bc;
            this.homePageController = hp;
            this.historyController = hc;
            this.favouritiesController = fc;
        }
        /// <summary>
        /// Startyp function for setting a homepage on form initialisation
        /// </summary>
        public void startAtHomePage()
        {
            string urlAdress = homePageController.getHomePageURL();
            String[] response = browsingController.getWebPage(currentTabSelected, urlAdress);
            activeTab = browserTab_0;

            inputTextURL.Text = urlAdress;
            outputTextHTML.Text = response[1];
            outputResMessege.Text = response[0];
            browserTab_0.Text = urlAdress;
        }

        /// <summary>
        /// Handler for "Go" button, send http request
        /// Also call function to create record in hostory each time
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public void submitBtnURL_Click(object sender, EventArgs e)
        {
            string urlAdress = inputTextURL.Text;
            String[] response = browsingController.getWebPage(currentTabSelected, urlAdress);
            
            outputTextHTML.Text = response[1];
            outputResMessege.Text = response[0];
            activeTab.Text = urlAdress;

            historyController.recordHistory(urlAdress, currentTabSelected);
        }

        /// <summary>
        /// Handler for clicking into buttons representing tabs in browser
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public void tab_Click(object sender, EventArgs e)
        {

            activeTab = (Button)sender;
            string[] currentTabNames = activeTab.Name.Split('_');
            currentTabSelected = Convert.ToInt32(currentTabNames[1]);

            string urlAdress = homePageController.getHomePageURL();
            String[] response = browsingController.getTabContent(currentTabSelected, urlAdress);

            outputTextHTML.Text = response[1];
            outputResMessege.Text = response[0];
            inputTextURL.Text = response[2];
            activeTab.Text = response[2];
        }

        /// <summary>
        /// I when Wind is load get homepage
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BrowserWindowMain_Load(object sender, EventArgs e)
        {
            startAtHomePage();
        }

        /// <summary>
        /// handler for Button that Add new Tabs in Browser
        /// Maximum number of tabs 25. layout willl fall apart sooner
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void addBtnTab_Click(object sender, EventArgs e)
        {
            if (numberOfTabs < 25) { 
            Button newTabButton = new Button();
            newTabButton.Location = new System.Drawing.Point(30 + numberOfTabs * 140, 65);
            newTabButton.Name = "browserTab_" + numberOfTabs.ToString();
            newTabButton.Size = new System.Drawing.Size(150, 25);
            newTabButton.TabIndex = 15 + numberOfTabs;
            newTabButton.Text = "New Tab";
            newTabButton.UseVisualStyleBackColor = true;
            newTabButton.Click += new System.EventHandler(this.tab_Click);
            tabButtonsList.Add(newTabButton);

            browsingController.createNewTab(homePageController.getHomePageURL());

            this.Controls.Add(newTabButton);

            numberOfTabs++;
            }
            else
            {
                outputResMessege.Text = "Maximum number of Tabs (25) reached";
            }

        }

        /// <summary>
        /// I will Probalny not use this
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void tabPanel_Paint(object sender, PaintEventArgs e)
        {

        }

        /// <summary>
        /// Create a new Form with controls to change HomePage settings
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void homebtnSettings_Click(object sender, EventArgs e)
        {
            var homePageSettings = new Form();
            homePageSettings.Height = 200;
            homePageSettings.Width = 500;

            //Create confirm button
            changeHomePage = new Button();
            changeHomePage.Location = new System.Drawing.Point(30 +  140, 65);
            changeHomePage.Name = "changebtnHomePage";
            changeHomePage.Size = new System.Drawing.Size(150, 25);
            changeHomePage.TabIndex = 20;
            changeHomePage.Text = "Change HomePage";
            changeHomePage.UseVisualStyleBackColor = true;
            changeHomePage.Click += new System.EventHandler(this.changeHomePage_Click);

            //Create input field
            inputURL = new TextBox();
            inputURL.Location = new System.Drawing.Point(24, 15);
            inputURL.Name = "inputTextURL";
            inputURL.Size = new System.Drawing.Size(450, 20);
            inputURL.TabIndex = 23;
            inputURL.Text = homePageController.getHomePageURL();


            homePageSettings.Controls.Add(changeHomePage);
            homePageSettings.Controls.Add(inputURL);

            homePageSettings.Show();
        }

        /// <summary>
        /// Handler of change home page button
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void changeHomePage_Click(object sender, EventArgs e)
        {
            homePageController.setHomePage(inputURL.Text);
            
        }

        /// <summary>
        /// Handler for button Prev, 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void prevBtnHistoryPrev_Click(object sender, EventArgs e)
        {
           int deepness = (int) deepnessPerTab[currentTabSelected] -1;
           deepnessPerTab.Insert(currentTabSelected, deepness);

            String prevURL = historyController.getHistoryPageURL(currentTabSelected, deepness);
            String[] response = browsingController.getWebPage(currentTabSelected, prevURL);

            outputTextHTML.Text = response[1];
            outputResMessege.Text = response[0]+ " History: ;" + deepness.ToString();
            activeTab.Text = prevURL;
            inputTextURL.Text = prevURL;
        }

        /// <summary>
        /// handler for button Next
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void historyBtnNext_Click(object sender, EventArgs e)
        {
            int deepness = (int)deepnessPerTab[currentTabSelected] + 1;
            deepnessPerTab.Insert(currentTabSelected, deepness);

            String nextURL = historyController.getHistoryPageURL(currentTabSelected, deepness);
            String[] response = browsingController.getWebPage(currentTabSelected, nextURL);

            outputTextHTML.Text = response[1];
            outputResMessege.Text = response[0] + " History: ;" + deepness.ToString();
            activeTab.Text = nextURL;
            inputTextURL.Text = nextURL;
        }

        /// <summary>
        /// Handeler for history btn
        /// Build Up a Panel with button links
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void historyBtn_Click(object sender, EventArgs e)
        {
            ArrayList historyTotal = (ArrayList) historyController.getHistory();
            int xCounter = 1;
            var historyList = new Form();
            historyList.Height = 1000;
            historyList.Width = 300;

            foreach (var singleLink in historyTotal)
            {
                Button newTabButton = new Button();

                newTabButton.Location = new System.Drawing.Point(25 , xCounter*30);
                newTabButton.Name = "historyTab_" + xCounter.ToString();
                newTabButton.Size = new System.Drawing.Size(250, 25);
                newTabButton.TabIndex = 15 + numberOfTabs;
                newTabButton.Text = (string)singleLink;
                newTabButton.UseVisualStyleBackColor = true;
                newTabButton.Click += new System.EventHandler(this.historyLink_Click);
                tabButtonsList.Add(newTabButton);

                browsingController.createNewTab(homePageController.getHomePageURL());

                historyList.Controls.Add(newTabButton);
                xCounter++;
            }
            historyList.Show();
        }
        /// <summary>
        /// Handler for button in History Panel
        /// Open URl in active tab
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void historyLink_Click(object sender, EventArgs e)
        {
             Button activatedLink = (Button)sender;

             String url = activatedLink.Text;
             String[] response = browsingController.getWebPage(currentTabSelected, url);

             inputTextURL.Text = url;
             outputTextHTML.Text = response[1];
             outputResMessege.Text = response[0];
             activeTab.Text = url;

             historyController.recordHistory(url, currentTabSelected);
        }
        /// <summary>
        /// Create form on favourities btn click
        /// Privide Options to edit or change elemets
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void favouritiesBtn_Click(object sender, EventArgs e)
        {
            ArrayList favouritiesList = (ArrayList) favouritiesController.getFavouritesList();

            //Create Form
            var favouritiesForm = new Form();
            favouritiesForm.Height = 1000;
            favouritiesForm.Width = 550;

            inputNameFav = new TextBox();
            inputNameFav.Location = new System.Drawing.Point(24, 15);
            inputNameFav.Name = "inputTextName";
            inputNameFav.Size = new System.Drawing.Size(120, 25);
            inputNameFav.TabIndex = 161;
            inputNameFav.Text = "Choose Name";
            favouritiesForm.Controls.Add(inputNameFav);

            //Add Fav Input forURL
            inputURLFav = new TextBox();
            inputURLFav.Location = new System.Drawing.Point(150, 15);
            inputURLFav.Name = "inputTextURL";
            inputURLFav.Size = new System.Drawing.Size(250, 25);
            inputURLFav.TabIndex = 162;
            inputURLFav.Text = "Provide URL";
            favouritiesForm.Controls.Add(inputURLFav);

            //Add Button
            addButtonFav = new Button();
            addButtonFav.Location = new System.Drawing.Point(410, 14);
            addButtonFav.Name = "addBtnFav";
            addButtonFav.Size = new System.Drawing.Size(50, 25);
            addButtonFav.TabIndex = 15 + numberOfTabs;
            addButtonFav.Text = "ADD";
            addButtonFav.UseVisualStyleBackColor = true;
            addButtonFav.Click += new System.EventHandler(this.addBtnFav_Click);
            favouritiesForm.Controls.Add(addButtonFav);


            int xCounter = 0;
            foreach (string[] favorite in favouritiesList)
            {

                TextBox inputNameUP = new TextBox();
                inputNameUP.Location = new System.Drawing.Point(24, 45 + xCounter * 30);
                inputNameUP.Name = "inputTextURL_" + xCounter.ToString();
                inputNameUP.Size = new System.Drawing.Size(120, 25);
                inputNameUP.TabIndex = 161;
                inputNameUP.Text = favorite[0];
                inputNameUpList.Add(inputNameUP);

                //Add Fav Input forURL
                TextBox inputURLFavUP = new TextBox();
                inputURLFavUP.Location = new System.Drawing.Point(150, 45 + xCounter * 30);
                inputURLFavUP.Name = "inputTextURL_" + xCounter.ToString();
                inputURLFavUP.Size = new System.Drawing.Size(250, 25);
                inputURLFavUP.TabIndex = 162;
                inputURLFavUP.Text = favorite[1];
                inputURLFavUPList.Add(inputURLFavUP);

                //Add Button
                Button addButtonUP = new Button();
                addButtonUP.Location = new System.Drawing.Point(410, 45 + xCounter * 30);
                addButtonUP.Name = "addBtnFav_"+ xCounter.ToString();
                addButtonUP.Size = new System.Drawing.Size(50, 25);
                addButtonUP.TabIndex = 250 + numberOfTabs;
                addButtonUP.Text = "Update";
                addButtonUP.UseVisualStyleBackColor = true;
                addButtonUP.Click += new System.EventHandler(this.addBtnFavUP_Click);
                addButtonUPList.Add(addButtonUP);

                //Add Button
                Button remButton = new Button();
                remButton.Location = new System.Drawing.Point(470, 45 + xCounter * 30);
                remButton.Name = "addBtnFav_" + xCounter.ToString();
                remButton.Size = new System.Drawing.Size(50, 25);
                remButton.TabIndex = 250 + numberOfTabs;
                remButton.Text = "Remove";
                remButton.UseVisualStyleBackColor = true;
                remButton.Click += new System.EventHandler(this.addBtnFavRem_Click);
                remButtonList.Add(remButton);

                favouritiesForm.Controls.Add(inputNameUP);
                favouritiesForm.Controls.Add(inputURLFavUP);
                favouritiesForm.Controls.Add(addButtonUP);
                favouritiesForm.Controls.Add(remButton);


                xCounter++;


            }
            favouritiesForm.Show();
        }

        /// <summary>
        /// Updated choosen Fav
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void addBtnFavUP_Click(object sender, EventArgs e)
        {   

            Button toUpdate = (Button)sender;
            string[] currentFav = toUpdate.Name.Split('_');
            int currentFavSelected = Convert.ToInt32(currentFav[1]);
            TextBox inputNameUP  =  inputNameUpList[currentFavSelected];
            TextBox inputURLFavUP = inputURLFavUPList[currentFavSelected];

            favouritiesController.updatefav(inputNameUP.Text, inputURLFavUP.Text, currentFavSelected);


        }

        /// <summary>
        /// Handler For Removing Things from fav list
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        Button toUpdate = new Button();
        private void addBtnFavRem_Click(object sender, EventArgs e)
        {
            toUpdate = (Button)sender;
            string[] currentFav = activeTab.Name.Split('_');
            int currentFavSelected = Convert.ToInt32(currentFav[1]);
            favouritiesController.removefav(currentFavSelected);
        }

        /// <summary>
        /// Handler for add favourite button
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void addBtnFav_Click(object sender, EventArgs e)
        {
            String name = inputNameFav.Text;
            String url = inputURLFav.Text;

            favouritiesController.createNewFav(name, url);


        }

    }
}
