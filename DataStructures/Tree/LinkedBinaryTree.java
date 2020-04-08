package data_structure.al_tree_traversals;

import data_structure.af_queus.Queue;
import data_structure.aj_trees.Position;

import data_structure.af_queus.LinkedQueue;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    protected Node<E> root = null;              // root of tree
    private int size = 0;                       // number of nodes in the tree


    public LinkedBinaryTree() {                 // constructs an empty binary tree

    }


    // -----------------nested Node class -----------------
    protected static class Node<E> implements Position<E> {

        private E element;              // an element stored at this node
        private Node<E> parent;         // a reference to the parent node (if any)
        private Node<E> left;           // a reference to the left child (if any)
        private Node<E> right;          // a reference to the right child (if any)


        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        @Override
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
    } // -------------------end of nested Node class -------------------------------------------


    //----------------------Nested ElementIterator class--------------------------------------
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = positions().iterator();

        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        public E next() {
            return positionIterator.next().getElement();                             // return element
        }

        public void remove() {
            positionIterator.remove();
        }
    }
    //-----------------------END of nested class


    //----------------TRAVERSALS METHODS--------------------------------------
    //PREORDER
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);        
            
        }
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);                      // fill snapshot recursively
            
        }

        return snapshot;
    } 




    // POSTORDER
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubtree(c, snapshot);
        
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);
            
        }
        return snapshot;
    }




    // BREADTH-FIRST
    public Iterable<Position<E>> breadthFirst() {
        List<Position<E>> snapshot =  new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());                          // start with the root
            while(!fringe.isEmpty()) {                       
                Position<E> p = fringe.dequeue();            // remove from front of the queue
                snapshot.add(p);                             // report this position
                for (Position<E> c : children(p)) {           
                    fringe.enqueue(c);                       // add children to back of queue
                }
            }
        }
        return snapshot;
    }


    // INORDER 
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) {
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if (right(p) != null) {
            inorderSubtree(right(p), snapshot);
        }

    }


    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);                   // fill snapshot recursively
        }

        return snapshot;
    }

    // ----------------END OPF TRAVERSAL ALGORITHMS---------------------------------------------------

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }



    @Override
    public Iterable<Position<E>> positions() {
        return preorder();                              // positions iterable with preorder traversal
//        return postorder();                            // positions iterable with postorder traversal
//        return breadthFirst();                         // positions iterable with breadthFirst traversal
//        return inorder();                              // positions iterable with inorder traversal
    }





    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<>(e, parent, left, right);
    }


    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Not valid position type");
        }

        Node<E> node = (Node<E>) p;                 // safe cast
        if (node.getParent() == node) {             // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        }
        return node;
    }


    public int size() {
        return size;
    }


    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> parent = node.getParent();
        if (node == parent.getLeft()) {
            return parent.getRight();
        }
        return parent.getLeft();
    }

   

    
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }


    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }


    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is not empty");
        }

        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalStateException {
        Node<E> parent = validate(p);

        if (parent.getLeft() != null) {
            throw new IllegalStateException("p already has a left child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalStateException {
        Node<E> parent = validate(p);

        if (parent.getRight() != null) {
            throw new IllegalStateException("p already has a left child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }


    public E set(Position<E> p, E e) {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }


    public void attach(Position<E> p, LinkedBinaryTree<E> tree1,
                       LinkedBinaryTree<E> tree2) throws IllegalArgumentException {
        Node<E> node = validate(p);

        if (isInternal(p)) {
            throw new IllegalArgumentException("p must be leaf");
        }
        size = tree1.size() + tree2.size();
        if (!tree1.isEmpty()) {
            tree1.root.setParent(node);
            node.setLeft(tree1.root);
            tree1.root = null;
            tree1.size = 0;
        }
        if (!tree2.isEmpty()) {
            tree2.root.setParent(node);
            node.setRight(tree2.root);
            tree2.root = null;
            tree2.size = 0;
        }
    }


    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("p has two children");
        }
        Node<E> child = (node.getLeft() != null ? node.getLeft(): node.getRight());
        if (child != null) {
            child.setParent(node.getParent());              // child's grandparent becomes its parent

        }
        if (node == root) {
            child = root;                                   // child becomes root
        }
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            }
            else {
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        node.setParent(node);
        node.setRight(null);
        node.setLeft(null);
        return temp;
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> integerLinkedBinaryTree = new LinkedBinaryTree<>();
        integerLinkedBinaryTree.addRoot(45);
        System.out.println(integerLinkedBinaryTree);
    }
}
