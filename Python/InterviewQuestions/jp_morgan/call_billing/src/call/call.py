

class Call(object):

    """Call class"""

    def __init__(self, type, call_duration, is_international, client_id):
        self.__call_type = type
        self.__call_duration = call_duration
        self.__is_international = is_international
        self.__client_id = client_id

    def __str__(self):
        """str method"""

        strText = \
            " Type: " + str(self.call_type) + \
            " Duration: " + str(self.call_duration) + \
            " is_international: " + str(self.is_international)

        return strText

    @property
    def call_type(self):
        return self.__call_type

    @call_type.setter
    def call_type(self, value):
        self.__call_type = value

    @property
    def call_duration(self):
        return self.__call_duration

    @call_duration.setter
    def call_duration(self, value):
        self.__call_duration = value

    @property
    def is_international(self):
        return self.__is_international

    @is_international.setter
    def is_international(self, value):
        self.__is_international = value
