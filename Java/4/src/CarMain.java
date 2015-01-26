/**
 * Software Engineering Foundations
 * Testing Car with owner Name
 * @author monica
 */

public class CarMain {
	public static void main (String[] args) {
		//create a Name
		Name myName = new Name("John", "David", "Smith");
		//create a Car
		Car myCar = new Car("Ford Ka", 40, 33.6, myName); 
		
		Name owner = myCar.getOwnerName();
		System.out.println("The car belongs to "  
				+ owner.getFirstAndLastName() );
		System.out.println("It is " + owner.getFirstName() 
				+ "’s car.") ;
		

	}  //end main method

}
