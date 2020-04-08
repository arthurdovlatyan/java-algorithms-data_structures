package data_structure.ai_iterator;

/**
 * Implementing this interface allows an object to be the target of the enhanced
 * {@code for} statement (sometimes called the "for-each loop" statement).
 */
public interface CustomIterable<E> {

    /**
     *  Returns an iterator of the elements in the collection.
     */
    CustomIterator<E> iterator();
}
