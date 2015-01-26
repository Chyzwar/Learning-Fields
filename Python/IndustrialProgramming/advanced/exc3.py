#!/bin/env python
# Testing exceptions

def divide(x, y):
    try:
        result = x / y
    except ZeroDivisionError:
        print ("division by zero!")
    else:
        print ("result is", result)
    finally:
        print ("executing finally clause")

xs = [(5,3), (8,2), (3,0), (4,1) ]
for x, y in xs:
    print("Input: ", x, y)
    divide(x,y)
    
