array = [2, 1, 3, 5, 4]


def solution(A):
    number_of_prefixes = 0

    for index in range(0, len(A)):

        if is_permutation(index, A[0:index]):
            number_of_prefixes += 1

    return number_of_prefixes
    pass


def is_permutation(P, part_list):
    set_range = {x for x in range(1, P + 1)}

    if len(set_range.intersection(part_list)) == P:
        return True
    else:
        return False

print(solution(array))
