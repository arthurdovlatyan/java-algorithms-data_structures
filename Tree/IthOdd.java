package DataStructures.Tree;

public class IthOdd
{
    private static class Node
    {
        int data;
        Node parent;
        Node left;
        Node right;
        int color; // 1 red , 0 black
        boolean isOdd;
        int oddSize = 0;

        public Node(int element,Node parent,Node left,Node right){
            this.data = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
            if (element % 2 == 0){
                isOdd = false;
            }else {
                isOdd = true;
            }
        }
        public Node(){

        }
    }

    Node TNULL;
    Node root;

    // constructor
    public IthOdd(){
        TNULL = new Node(0,null,null,null);
        TNULL.oddSize = 0;
        root = TNULL;
    }

    public void insert(int key) {
        // Ordinary Binary Search Insertion
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1; // new node must be red
        if (node.data % 2 == 0){
            node.isOdd = false;
        }else {
            node.isOdd = true;
            node.oddSize = 1;
        }

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x.oddSize = x.oddSize + node.oddSize;
                x = x.left;
            } else {
                x.oddSize = x.oddSize + node.oddSize;
                x = x.right;
            }
        }

        // y is parent of x
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        // if new node is a root node, simply return
        if (node.parent == null){
            node.color = 0;
            return;
        }

        // if the grandparent is null, simply return
        if (node.parent.parent == null) {
            return;
        }

        // Fix the tree
        fixInsert(node);
    }
    private void fixInsert(Node k){
        Node u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == 1) {
                    // case 1
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // case 2
                        k = k.parent;
                        rightRotate(k);
                    }
                    // case 3
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle

                if (u.color == 1) {
                    // mirror case 1
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // mirror case 2
                        k = k.parent;
                        leftRotate(k);
                    }
                    // mirror case 3
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }
    // rotate left at node x
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        y.oddSize = x.oddSize;
        x.oddSize = x.left.oddSize + x.right.oddSize + (x.isOdd == true ? 1: 0);
    }

    // rotate right at node x
    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
        y.oddSize = x.oddSize;
        x.oddSize = x.left.oddSize + x.right.oddSize + (x.isOdd ? 1: 0);
    }
    private void rbTransplant(Node u, Node v){
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left){
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }
    private void deleteNodeHelper(Node node, int key) {
        // find the node containing key
        Node z = TNULL;
        Node x, y;
        while (node != TNULL){
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0){
            fixDelete(x);
        }
        fixAfterDelete(x);
    }
    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    // case 3.1
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    // case 3.2
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        // case 3.3
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    // case 3.4
                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    // case 3.1
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0 && s.right.color == 0) {
                    // case 3.2
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        // case 3.3
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    // case 3.4
                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }
    public Node minimum(Node node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }
    public void fixAfterDelete(Node x){
        while (x != TNULL){
            x.oddSize = x.left.oddSize + x.right.oddSize + (x.isOdd ? 1 : 0);
        }
    }
    public void deleteNode(int data) {
        deleteNodeHelper(root, data);
    }
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }
    private void printHelper(Node root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.color == 1?"RED":"BLACK";
            System.out.println(root.data + "(" + sColor + ")" + " oddSize "  + root.oddSize);
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }


    public Node search(Node x,int ithSmaleest){
       if (x == TNULL){
           return null;
       }
       int k = x.left.oddSize + (x.isOdd ? 1 : 0);
       if (k == ithSmaleest && x.isOdd){
           return x;
       }else if (ithSmaleest < k){
           return search(x.left,ithSmaleest);
       }else {
           return search(x.right,ithSmaleest - k);
       }
    }
    public void findAndPrintIthOdd(int ith){
        Node x = search(root,ith);
        if (x == null){
            return;
        }else {
            String sColor = x.color == 1?"RED":"BLACK";
            System.out.println(x.data + "(" + sColor + ")" + " oddSize "  + x.oddSize);

        }
    }

    public static void main(String[] args) {
        IthOdd tree = new IthOdd();
        tree.insert(2);
        tree.prettyPrint();
        tree.insert(3);
        tree.prettyPrint();
        tree.insert(4);
        tree.prettyPrint();
        tree.insert(6);
        tree.prettyPrint();
        tree.insert(7);
        tree.prettyPrint();
        tree.insert(8);

        tree.prettyPrint();

        tree.findAndPrintIthOdd(1);
        tree.findAndPrintIthOdd(2);
    }
}
