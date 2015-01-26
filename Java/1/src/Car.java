/**
 * Software Engineering Foundations
 * A first class
 * @author monica
 */
public class Car {
  //instance variables
  private String model;	 	
  private int tankSize;
  private double manfMPG;

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
	  //there are 0.22 gallons per litre
        double result = tankSize * manfMPG * 0.22;
	  return result;
  }	  
} 
