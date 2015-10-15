from src.billing.client_billing import ClientBilling
from src.client.client_factory import ClientFactory
from src.call.call_plan import CallPlan
from src.call.call import Call


from random import randint, choice

# Simple Script runing tests on billing systems

# mock some client data:
client_list = [
    {"client_id": 232, "call_plan_id": 1, "client_type": 'new'},
    {"client_id": 245, "call_plan_id": 1, "client_type": 'new'},
    {"client_id": 267, "call_plan_id": 1, "client_type": 'new'},
    {"client_id": 268, "call_plan_id": 1, "client_type": 'new'},
    {"client_id": 299, "call_plan_id": 1, "client_type": 'new'},
    {"client_id": 306, "call_plan_id": 1, "client_type": 'new'},
    {"client_id": 307, "call_plan_id": 1, "client_type": 'standard'},
    {"client_id": 308, "call_plan_id": 1, "client_type": 'standard'},
    {"client_id": 309, "call_plan_id": 1, "client_type": 'standard'},
    {"client_id": 400, "call_plan_id": 1, "client_type": 'standard'},
    {"client_id": 401, "call_plan_id": 1, "client_type": 'standard'},
    {"client_id": 402, "call_plan_id": 1, "client_type": 'standard'},
]

# define basic call plan
call_plan = [
    {"call_plan_id": 1,
     "tariffs": {
         "regular_call": 0.05,
         "late_call": 0.02,
         "weekend_call": 0.01,
         "international_rate": 2.00
     }}
]

# define basic call types
call_types = ["regular_call", "late_call", "weekend_call"]

# create basic call_plan
client_plan = CallPlan(call_plan[0], call_plan[0]['tariffs'])

client_factory = ClientFactory()
client_billing = ClientBilling()


def fake_call(client):
    """create fake call"""
    random_type = call_types[randint(0, 2)]
    call_duration = randint(0, 20)
    is_international = choice([True, False])

    call = Call(random_type, call_duration, is_international, client.client_id)
    print str(call)

    return call

for client in client_list:

    # Create client object for each fake client
    client = client_factory.factory(
        client['client_id'], client_plan, client['client_type'])

    # generate pseudorandom call_history
    call_history = [fake_call(client) for x in range(1, randint(0, 10))]

    # produce billing
    client_bill = client_billing.make_billing(client, call_history)
    print("Total for client_id: " +
          str(client.client_id) + " is " + str(client_bill))
