# import sys

# input_all = []

# for line in sys.stdin:
#     print(line)
#     input_all.append([int(x) for x in line.split() if x.isdigit()])
#

# https://www.hackerrank.com/challenges/mark-and-toys

input_all = [[7, 50], [1, 12, 5, 111, 200, 1000, 10]]

items = input_all[1]
K = input_all[0][1]


def max_toys(K, items):
    items.sort()
    N = 0
    summed = 0
    for item in items:
        if summed + item < K:
            summed += item
            N += 1
    return N


print(max_toys(K, items))

