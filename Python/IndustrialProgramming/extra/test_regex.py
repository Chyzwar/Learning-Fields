#!/bin/env python
# Simple regular expression examples

import sys
import fileinput
import re
import string

# print all lines with 'read' event types
file='/home/hwloidl/tmp/sample_10k_lines.json'
print ("Reading from ", file)
with open(file,"r") as f:
    for line in f:                                   # read line-by-line
        if (re.search('"event_type":"read"', line)): # search for string in line
            print (line)                             # if it exists, print entire line

# as above, but split the line, and print one element per line
file='/home/hwloidl/tmp/sample_10k_lines.json'
print ("Reading from ", file)
with open(file,"r") as f:
    for line in f:
        if (re.search('"event_type":"read"', line)):
            line0 = re.sub("[{}]", "", line)     # remove {}
            for x in re.split("[ ]*,[ ]*",line0):# split ','-separated elements
                print (re.sub(':','->', x))       # replace ':' by '->'

