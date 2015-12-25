
# producer --->  proxy ---> consumer

#           ^           ^
#           :)          10msg/s

# timestamp
# 09:30:23.00
# 09:30:23.37
# 09:30:25.242

current_sec_stack = []


def filter_mess(timestamp):
    if len(current_sec_stack) < 10:
        current_sec_stack.append(timestamp)
        pass_message(timestamp)
    else:
        remove_old(current_sec_stack)
        filter_mess(timestamp)


def remove_old(current_sec_stack):
    per_sec = []

    for timestamp in current_sec_stack:
        per_sec.append(get_seccond(timestamp))

    for sec in per_sec:
        if len(sec) = 10:
            current_sec_stack.remove(sec[0])


def filter_messages(msg):
    seccond = msg[6:9]

    len(current_sec_stack[seccond])
