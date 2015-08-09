using System.Collections;
using System.Collections.Generic;
using WebBrowser_OuterSpace.controllers;
using WebBrowser_OuterSpace.models;
namespace WebBrowser_OuterSpace
{
    partial class BrowserWindowMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.submitBtnURL = new System.Windows.Forms.Button();
            this.inputTextURL = new System.Windows.Forms.TextBox();
            this.outputTextHTML = new System.Windows.Forms.TextBox();
            this.outputResMessege = new System.Windows.Forms.Label();
            this.addBtnTab = new System.Windows.Forms.Button();
            this.browserTab_0 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.historyBtnPrev = new System.Windows.Forms.Button();
            this.historyBtnNext = new System.Windows.Forms.Button();
            this.historyBtn = new System.Windows.Forms.Button();
            this.favouritiesBtn = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // submitBtnURL
            // 
            this.submitBtnURL.Location = new System.Drawing.Point(777, 16);
            this.submitBtnURL.Name = "submitBtnURL";
            this.submitBtnURL.Size = new System.Drawing.Size(75, 25);
            this.submitBtnURL.TabIndex = 0;
            this.submitBtnURL.Text = "Go";
            this.submitBtnURL.UseVisualStyleBackColor = true;
            this.submitBtnURL.Click += new System.EventHandler(this.submitBtnURL_Click);
            // 
            // inputTextURL
            // 
            this.inputTextURL.Location = new System.Drawing.Point(186, 19);
            this.inputTextURL.Name = "inputTextURL";
            this.inputTextURL.Size = new System.Drawing.Size(585, 20);
            this.inputTextURL.TabIndex = 1;
            this.inputTextURL.Text = "Enter URL and click on go";
            // 
            // outputTextHTML
            // 
            this.outputTextHTML.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.outputTextHTML.Location = new System.Drawing.Point(24, 96);
            this.outputTextHTML.Multiline = true;
            this.outputTextHTML.Name = "outputTextHTML";
            this.outputTextHTML.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.outputTextHTML.Size = new System.Drawing.Size(1365, 650);
            this.outputTextHTML.TabIndex = 2;
            // 
            // outputResMessege
            // 
            this.outputResMessege.AutoSize = true;
            this.outputResMessege.Location = new System.Drawing.Point(21, 47);
            this.outputResMessege.Name = "outputResMessege";
            this.outputResMessege.Size = new System.Drawing.Size(29, 13);
            this.outputResMessege.TabIndex = 3;
            this.outputResMessege.Text = "Res:";
            // 
            // addBtnTab
            // 
            this.addBtnTab.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.addBtnTab.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.addBtnTab.Location = new System.Drawing.Point(1279, 47);
            this.addBtnTab.Name = "addBtnTab";
            this.addBtnTab.Size = new System.Drawing.Size(110, 23);
            this.addBtnTab.TabIndex = 4;
            this.addBtnTab.Text = "Add Tab";
            this.addBtnTab.UseVisualStyleBackColor = false;
            this.addBtnTab.Click += new System.EventHandler(this.addBtnTab_Click);
            // 
            // browserTab_0
            // 
            this.browserTab_0.Location = new System.Drawing.Point(24, 65);
            this.browserTab_0.Name = "browserTab_0";
            this.browserTab_0.Size = new System.Drawing.Size(150, 25);
            this.browserTab_0.TabIndex = 0;
            this.browserTab_0.Text = "Home Tab";
            this.browserTab_0.UseVisualStyleBackColor = true;
            this.browserTab_0.Click += new System.EventHandler(this.tab_Click);
            // 
            // button1
            // 
            this.button1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.button1.Location = new System.Drawing.Point(1279, 15);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(110, 25);
            this.button1.TabIndex = 5;
            this.button1.Text = "HomePage Settings";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.homebtnSettings_Click);
            // 
            // historyBtnPrev
            // 
            this.historyBtnPrev.Location = new System.Drawing.Point(24, 17);
            this.historyBtnPrev.Name = "historyBtnPrev";
            this.historyBtnPrev.Size = new System.Drawing.Size(75, 23);
            this.historyBtnPrev.TabIndex = 6;
            this.historyBtnPrev.Text = "Prev";
            this.historyBtnPrev.UseVisualStyleBackColor = true;
            this.historyBtnPrev.Click += new System.EventHandler(this.prevBtnHistoryPrev_Click);
            // 
            // historyBtnNext
            // 
            this.historyBtnNext.Location = new System.Drawing.Point(105, 17);
            this.historyBtnNext.Name = "historyBtnNext";
            this.historyBtnNext.Size = new System.Drawing.Size(75, 23);
            this.historyBtnNext.TabIndex = 7;
            this.historyBtnNext.Text = "Next";
            this.historyBtnNext.UseVisualStyleBackColor = true;
            this.historyBtnNext.Click += new System.EventHandler(this.historyBtnNext_Click);
            // 
            // historyBtn
            // 
            this.historyBtn.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.historyBtn.Location = new System.Drawing.Point(1161, 15);
            this.historyBtn.Name = "historyBtn";
            this.historyBtn.Size = new System.Drawing.Size(112, 25);
            this.historyBtn.TabIndex = 8;
            this.historyBtn.Text = "History";
            this.historyBtn.UseVisualStyleBackColor = true;
            this.historyBtn.Click += new System.EventHandler(this.historyBtn_Click);
            // 
            // favouritiesBtn
            // 
            this.favouritiesBtn.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.favouritiesBtn.Location = new System.Drawing.Point(1057, 17);
            this.favouritiesBtn.Name = "favouritiesBtn";
            this.favouritiesBtn.Size = new System.Drawing.Size(98, 23);
            this.favouritiesBtn.TabIndex = 9;
            this.favouritiesBtn.Text = "Favourities";
            this.favouritiesBtn.UseVisualStyleBackColor = true;
            this.favouritiesBtn.Click += new System.EventHandler(this.favouritiesBtn_Click);
            // 
            // BrowserWindowMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.ClientSize = new System.Drawing.Size(1401, 758);
            this.Controls.Add(this.favouritiesBtn);
            this.Controls.Add(this.historyBtn);
            this.Controls.Add(this.historyBtnNext);
            this.Controls.Add(this.historyBtnPrev);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.browserTab_0);
            this.Controls.Add(this.addBtnTab);
            this.Controls.Add(this.outputResMessege);
            this.Controls.Add(this.outputTextHTML);
            this.Controls.Add(this.inputTextURL);
            this.Controls.Add(this.submitBtnURL);
            this.Name = "BrowserWindowMain";
            this.Text = "WebBrower- OuterSpace";
            this.Load += new System.EventHandler(this.BrowserWindowMain_Load);
            this.ResumeLayout(false);
            this.PerformLayout();
         
        }

        #endregion
        //Controllers referencees
        private BrowsingController browsingController;
        private HomePageController homePageController;
        private HistoryController historyController;
        private FavouritesController favouritiesController;

        //used by view elements
        private int currentTabSelected = 0;
        private int numberOfTabs = 1;
        private ArrayList deepnessPerTab = new ArrayList() { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        private List<System.Windows.Forms.Button> tabButtonsList = new List<System.Windows.Forms.Button>();
        private System.Windows.Forms.Button activeTab;

        //Home Page setup popup
        private System.Windows.Forms.Button changeHomePage;
        private System.Windows.Forms.TextBox inputURL;

        //Form for favourities
        private System.Windows.Forms.TextBox inputURLFav;
        private System.Windows.Forms.TextBox inputNameFav;
        private System.Windows.Forms.Button addButtonFav;
        private List<System.Windows.Forms.TextBox> inputNameUpList= new List<System.Windows.Forms.TextBox>();
        private List<System.Windows.Forms.TextBox> inputURLFavUPList= new List<System.Windows.Forms.TextBox>();
        private List<System.Windows.Forms.Button> addButtonUPList = new List<System.Windows.Forms.Button>();
        private List<System.Windows.Forms.Button> remButtonList = new List<System.Windows.Forms.Button>();


        private System.Windows.Forms.Button submitBtnURL;
        private System.Windows.Forms.TextBox inputTextURL;
        private System.Windows.Forms.TextBox outputTextHTML;
        private System.Windows.Forms.Label outputResMessege;
        private System.Windows.Forms.Button addBtnTab;
        private System.Windows.Forms.Button browserTab_0;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button historyBtnPrev;
        private System.Windows.Forms.Button historyBtnNext;
        private System.Windows.Forms.Button historyBtn;
        private System.Windows.Forms.Button favouritiesBtn;


    }
}

