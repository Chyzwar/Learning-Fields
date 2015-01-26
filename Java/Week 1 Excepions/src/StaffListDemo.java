
//program demonstrating using a list of Staff objects
//adding and removing and listing and searching
//uses exceptions
import java.io.*;

public class StaffListDemo
{
    private StaffList entries;
    private StaffListInterface interaction;
	private StaffListGUI gui;


    public StaffListDemo() throws DuplicateIDException
    {
    	//initialise empty list of staff
        entries = new StaffList();
        //this will handle text interaction with user
        interaction = new StaffListInterface(entries);
        BufferedReader buff = null;
    	String data [] = new String[3];
        try {
        	buff = new BufferedReader(new FileReader("StaffList.txt"));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		//create staff object
	    		int hours = Integer.parseInt(data[2].trim());
	    		Staff s = new Staff(data[0], data[1], hours);
	    		//add to list
	            entries.addDetails(s);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        catch (NumberFormatException nfe) {
        	System.out.println(data[0] + ": Hours worked not a number :" + data[2]);
        	System.out.println("Program stopped");
        	System.exit(1);
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
    }

    /**
     * Allow the user to interact with the staff list.
     */
    public void showInterface()
    {
        interaction.run();
    }
    
    /**
     * Show GUI
     */
    public void showGUI() {
        gui = new StaffListGUI(entries);
        gui.setVisible(true);
    }     
    
    public static void main (String arg[]) throws DuplicateIDException {
       	//creates demo object, with a populated staff list
    	StaffListDemo sld = new StaffListDemo();   
    	
    	//allow user to interact using a GUI
    	sld.showGUI();

    	//allow user to interact with this list
    	//using text interface
    	sld.showInterface();	
    	
    }

}
