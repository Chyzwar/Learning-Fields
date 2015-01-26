#!/bin/env python

x = 5
eval ('x')
# expect: 5
f = lambda x: eval('x * x')
f(4)
# expect 16
exec 'print ("The value of x is ", x)'
# expect 5
print ("Square of ", x, " is ", f(x))
