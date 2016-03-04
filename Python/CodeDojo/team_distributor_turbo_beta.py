import random

number_teams = 12
number_ngo = 5
equality_param = 0
generic_choices = [1, 2, 3]


ngos = [x for x in range(1, number_ngo + 1)]
team_choices = [{x : random.sample(ngos, 3)} for x in range(1, number_teams + 1)]
print("-------------")
print("Team choices:")
print(team_choices)
print("-------------")

def distribute(team_choices, ngos, equality_param):
    print("Started")
    allocation = { x : [] for x in ngos }
    popularity_index = {x: 0 for x in ngos}


    for ngo in ngos:
        for team, team_choice in team_choices.iteritems():
            if ngo in team_choice:
                allocation[ngo].push(team)

    print(allocation)
    print("finihsed")


distribute(team_choices, ngos, equality_param)
