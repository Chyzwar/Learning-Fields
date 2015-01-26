#!/bin/env python

import math     # sqrt
import operator  # operators as functions


class Vector(object):
    # constructor

    def __init__(self, coord):
        self.coord = coord

    # turns the object into string
    def __str__(self):
        return str(self.coord)

    def __abs__(self):
        '''Vector length (Euclidean norm).'''
        return math.sqrt(sum(x * x for x in self.coord))

    def __add__(self, other):
        '''Vector addition.'''
        return map(operator.add, self.coord, other.coord)

if __name__ == "__main__":
    v1 = Vector(range(5))
    print("Vector: ", str(v1))
    print(Vector.__abs__.__doc__, abs(v1))
    print(Vector.__add__.__doc__, v1 + v1)
