input = [3, 2147483647, 1, 0]

list = [int(x) for x in input]


for x in list[1:]:
    print(~x & 0xFFFFFFFF)
