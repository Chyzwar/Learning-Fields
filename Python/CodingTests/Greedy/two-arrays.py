# import sys

# input_all = []

# for line in sys.stdin:
#     input_all.append([int(x) for x in line.split() if x.isdigit()])
import itertools


# 5
# 2 4
# 1 3
# 3 1
# 5 5
# 2 3 1 1 1
# 1 3 4 3 3
# 10 9
# 1 5 1 4 4 2 7 1 2 2
# 8 7 1 7 7 4 4 3 6 7
# 10 9
# 3 6 8 5 9 9 4 8 4 7
# 5 1 0 1 6 4 1 7 4 3
# 10 4
# 4 4 3 2 1 4 4 3 2 4
# 2 3 0 1 1 3 1 0 0 2

input_all = [[4],
             [2, 4],
             [1, 3], [3, 1],
             [2, 4],
             [1, 3], [3, 1],
             [2, 4],
             [1, 3], [3, 1],
             [73, 95],
             [54, 65, 7, 38, 39, 90, 80, 93, 38, 75, 11, 42, 1, 53, 64, 28, 92, 91, 46,
              7, 91, 35, 61, 1, 40, 67, 86, 55, 15, 68, 64, 83, 13, 4, 82, 60, 63,
                 52, 74, 68, 54, 81, 36, 18, 53, 23, 48, 34, 58, 2, 36, 64, 50, 34,
              10, 7, 78, 93, 59, 44, 45, 1, 87, 47, 36, 66, 1, 24, 60, 12, 46, 81, 30],
             [1, 41, 25, 9, 84, 34, 53, 10, 25, 48, 7, 52, 20, 52, 1, 50, 9, 29, 38,
              21, 1, 88, 40, 81, 1, 48, 11, 31, 68, 2, 35, 52, 53, 46, 49, 83, 1,
              8, 39, 75, 34, 23, 67, 1, 43, 87, 54, 82, 58, 82, 29, 20, 26, 12, 10,
              57, 84, 91, 79, 49, 69, 30, 32, 43, 24, 91, 22, 73, 67, 27, 87, 47, 30]
             ]


T = input_all[0][0]

KN_index = [1 + 3 * x for x in range(0, T)]
AB_index = set([x for x in range(1, 3 * T + 1)]) - set(KN_index)


KN = [input_all[k] for k in KN_index]
AB = [input_all[k] for k in AB_index]


def two_arrays(T, KN, AB):
    for i in range(0, T):
        A = AB[i * 2]
        B = AB[(i * 2) + 1]
        K = KN[i][1]
        N = KN[i][0]
        if (min(A) + max(B)) < K:
            print("NO")
        else:
            A_perms = permute_in_place(A)
            B_perms = permute_in_place(B)
            check_sum(K, N, A_perms, B_perms)


def check_sum(K, N, A_perms, B_perms):
    true_cunter = 0

    for A_perm in A_perms:
        summs = [zip(A_perm, B_perm) for B_perm in B_perms]
        for ab_sum in summs:
            summed_tuples = [x for x in [sum(x) for x in ab_sum]]

            if sum(i >= K for i in summed_tuples) == N:
                true_cunter += 1

    if true_cunter == 0:
        print("NO")
        return
    else:
        print("YES")
        return


def permute_in_place(a):
    a.sort()
    yield list(a)

    if len(a) <= 1:
        return

    first = 0
    last = len(a)
    while 1:
        i = last - 1

        while 1:
            i = i - 1
            if a[i] < a[i + 1]:
                j = last - 1
                while not (a[i] < a[j]):
                    j = j - 1
                a[i], a[j] = a[j], a[i]  # swap the values
                r = a[i + 1:last]
                r.reverse()
                a[i + 1:last] = r
                yield list(a)
                break
            if i == first:
                a.reverse()
                return

two_arrays(T, KN, AB)
