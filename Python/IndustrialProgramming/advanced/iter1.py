#!/bin/env python
# A simple iterator example


class Reverse:

    """Iterator for looping over sequence backwards."""

    def __init__(self, data):
        self.data = data
        self.index = len(data)

    def __iter__(self):
        return self

    def next(self):
        if self.index == 0:
            raise StopIteration
        self.index = self.index - 1
        return self.data[self.index]

it = iter(Reverse(range(5)))
try:
    while (True):
        x = it.next()
        print (x)
except StopIteration:
    print("Stopped with iteration.")
