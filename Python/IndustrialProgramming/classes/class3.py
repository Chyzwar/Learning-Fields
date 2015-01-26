#!/bin/env python


class Bla():
    __privateVar = 4

    def method(self):
        print (self.__privateVar)
        print (self.__class__.__dict__['_Bla__privateVar'])

print("Testing classes; expect value '4' printed 2 times.")
b = Bla()
b.method()              # prints 4 (twice)
