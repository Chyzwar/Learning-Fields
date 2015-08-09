#!/bin/env python


class D:
    pass         # empty class object


def method(self):     # just a function
    print (D.classVar)    # not-yet existing attribute
    print (D.__dict__['classVar'])   # same effect
    print (self.classVar)            # ditto

d = D()               # create an instance
D.method = method     # add new class attributes
D.classVar = 42
print("Testing adding of methods to class; expect value '42' printed 3 times.")
d.method()            # prints 42 (thrice)
