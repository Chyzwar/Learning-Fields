#!/bin/env python


class Vector(object):
    # constructor

    def __init__(self, coord):
        self.coord = coord

    # turns the object into string
    def __str__(self):
        return str(self.coord)

    def __getitem__(self, index):
        '''Return the coordinate with number index.'''
        return self.coord[index]

    def __getslice__(self, left, right):
        '''Return a subvector.'''
        return Vector(self.coord[left:right])

if __name__ == "__main__":
    v1 = Vector(range(5))
    print("Vector: ", str(v1))
    print("Element at index 2: ", v1[2])
    print("Slice up to index 2:", str(v1[0:2]))
