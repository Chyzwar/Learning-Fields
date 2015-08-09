
public class AccMain 
{
public static void main (String[] args)
	{	
	//Create three different accounts
	Acc Acc1= new Acc("023456",1030.21,2000.234);
	Acc Acc2= new Acc("034567",2010342.12322,7653);
	Acc Acc3= new Acc("872671",4000.12,1000);

	//Get accounts numbers
	String numberAcc1=Acc1.getAccount();
	String numberAcc2=Acc2.getAccount();
	String numberAcc3=Acc3.getAccount();
	
	//get balance for accounts
	double balanceAcc1=Acc1.getBalance();
	double balanceAcc2=Acc2.getBalance();
	double balanceAcc3=Acc3.getBalance();
	
	//get overdrafts limits
	double overAcc1=Acc1.getOver();
	double overAcc2=Acc2.getOver();
	double overAcc3=Acc3.getOver();
	
	//get maximum available amount
	double maxAcc1=Acc1.getMax();
	double maxAcc2=Acc2.getMax();
	double maxAcc3=Acc3.getMax();
	
	// do some printing
	System.out.println(" Account 1 " + numberAcc1 + " Have Balance " + balanceAcc1 + " With overdraft "+ overAcc1 +  " Maximum amount avaible is " + maxAcc1);
	System.out.println(" Account 2 " + numberAcc2 + " Have Balance " + balanceAcc2 + " With overdraft "+ overAcc2 +  " Maximum amount avaible is " + maxAcc2);
	System.out.println(" Account 3 " + numberAcc3 + " Have Balance " + balanceAcc3 + " With overdraft "+ overAcc3 +  " Maximum amount avaible is " + maxAcc3);
	
	// find "richest"
	int select;
	if (maxAcc1>maxAcc2 & maxAcc1>maxAcc3)
		{
		select=1; 
		}
		else if (maxAcc2>maxAcc3 & maxAcc2>maxAcc1) 
		{
		select=2;
		}
		else
		{
		select=3;
		}
	System.out.println("Account " + select+ " Have bigest possible capital");
	
	//New balance for rich guy
	 balanceAcc2=Acc2.setnewBalance();
	 System.out.println(" Account 2 " + numberAcc2 + " Have new better Balance " + balanceAcc2);
	
	
	}
}
