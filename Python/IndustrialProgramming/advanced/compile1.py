#!/bin/env python

c = compile('map(lambda x:x*2,range(10))', # code
  'pseudo-file.py',      # filename for error msg
  'eval')  # or 'exec' (module) or 'single' (stm)
eval(c)
