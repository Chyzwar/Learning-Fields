A = [[[] for i in range(1, 4)] for i in range(1, 8)]

A[0][0] = 5
A[0][1] = 4
A[0][2] = 4
A[1][0] = 4
A[1][1] = 3
A[1][2] = 4
A[2][0] = 3
A[2][1] = 2
A[2][2] = 4
A[3][0] = 2
A[3][1] = 2
A[3][2] = 2
A[4][0] = 3
A[4][1] = 3
A[4][2] = 4
A[5][0] = 1
A[5][1] = 4
A[5][2] = 4
A[6][0] = 4
A[6][1] = 1
A[6][2] = 1


def solution(A):
    countries_color = {}

    N = len(A)
    M = len(A[0])

    for i in range(0, N):
        for j in range(0, M):

            if A[i][j] in countries_color:

                for by_colour in countries_color[A[i][j]]:
                    for countries_point in by_colour:

                        if countries_point[0] is i:
                            if countries_point[1] is ((j + 1) or (j - 1)):

                            else:

                        if countries_point[1] is j:

            else:
                countries_color[A[i][j]] = [[(i, j)]]

    for countries_color
    return len(countries)

    pass

solution(A)
