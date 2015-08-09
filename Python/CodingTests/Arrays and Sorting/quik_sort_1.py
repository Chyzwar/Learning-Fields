# N = int(input())
# items = input().split()
# items = [int(x) for x in items]


array = [4, 5, 3, 7, 2]
P = array[0]


partitionA = [a for a in array if a < P]
partitionB = [b for b in array if b > P]

result = partitionA + [P] + partitionB

print(result)
# for x in result:
#     print(x, end=" ")