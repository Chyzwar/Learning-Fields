#!/bin/env python

s = sum(i * i for i in range(10))
print("Sum of squares of 0..9 (expect 285):", s)
# expect: 285

xvec = [10, 20, 30]
yvec = [7, 5, 3]
z = sum(x * y for x, y in zip(xvec, yvec))
print("Vector product of ", xvec, " and ", yvec, " is (expect 260): ", z)
# expect: 260
