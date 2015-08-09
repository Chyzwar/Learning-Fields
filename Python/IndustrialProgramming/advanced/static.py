#!/bin/env python
# Simple example of properties

class Static:
  val = 5
  # class method
  def sqr(c): return c.val * c.val
  sqr = classmethod(sqr)

Static.sqr()
Static().sqr()
