# import sys
import itertools

# input_all = []

# for line in sys.stdin:
#     print(line)
#     input_all.append([int(x) for x in line.split()])


# n = input_all[0]
# relations = input_all[1:]


n = 6
relations = [['Jon', 'Mark'],
             ['Jon', 'David'],
             ['Mark', 'Paul'],
             ['Paul', 'Lee'],
             ['Paul', 'Steve']]

employees = set(itertools.chain(*relations))


def company_hierarchy(employees, relations, n):
    levels = {}

    for person in employees:
        levels[person] = find_level(relations, person, 0)
    return levels


def find_level(relations, person, level):
    person_man = person_manager(person, relations)
    if person_man is not False:
        level = level + find_level(relations, person_man, 1)
    return level


def person_manager(person, relations):
    manager = ""
    for relation in relations:
        if person is relation[1]:
            manager = relation[0]
            break
    if manager is not "":
        return manager
    else:
        return False

print(company_hierarchy(employees, relations, n))
