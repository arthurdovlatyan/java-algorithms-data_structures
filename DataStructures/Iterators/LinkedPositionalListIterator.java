package data_structure.ai_iterator;

import data_structure.ah_positional_list.LinkedPositionalList;
import data_structure.ah_positional_list.Position;
import data_structure.ah_positional_list.PositionalList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Implementation of a positional list stores as doubly linked list*/
public class LinkedPositionalListIterator<E> implements PositionalList<E>, Iterable<E> {

    private Node<E> header;                 // header sentinel
    private Node<E> trailer;                // trailer sentinel
    private int size = 0;                    // number of elements in the list

    /** Constructs a new empty list. */
    public LinkedPositionalListIterator() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    // -----------------Nested Node class -----------------------------------------------
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;               //  reference to the element stored at this node
            prev = p;                  // reference to the previous node in the list
            next = n;                  // reference to the subsequent node in the list
        }

        public E getElement() throws IllegalStateException{
            if (next == null) {        //  convention for defunct node
                throw new IllegalStateException("Position is no longer valid");
            }
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    } // ---------------------end of nested Node class ------------------------------------------------


    //---------------- nested PositionIterator class ----------------
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = LinkedPositionalListIterator.this.first();     // position of the next element of the report
        private Position<E> recent = null;                                          // position of the last reported element

        /** Tests whether the iteration has a next object */
        public boolean hasNext() {
            return this.cursor != null;
        }


        /** Returns the next position in the iterator */
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) {
                throw new NoSuchElementException("nothing left");
            }
            this.recent = this.cursor;
            this.cursor = LinkedPositionalListIterator.this.after(this.cursor);
            return this.recent;
        }


        /** Removes the element returned by the most recent call to the next */
        public void remove() throws IllegalStateException {
            if (recent == null) {
                throw new IllegalStateException("nothing to remove");
            }
            LinkedPositionalListIterator.this.remove(this.recent);              // remove from outer list
            this.recent = null;                                                 // do not allow remove again until next is called
        }
    }       //-------------end nested PositionIterator class ------------------------------------------------------


    //-----------------------nested PositionIterable class ----------------------------------------------------------
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    } // ----------------------- END nested PositionIterable class ---------------------------------------------------


    /** Returns an itarable representation if the list's positions */
    public Iterable<Position<E>> positions() {
        return new PositionIterable();                                  // create a new instance of inner class
    }



    //------------------------nested ElementIterator class ---------------------------------
    /** This class adapts the iteration produced by positions() to return elements */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = new PositionIterator();

        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        public E next() {
            return positionIterator.next().getElement();            // RETURNS ELEMENT!
        }

        public void remove() {
            positionIterator.remove();
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Position<E> first() {
        return position(this.header.getNext());
    }

    public Position<E> last() {
        return position(this.trailer.getPrev());
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    public Position<E> addFirst(E e) {
        return addBetween(e, this.header, this.header.getNext());
    }

    public Position<E> addLast(E e) {
        return addBetween(e, this.trailer.getPrev(), this.trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e)  throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(node.next);
        successor.setPrev(node.prev);

        node.setElement(null);                  // help with garbage collection and convention for defunct node
        node.setPrev(null);
        node.setNext(null);
        this.size--;
        return answer;

    }



    /** Validates the position and returns it as a node */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node )) {
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>) p;                 // safe cast
        if (node.getNext() == null) {               // convention of defunct node
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    /** Returns the given node as a Position (or null if it is a sentinel)*/
    private Position<E> position(Node<E> node) {
        if (node == this.header || node == this.trailer) {
            return null;
        }
        return node;
    }

    /** Adds element e to the linked list between the given nodes. */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newestNode = new Node<>(e, pred, succ);
        pred.setNext(newestNode);
        succ.setPrev(newestNode);
        this.size++;
        return position(newestNode);
    }

    /**  Returns an iterator of the elements stored in the list. */
    public Iterator<E> iterator() {
        return new ElementIterator();

    }
}
