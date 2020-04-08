package data_structure.ai_iterator;


public interface CustomIterator<E> {

    /**Returns true if there is at least one additional element in the sequence, and false otherwise. */
    boolean hasNext();

    /** Returns the next element in the sequence. */
    E next();

    /**  Removes from the collection the element returned by the most recent call to next( ). Throws an IllegalStateException
     *  if next has not yet been called, or if remove was already called since the most recent call to next.
     */
    default void remove() throws IllegalStateException, UnsupportedOperationException{
        throw new UnsupportedOperationException("remove");
    }
}
