from random import randint
import functools
import timeit
import time


array = [3, 1, 2, 4, 3]
big_array = [randint(0, 1000000) for x in range(1, 100000)]


def timeit(func):
    @functools.wraps(func)
    def newfunc(*args, **kwargs):
        startTime = time.time()
        func(*args, **kwargs)
        elapsedTime = time.time() - startTime
        print('function [{}] finished in {} ms'.format(
            func.__name__, int(elapsedTime * 1000)))
    return newfunc


@timeit
def solution(A):
    sidesA = [sum([x for x in A[0:y]]) for y in range(1, len(A))]
    sidesB = [sum([x for x in A[y::]]) for y in range(1, len(A))]

    return min([abs(x - y) for x, y in zip(sidesA, sidesB)])


@timeit
def solution_faster(A):
    current_diff = None

    for i in range(1, len(A)):
        diff = abs(sum(A[0:i]) - sum(A[i::]))

        if current_diff is None:
            current_diff = diff

        if diff is 0:
            current_diff = 0
            continue

        if current_diff > diff:
            current_diff = diff

    print(current_diff)


# solution_faster(array)
solution_faster(big_array)
# solution(array)
# solution(big_array)
