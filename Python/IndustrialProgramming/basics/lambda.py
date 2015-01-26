#!/bin/env python

def make_incrementor(n):
  return lambda x: x + n

f = make_incrementor(42)
print ("increment 0 by 42 ", f(0))
print ("increment 1 by 43 ", f(1))

