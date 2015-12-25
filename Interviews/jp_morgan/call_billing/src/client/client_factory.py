from new_client import NewClient
from standard_client import StandardClient


class ClientFactory(object):

    """ClientFactory manage creation of Client objects"""

    def factory(self, client_id, call_plan, client_type):
        """Create Client object type based on client_type"""
        if client_type is "new":
            return NewClient(client_id, call_plan, client_type)

        if client_type is "standard":
            return StandardClient(client_id, call_plan, client_type)
