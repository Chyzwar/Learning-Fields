input = [3, 2147483647, 1, 0]


import sys


def lonelyinteger(input):
    lists = list(input)
    print(lists)
    answer = [x for x in lists if lists.count(x) == 1]
    return answer

# Tail starts here
if __name__ == '__main__':
    a = int(input())
    b = map(int, input().strip().split(" "))
    print(lonelyinteger(b))


def insert_sort(ar):

    list = list(ar)[1:][0].split()
    sorted = list[:-1]

    for i in range(1, len(items)):
        j = i
        while j > 0 and items[j - 1] > items[j]:
            items[j - 1], items[j] = items[j], items[j - 1]
            sys.stdout.write(" ".join(str(x) for x in items))
            sys.stdout.write('\n')
            j -= 1


insert_sort(sys.stdin)
