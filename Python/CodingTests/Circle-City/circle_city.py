# import sys

# input_all = []

# for line in sys.stdin:
#     input_all.append([int(x) for x in line.split() if x.isdigit()])


input_all = [[5], [1, 3], [1, 4], [4, 4],
             [25, 11], [25, 12]]


def circle_defence(cities):
    for city in cities:
        if city[1] >= latitude_points(city[0]):
            print("possible")
        else:
            print("impossible")


def latitude_points(R):
	#WTF
    #WTF a(n) = 8 * A046080(n) + 4 for n > 0.


circle_defence(input_all[1:])

print(latitude_points(25))
