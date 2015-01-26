using System;
using System.Text;
using System.Net;
using System.IO;
using System.Threading;

namespace WebBrowser_OuterSpace.models
{
    /// <summary>
    /// This calss will represent a single HTML document
    /// Also hold response code and error messeges
    /// </summary>
    class WebDocumenTab
    {
        /// <summary>
        /// I share minimal access because of threading
        /// </summary>
        private String urlAddress;
        private String webDocument;
        private HttpStatusCode httpResponseCode;
        private String errorMessage;

        private Boolean documentReady = false;
        private Object thisLock = new Object();

        public WebDocumenTab(String urlAddress)
        {
            this.urlAddress = urlAddress;
        }

        /// <summary>
        /// This function fetch a document from Web , it is used as thread
        /// Locks because of shared web document
        /// </summary>
        public void fetchWebDocument()
        {
            lock (thisLock)    
            {
                documentReady = false;
                errorMessage = "";
                webDocument = "";
                try
                {

                    HttpWebRequest request = (HttpWebRequest)WebRequest.Create(urlAddress);
                    HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                    httpResponseCode = response.StatusCode;

                    if (httpResponseCode == HttpStatusCode.OK)
                    {
                        Stream receiveStream = response.GetResponseStream();
                        StreamReader readStream = null;
                        if (response.CharacterSet == null)
                            readStream = new StreamReader(receiveStream);
                        else
                            readStream = new StreamReader(receiveStream, Encoding.GetEncoding(response.CharacterSet));
                        webDocument = readStream.ReadToEnd();
                        response.Close();
                        readStream.Close();

                        documentReady = true;
                    }

                }
                catch (ProtocolViolationException e)
                {
                    errorMessage = e.Message;
                }
                catch (NotSupportedException e)
                {
                    errorMessage = e.Message;
                }
                catch (WebException e)
                {
                    if (e.Status == WebExceptionStatus.ProtocolError)
                    {
                        httpResponseCode = ((HttpWebResponse)e.Response).StatusCode;

                    }
                    else
                    {
                        errorMessage = e.Message;
                    }
                   
                }
                catch (NullReferenceException e)
                {
                    errorMessage = e.Message;
                }
                catch (InvalidOperationException e)
                {
                    errorMessage = e.Message;
                }
                catch (UriFormatException e)
                {
                    errorMessage = e.Message;
                }
                catch (SystemException e)
                {
                    errorMessage = e.Message;
                }
            }
        }

        /// <summary>
        /// Start and end new threads that will fetch web documents
        /// </summary>
        public void runFetchThread()
        {
            Thread mainThread = Thread.CurrentThread;
            Thread newFetch = new Thread(fetchWebDocument);
            newFetch.Start();
            newFetch.Join();
        }

        /// <summary>
        /// Get response code or error messege from exceptions
        /// </summary>
        /// <returns></returns>
        public String getResponseMessage()
        {
            if (!String.IsNullOrEmpty(errorMessage))
            {
                return errorMessage;
            }
            return "HTTP Code: " + ((int)httpResponseCode).ToString() + " " + httpResponseCode.ToString();
        }

        /// <summary>
        /// Getter for webDocument with extra safety
        /// </summary>
        /// <returns></returns>
        public String getWebDocument()
        {
            if (!String.IsNullOrEmpty(webDocument))
            {
                return webDocument;
            }

            return "No Ducument has been fetched";
        }

        /// <summary>
        /// If url is diffrent or document is not there it will make a new request
        /// </summary>
        /// <param name="url"></param>
        /// <returns></returns>
        public String[] makeRequest(String url)
        {
            if (urlAddress != url || documentReady == false)
            {
                this.urlAddress = url;
                this.runFetchThread();

            }
            String[] result = new string[] { getResponseMessage(), getWebDocument() };
            return result;
        }


        /// <summary>
        /// Get Ready document, this is used when moving bewteeen tabs without changing URl 
        /// </summary>
        /// <returns></returns>
        public String[] retrieveDocument()
        {
            if (this.documentReady)
            {
                String[] result = new string[] { getResponseMessage(), getWebDocument(), this.urlAddress };
                return result;
            }
            else
            {
                this.runFetchThread();
                String[] result = new string[] { getResponseMessage(), getWebDocument(), this.urlAddress };
                return result;
            }

        }
    }
}
