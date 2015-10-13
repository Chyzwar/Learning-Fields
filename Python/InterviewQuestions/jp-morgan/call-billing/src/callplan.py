

class CallPlan(object):

    """Call plan class, use kwargs(tariff) for flexibility
        Tariff have some default values"""

    def __init__(self, **tariff):
        self.regular_calls = tariff.get('regular_calls', 0.05)
        self.late_calls = tariff.get('late_calls', 0.02)
        self.weekend_calls = tariff.get('weekend_calls', 0.01)

    @property
    def x(self):
        """I'm the 'x' property."""
        print "getter of x called"
        return self._x

    @x.setter
    def x(self, value):
        print "setter of x called"
        self._x = value

    @x.deleter
    def x(self):
        print "deleter of x called"
        del self._x