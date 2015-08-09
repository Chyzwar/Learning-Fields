/**
 * Software Engineering Foundations
 * A first main method using a class
 * @author monica
 */
public class CarMain {
    public static void main (String[] args) {
        //create a Car
        Car myCar = new Car("Ford Ka", 40, 33.6);

        //get model
        String model = myCar.getModel();

        //get estimated distance
        double distance = myCar.estimateDistance();

        //print the details to standard output
        System.out.println(model + " can travel "
                           + distance + " miles");
    }  //end main method

}
