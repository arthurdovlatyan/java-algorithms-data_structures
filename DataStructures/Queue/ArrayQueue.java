package data_structure.af_queus;

public class ArrayQueue<E> implements Queue<E> {
    private static final int CAPACITY = 1000;
    private E[] data;                           // generic array used for storage
    private int f = 0;                          // index of the front element
    private int sz = 0;                         // current number of elements

    public ArrayQueue() {
        this(CAPACITY);
    }
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return this.sz;
    }

    public boolean isEmpty() {
        return this.sz == 0;
    }

    public void enqueue(E e) throws IllegalStateException {
        if (this.sz == this.data.length) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (this.f + this.sz) % this.data.length;
        this.data[avail] = e;
        this.sz++;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return this.data[f];
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = this.data[this.f];
        this.data[this.f] = null;
        this.f = (this.f + 1) % this.data.length;
        this.sz--;
        return answer;
    }

}
