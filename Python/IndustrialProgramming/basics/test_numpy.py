#!/bin/env python

import numpy as np
m1 = np.array([ [1,2,3],
                [7,3,4] ]); # fixed test input
# m1 = np.zeros((4,3),int); # initialise a matrix
r1 = np.ndim(m1);       # get the number of dimensions for matrix 1
m, p = np.shape(m1);    # no. of rows in m1 and no. of cols in m1
# use range(0,4) to generate all indices
# use m1[i][j] to lookup a matrix element

print("Matrix m1 is an ", r1, "-dimensional matrix, of shape ", m, "x", p)
