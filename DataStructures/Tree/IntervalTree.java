package data_structure.newStructure.DataStructures.Tree;

public class IntervalTree {
    private static class Node
    {
        private static class Interval
        {
            // instance variables
            private int start;
            private int end;

            // constructors
            public Interval()
            {
                start = 0;
                end = 0;
            }
            public Interval(int start,int end)
            {
                this.start = start;
                this.end = end;
            }

            // getters
            public int getStart() {
                return start;
            }
            public int getEnd() {
                return end;
            }

            // setters
            public void setStart(int start) {
                this.start = start;
            }

            public void setEnd(int end) {
                this.end = end;
            }
        }

        // Factory function
        public Interval createInterval(int start,int end)
        {
            return new Interval(start,end);
        }

        // Instance variables
        private Interval interval;
        private Node parent;
        private Node left;
        private Node right;
        private int color; // 0 black, 1 red
        private int maxSize;

        // constructors
        public Node(int start, int end,Node parent, Node left, Node right,int color, int maxSize)
        {
            this.interval = createInterval(start,end);
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.maxSize = maxSize;
        }

        // getters
        public Interval getInterval() {
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
        public int getMaxSize(){
            return maxSize;
        }

        // setters
        public void setInterval(int start,int end) {
            this.interval = createInterval(start,end);
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
        public void setMaxSize(int maxSize){
            this.maxSize = maxSize;
        }
    }

    private Node root;
    private final Node TREE_NIL;


    public IntervalTree(){
        TREE_NIL = new Node(0,0,null,null,null,0,0);
        root = TREE_NIL;
    }

    public void insert(int start,int end){
        insertHelper(new Node(start,end, TREE_NIL, TREE_NIL, TREE_NIL,1,end));
    }

    public void insertHelper(Node newNode){
        Node y = null;
        Node x = this.root;

        while (x != TREE_NIL){
            y = x;
            if (newNode.interval.start < x.interval.start){
                if (newNode.getMaxSize() > x.getMaxSize()){
                    x.setMaxSize(newNode.getMaxSize());
                }
                x = x.getLeft();
            }else {
                if (newNode.getMaxSize() > x.getMaxSize()){
                    x.setMaxSize(newNode.getMaxSize());
                }
                x = x.getRight();
            }
        }

        newNode.setParent(y);
        if (y == null){
            root = newNode;
        }else if (newNode.interval.start < y.interval.start){
            y.setLeft(newNode);
        }else {
            y.setRight(newNode);
        }

        if (newNode.parent == null){
            newNode.color = 0;
            return;
        }

        // if the grandparent is null, simply return
        if (newNode.parent.parent == null) {
            return;
        }
        fixInsert(newNode);

    }

    private void fixInsert(Node k){
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
        if (y.right != TREE_NIL) {
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
        x.setMaxSize(Math.max(x.getMaxSize(),Math.max(x.getLeft().getMaxSize(),x.getRight().getMaxSize())));
        y.setMaxSize(Math.max(y.getMaxSize(),Math.max(y.getLeft().getMaxSize(),y.getRight().getMaxSize())));
    }
    // rotate left at node x
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TREE_NIL) {
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
        x.setMaxSize(Math.max(x.getMaxSize(),Math.max(x.getLeft().getMaxSize(),x.getRight().getMaxSize())));
        y.setMaxSize(Math.max(y.getMaxSize(),Math.max(y.getLeft().getMaxSize(),y.getRight().getMaxSize())));
    }

    public Node minimum(Node node) {
        while (node.left != TREE_NIL) {
            node = node.left;
        }
        return node;
    }

    // find the node with the maximum key
    public Node maximum(Node node) {
        while (node.right != TREE_NIL) {
            node = node.right;
        }
        return node;
    }

    public Node IntervalSearch(int start,int end){
        Node x = root;
        while (x != TREE_NIL && (start > x.interval.end || x.interval.start > end)){
            if (x.left != TREE_NIL && x.left.getMaxSize() >= start){
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        return x;
    }

    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }
    private void deleteNodeHelper(Node node, int key) {
        // find the node containing key
        Node z = TREE_NIL;
        Node x, y;
        while (node != TREE_NIL){
            if (node.interval.start == key) {
                z = node;
            }
            if (node.interval.start <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TREE_NIL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TREE_NIL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TREE_NIL) {
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
        fixSize(x);
    }
    public void fixSize(Node x){
        Node z = x;
        int newMaxInt = x.getMaxSize();
        while (z != TREE_NIL && z.getParent().getMaxSize() < newMaxInt){
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
    
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }
    private void printHelper(Node root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != TREE_NIL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.color == 1?"RED":"BLACK";
            System.out.println(root.interval.getStart() + " " + root.interval.getEnd() +
                    " maxSize " + root.getMaxSize()+ "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }


    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
//        tree.insert(0,3);
//        tree.insert(5,8);
//        tree.insert(6,10);
//        tree.insert(8,9);
//        tree.insert(15,23);
//        tree.insert(16,21);
//        tree.insert(17,19);
//        tree.insert(19,20);
//        tree.insert(25,30);
//        tree.insert(26,26);
//        tree.prettyPrint();


        tree.insert(17,19);
        tree.insert(5,11);
        tree.insert(22,23);
        tree.insert(4,8);
        tree.insert(15,18);
        tree.insert(7,10);

        tree.prettyPrint();



        System.out.println();
        Node x = tree.IntervalSearch(17,20);
        if (x != tree.TREE_NIL){
            System.out.println(x.interval.start + " " + x.interval.end);
        }
    }
}
