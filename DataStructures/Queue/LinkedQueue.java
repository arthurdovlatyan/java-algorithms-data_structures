package data_structure.af_queus;

import data_structure.ad_linked_list.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public static void main(String[] args) {
    }

    public LinkedQueue() {}

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void enqueue(E element) {
        this.list.addLast(element);
    }

    public E first() {
        return this.list.first();
    }

    public E dequeue() {
        return this.list.removeFirst();
    }
}
