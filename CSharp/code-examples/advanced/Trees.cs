// Exercise from Lecture 4 and 6: Binary Search Tree

using System;
using System.Collections;
using System.Collections.Generic;

// exceptions used in either Tree or Node
class TreeException: System.Exception
{
    public TreeException(string msg): base(msg) { }
}

public class Node<T>   // generic over type of value in each node
    where T: IComparable // T implements a CompareTo method
{
    // Private member-variables
    private T data;
    private Node<T> left;
    private Node<T> right;

    // delegates used in this class
    public delegate T TreeMapperDelegate(T t);

    // constructors
    public Node()
    {
        this.Left = null;
        this.Right = null;
    }
    public Node(T x)
    {
        this.data = x;
        this.Left = null;
        this.Right = null;
    }
    public Node(T x, Node<T> left, Node<T> right)
    {
        this.data = x;
        this.Left = left;
        this.Right = right;
    }

    // properties accessing fields
    public T Value
    {
        get
        {
            return data;
        }
        set
        {
            data = value;
        }
    }

    public Node<T> Left
    {
        get
        {
            return this.left;
        }
        set
        {
            this.left = value;
        }
    }

    public Node<T> Right
    {
        get
        {
            return this.right;
        }
        set
        {
            this.right = value;
        }

    }

    // -----------------------------------------------------------------------------
    // basic operations on Nodes
    public void Insert (T x)
    {
        if (this.Value.CompareTo(x) < 0)
        {
            if (this.Right == null)
            {
                this.Right = new Node<T>(x);
            }
            else
            {
                this.Right.Insert(x);
            }
        }
        else
        {
            if (this.Left == null)
            {
                this.Left = new Node<T>(x);
            }
            else
            {
                this.Left.Insert(x);
            }
        }
    }

    public bool Find (T x)
    {
        if (this.Value.CompareTo(x) == 0)
        {
            return true;
        }
        else if (this.Value.CompareTo(x) < 0)
        {
            if (this.Right == null)
            {
                return false;
            }
            else
            {
                return this.Right.Find(x);
            }
        }
        else
        {
            if (this.Left == null)
            {
                return false;
            }
            else
            {
                return this.Left.Find(x);
            }
        }
    }

    /* generic depth-first traversal */
    public LinkedList<T> dfs()
    {
        LinkedList<T> result = new LinkedList<T>();
        this.dfsAux(result);
        return result;
    }

    public void dfsAux(LinkedList<T> accum)
    {
        if (this.Left != null)
        {
            this.Left.dfsAux(accum);
        }
        accum.AddLast(this.Value);
        if (this.Right != null)
        {
            this.Right.dfsAux(accum);
        }
    }

    // -----------------------------------------------------------------------------
    // higher-order functions over trees
    public void mapTree(TreeMapperDelegate f)
    {
        this.Value = f(this.Value);
        if (this.Left == null)  { }
        else
        {
            this.Left.mapTree(f);
        }
        if (this.Right == null) { }
        else
        {
            this.Right.mapTree(f);
        }
        return;
    }

    public override string ToString()
    {
        return this.ToStringIndent(0);
    }

    public string ToStringIndent(int n)
    {
        string str = "";
        for (int i = 0; i < n; i++)
        {
            str += " ";
        }
        return str + this.data.ToString() + "\n" +
               ((this.Left == null) ? str + " ." + "\n" : this.Left.ToStringIndent(n + 1)) +
               ((this.Right == null) ? str + " ." + "\n" : this.Right.ToStringIndent(n + 1)) ;
    }
}

public class Tree<T> where T: IComparable
{
    private Node<T> root = null;

    public Tree()
    {
        root = null;
    }

    public virtual void Clear()
    {
        root = null;
    }

    public Node<T> Root
    {
        get
        {
            return root;
        }
        set
        {
            root = value;
        }
    }

    public void Insert(T x)
    {
        if (this.Root == null)
        {
            this.Root = new Node<T>(x);
        }
        else
        {
            this.Root.Insert(x);
        }
    }

    public bool Find (T x)
    {
        if (this.Root == null)
        {
            return false;
        }
        else
        {
            return this.Root.Find(x);
        }
    }

    // ToDo: implement the following method, which should be a class invariant
    // public bool WellFormedTree ( ) { ... }

    public LinkedList<T> dfs()
    {
        return this.Root.dfs();
    }

    public override string ToString()
    {
        if (this.Root == null)
        {
            return "<empty>";
        }
        else
        {
            return this.Root.ToStringIndent(0);
        }
    }

    public void print (Fixity how)
    {
        IEnumerator iter;
        string str;
        switch (how)
        {
        case Fixity.Prefix:
            iter = new PrefixEnumerator(this);
            str = "PrefixEnumerator";
            break;
        case Fixity.Infix:
            iter = new InfixEnumerator(this);
            str = "InfixEnumerator";
            break;
        case Fixity.Postfix:
            iter = new PostfixEnumerator(this);
            str = "PostfixEnumerator";
            break;
        default:
            iter = new InfixEnumerator(this);
            str = "InfixEnumerator";
            break;
        }

        System.Console.Write("Flatten tree using " + str + " : [");
        if (iter.MoveNext())
        {
            System.Console.Write("{0}", iter.Current);
        }
        while (iter.MoveNext())
        {
            System.Console.Write(",{0}", iter.Current);
        }
        System.Console.WriteLine("]");

        /*
        System.Console.Write("Flatten tree using "+str+": ");
        foreach (int x in this) {
        System.Console.Write(" {0}", x);
             }
             System.Console.WriteLine("");
             */
    }

    // -------------------------------------------------------
    // IEnumerator implementation
    // This does a depth-first, pre-fix traversal of the tree
    // see http://msdn.microsoft.com/en-us/library/78dfe2yb.aspx

    public enum Fixity { Prefix, Infix, Postfix };

    /*
      public IEnumerator GetEnumerator() {
      return (IEnumerator) new PrefixEnumerator(this);
      }
    */

    private class PrefixEnumerator : IEnumerator
    {
        private Node<T> curr_node;
        private Node<T> root;
        private Stack stack;
        public  const string Name = "PrefixEnumerator";

        public PrefixEnumerator ()
        {
            throw new TreeException("PrefixEnumerator: Missing argument to constructor");
        }

        public PrefixEnumerator (Tree<T> t)
        {
            this.curr_node = null;    // NB: needs to start with -1;
            this.root = t.root;
            this.stack = new Stack();
        }

        // go up in the tree, and find the next node
        private bool Unwind()
        {
            if (stack.Count == 0)   // we are done
            {
                curr_node = null;
                return false;
            }
            else
            {
                Node<T> parent = (Node<T>)this.stack.Pop();
                if (parent.Left == curr_node)   // I am a left child
                {
                    if (parent.Right != null)     // and there is a right sub-tree
                    {
                        stack.Push((object)parent);
                        curr_node = parent.Right;   // pick it as next
                    }
                    else
                    {
                        curr_node = parent;         // otw, go one step up and unwind further
                        return Unwind();
                    }
                }
                else if (parent.Right == curr_node)     // I am a right child
                {
                    curr_node = parent;                   // otw, go one step up and unwind further
                    return Unwind();
                }
                else
                {
                    throw new TreeException(String.Format("Ill-formed spine during Unwind(): node {0} should be a parent of {1}", parent.ToString(),  curr_node.ToString()));
                }
                return true;
            }
        }

        public bool MoveNext( )
        {
            // both sub-trees haven't been traversed, yet
            if (this.curr_node == null)
            {
                this.curr_node = this.root;
                return true;
            }
            else
            {
                if (curr_node.Left != null)
                {
                    stack.Push((object)curr_node);
                    this.curr_node = curr_node.Left;
                    return true;
                }
                else if (curr_node.Right != null)
                {
                    stack.Push((object)curr_node);
                    this.curr_node = curr_node.Right;
                    return true;
                }
                else     // I am a leaf
                {
                    return (Unwind());
                }
            }
        }

        public void Reset( )
        {
            this.curr_node = null;
        }

        public object Current
        {
            get
            {
                return this.curr_node.Value ;
            }
        }
    }
    // -------------------------------------------------------
    // IEnumerator implementation
    // This does a depth-first, in-fix traversal of the tree
    // see http://msdn.microsoft.com/en-us/library/78dfe2yb.aspx

    public IEnumerator GetEnumerator()
    {
        return (IEnumerator) new InfixEnumerator(this);
    }

    private class InfixEnumerator : IEnumerator
    {
        private Node<T> curr_node;
        private Node<T> root;
        private Stack stack;
        public  const string Name = "InfixEnumerator";

        public InfixEnumerator ()
        {
            throw new TreeException("InfixEnumerator: Missing argument to constructor");
        }

        public InfixEnumerator (Tree<T> t)
        {
            this.curr_node = null;    // NB: needs to start with -1;
            this.root = t.root;
            this.stack = new Stack();
        }

        // go up in the tree, and find the next node
        private bool Unwind()
        {
            if (stack.Count == 0)   // we are done
            {
                curr_node = null;
                return false;
            }
            else
            {
                Node<T> parent = (Node<T>)this.stack.Pop();
                if (parent.Left == curr_node)   // I am a left child
                {
                    curr_node = parent;
                    return true;
                }
                else
                {
                    curr_node = parent;         // otw, go one step up and unwind further
                    return (Unwind());
                }
            }
        }

        // go down to left-most node in this tree
        private void LeftMost(Node<T> node)
        {
            if (node.Left != null)
            {
                stack.Push((object)node);
                LeftMost(node.Left);
            }
            else
            {
                stack.Push((object)node);
                return;
            }
        }

        private bool EnterTree(Node<T> node)
        {
            // need to traverse both sub-trees
            LeftMost(node);     // fills stack
            if (stack.Count == 0)
            {
                return false;
            }
            else
            {
                curr_node = (Node<T>) stack.Pop();
                return true;
            }
        }

        public bool MoveNext( )
        {
            if (this.curr_node == null)
            {
                return (EnterTree(this.root));
            }
            else
            {
                // left sub-tree has been processed
                if (curr_node.Right != null)
                {
                    stack.Push((object)curr_node);
                    return (EnterTree(curr_node.Right));
                }
                else     // I am a leaf
                {
                    return (Unwind());
                }
            }
        }

        public void Reset( )
        {
            this.curr_node = null;
        }

        public object Current
        {
            get
            {
                return this.curr_node.Value ;
            }
        }
    }

    private class PostfixEnumerator : IEnumerator
    {
        private Node<T> curr_node;
        private Node<T> root;
        private Stack stack;
        public  const string Name = "PostfixEnumerator";

        public PostfixEnumerator ()
        {
            throw new TreeException("PostfixEnumerator: Missing argument to constructor");
        }
        public PostfixEnumerator (Tree<T> t)
        {
            throw new TreeException("PostfixEnumerator: Not implemented");
        }

        public bool MoveNext( )
        {
            throw new TreeException("PostfixEnumerator: Not implemented");
        }

        public void Reset( )
        {
            throw new TreeException("PostfixEnumerator: Not implemented");
        }

        public object Current
        {
            get
            {
                throw new TreeException("PostfixEnumerator: Not implemented");
            }

        }
    }
}

public class Tester
{

    private static int inc (int i)
    {
        return i + 1;
    }

    public static void Main(string[] args)
    {
        int i;
        Tree<int> t = new Tree<int>();

        if (args.Length < 1)
        {
            System.Console.WriteLine("Provide int values to add to the tree on the command line ...");
        }
        else
        {
            // build tree ...
            // ToDo: use exceptions to catch non-int values
            foreach (string s in args)
            {
                i = Convert.ToInt32(s); // int.Parse(s);
                System.Console.WriteLine("inserting {0} ...", i);
                t.Insert(i);
            }
            // test Find ...
            System.Console.WriteLine("Having added all arguments into the tree it looks like this:\n{0}", t.ToString());
            int[] xs = { 0, 5, 7, 9, 99 };
            foreach (int x in xs)
            {
                System.Console.WriteLine("Finding value {0} in tree: {1}", x, t.Find(x));
            }
            // test Insert ...
            int z = 5;
            System.Console.WriteLine("inserting {0} ...", z);
            t.Insert(5);
            System.Console.WriteLine("After inserting {0} the new tree looks like this:\n{1}", z, t.ToString());
            foreach (int x in xs)
            {
                System.Console.WriteLine("Finding value {0} in tree: {1}", x, t.Find(x));
            }
            // test generic dfs ...
            System.Console.WriteLine("Testing generic dfs with infix order of nodes, producing a sorted list of values ...");
            foreach (int elem in t.dfs())
                System.Console.Write(" " + elem.ToString());
            // test delegates ...
            System.Console.WriteLine("\nTesting mapTree on above int tree, increasing each element by 1 ...");
            Node<int>.TreeMapperDelegate f = new Node<int>.TreeMapperDelegate(Tester.inc);
            t.Root.mapTree(f);
            System.Console.WriteLine("{0}", t.ToString());
            System.Console.WriteLine("Testing mapTree on above int tree, doubling each element ...");
            t.Root.mapTree(n => n + 1);
            System.Console.WriteLine("{0}", t.ToString());
            // same thing again, shorter
            System.Console.WriteLine("Again, testing mapTree on above int tree, increasing each element by 1 (using lambda expression)...");
            t.Root.mapTree(new Node<int>.TreeMapperDelegate((int j) =>
            {
                return j + 1 ;
            }));
            System.Console.WriteLine("{0}", t.ToString());

            // use different iterators to print the tree
            System.Array arrFix = System.Enum.GetValues (Tree<int>.Fixity.Infix.GetType());
            foreach (Tree<int>.Fixity fix in arrFix)
            {
                try
                {
                    t.print(fix); // eg.: t.print(Tree<int>.Fixity.Infix);
                }
                catch (TreeException e)
                {
                    System.Console.WriteLine("print failed, using a {0} traversal", fix.ToString());
                    System.Console.WriteLine(e.Message);
                }
            }
        }
    }
}

