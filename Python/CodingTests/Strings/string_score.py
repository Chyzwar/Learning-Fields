

string_1 = "abcdfcba"
string_2 = "abhaa"


def string_score(string):
    score = 0
    len_str = len(string)
    for index in range(0, len_str / 2):
        if string[index] is string[len_str - (index + 1)]:
            score = score + 1

    return score


# print(string_score(string_1))
print(string_score(string_2))
