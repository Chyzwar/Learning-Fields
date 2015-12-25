

def solution(N):
    for number in range(1, N + 1):
        to_print = ''

        if number % 3 == 0:
            to_print += "Fizz"

        if number % 5 == 0:
            to_print += "Buzz"

        if number % 7 == 0:
            to_print += "Woof"

        if to_print:
            print to_print
        else:
            print str(number)

    pass


solution(16)
