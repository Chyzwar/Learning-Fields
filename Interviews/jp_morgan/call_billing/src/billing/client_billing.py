

class ClientBilling(object):

    """ClientBilling class, work with billings """

    def make_billing(self, client, call_history):
        """Make billiong for client with call history"""
        total = 0

        for call in call_history:
            total += client.call_cost(call)

        return total
