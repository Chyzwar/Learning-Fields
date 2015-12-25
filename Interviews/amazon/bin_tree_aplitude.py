class BinTree:
    x = 0
    l = None
    r = None

    def __init__(self, x, l, r):
        self.x = x
        self.l = l
        self.r = r


T = BinTree(5,
            BinTree(8,
                    BinTree(12,
                            BinTree(1,
                                    None,
                                    None),
                            None),
                    BinTree(6,
                            None,
                            None),
                    ),
            BinTree(9,
                    BinTree(7,
                            BinTree(2,
                                    None,
                                    None),
                            None),
                    BinTree(4,
                            None,
                            BinTree(3,
                                    None,
                                    None)

                            )
                    )
            )

TNone = BinTree(0, None, None)


def solution(T):
    max_amplitude = 0
    paths = get_paths(T)

    for path in paths:
        amplitude = max(path) - min(path)

        if max_amplitude < amplitude:
            max_amplitude = amplitude

    return max_amplitude
    pass


def get_paths(T):
    paths = []

    if not (T.l or T.r):
        return [[T.x]]
    if T.l:
        paths.extend([[T.x] + child for child in get_paths(T.l)])
    if T.r:
        paths.extend([[T.x] + child for child in get_paths(T.r)])
    return paths


print(solution(T))
print(get_paths(T))

print(solution(TNone))
print(get_paths(TNone))
