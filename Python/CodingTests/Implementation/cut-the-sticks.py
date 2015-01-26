# N = int(input())
# items = input().split()

N = 6
items = ['5', '4', '4', '2', '2', '8']


items_int = [int(x) for x in items]
shortening = []
shortening_list = []


def cut_the_sticks(sticks):
    if len(sticks) == 0:
        sticks.append(0)
        return
    else:
        sticks_sorter = shorten_sticks(sticks)
        shortening.append(sticks_sorter)
        cut_the_sticks(sticks_sorter)


def shorten_sticks(sticks):
    shortest_stick = min(sticks)
    shortened_sticks = []
    shortening = 0

    for stick in sticks:
        shortened_sticks.append(stick - shortest_stick)
        shortening += 1

    shortened_sticks = [x for x in shortened_sticks if x is not 0]
    shortening_list.append(shortening)
    return shortened_sticks


cut_the_sticks(items_int)

for x in shortening_list:
    print(x)
