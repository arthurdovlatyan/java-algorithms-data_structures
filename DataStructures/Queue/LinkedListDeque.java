package data_structure.af_queus;

import data_structure.ad_linked_list.DoublyLinkedList;

public class LinkedListDeque<E> implements Deque<E> {

    DoublyLinkedList<E> doublyLinkedList = new DoublyLinkedList<>();

    public int size() {
        return doublyLinkedList.size();
    }

    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    public E first() {
        return doublyLinkedList.first();
    }

    public E last() {
        return doublyLinkedList.last();
    }


    public void addFirst(E element) {
        doublyLinkedList.addFirst(element);
    }

    public void addLast(E element) {
        doublyLinkedList.addLast(element);
    }

    public E removeFirst() {
        return doublyLinkedList.removeFirst();
    }

    public E removeLast() {
        return doublyLinkedList.removeLast();
    }
}
