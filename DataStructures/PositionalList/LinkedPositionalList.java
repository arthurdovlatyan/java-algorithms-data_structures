package data_structure.ah_positional_list;

/** Implementation of a positional list stores as doubly linked list*/
public class LinkedPositionalList<E> implements PositionalList<E> {

    private Node<E> header;                 // header sentinel
    private Node<E> trailer;                // trailer sentinel
    private int size = 0;                    // number of elements in the list

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

    /** Constructs a new empty list. */
    public LinkedPositionalList() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, header, null);
        header.setNext(trailer);
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

}
