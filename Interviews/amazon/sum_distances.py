A = [0, 0, 0]

A[0] = 1
A[1] = 3
A[2] = -3

B = [0, 0, 0, 0, 0, 0]

B[0] = -8
B[1] = 4
B[2] = 0
B[3] = 5
B[4] = -3
B[5] = 6


def solution(A):
    max_value = 0

    for P in range(0, len(A)):
        for Q in range(0, len(A)):
            value = A[P] + A[Q] + (Q - P)

            if max_value < value:
                max_value = value

    return max_value


print(solution(A))
print(solution(B))
