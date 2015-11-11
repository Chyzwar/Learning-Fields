

class Client(object):

    """Client basic class"""

    def __init__(self, client_id, call_plan):
        self.client_id = client_id
        self.call_plan = call_plan

    def call_cost(self, call):
        """Abstract Method, calculate cost per call"""
        raise NotImplementedError('subclasses must override call_cost()!')

    def per_minute(self, call_type):
        """Abstract Method, calculate base cost per minute"""
        raise NotImplementedError('subclasses must override per_minute()!')
