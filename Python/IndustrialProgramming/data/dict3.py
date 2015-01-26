#!/bin/env python
# Example of dictionaries and JSON files

import random
import sys
import json


def mkTelDict(n, names):
    """Build a dictionary of <n> random telephone numbers for <n> random first-names taken from <names>."""
    tel_dict = dict()
    for i in range(n):
        no = random.randint(1000, 9999)
        name = names[random.randint(0, 99)]
        tel_dict[name] = no

    return tel_dict


def ppTelDict(tel):
    """Pretty print a phone dictionary."""
    for k, v in tel.iteritems():
        print(k, " -> ", v)


def printNoOf(name, tel):
    """Print phone number of <name> in dictionary <tel>, or sorry message."""
    if name in tel:
        print("The tel no. of " + name + " is ", tel[name])
    else:
        print("No phone number for " + name + ", sorry!")


def readFile(fname):
    """Read contents of a file, and put each line of the file into an element of a list."""
    fd = open(fname, 'r')
    names = []
    for line in fd:
        names.append(line[0:-1])
    return names

# -----------------------------------------------------------------------------
# Constants
file = 'names.txt'
jfile = 'tel.json'

# -----------------------------------------------------------------------------
# main

if (len(sys.argv) != 2):  # expect 1 args: n
    print(
        "Usage: dict2.py <int>\n build a phone dictionary with <n> entries and write it to a JSON file")
else:
    n = int(sys.argv[1])   # read from command-line
    names = readFile(file)
    tel = mkTelDict(n, names)
    ppTelDict(tel)

    json.dump(tel, fp=open(jfile, 'w'), indent=2)
    print("Data has been written to file ", jfile)

    tel_new = json.loads(open(jfile, 'r').read())
    ppTelDict(tel_new)

    the_name = "Billy"
    printNoOf(the_name, tel_new)
    the_name = names[random.randint(0, 99)]
    printNoOf(the_name, tel_new)
