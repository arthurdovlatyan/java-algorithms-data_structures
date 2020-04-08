package data_structure.ag_list;

public interface List<E>  {

    /**
     * Returns the number of elements in this list
     */
    int size();


    /**
     * Returns whether the list is empty
     */
    boolean isEmpty();


    /**
     * Returns (but does not remove) the element at index i
     */
    E get(int i) throws IndexOutOfBoundsException;


    /**
     * Replaces the element at given index i, and returns the replaced element
     */
    E set(int i, E e) throws IndexOutOfBoundsException;


    /**
     * Inserts element e to be at index i, shifting all subsequent elements later
     */
    void add(int i, E e) throws IndexOutOfBoundsException;


    /**
     * Removes and returns the element at index i, shifting all subsequent elements earlier
     */
    E remove(int i) throws IndexOutOfBoundsException;
}
