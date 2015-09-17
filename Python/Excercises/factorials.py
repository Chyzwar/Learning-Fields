from functools import wraps


def memo(f):
    """Memoizing decorator for dynamic programming."""
    @wraps(f)
    def func(*args):
        if args not in func.cache:
            func.cache[args] = f(*args)
        return func.cache[args]
    func.cache = {}
    return func


@memo
def fact(n):
    if n == 0 or n == 1:
        return 1
    else:
        return n * fact(n - 1)
