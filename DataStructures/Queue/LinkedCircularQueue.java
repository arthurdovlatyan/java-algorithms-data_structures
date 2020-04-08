package data_structure.af_queus;

import data_structure.ad_linked_list.CircularlyLinkedList;

public class LinkedCircularQueue<E> implements CircularQueue<E> {
    CircularlyLinkedList<E> list = new CircularlyLinkedList<>();

    public LinkedCircularQueue() {}

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

    public void rotate() {
        this.list.rotate();
    }
}
