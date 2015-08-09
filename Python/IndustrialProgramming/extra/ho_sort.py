#!/bin/env python
# Higher order sorting in Python

import sys
import random
import os
import fractions
#import json

# -----------------------------------------------------------------------------
# constants

names = ["Jack", "Thomas", "Joshua", "Oliver", "Harry",
         "James", "William", "Samuel", "Daniel", "Charlie"]

# -----------------------------------------------------------------------------
# functions


def gcd(x, y):
    """Compute the greatest common divisor, using Euclid's algorithm (top level function)"""
    assert(x >= 0 and y >= 0)  # sanity check on the input
    if (x < y):
        res = gcd_rec(y, x)  # uses recursive version in this case
    else:
        res = gcd_rec(x, y)  # uses recursive version in this case
    return res


def gcd_rec(x, y):
    """Compute the greatest common divisor, using Euclid's algorithm (recursive version)"""
    assert(x >= 0 and y >= 0 and x >=
           y)  # NB: x>=y to save that test in the recursive calls
    if (y < 1):
        res = x
        # base case
    else:
        res = gcd_rec(y, x % y)  # recursion case
    return res


def mkList(n, m=65535):
    """Generate a list of <n> random elements bounded by <m>."""
    list = []
    for i in range(n):
        no = random.randint(0, m)
        list.append(no)

    return list


def mkFavNums(n, names, m=65535):
    """For each person in <names> generate a list of <n> favourite numbers each bounded by <m>."""
    dict = {}
    for nam in names:
        list = []
        for i in range(n):
            no = random.randint(0, m)
            list.append(no)
        dict[nam] = list
    return dict


def countMatches(x, dict):
    """How often does n appear as a favourite number?"""
    n = 0
    for k in dict.keys():
        if x in dict[k]:
            n += 1
    return n


def mostFavNum(dict):
    """Return the most favourite number."""
    # we use sets to collect the numbers
    xs = set([])
    # iterate over the dictionary entries
    for k in dict.keys():
        xs = xs | set(dict[k])
    # decorate each number with the matches, and use this as 1st arg in the
    # tuple
    xs_dec = [(countMatches(x, dict), x) for x in xs]
    # sort the list by first component in the tuple (no of matches)
    xs_dec.sort()
    # return xs_dec[-10:-1]  # return largest 10 values, if you prefer (for
    # testing)
    n, x = xs_dec[-1]  # largest elem
    return x          # return it's value


def mostFavNumGen(dict, decorator):
    """Return the most favourite number, using a decorator function argument."""
    # we use sets to collect the numbers
    xs = set([])
    # iterate over the dictionary entries
    for k in dict.keys():
        xs = xs | set(dict[k])
    # decorate each number with the matches, and use this as 1st arg in the
    # tuple
    xs_dec = [(decorator(x, dict), x) for x in xs]
    # sort the list by first component in the tuple (no of matches)
    xs_dec.sort()
    # return xs_dec[-10:-1]  # return largest 10 values, if you prefer (for
    # testing)
    n, x = xs_dec[-1]  # largest elem
    return x          # return it's value


def my_cmp(x, y):
    """Custom comparison operator to return inverse of the default order."""
    return (-1) * (x - y)


def gcd_cmp(x, y):
    """Weird comparison that computes the gcd of two numbers."""
    z = gcd(x, y)
    if z == 1:
        return 1
    else:
        return -1

# -----------------------------------------------------------------------------
# main

if (len(sys.argv) != 2):  # expect 0 args:
    print("Usage:  ", argv[0], " <n> ... sort a list of <n> random numbers")
else:
    n = int(sys.argv[1])   # read from command-line

    # -------------------------------------------------------
    # I. generic sorting of integer values
    # -------------------------------------------------------
    # Generate input
    print ("Generating a random list ...")
    xs = mkList(n)
    print (xs)
    print ("Sorting list using default < ...")
    ys = list(xs)  # this clones the input
    ys.sort()
    print (ys)
    # zs = xs
    # print ("Input ...")
    # print (zs)
    print (
        "Doing a custom sort, here descending rather than ascending order ...")
    zs = list(xs)
    zs.sort(cmp=my_cmp)  # specify a function to use for comparison
    print (zs)
    print ("Doing a custom sort, here in gcd order ...")
    zs = list(xs)
    zs.sort(cmp=gcd_cmp)  # specify a function to use for comparison
    print (zs)

    # -------------------------------------------------------
    # II. towards the CW
    # -------------------------------------------------------
    # Generate input
    print ("Generating a favourite numbers ...")
    favs = mkFavNums(n, names, 13)
    print (favs)
    print (
        "Computing most favourite number (the one with most occurrences) ...")
    print(mostFavNum(favs))

    print ("Now using higher-order functions to do the sorting ...")
    print (
        "We declare, that the most favourite number should be the largest number ...")
    print(mostFavNumGen(favs, (lambda x, y: x)))
    print (
        "We declare, that the most favourite number should be the one with most matches ...")
    print(mostFavNumGen(favs, countMatches))
