

class CallPlan(object):

    """Call plan class, use dictionary for tariff """

    def __init__(self, call_plan_id, tariffs):
        self.__call_plan_id = call_plan_id
        self.__plan_tariffs = tariffs

    @property
    def plan_tariffs(self):
        return self.__plan_tariffs

    @plan_tariffs.setter
    def plan_tariffs(self, value):
        self.__plan_tariffs = value
