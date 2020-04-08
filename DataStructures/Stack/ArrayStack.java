package data_structure.ae_stacks;

public class ArrayStack<E> implements Stack<E> {
    public static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {

        data = (E[])new Object[capacity];
    }

    public int size() {

        return this.t + 1;
    }

    public boolean isEmpty() {

        return this.t == -1;
    }

    public void push(E e) throws IllegalStateException {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[++this.t] = e;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[this.t];
        data[this.t] = null;
        this.t--;
        return answer;
    }
}
