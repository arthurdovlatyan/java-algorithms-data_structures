package data_structure.ai_iterator;

import data_structure.ag_list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListIterator<E> implements List<E>, Iterable<E> {

    private static final int CAPACITY = 16;
    private int size = 0;
    private E[] data;


    public ArrayListIterator() {
        this(CAPACITY);
    }
    public ArrayListIterator(int capacity) {
        this.data = (E[]) new Object[capacity];
    }


   // ----------- nested ArrayIterator class ----------------

    /**
     * A (nonstatoc) inner class. EACH INSTANCE CONATAINS AN IMPLICIT REFERENCE TO THE CONTAINING LIST, ALLOWING IT TO
     * ACCESS THE LISTS'S MEMBERS
     */
    private class ArrayIterator implements Iterator<E> {
        private int j = 0;                          // index of the next element to the report
        private boolean removable = false;          // can remove() be called at this time


        /**
         * Tests whether the iterator has a next object.
         * @return true if there are further objects, false otherwise
         */
        public boolean hasNext() {
            return this.j < ArrayListIterator.this.size;    // size is field of outher instance
        }


        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException{
            if (this.j == ArrayListIterator.this.size) {
                throw new NoSuchElementException("No next element");
            }
            this.removable = true;                          // current element can be subsequently be removed
            return ArrayListIterator.this.data[this.j++];   // post-increment j, so it is ready for the future call to next
        }


        /**
         * Removes the element returned by the most recent call to next
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove() method was already called since recent next() method
         */
        public void remove() throws IllegalStateException {
            if (!removable) {
                throw new IllegalStateException("Nothing to remove");
            }
            ArrayListIterator.this.remove(this.j - 1);      // that was the last one returned after next() method call
            this.j--;                                         // next element has shifted one cell to the left
            this.removable = false;                           // do not allow remove again untill next() method is called
        }
    }       // ----------- END nested ArrayIterator class ----------------

    /** Returns an iterator of the element stored in the list */
    public Iterator<E> iterator() {
        return new ArrayIterator();                           // create a new instance of inner class
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
