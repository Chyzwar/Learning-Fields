# A binary search tree (BST) or ordered binary tree is a node-based binary
# tree data structure which has the following properties:

# Left subtree of a node contains only nodes with keys less than the nodes key.
# Right subtree of a node cont only nodes with keys greater than the nodes key.
# Both the left and right subtrees must also be binary search trees.


class Node:

    def __init__(self, data):
        """
        Node Constructor
        """
        self.left = None
        self.right = None
        self.data = data

    def insert(self, data):
        if self.data:
            if data < self.data:
                if self.left is None:
                    self.left = Node(data)
                else:
                    self.left.insert(data)
            elif data > self.data:
                if self.right is None:
                    self.right = Node(data)
                else:
                    self.right.insert(data)
        else:
            self.data = data

    def in_order_print(self):
        if self.left:
            print("going into left:", self.left.data)
            self.left.in_order_print()

        print(self.data)

        if self.right:
            print("going into right:", self.right.data)
            self.right.in_order_print()


root = Node(8)
root.insert(3)
root.insert(10)
root.insert(1)
root.insert(6)
root.insert(4)
root.insert(7)
root.insert(14)
root.insert(13)

root.in_order_print()
