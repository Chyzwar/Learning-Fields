class Vector(object):

    def __mul__(self, scalar):
        'Multiplication with a scalar from the right.'
        return map(lambda x: x * scalar, self.coord)

    def __rmul__(self, scalar):
        'Multiplication with a scalar from the left.'
        return map(lambda x: scalar * x, self.coord)
