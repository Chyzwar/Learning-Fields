#!/bin/env python
# Simple example of static methods

class Static:
  val = 5
  # class method
  def sqr(c): return c.val * c.val
  sqr = classmethod(sqr)

print("The square of ", Static.val, " is ", Static.sqr())
print("The square of ", Static.val, " is ", Static().sqr())
