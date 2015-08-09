#!/bin/env python
# Simple example of static methods

class Static:
  # static method
  def __bla(): print "Hello, world!"
  hello = staticmethod(__bla)

Static.hello()
Static().hello()
