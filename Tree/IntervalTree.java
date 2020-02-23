package DataStructures.Tree;

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
    private Node TNIL;


    public IntervalTree(){
        TNIL = new Node(0,0,null,null,null,0,0);
        root = TNIL;
    }

    public void insert(int start,int end){
        insertHelper(new Node(start,end,TNIL,TNIL,TNIL,1,end));
    }

    public void insertHelper(Node newNode){
        Node y = null;
        Node x = this.root;

        while (x != TNIL){
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

        y.setMaxSize(Math.max(y.interval.end,Math.max(y.getLeft().getMaxSize(),y.getRight().getMaxSize())));
        x.setMaxSize(Math.max(x.interval.end,Math.max(x.getLeft().getMaxSize(),x.getRight().getMaxSize())));
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

        y.setMaxSize(Math.max(y.interval.end,Math.max(y.getLeft().getMaxSize(),y.getRight().getMaxSize())));
        x.setMaxSize(Math.max(x.interval.end,Math.max(x.getLeft().getMaxSize(),x.getRight().getMaxSize())));
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

    public Node IntervalSearch(int start,int end){
        Node x = root;
        while (x != TNIL && (start > x.interval.end || x.interval.start > end)){
            if (x.left != TNIL && x.left.getMaxSize() >= start){
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
        Node z = TNIL;
        Node x, y;
        while (node != TNIL){
            if (node.interval.start == key) {
                z = node;
            }
            if (node.interval.start <= key) {
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
        if (yOriginalColor == 0){
            fixDelete(x);
        }
        fixSize(x);
    }
    public void fixSize(Node x){
        Node z = x;
        int newMaxInt = x.getMaxSize();
        while (z != root){
            if (z.getParent().getMaxSize() < z.getMaxSize()){
                z = z.getParent();
                z.setMaxSize(newMaxInt);
            }else {
                newMaxInt = z.getParent().getMaxSize();
                z = z.getParent();
            }
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
        if (root != TNIL) {
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
                    " maxSize " + root.getMaxSize()+ "(" + sColor + ")" + " max " + root.getMaxSize());
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }


    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        tree.insert(0,5);
        tree.prettyPrint();
        tree.insert(8,13);
        tree.prettyPrint();
        tree.insert(16,21);
        tree.prettyPrint();
        tree.insert(4,9);
        tree.prettyPrint();
        tree.insert(12,17);
        tree.prettyPrint();
        tree.insert(1,20);


        tree.prettyPrint();


        System.out.println();
        Node x = tree.IntervalSearch(10,11);
        if (x != tree.TNIL){
            System.out.println(x.interval.start + " " + x.interval.end);
        }

        tree.deleteNode(8);
        tree.prettyPrint();
        System.out.println();
        x = tree.IntervalSearch(10,11);
        if (x != tree.TNIL){
            System.out.println(x.interval.start + " " + x.interval.end);
        }
        tree.deleteNode(1);
        tree.prettyPrint();
    }
}
