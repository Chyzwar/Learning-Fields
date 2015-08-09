# import sys

# input_all = []

# for line in sys.stdin:
#     input_all.append([int(x) for x in line.split()])


input_all = [[6], [-1, -2, -3, -4, -5, -6]]

list_arrays = input_all[1::2]


def maximum_subarray(list_arrays):
    for array in list_arrays:
        tu = max_subarray_conti(array), max_subarray_non(array)
        print(str(tu).strip('(').strip(')').replace(',', ''))


def max_subarray_conti(array):
    max_ending_here = max_so_far = array[0]
    for x in array[1:]:
        max_ending_here = max(x, max_ending_here + x)
        max_so_far = max(max_so_far, max_ending_here)
    return max_so_far


def max_subarray_non(array):
    sums = sum([x for x in array if x > 0])
    if sums is 0:
        return max(array)
    else:
        return sums


maximum_subarray(list_arrays)
