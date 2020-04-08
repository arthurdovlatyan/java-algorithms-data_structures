package data_structure.ae_stacks;

import data_structure.ad_linked_list.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public LinkedStack() {}

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void push(E element) {
        this.list.addFirst(element);
    }

    public E top() {
        return this.list.first();
    }

    public E pop() {
        return this.list.removeFirst();
    }
}
