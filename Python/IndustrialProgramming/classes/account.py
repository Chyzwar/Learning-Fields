#!/bin/env python
# Bank account example, from the C# revision class

# define a new exception


class InsufficientBalance(Exception):
    pass


class BankAccount:

    "Plain bank account."
    __latestAccountNo = 1000
    # NB: this init is done too late, when executing the constructor

    def __init__(self, name, accountNo=0, balance=0):
        # make sure that the variable is defined
        if BankAccount.__dict__.has_key(BankAccount.__latestAccountNo):
            BankAccount.__latestAccountNo += 1
        else:
            BankAccount.__latestAccountNo = 1000
        # check whether we have an accountNo input, otw pickup value from __latestAccountNo
        if accountNo == 0:  # this means, pick-up value from local var
            self.accountNo = BankAccount.__latestAccountNo
        else:
            self.accountNo = accountNo
        self.name = name
        self.balance = balance

    def Deposit(self, x):
        """Depositing money into the account."""
        self.balance += x

    def Withdraw(self, x):
        """Withdrawing money from the account."""
        if self.balance >= x:
            self.balance -= x
        else:
            raise InsufficientBalance, "Balance too low: %d" % self.balance

    def GetBalance(self):
        """Return the current balance on the account."""
        return self.balance

    def ShowBalance(self):
        """Display the current balance on the account."""
        print ("Current Balance: ", self.balance)

    def ShowAccount(self):
        """Display details of the BankAccount."""
        print ("Account Number: ", self.accountNo, "\tAccount Name: ", self.name, "\tCurrent Balance: ", self.balance)


class ProperBankAccount(BankAccount):

    """Bank account with overdraft."""

    def __init__(self, name, accountNo=0, balance=0):
        """Constructor"""
        BankAccount.__init__(self, name, accountNo, balance)
        self.overdraft = 0

    def Withdraw(self, x):
        """Withdrawing money from a ProperBankAccount account."""
        if self.balance + self.overdraft >= x:
            self.balance -= x
        else:
            raise InsufficientBalance, "Balance (incl overdraft) too low: %d" % self.balance

    def ShowAccount(self):
        """Display details of the BankAccount."""
        BankAccount.ShowAccount(self)
        print ("\t with an overdraft of ", self.overdraft)


class Tester:

    """Tester class."""

    def RunTrans(self, acct):
        """Run a sequence of transactions."""
        if (isinstance(acct, ProperBankAccount)):  # test class membership
            acct.overdraft = 200                 # if ProperBankAccount, set overdraft
        acct.ShowAccount()
        acct.ShowBalance()
        # first, deposit something
        x = 600
        print("Depositing ", )
        acct.Deposit(x)
        acct.ShowBalance()
        # then, try to withdraw something
        y = 400
        print("Withdrawing ", y)
        try:
            acct.Withdraw(y)
        except InsufficientBalance:
            print("InsufficientBalance ", acct.GetBalance(), " for withdrawl of ", y)
        acct.ShowBalance()
        # then, try to withdraw the same amount again
        print("Withdrawing ", y)
        try:
            acct.Withdraw(y)
        except InsufficientBalance:
            print("InsufficientBalance ", acct.GetBalance(), " for withdrawl of ", y)
        acct.ShowBalance()
        acct.ShowAccount()


# main:
if __name__ == '__main__':  # check whether this module is the main module
    t = Tester()
    # generate a tester instance

    # create a basic account; NB: no 'new' needed
    mine = BankAccount("MyAccount")
    # create a proper account; NB: no 'new' needed
    mineOvdft = ProperBankAccount("MyProperAccount")

    # t.RunTrans(mine)
    # t.RunTrans(mineOvdft)

    # put both accounts into a list; NB: polymorphic
    accts = [mine, mineOvdft]
    # iterate over the list
    for acct in accts:
            # run transactions on the current account
        t.RunTrans(acct)
