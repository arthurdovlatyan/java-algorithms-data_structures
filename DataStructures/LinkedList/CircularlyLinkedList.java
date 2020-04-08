package data_structure.ad_linked_list;

public class CircularlyLinkedList<E> {
    private Node<E> tail = null;                // we store tail (but not head)

    private int size = 0;                       // number of nodes in the list

    public CircularlyLinkedList() {             // constructs an initially empty list

    }

    public Node<E> getTail() {
        return tail;
    }
    //---------------- nested Node class ----------------
    /**
     * Node of a circular linked list, which stores a reference to its
     * element and to the subsequent node in the list (or null if this
     * is the last node).
     */
    public static class Node<E> {


        /** The element stored at this node */
        private E element;            // reference to the element stored at this node

        /** A reference to the subsequent node in the list */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         * @param n  reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods
        /**
         * Returns the element stored at the node.
         * @return the element stored at the node
         */
        public E getElement() {

            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods
        /**
         * Sets the node's next reference to point to Node n.
         * @param n    the node that should follow this one
         */
        public void setNext(Node<E> n) {

            next = n;
        }
    } //----------- end of nested Node class -----------


    public int size() {
        return this.size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * returns (but does not remove) the first element
     * @return
     */
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return tail.getNext().getElement();                 // the head is *after* the tail

    }


    /**
     *  update methods
     *  rotate the first element to the back of the list
     */
    public void rotate() {
        if (tail != null) {                                 //  if empty, do nothing
            tail = tail.getNext();                          // the old head becomes the new tail
        }
    }


    /**
     * Adds element e to the front of the list
     * @param e
     */
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);                             // link to itself circularly
        }
        else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }


    /**
     *  Adds element e to the end of the list
     * @param e
     */
    public void addLast(E e) {
        addFirst(e);                                       //  insert new element at front of list
        tail = tail.getNext();                             // now new element becomes the tail
    }


    /**
     *  Removes and returns the first element
     * @return
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;                                    // nothing to remove
        }
        Node<E> head = tail.getNext();
        if (head == tail) {
            tail = null;                                    // must be the only node left
        }
        else {
            tail.setNext(head.getNext());                    // removes ”head” from the list
        }
        size--;
        return head.getElement();
    }


}
