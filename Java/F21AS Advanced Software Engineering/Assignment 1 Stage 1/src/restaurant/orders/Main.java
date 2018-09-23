package restaurant.orders;

/**Program store information  on restaurant orders and menu.
 * DishItem - Is entry in Menu
 * DishOrder -Is single order
 * @authors Marcin Kopacz(mpk31), Stian Dalviken (sd196), Kamontorn Khamrun (kk249)
 *
 */
public class Main 
{
	/**Main Method where everything starts
	 * Method create CompetitorManager object as cm
	 * Initialize run() method in cm
	 * @param args(currently we don't use any)
	 */
	public static void main(String[] args) 
	{
		ManagerClass mc = new ManagerClass();
		mc.run();
	}
}