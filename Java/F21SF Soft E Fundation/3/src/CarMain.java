/**
 * Software Engineering Foundations
 * Basics B
 * A first main method using a class
 * @author monica
 */

public class CarMain {
	public static void main (String[] args) {
		Car myCar = new Car("Ford Ka", 40, 33.6); 
	
		//get model
 		String model = myCar.getModel();

		//get estimated distance
		double distance = myCar.estimateDistance();
	
		
		//get distance as a string to 1 decimal place
		String myDistString = String.format("%.1f", distance);

		//print the details to standard output
		System.out.println(model + " can travel "
				+ myDistString + " miles"); 
		
		Car yourCar = new Car("Mercedes Benz E280", 65, 22);
		double myDist = myCar.estimateDistance();
		double yourDist = yourCar.estimateDistance();
		System.out.println(yourCar.getModel() + " can travel "
				+ yourDist + " miles"); 
		double difference = myDist - yourDist;
		double absDiff = Math.abs(difference);	
		String absString = String.format ("%.1f", absDiff);
		String diffMessage = "";
		if (myDist < yourDist) 
		    diffMessage = absString
		                 + " miles less than the ";
		  else if (myDist > yourDist) 
		    diffMessage = absString
		                + " miles more than the ";
		  else
		    diffMessage = "the same as the ";
		
		 System.out.println("The " + myCar.getModel() + " can travel "
			 + myDistString + " miles on a full tank, which is "
			 + diffMessage + yourCar.getModel() );
	}  //end main method

}
