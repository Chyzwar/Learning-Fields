from __future__ import division
import numpy as np
import itertools

n = 6
iters = 1000
firstzero = 0
bothzero = 0
""" The next line iterates over arrays of length n+1 which contain only -1s and 1s """
for S in itertools.product([-1, 1], repeat=n + 1):
    """For i from 0 to iters -1 """
    for i in xrange(iters):
        """ Choose a random array of length n.
            Prob 1/4 of being -1, prob 1/4 of being 1 and prob 1/2 of being 0. """
        F = np.random.choice(np.array([-1, 0, 0, 1], dtype=np.int8), size=n)
        """The next loop just makes sure that F is not all zeros."""
        while np.all(F == 0):
            F = np.random.choice(
                np.array([-1, 0, 0, 1], dtype=np.int8), size=n)
        """np.convolve(F, S, 'valid') computes two inner products between
        F and the two successive windows of S of length n."""
        FS = np.convolve(F, S, 'valid')
        if FS[0] == 0:
            firstzero += 1
        if np.all(FS == 0):
            bothzero += 1

print("firstzero: %i" % firstzero)
print("bothzero: %i" % bothzero)
