#!/bin/env python
# Simple example of properties

class Rectangle(object):
  def __init__(self, width, height):
    self.width  = width
    self.height = height
  # this generates a read only property
  area = property(
    lambda self: self.width * self.height, # anonymous function, computing the area
    doc="Rectangle area (read only).")

print("Area of a 5x2 rectange: ", Rectangle(5,2).area)
