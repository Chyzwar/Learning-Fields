

class CallPlan(object):

    """Call plan class, use kwargs(tariff) for flexibility
        Tariff have some default values"""

    def __init__(self, **tariff):
        self.regular_calls = tariff.get('regular_calls', 0.05)
        self.late_calls = tariff.get('late_calls', 0.02)
        self.weekend_calls = tariff.get('weekend_calls', 0.01)
