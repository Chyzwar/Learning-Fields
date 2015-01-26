// Exercise from Lecture 9, C# Revision: extended bank account
// Additionally uses:
// . refined access modifiers
// . hiding methods of the base-class
// . inheritance to add an overdraft facility
// . automatic properties to implement Overdraft
// . exception to handle insufficient balance
// . overloading (on constructors)
// . static fields (auto generate account number)
// -----------------------------------------------------------------------------

using System;

// define an exception, triggered by the balance being too low
public class InsufficientBalance : System.Exception {
    public InsufficientBalance(string msg) :base(msg) {
    }
}


// define an exception, triggered by an invariant violation
public class InvariantViolation : System.Exception {
    public InvariantViolation(string msg) :base(msg) {
    }
}

// the basic accoount
class BankAccount {
  // a static field, holding the latest account number
  protected static ulong latestAccountNo = 1000; // 29857243
  // fields; protected, to be visible in derived classes
  protected ulong accountNo;
  protected decimal balance;
  protected string name;

  // constructor (overloaded): auto assign account number
  public BankAccount(string name) {
    latestAccountNo++;
    this.accountNo = latestAccountNo;
    this.name = name;
    this.balance = 0M;
  }

  // constructor (overloaded): fixed account number
  public BankAccount(ulong no, string name) {
    this.accountNo = no;
    this.name = name;
    this.balance = 0M;
  }

  // depositing money into the account
  public void Deposit(decimal x) {
    this.balance += x;
  }

  // withdrawing money from the account
  // NB: we want to override this for a ProperBankAccount, hence virtual
  public virtual void Withdraw(decimal x) {
    if (this.balance >= x) { 
      this.balance -= x;
    } else {
      throw new InsufficientBalance(String.Format("Balance too low: {0}", this.balance));
    }
  }

  // read balance field
  public decimal GetBalance() { return this.balance; }

  // show balance field
  public void ShowBalance() {
    Console.WriteLine("Current Balance: " + this.balance.ToString());
  }

  // show details of the account
  public virtual void ShowAccount() {
    Console.WriteLine("Account Number: {0}\tAccount Name: {1}\tCurrent Balance: {2}", 
		      this.accountNo, this.name, this.balance.ToString());
  }

  // Class invariants: 
  // invariant: this.balance >= 0
  public virtual bool Invariant() {
    return this.balance >= 0;
  }

  // -----------------------------------------------------------------------------
  public void RunTrans() { // works on BankAccount and ProperBankAccount
      this.ShowAccount();
      this.ShowBalance();
      Console.WriteLine("Depositing " + 600);
      this.Deposit(600);
      this.ShowBalance();
      Console.WriteLine("Withdrawing " + 400);
      try {
	this.Withdraw(400);
      } catch (InsufficientBalance e) {
	Console.WriteLine("InsufficientBalance {0} for withdrawl of {1}", this.GetBalance(), 400);
      }
      this.ShowBalance();
      Console.WriteLine("Withdrawing " + 400);
      try {
	this.Withdraw(400);
      } catch (InsufficientBalance e) {
	Console.WriteLine("InsufficientBalance {0} for withdrawl of {1}", this.GetBalance(), 400);
      }
      this.ShowBalance();
      this.ShowAccount();
    }
}

class ProperBankAccount: BankAccount {
  // overdraft field, with default get and set properties
  public decimal overdraft { get ; set ; }

  // constructor (overloaded)
  public ProperBankAccount(string name) :base(name) {
    // nothing; use set property on overdraft
  }

  // constructor (overloaded): fixed account number
  public ProperBankAccount(ulong no, string name) :base(no,name) {
    // nothing; use set property on overdraft
  }

  // Deposit is inherited from BankAccount

  // NB: override Withdraw to account for overdraft
  public override void Withdraw(decimal x) {
    if (this.balance+this.overdraft >= x) { 
      this.balance -= x;
    } else {
      throw new InsufficientBalance(String.Format("Balance (including overdraft of {0}) too low: {1}", this.overdraft, this.balance));
    }
  }

  // GetBalance and ShowBalance are inherited

  // override ShowAccount
  public override void ShowAccount() {
    base.ShowAccount();
    Console.WriteLine("\twith an overdraft of {0}", this.overdraft);
  } 

  // Class invariants: 
  // invariant: this.balance >= - this.overdraft   
  public override bool Invariant() {
    return this.balance >= - this.overdraft;
  }
}

class Tester {
  // a class for running tests from the Main method
  class RunTester {
    // RunTransactions works on BankAccount and ProperBankAccount
    public void RunTransactions(BankAccount acct) { 
      // if it has an overdraft facility, initialise its value
      ProperBankAccount pacct = acct as ProperBankAccount;
      if (pacct != null) {
	pacct.overdraft = 200M;
      }
      /* or:
      if (acct is ProperBankAccount) {
	((ProperBankAccount)acct).overdraft = 200;
      }
      */
      acct.ShowAccount();
      acct.ShowBalance();
      // first, deposit something 
      decimal x = 600M;
      Console.WriteLine("Depositing " + x);
      acct.Deposit(x);
      acct.ShowBalance();
      // then, try to withdraw something 
      decimal y = 400M;
      Console.WriteLine("Withdrawing " + y);
      try {
	acct.Withdraw(y);
      } catch (InsufficientBalance e) {
	Console.WriteLine("InsufficientBalance {0} for withdrawl of {1}", acct.GetBalance(), y);
      }
      acct.ShowBalance();
      // then, try to withdraw the same amount again
      Console.WriteLine("Withdrawing " + y);
      try {
	acct.Withdraw(y);
      } catch (InsufficientBalance e) {
	Console.WriteLine("InsufficientBalance {0} for withdrawl of {1}", acct.GetBalance(), y);
      }
      acct.ShowBalance();
      acct.ShowAccount();
    }
  }

  public static void Main(){
    RunTester t = new RunTester();

    // create a basic account
    BankAccount mine = new BankAccount("MyAccount");
    // create a proper account
    ProperBankAccount mineOvdft = new ProperBankAccount("MyProperAccount");
    // collect them in an array
    BankAccount[] accts = new  BankAccount[2] { mine, mineOvdft };

    for (int i=0; i<accts.Length; i++) {
      t.RunTransactions(accts[i]); // or: accts[i].RunTrans();
    }

    // Main0();
  }
   
 public static void Main0(){ // alternative version of the above
    // create a basic account
    BankAccount mine2 = new BankAccount("My2ndAccount");
    // run transactions
    mine2.RunTrans();

    // create a proper account
    ProperBankAccount mine2Ovdft = new ProperBankAccount("My2ndProperAccount");
    mine2Ovdft.overdraft = 250;
    // run transactions
    mine2Ovdft.RunTrans();

    try {
      Console.WriteLine("Trying to withdraw 300");
      mine2Ovdft.Withdraw(300);
    } catch (InsufficientBalance e) {
      Console.WriteLine("InsufficientBalance {0} for withdrawl of {1}", mine2Ovdft.GetBalance(), 300);
    }
    Console.WriteLine("Balance of mineOvdft {0}", mine2Ovdft.GetBalance());
    mine2Ovdft.ShowAccount();

  }
}