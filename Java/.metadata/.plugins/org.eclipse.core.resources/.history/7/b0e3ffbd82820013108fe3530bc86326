import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
public class StaffListInterface
{
	private Scanner scanner;
    private StaffList allStaff;
   
    public StaffListInterface(StaffList list)
    {
    	allStaff = list;
    	scanner = new Scanner(System.in);
    }
    
    /**
     * Read a series of commands from the user to interact
     * with the address book. Stop when the user types 'quit'.
     */
    public void run()
    {
        System.out.println("StaffList");
        System.out.println("Type 'help' for a list of commands.");
        
        String command;
        do{
        	System.out.println("Enter your command:");
            command = scanner.nextLine();
            if(command.equals("add")){ 
                add();
            }
            else if (command.equals("remove"))
            	remove();
            else if(command.equals("findID")){
                findID();
            }
            else if(command.equals("list")){
                list();
            }
            else if(command.equals("listByID")){
                listByID();
            }
            else if(command.equals("listByName")){
                listByName();
            }
            else if (command.equals("print")) {
            	print();
            }
            else if(command.equals("help")){
                help();
            }
            else if (!command.equals("quit")){
               System.out.println("Invalid command");
            }
        } while(!(command.equals("quit")));

        System.out.println("Goodbye.");
    }
    
    /**
     * Add a new entry.
     */
    private void add()
    {
        String message = "";
        try {
	    	//get details from 
	        System.out.print("ID? : ");
	        String id = scanner.nextLine();
	        System.out.print("Lastname, Firstname? : ");
	        String name = scanner.nextLine();
	        System.out.print("Hours worked? : ");
	        int hours = scanner.nextInt();
        	scanner.nextLine(); //read end of line character
        	//create new staff object and add to list
        	Staff newStaff = new Staff(id, name, hours);
        	allStaff.addDetails(newStaff);
        	message = "Details added";
        }
        catch (InputMismatchException e) {
        	message = "Hours worked not a number, staff details not added";
        	scanner.nextLine(); //read end of line character

        }
        catch (IllegalStateException e){ // if params invalid
        	message = e.getMessage() + "\nDetails not added";
        }
        catch (DuplicateIDException e){
        	message = e.getMessage() + "\nDetails not added";
        }
        System.out.println(message);
    }
    
    private void remove() {
    	System.out.println("Enter ID of person to be removed: ");
    	String id = scanner.nextLine();
        allStaff.removeDetails(id);
    }
    
    /**
     * Find an entry matching a key.
     */
    private void findID()
    {
        System.out.print("Type the ID to search for: ");
        String id = scanner.nextLine();
        Staff result = allStaff.findById(id);
        System.out.println(result);
    }
    
    /**
     * List the available commands.
     */
    private void help()
    {
        System.out.println("add, remove, findID, list, listByID, listByName, print, help, quit");
    }
    
    /**
     * List the address book's contents.
     */
    private void list()
    {
        System.out.println(allStaff.listDetails());
    }
    
    /**
     * List the address book's contents.
     */
    private void listByID()
    {
        System.out.println(allStaff.listByID());
    }  
    
    /**
     * List the address book's contents.
     */
    private void listByName()
    {
        System.out.println(allStaff.listByName());
    } 
    
    /**
     * Print to text file
     */
    private void print() {
    	FileWriter fw = null;
    	try {
        	fw = new FileWriter("StaffDetails.txt");
        	fw.write(allStaff.listDetails());
        	fw.close();
    	}
    	catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
    		System.exit(1);
    	}
    	
    	catch (IOException ioe){
    		System.exit(1);
    	}

    }
}
