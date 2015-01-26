#!/bin/env python


class Vector(object):
    # constructor

    def __init__(self, coord):
        self.coord = coord
    # turns the object into string
    # use <> as brackets, and ; as separator

    def __str__(self):
        s = "<"
        if len(self.coord) == 0:
            return s + ">"
        else:
            s = s + str(self.coord[0])
        for x in self.coord[1:]:
            s = s + ";" + str(x)
        return s + ">"

v1 = Vector([1, 2, 3])
# performs conversion to string as above
print (v1)
