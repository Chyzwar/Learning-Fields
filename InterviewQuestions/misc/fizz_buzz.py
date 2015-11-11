

def solution(N):
    for number in range(1, N + 1):
        to_print = ''
        is_fizz = False

        if number % 3 == 0:
            to_print += "Fizz"
            is_fizz = True

        if number % 5 == 0:
            to_print += "Buzz"
            is_fizz = True

        if number % 7 == 0:
            to_print += "Woof"
            is_fizz = True

        if is_fizz:
            print to_print
        else:
            print str(number)

    pass


solution(16)
