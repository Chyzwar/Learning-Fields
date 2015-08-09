#!/usr/bin/env python3
# -*- coding: iso-8859-15 -*-

print("Hello world")
print("Hello", "world")          # arguments are space-separated
print("Hello", "world", sep=',') # choose your own space-separator
print("Hello", "world", end='')  # no newline at the end
print("Hello", "world", file=open('hello.txt','w'))  # write to file
n=9
print("Hello", n, "worlds")      # ints etc are converted to strings
