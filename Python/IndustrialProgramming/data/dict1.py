#!/bin/env python2

# Simple example of a dictionary: phone dictionary

tel = dict([('guido', 4127), ('jack', 4098)])
# expect this
# {'jack': 4098, 'guido': 4127}

# add me to the dictionary
tel['me'] = 1234

print("After adding me ...")
print(tel)

print("After deleting me ...")
del tel['me']

for k, v in tel.iteritems():
    print ("The phone number of ", k, " is ", v)
