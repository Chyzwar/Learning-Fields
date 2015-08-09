#!/bin/env python


class Vector(object):
    # constructor

    def __init__(self, coord):
        self.coord = coord
    # turns the object into string

    def __str__(self):
        return str(self.coord)

v1 = Vector([1, 2, 3])
# performs conversion to string as above
print (v1)
