/**
 * Software Engineering Foundations
 * Car class with owner details
 * This class is the first object introduced on the course F21SF
 * and is used in various ways to demonstrate some basic programmng principles
 * @author monica
 */
public class Car {

  //instance variables
  private String model;	 	
  private int tankSize;
  private double manfMPG; //manufacturers miles per gallon
  private Name ownerName;
  //gallons per litre
  private static final double GPL = 0.22;

  /** Creates a Car object with values specified in the parameters
   * @param  model  the model of the car
   * @param  tank   the size of the tank in litres
   * @param  mpg    the miles per gallon, as supplied by the manufacturer
   * @param  owner  the name of the owner of the car
   */
  public Car(String model, int tank, double mpg, Name owner)
  {                          
	this.model = model;
	tankSize = tank;
	manfMPG = mpg;
	ownerName = owner;
  }
    
  /** returns the name of the model of the car e.g. Ford Ka
   * @return the model
   */
  public String getModel() {
	  return model;
  }
  
  /** returns the name of the owner of the car 
   * @return the owner's name
   */
  public Name getOwnerName() {
	  return ownerName; 
  }
  
  /** returns the estimated distance car can travel
   * on a full tank
   * @return the estimated distance
   */
  public double estimateDistance()
  {
	  //there are 0.22 gallons per litre
	  return tankSize * manfMPG * GPL;
  }
  
  /** Determines whether the size of the car’s tank
   * is bigger than a specified value
   * @param  size  the value that the tank is compared with
   * @return       true if the tank is bigger
   * than the value provided, false otherwise
   */
  public boolean tankBigger(int size) {
	  return tankSize > size;
  }
} 
