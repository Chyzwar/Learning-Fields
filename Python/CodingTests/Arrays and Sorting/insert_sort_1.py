# N = int(input())
# items = input().split()
import sys

N = 10
items = ['2', '3', '4', '5', '6', '7', '8', '9', '10', '1']


items = [int(x) for x in items]


def insert_sort(items):
    v = items[-1]
    items = items[:-1]
    ar = [x for x in items]
    ar.append(0)

    for i in range(1, len(items) + 2):
        if i != len(items) + 1 and ar[-(i + 1)] > v:
            ar[-i] = ar[-(i + 1)]
            printlist(ar)
        else:
            ar[-i] = v
            printlist(ar)
            break


def printlist(list):
    sys.stdout.write(" ".join(str(x) for x in list))
    sys.stdout.write('\n')

insert_sort(items)

#         j = i
#         while j > 0 and items[j - 1] > items[j]:
#             items[j - 1], items[j] = items[j], items[j - 1]
#             sys.stdout.write(" ".join(str(x) for x in items))
#             sys.stdout.write('\n')
#             j -= 1
