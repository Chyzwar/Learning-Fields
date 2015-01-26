#!/bin/env python
# Show number of lines for each file given as a command line argument

import sys

for arg in sys.argv[1:]:
  try:
    f = open(arg, 'r')
  except IOError:
    print ('cannot open', arg)
  else:
    print (arg, 'has', len(f.readlines()), 'lines')
    f.close()
