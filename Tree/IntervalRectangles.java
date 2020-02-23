package DataStructures.Tree;

import java.util.ArrayList;
import java.util.List;

public class IntervalRectangles {
    private static class Node {
        private static class Interval {
            private static class Coordinate {
                int x;
                int y;

                // constructor
                public Coordinate(int x, int y) {
                    this.x = x;
                    this.y = y;
                }

                // getters
                public int getX() {
                    return x;
                }

                public int getY() {
                    return y;
                }

                //setters
                public void setX(int x) {
                    this.x = x;
                }

                public void setY(int y) {
                    this.y = y;
                }
            }

            // factory function for Coordinate
            public Coordinate newCoordinate(int x, int y) {
                return new Coordinate(x, y);
            }

            private Coordinate left;
            private Coordinate right;

            // constructor
            public Interval(int x1, int y1, int x2, int y2) {
                left = newCoordinate(x1, y1);
                right = newCoordinate(x2, y2);
            }

            // getters
            public Coordinate getLeft() {
                return left;
            }

            public Coordinate getRight() {
                return right;
            }

            // setters
            public void setLeft(int x, int y) {
                this.left = newCoordinate(x, y);
            }

            public void setRight(int x, int y) {
                this.right = newCoordinate(x, y);
            }
        }

        public Interval newInterval(int x1, int y1, int x2, int y2) {
            return new Interval(x1, y1, x2, y2);
        }

        private Node.Interval interval;
        private Node parent;
        private Node left;
        private Node right;
        private int color; // 0 black, 1 red
        private int maxSize;

        // constructors
        public Node(int x1, int y1, int x2, int y2, Node parent, Node left, Node right, int color, int maxSize) {
            this.interval = newInterval(x1, y1, x2, y2);
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.maxSize = maxSize;
        }

        // getters
        public Node.Interval getInterval() {
            return interval;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getColor() {
            return color;
        }

        public int getMaxSize() {
            return maxSize;
        }

        // setters
        public void setInterval(int x1, int y1, int x2, int y2) {
            this.interval = newInterval(x1, y1, x2, y2);
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }
    }

    private Node root;
    private Node TNIL;

    // constructor
    public IntervalRectangles() {
        TNIL = new Node(0, 0, 0, 0, null, null, null, 0, 0);
        root = TNIL;
    }

    public void insert(int x1, int y1, int x2, int y2) {
        insertHelper(new Node(x1, y1, x2, y2, TNIL, TNIL, TNIL, 1, x2));
    }

    public void insertHelper(Node newNode) {
        Node y = null;
        Node x = this.root;

        while (x != TNIL) {
            y = x;
            if (newNode.interval.left.x < x.interval.left.x) {
                if (newNode.getMaxSize() > x.getMaxSize()) {
                    x.setMaxSize(newNode.getMaxSize());
                }
                x = x.getLeft();
            } else {
                if (newNode.getMaxSize() > x.getMaxSize()) {
                    x.setMaxSize(newNode.getMaxSize());
                }
                x = x.getRight();
            }
        }

        newNode.setParent(y);
        if (y == null) {
            root = newNode;
        } else if (newNode.interval.left.x < y.interval.left.x) {
            y.setLeft(newNode);
        } else {
            y.setRight(newNode);
        }

        if (newNode.parent == null) {
            newNode.color = 0;
            return;
        }

        // if the grandparent is null, simply return
        if (newNode.parent.parent == null) {
            return;
        }
        fixInsert(newNode);

    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == 1) {
                    // case 3.1
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // case 3.2.2
                        k = k.parent;
                        rightRotate(k);
                    }
                    // case 3.2.1
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle

                if (u.color == 1) {
                    // mirror case 3.1
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // mirror case 3.2.2
                        k = k.parent;
                        leftRotate(k);
                    }
                    // mirror case 3.2.1
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

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNIL) {
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
        x.setMaxSize(Math.max(x.getMaxSize(), Math.max(x.getLeft().getMaxSize(), x.getRight().getMaxSize())));
        y.setMaxSize(Math.max(y.getMaxSize(), Math.max(y.getLeft().getMaxSize(), y.getRight().getMaxSize())));
    }

    // rotate left at node x
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNIL) {
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
        x.setMaxSize(Math.max(x.getMaxSize(), Math.max(x.getLeft().getMaxSize(), x.getRight().getMaxSize())));
        y.setMaxSize(Math.max(y.getMaxSize(), Math.max(y.getLeft().getMaxSize(), y.getRight().getMaxSize())));
    }

    public Node minimum(Node node) {
        while (node.left != TNIL) {
            node = node.left;
        }
        return node;
    }

    // find the node with the maximum key
    public Node maximum(Node node) {
        while (node.right != TNIL) {
            node = node.right;
        }
        return node;
    }


    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }

    private void deleteNodeHelper(Node node, int key) {
        // find the node containing key
        Node z = TNIL;
        Node x, y;
        while (node != TNIL) {
            if (node.interval.left.x == key) {
                z = node;
            }
            if (node.interval.left.x <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNIL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNIL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNIL) {
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
        if (yOriginalColor == 0) {
            fixDelete(x);
        }
        fixSize(x);
    }

    public void fixSize(Node x) {
        Node z = x;
        int newMaxInt = x.getMaxSize();
        while (z != TNIL && z.getParent().getMaxSize() < newMaxInt) {
            z = z.getParent();
            z.setMaxSize(newMaxInt);
        }
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

    private void rbTransplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

    private void printHelper(Node root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != TNIL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println("Left " + root.interval.left.x + " " + root.interval.left.y + " right "
                    + root.interval.right.x + " " + root.interval.right.y +
                    " maxSize " + root.getMaxSize() + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public Node IntervalSearch(int start1, int end1, int start2, int end2) {
        Node x = root;
        while (x != TNIL && (start1 > x.interval.right.x || x.interval.left.x > start2)) {
            if (x.left != TNIL && x.left.getMaxSize() >= start1) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        if (x.interval.left.x > start2 || start1 > x.interval.right.x) {
            return TNIL;
        }
        if (x.interval.left.y < end2 || end1 < x.interval.right.y) {
            return TNIL;
        }
        return x;
    }

    public static void main(String[] args) {
        IntervalRectangles itRec = new IntervalRectangles();
        itRec.insert(2, 17, 7, 13);
        itRec.insert(12, 17, 22, 13);
        itRec.insert(4, 12, 12, 8);
        itRec.insert(15, 12, 21, 9);
        itRec.insert(1, 7, 11, 3);
        itRec.insert(14, 10, 23, 5);
        itRec.insert(20, 4, 27, 1);

        itRec.prettyPrint();

        Node mew = itRec.IntervalSearch(15, 12, 21, 9);
        if (mew != null) {
            System.out.println("Left " + mew.interval.left.x + " " + mew.interval.left.y + " right "
                    + mew.interval.right.x + " " + mew.interval.right.y +
                    " maxSize " + mew.getMaxSize() + "(" + mew.getColor() + ")");
        }
    }

    public static List<Node.Interval> rectangleOverlap(IntervalRectangles tree) {
        List<Node.Interval> buffer = new ArrayList<>();
        if (tree.root != tree.TNIL) {
            rectangleOverlapHelper(buffer, tree);
        }
        return buffer;
    }

    public static void rectangleOverlapHelper(List<Node.Interval> buffer, IntervalRectangles tree) {
        int x = tree.root.interval.left.x;
        Node inMind = tree.root;
        tree.deleteNode(x);
        List<Node.Interval> buffer1 = new ArrayList<>();
        while (tree.IntervalSearch(inMind.interval.right.x, inMind.interval.right.y,
                inMind.interval.left.x, inMind.interval.left.y) != tree.TNIL) {
            buffer1.add(tree.IntervalSearch(inMind.interval.right.x, inMind.interval.right.y,
                    inMind.interval.left.x, inMind.interval.left.y).interval);
            tree.deleteNode(inMind.interval.right.x);
        }
        if (!buffer1.isEmpty()) {
            buffer.add(inMind.interval);
            buffer.addAll(buffer1);
            buffer.add(new Node.Interval(0, 0, 0, 0));
        }
//        while (!buffer1.isEmpty()){
//            tree.insert(buffer1.);
//        }
        rectangleOverlapHelper(buffer, tree);
    }
}
