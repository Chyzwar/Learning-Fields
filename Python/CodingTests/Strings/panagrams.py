

# sentence = string(input())

sentence = "We promptly judged antique ivory buckles for the prize"


def is_paragram(string):
    panagram = "The quick brown fox jumps over the lazy dog"
    is_para = True

    for s in panagram.lower():
        if s not in string.lower():
            is_para = False
            break

    if is_para == False:
        print("not pangram")

    else:
        print("pangram")


is_paragram(sentence)
