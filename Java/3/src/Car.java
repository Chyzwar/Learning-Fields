/**
 * Software Engineering Foundations
 * Car class introduced in L2, now has constant GPL and boolean method
 * @author monica
 */
public class Car {
  //instance variables
  private String model;	 	
  private int tankSize;
  private double manfMPG; //manufacturers miles per gallon
  //gallons per litre
  private static final double GPL = 0.22;

  //constructor
  public Car(String model, int tank, double mpg)
  {                          
	this.model = model;
	tankSize = tank;
	manfMPG = mpg;
  }
    
  //Return model
  public String getModel() {
	  return model;
  }
  
  //estimate distance car can travel
  public double estimateDistance()
  {
	  double estimate = tankSize * manfMPG * GPL;
	  return estimate;
  }
  
  //returns true if this tank is bigger than the one in the parameter
  //returns false otherwise
  public boolean tankBigger(int size) {
	  return tankSize > size;
  }
} 
