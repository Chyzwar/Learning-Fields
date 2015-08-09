#!/bin/env python
# Testing exceptions

while True:
  try:
    x = int(raw_input("Please enter a number: "))
    break
  except ValueError:
    print ("Not a valid number.  Try again...")
