# import sys

# input_all = []

# for line in sys.stdin:
#     print(line)
#     input_all.append([int(x) for x in line.split() if x.isdigit()])


input_all = [[10, 9],
             [2, 1],
             [3, 1],
             [4, 3],
             [5, 2],
             [6, 1],
             [7, 2],
             [8, 6],
             [9, 8],
             [10, 8]]

N = input_all[0][0]
M = input_all[0][1]

Tree = input_all[1:]


def veriti_count(Tree, N):
    vert_counts = {}
    for i in range(1, N + 1):
        for edge in Tree:
            if i in edge:
                if i in vert_counts:
                    vert_counts[i] += 1
                else:
                    vert_counts[i] = 1
    return vert_counts


def even_tree(Tree, N):
    vert_counts = veriti_count(Tree, N)

        for counts in veriti_count:
            if counts.value() % 2 == 0
