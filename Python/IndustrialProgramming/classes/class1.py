#!/bin/env python


class C:

    """Purpose-free demo class."""
    classVar1 = 42

    def method1(self):
        "Just a random method."
        print ("classVar1 = %d" % C.classVar1)

print("Testing classes; expect value '42' printed 2 times.")
X = C                 # alias the class object
x = X()               # create an instance of C
X.method1(x)          # call method (class view)
x.method1()           # call method (instance view)
