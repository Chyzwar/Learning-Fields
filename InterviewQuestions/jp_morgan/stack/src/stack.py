class Stack:

    def __init__(self):
        self.items = []

    def is_empty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        if self.is_empty():
            raise EmptyStackError()
        return self.items.pop()

    def size(self):
        return len(self.items)

    def __str__(self):
        return str(self.items)


class EmptyStackError(Exception):

    def __init__(self):

        # Call the base Exception constructor
        super(EmptyStackError, self).__init__('Stack is empty')
