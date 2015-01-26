#!/bin/env python

def fibs(n):
  """Return the Fibonacci series up to n."""
  result = []
  a, b = 0, 1
  while b < n:
    result.append(b)
    # see below
    a, b = b, a+b
  return result

# body
f100 = fibs(100)  # call it
print(f100)       # write the result

