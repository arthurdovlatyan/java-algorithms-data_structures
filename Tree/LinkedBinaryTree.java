package DataStructures.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedBinaryTree<E> {
    // ---------------------- nested Node class ------------------------
    protected static class Node<E> {
        private E element;          // an element stored at this node
        private Node<E> parent;     // a reference to the parent node
        private Node<E> left;       // a reference to the left child
        private Node<E> right;      // a reference to the right child

        /**
         * Construct a node with the given element and neighbours
         *
         * @param e          indicates the element of the node
         * @param above      indicates the parent reference of the node
         * @param leftChild  indicates the left child of the node
         * @param rightChild indicates the right child of the node
         */
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            this.element = e;
            this.parent = above;
            this.left = leftChild;
            this.right = rightChild;
        }

        // accessor methods
        public E getElement() { return element; }

        public Node<E> getParent() { return parent; }

        public Node<E> getLeft() { return left; }

        public Node<E> getRight() { return right; }

        // mutator methods
        public void setElement(E element) { this.element = element; }

        public void setParent(Node<E> parent) { this.parent = parent; }

        public void setLeft(Node<E> left) { this.left = left; }

        public void setRight(Node<E> right) { this.right = right; }

        public void finalize(){
            this.element = null;
            this.parent = this;
            this.left = null;
            this.right = null;
        }
    }
    //------------------ end of the Nested Node class ----------------------------------


    /**
     * Factory function to create a new node storing element e
     * @param e          indicates the element of the node
     * @param parent      indicates the parent reference of the node
     * @param left  indicates the left child of the node
     * @param right indicates the right child of the node
     * @return a new node with given parameters
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
        return new Node<E>(e,parent,left,right);
    }


    // LinkedBinaryTree instance variables
    protected Node<E> root = null;            // root of the tree
    private int size = 0;                   // number of nodes in the tree

    // constructor
    public LinkedBinaryTree() { }


    /**
     * Returns the number of nodes in the tree
     * @return is the number of nodes in the tree
     */
    public int size() { return size; }

    /**
     * Returns the root position of the tree T
     * @return
     */
    public Node<E> root() { return root; }

    /**
     * Returns the position of p's parent
     * @param p is the position queried
     * @return
     * @throws IllegalArgumentException
     */
    public Node<E> parent(Node<E> p) throws IllegalArgumentException{
        return p.getParent();
    }

    /**
     * Returns the position of p's left child
     * @param p is the position queried
     * @return
     * @throws IllegalArgumentException
     */
    public Node<E> left(Node<E> p) throws IllegalArgumentException{
        return p.getLeft();
    }

    /**
     * Returns the position of p's right child
     * @param p is the position queried
     * @return
     * @throws IllegalArgumentException
     */
    public Node<E> right(Node<E> p) throws IllegalArgumentException{
        return p.getRight();
    }


    // update methods

    /**
     * Places element e at the root of an empty tree and returns it's new Position
     * @param e
     * @return
     * @throws IllegalStateException
     */
    public Node<E> addRoot(E e) throws IllegalStateException{
        if (!isEmpty()) { throw new IllegalStateException("Tree is not Empty");}
        root = createNode(e,null,null,null);
        size = 1;
        return root;
    }

    /**
     * Creates a left child of Position p storing element e; returns it's postion
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public Node<E> addLeft(Node<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = p;
        if (parent.getLeft() != null ) { throw new IllegalArgumentException("p already has a left child");}
        Node<E> child = createNode(e,parent,null,null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a right child of Position p storing element e; returns it's position
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public Node<E> addRight(Node<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = p;
        if (parent.getRight() != null ) { throw new IllegalArgumentException("p already has a right child"); }
        Node<E> child = createNode(e,parent,null,null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with e and returns the replace element
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public E set(Node<E> p, E e) throws IllegalArgumentException{
        Node<E> node = p;
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches tree t1 and t2 as left and right subtrees of external p
     * @param p
     * @param t1
     * @param t2
     * @throws IllegalArgumentException
     */
    public void attach(Node<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException{
        Node<E> node = p;
        if (isInternal(p)) { throw new IllegalArgumentException("p must be a leaf");}
        size += t1.size() + t2.size();
        if (!t1.isEmpty()){
            t1.root.setParent(node);
            root.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }if (!t2.isEmpty()){
            t2.root.setParent(node);
            root.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    public E remove(Node<E> p) throws IllegalArgumentException {
        Node<E> node = p;
        if (numChildren(p) == 2){ throw new IllegalArgumentException("p has two children");}
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) { child.setParent(node.getParent());}
        if (node == root) { root = child; }
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()){
                parent.setLeft(child);
            }else {
                parent.setRight(child);
            }
        }
        size--;
        E tmp = node.getElement();
        node.finalize();
        return tmp;
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Node<E>> posIterator = positions().iterator();
        public boolean hasNext() { return posIterator.hasNext();}
        public E next() { return  posIterator.next().getElement(); }
        public void remove() { posIterator.remove(); }
    }

    public Iterator<E> iterator() { return new ElementIterator(); }

    public Iterable<Node<E>> positions() { return inorder(); }


    public boolean isInternal(Node<E> p) { return numChildren(p) > 0; }
    public boolean isExternal(Node<E> p) { return numChildren(p) == 0; }
    public boolean isRoot(Node<E> p) { return p == root(); }
    public boolean isEmpty() { return size() == 0; }

    /**
     * Returns the number of levels separating Position p from the root
     * @param p
     * @return
     */
    public int depth(Node<E> p) {
        if (isRoot(p)){
            return 0;
        }else {
            return 1 + depth(parent(p));
        }
    }

    public int height(Node<E> p){
        int h = 0;
        for (Iterator<Node<E>> c = children(p).iterator(); c.hasNext();){
            Node<E> d = c.next();
            h = Math.max(h,1 + height(d));
        }
        return h;
    }


    private void preOrderSubtree(Node<E> p, List<Node<E>> snapshot) {
        snapshot.add(p);    // for preorder, we add position p before exploring subtrees
        for (Iterator<Node<E>> it = children(p).iterator();it.hasNext();){
            preOrderSubtree(it.next(),snapshot);
        }
    }

    public Iterable<Node<E>> preorder( ) {
        List<Node<E>> snapshot = new ArrayList<Node<E>>( );
        if (!isEmpty( ))
            preOrderSubtree(root(), snapshot);      // fill the snapshot recursively
        return snapshot;
    }

    private void postOrderSubtree(Node<E> p, List<Node<E>> snapshot){
        for (Iterator<Node<E>> it = children(p).iterator();it.hasNext();){
            postOrderSubtree(it.next(),snapshot);
        }
        snapshot.add(p);
    }
    public Iterable<Node<E>> postorder(){
        List<Node<E>> snapshot = new ArrayList<Node<E>>();
        if (!isEmpty()) { postOrderSubtree(root(),snapshot);}
        return snapshot;
    }


    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        Node<E> parent = parent(p);
        if (parent == null) {
            return null;
        } // must be the root node
        if (p == left(parent)) {
            return right(parent);
        } // if p is the left child return the right one
        else {
            return left(parent);
        }  // if p is the right child then return the left child
    }

    public int numChildren(Node<E> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }

    public Iterable<Node<E>> children(Node<E> p) {
        List<Node<E>> snapshot = new ArrayList<Node<E>>(2); // max capacity of 2
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    private void InorderSubtree(Node<E> p, List<Node<E>> snapshot){
        if (left(p) != null){
            InorderSubtree(left(p),snapshot);
        }
        snapshot.add(p);
        if (right(p) != null){
            InorderSubtree(right(p),snapshot);
        }
    }

    public Iterable<Node<E>> inorder(){
        List<Node<E>> snapshot = new ArrayList<Node<E>>();
        if (!isEmpty()) { InorderSubtree(root(),snapshot);}
        return snapshot;
    }

    public void Euler(Node<E> p, List<Node<E>> snapshot){
        if (left(p) != null){
            Euler(left(p),snapshot);
        }
        snapshot.add(p);
        if (right(p) != null){
            Euler(right(p),snapshot);
        }
    }






}


/**
 * Validates the position and returns a tree
 * @param p  is the position queried
 * @return is the node corresponding to the position
 * @throws IllegalArgumentException if p is not instance of tree and if p is deleted from the tree
 */
//    protected Node<E> validate (Position<E> p) throws IllegalArgumentException{
//        if (!(p instanceof Node)) { throw new IllegalArgumentException("Not valid position type");}
//        Node<E> node = (Node<E>) p;
//        if (node.getParent() == node){throw new IllegalArgumentException("p is no longer in the tree");}
//        return node;
//    }

//TODO
//
//    public Iterable<Node<E>> breadFirst(){
//        List<Node<E>> snapshot = new ArrayList<Node<E>>();
//        if (!isEmpty()){
//            Queue<Node<E>> q = new LinkedBlockingQueue<Node<E>>();
//            q.enqueue(root());
//            while (!q.isEmpty()){
//                Position<E> p = q.dequeue();
//                snapshot.add(p);
//                for (Iterator<Position<E>> it = children(p).iterator(); it.hasNext();){
//                    q.enqueue(it.next());
//                }
//            }
//        }
//        return snapshot;
//    }

