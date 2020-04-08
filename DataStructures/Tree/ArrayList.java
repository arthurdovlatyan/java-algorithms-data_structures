package data_structure.al_tree_traversals;

import data_structure.ag_list.List;

public class ArrayList<E> implements List<E> {

    private static final int CAPACITY = 16;
    private int size = 0;
    private E[] data;


    public ArrayList() {
        this(CAPACITY);
    }
    public ArrayList(int capacity) {
        this.data = (E[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E get(int i) throws IndexOutOfBoundsException{
        checkIndex(i, this.size);
        return this.data[i];
    }


    public E set(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i, this.size);
        E temp = this.data[i];
        this.data[i] = e;
        return temp;
    }


    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, this.size + 1);
        if (this.size == this.data.length) {
            resize(this.data.length * 2);                           //  not enough capacity, resizing array by 2
        }
        for (int k=this.size - 1; k>=i; k--) {                              //  start by shifting rightmost
            this.data[k + 1] = this.data[k];
        }
        this.data[i] = e;                                                   // ready to place the new element
        this.size++;
    }


    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, this.size);
        E answer = this.data[i];
        for (int k=i; k<this.size - 1; k++) {                               // shift elements to fill hole
            this.data[k] = this.data[k+1];
        }
        this.data[this.size - 1] = null;                                    // help garbage collection
        this.size--;
        return answer;
    }

    /**
     * Checks whether the given index is in the range [0, n−1]
     * @param i index
     * @param n the length of array
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }


    /**
     * Resizes internal array to have given capacity >= size
     */
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];                                  //  safe cast; compiler may give warning
        for (int k=0; k<this.size; k++) {
            temp[k] = this.data[k];
        }
        this.data = temp;                                                       // start using the new array
    }
}
