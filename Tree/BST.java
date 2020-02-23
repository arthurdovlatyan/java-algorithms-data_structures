package DataStructures.Tree;

import DataStructures.DefaultComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class BST<E> {
    // ---------------------------- nested Node class ---------------------------------------
    private static class Node<E> {

        // Instance variables
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        // Constructor
        public Node(E element,Node<E> parent,Node<E> left,Node<E> right){
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        // Getters
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // Setters
        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public void finalize(){
            this.element = null;
            this.parent = null;
            this.right = null;
            this.left = null;
        }
    }
    // ---------------------------- end of Node class ---------------------------------------

    // instance variables
    private Node<E> root = null;
    private int size;
    private Comparator<E> comp;

    // instance variables
    public BST(){
        comp = new DefaultComparator<>();
    }
    public BST(Comparator<E> cmp){
        comp = cmp;
    }

    /**
     * Returns the size of the BST
     * @return int which indicates the size of the BST
     */
    public int size(){
        return size;
    }

    /**
     * Returns true if the BST is Empty
     * @return true if the BST isEmpty
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Performs a binary search on a tree rooted at
     * root instance in search for a node with target element
     * @param root is the subtree on which the search will be performed
     * @param target is the target to be searched
     * @return is a Node<E> instance
     */
    private Node<E> searchIterative(Node<E> root,E target){
        Node<E> current = root;
        while (current != null){
            int cmp = comp.compare(current.getElement(),target);
            if (cmp == 0){
                return current;
            }else if (cmp < 0){
                current = current.getRight();
            }else {
                current = current.getLeft();
            }
        }
        return null;
    }
    private Node<E> searchRecursive(Node<E> root,E target){
        if (root == null){
            return null;
        }else{
            int cmp = comp.compare(root.getElement(),target);
            if (cmp == 0){
                return root;
            }else if(cmp < 0){
                return searchRecursive(root.getRight(),target);
            }else {
                return searchRecursive(root.getLeft(),target);
            }
        }
    }

    /**
     * Factory function which creates a node instance
     * @param element is the element of the new node
     * @param parent is the parent link of the newly created node
     * @param left is the left link of the newly created node
     * @param right is the right link of the newly created node
     * @return is a node object with the parameters mentioned above
     */
    private Node<E> createNode(E element,Node<E> parent,Node<E> left,Node<E> right){
        return new Node<>(element,parent,left,right);
    }

    /**
     * Performs the Inorder tree walk on the BST
     * @return Returns a ordered list of BST elements
     */
    public List<Node<E>> Inorder(){
        List<Node<E>> buffer = new ArrayList<Node<E>>();
        if (!isEmpty()){
            inorderFill(root,buffer);
        }
        return buffer;
    }

    /**
     * Helper function for the Inorder tree walk
     * @param n is the node to be added
     * @param buffer an Iterable collection to be filled with nodes
     */
    public void inorderFill(Node<E> n,List<Node<E>> buffer){
        if (n != null){
            inorderFill(n.getLeft(),buffer);
            buffer.add(n);
            inorderFill(n.getRight(),buffer);
        }
    }

    /**
     * Returns the node with the minimal element in the BST
     * @return is the minimal value in the BST rooted at y
     */
    public Node<E> treeMin(Node<E> y){
        Node<E> x = y;
        while (x != null){
            x = x.getLeft();
        }
        return x;
    }

    /**
     * Returns the node with the maximal element in the BST
     * @return is the node with maximal value in the BST rooted at y
     */
    public Node<E> treeMax(Node<E> y){
        Node<E> x = y;
        while (x != null){
            x = x.getRight();
        }
        return x;
    }

    /**
     * Finds the successor of node x
     * Considers two cases if x has right subtree than successor is leftmost node of x.right
     * Else the successor is the ancestor of x whose child is left parent of x
     * @param x is the parameter of the child to be searched
     * @return node who is the successor of x
     */
    public Node<E> successor(Node<E> x){
        if (x.getRight() != null){
            return treeMin(x.getRight());
        }
        Node<E> y = x.getParent();
        while (y != null && x == y.getRight()){
            x = y;
            y = y.getParent();
        }
        return y;
    }



    public void insert(E element){
        Node<E> newElement = createNode(element,null,null,null);
        Node<E> y = null;
        Node<E> x = root;
        while (x != null){
            y = x;
            int cmp = comp.compare(x.getElement(),newElement.getElement());
            if (cmp > 0){
                x = x.getLeft();
            }else {
                x = x.getRight();
            }
        }
        newElement.setParent(y);
        if (y == null){
            root = newElement;
        }else{
            int cmp = comp.compare(y.getElement(), newElement.getElement());
            if (cmp > 0){
                y.setLeft(newElement);
            }else{
                y.setRight(newElement);
            }
        }
    }

    /**
     * Replaces subtree rooted at u with the the subtree rooted at v
     * @param u subtree of the BST
     * @param v s
     */
    public void transplant(Node<E> u, Node<E> v){
        if (u.getParent() == null){
            root = v;
        }else if ( u == u.getParent().getLeft()){
            u.getParent().setLeft(v);
        }else {
            u.getParent().setRight(v);
        }
        if (v != null){
            v.setParent(u.getParent());
        }
    }

    /**
     * Deletes a node x and returns the element of the node
     * @param x is the node to be deleated
     * @return is an element
     */
    public E delete(Node<E> x){
        if(x.getLeft() == null) {
            transplant(x, x.getRight());
        }else if( x.getRight() == null){
            transplant(x,x.getLeft());
        }else {
            Node<E> y = treeMin(x.getRight());
            if (y.getParent() != x){
                transplant(y,y.getRight());
                y.setRight(x.getRight());
                y.getRight().setParent(y);
            }
            transplant(x,y);
            y.setLeft(x.getLeft());
            y.getLeft().setParent(y);
        }
        return x.getElement();
    }






    //TODO 12.1 - 3
    public List<Node<E>> inorderIterative(){
        List<Node<E>> buffer = new ArrayList<>();
        if (isEmpty()){
            return buffer;
        }
        Stack<Node<E>> st = new Stack<>();
        st.push(root);
        while (!st.empty()){
            Node<E> el = st.pop();
            if (buffer.isEmpty()){
                buffer.add(el);
            }else{
                int cmp = comp.compare(el.element,buffer.get(0).getElement());
                if (cmp > 0){
                    buffer.add(0,el);
                }else{
                    buffer.add(el);
                }
            }
            if (el.getLeft() != null) {
                st.add(el.getLeft());
            }
            if (el.getRight() != null) {
                st.add(el.getRight());
            }
        }
        return buffer;
    }

    //TODO 12.1 - 4
    public List<Node<E>> postOrder(){
        List<Node<E>> buffer = new ArrayList<>();
        if (!isEmpty()){
            postOrderHelper(root,buffer);
        }
        return buffer;
    }
    private void postOrderHelper(Node<E> n,List<Node<E>> buffer){
        if (root != null){
            postOrderHelper(root.getLeft(),buffer);
            postOrderHelper(root.getRight(),buffer);
            buffer.add(root);
        }
    }
    public List<Node<E>> preOrder(){
        List<Node<E>> buffer = new ArrayList<>();
        if (!isEmpty()){
            preOrderHelper(root,buffer);
        }
        return buffer;
    }
    private void preOrderHelper(Node<E> n,List<Node<E>> buffer){
        if (root != null){
            buffer.add(root);
            preOrderHelper(n.getLeft(),buffer);
            preOrderHelper(n.getRight(),buffer);
        }
    }

    //TODO 12.2 -2
    public Node<E> treeMinRecursive(Node<E> x){
        if (x.getLeft() != null){
            return treeMinRecursive(x.getLeft());
        }
        return x;
    }
    public Node<E> treeMaxRecursive(Node<E> x){
        if (x.getRight() != null){
            return treeMaxRecursive(x.getRight());
        }
        return x;
    }

    //TODO 12.2 - 3
    public Node<E> predecessor(Node<E> x){
        if (x.getLeft() != null){
            return treeMax(x);
        }
        Node<E> y = x.getParent();
        while (y != null && x == y.getLeft()){
            x = y;
            y = y.getParent();
        }
        return y;
    }

    //TODO 12.2 - 7
    public List<Node<E>> inorderAlternate(){
        List<Node<E>> buffer = new ArrayList<>();
        if (isEmpty()){
            return buffer;
        }
        Node<E> x = root;
        while (x.getLeft() != null){
            x = x.getLeft();
        }
        buffer.add(x);
        int i = 0;
        while (i < size() - 1){
            if (x.getRight() != null){
                x = x.getRight();
            }else {
                Node<E> y = x.getParent();
                while (y != null && x == y.getLeft()){
                    x = y;
                    y = y.getParent();
                }
                x = y;
            }
            buffer.add(x);
        }
        return buffer;
    }

    //TODO 12.3 - 1
    public void insertRecursive(E element){
        insertRecursiveHelper(root,element);
    }
    public void insertRecursiveHelper(Node<E> n,E element){
        
    }
}


class SearchTree{
    private Node tree;
    private Node root = null;
    private int size = 0;

    private class Node{
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int k) {
            key=k;
            left=null;
            right=null;
            parent=null;
        }

        Node(int k,Node l,Node r,Node p) {
            key=k;
            left=l;
            right=r;
            parent=p;
        }
    }


    public SearchTree(){

    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void print(Node tree,int indent) {
        if(tree==null) {
            return;
        }
        print(tree.right,indent+5);
        space(indent,' ');
        System.out.println(tree.key);
        print(tree.left,indent+5);
    }


    private void space(int indent,char a) {
        for(int i=0;i<indent;i++)
            System.out.print(a);
    }

    public void print() {
        System.out.println("- - - - - - - - - - ");
        print(root,0);
        System.out.println("- - - - - - - - - - ");
    }

    private Node search(int key) {
        Node current=root;
        while(current!=null) {
            if(current.key==key) {
                return current;
            }
            current=(key<current.key) ? current.left : current.right;
        }
        return null;
    }


    boolean find(int key) {
        return search(key) != null;
    }

    public void insert(int key) {
        Node newElement = new Node(key,null,null,null);
        Node y = null;
        Node x = root;
        while (x != null){
            y = x;
            if (x.key > newElement.key){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        newElement.parent = y;
        if (y == null) {
            root = newElement;
        }else {
            if (y.key > newElement.key){
                y.left = newElement;
            }else {
                y.right = newElement;
            }
        }
    }

    public void remove(int key) {
        Node del = search(key);
        if (del == null){
            return;
        }
        if (del.left == null){
            if (del.parent == null){
                root = del.right;
            }else if (del == del.parent.left){
                del.parent.left = del.right;
            }else {
                del.parent.right = del.right;
            }
            if (del.right != null){
                del.right.parent = del.parent;
            }
        }else if (del.right == null){
            if (del.parent == null){
                root = del.left;
            }else if (del == del.parent.left){
                del.parent.left = del.left;
            }else {
                del.parent.right = del.left;
            }
            if (del.left != null){
                del.left.parent = del.parent;
            }
        }else {
            Node y = del.right;
            while (y.left != null){
                y = y.left;
            }
            if (y.parent != del){
                if (y.parent == null){
                    root = y.right;
                }else if (y == y.parent.left){
                    y.parent.left = y.right;
                }else {
                    y.parent.right = y.right;
                }
                if (y.right != null){
                    y.right.parent = y.parent;
                }
                y.right = del.right;
                y.right.parent = del.parent;
            }
            if (del.parent == null){
                root = y;
            }else if (del == del.parent.left){
                del.parent.left = y;
            }else {
                del.parent.right = y;
            }
            if (del.left != null){
                y.parent = del.parent;
            }
            y.left = del.left;
            y.left.parent = y;
        }
    }

    public static void main(String args []) {
        SearchTree s=new SearchTree();

        System.out.println("*****   THE 3, 7, 2, 8, 4, 1, 6, 5 KEYS ARE INSERTED:  *****");
        s.insert(3); s.insert(7); s.insert(2); s.insert(8);
        s.insert(4); s.insert(1); s.insert(6); s.insert(5);
        s.print();

        System.out.println("***** 3 IS DELETED: *****");
        s.remove(3);
        s.print();

        System.out.println("***** 4 IS DELETED: *****" );
        s.remove(4);
        s.print();

        System.out.println("***** 5 IS DELETED: *****" );
        s.remove(5);
        s.print();

    }
}