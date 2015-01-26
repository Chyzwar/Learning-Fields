#!/bin/env python


class Vector(object):
    # constructor

    def __init__(self, coord):
        self.coord = coord

    # turns the object into string
    def __str__(self):
        return str(self.coord)

    def __mul__(self, scalar):
        '''Multiplication with a scalar from the right.'''
        return map(lambda x: x * scalar, self.coord)

    def __rmul__(self, scalar):
        '''Multiplication with a scalar from the left.'''
        return map(lambda x: scalar * x, self.coord)

if __name__ == "__main__":
    v1 = Vector(range(5))
    print("Vector: ", str(v1))
    print("Testing scalar multiplication (from right):",  v1 * 5)
    print("Testing scalar multiplication (from left):",   5 * v1)
