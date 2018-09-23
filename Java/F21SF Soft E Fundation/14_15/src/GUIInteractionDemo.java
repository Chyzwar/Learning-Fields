import java.io.*;

public class GUIInteractionDemo
{
	private StaffList entries;
	
    public GUIInteractionDemo() 
    {
    	//initialise empty list of staff
        entries = new StaffList();
        addStaff();
    }
    
    private void addStaff() {
        //load staff data from file
        BufferedReader buff = null;
        try {
        	buff = new BufferedReader(new FileReader("StaffList.csv"));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		processLine(inputLine);
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
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }   	
    }
    //splits inputline, creates staff object ands adds to list of entries
    private void processLine(String inputLine) {
		//split line into parts
		String data [] = inputLine.split(",");
		//create staff object
		Name name = new Name(data[1]);
		int level = Integer.parseInt(data[3]);
		Staff s = new Staff(data[0], name, data[2], level);
		//add to list
        entries.addDetails(s);
    }
  
    //show GUIs
    private void showGUI() {
    	//create main GUI with StaffList object
    	StaffListGUI gui = new StaffListGUI(entries);
        gui.setVisible(true);

    }
    

    public static void main (String arg[]) {
       	//creates demo object which sets up the interface
    	//then just waits for user interaction
    	GUIInteractionDemo demo = new GUIInteractionDemo();   	
    	demo.showGUI();

    }

}
