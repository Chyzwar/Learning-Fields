#!/bin/env python


def reverse(data):
    """Generator, traversing the data in reverse order."""
    for index in range(len(data) - 1, -1, -1):
        yield data[index]

if __name__ == "__main__":
    for char in reverse('golf'):
        print (char)
    # espect: f l o g
