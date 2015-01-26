
public class Acc
{
// instance variables
private String accNumber;
private double balance;
private double overdraft;

//constructor
public Acc(String acc, double balance, double over)
	{
	accNumber=acc;
	this.balance=balance;
	overdraft=over;
	}
// get account number
public String getAccount()
	{
	return accNumber;
	}
//get balance on account
public double getBalance()
	{
	return balance;
	}
//get overdraft
public double getOver()
	{
	return overdraft;
	}
//calculate maximum amount that can be used
public double getMax()
	{
	double max=balance+overdraft;
	return max;
	}
	
public double setnewBalance()
	{	
	return balance=0;
	}
}
