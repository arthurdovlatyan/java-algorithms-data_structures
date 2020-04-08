package data_structure.ao_map;


public interface Map<K,V> {

    /**
     * Returns the size of the Map
     * @return type is an integer indicating the size of the map
     */
    int size();

    /**
     * Returns true if the map is empty else returns false
     * @return a boolean value to indicate the emptynes or fulness of the map
     */
    boolean isEmpty();

    /**
     * Returns the value associated with the given key if no such key is stored in the collection then returns null
     * @param key is the key to be searched in the collection
     * @return is the value to be searched in the collection
     */
    V get(K key);

    /**
     * Inserts a new value according to the key value pair if the key doesn't exist then returns null if key already
     * exists then returns the old value ass return argument
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    /**
     * Removes the value associated with the given key if the key is absent then value returned is null
     * @param key is the key to be deleted
     * @return is the value associated with the given key
     */
    V remove(K key);

    /**
     * Returns an iterable collection of keys
     * @return
     */
    Iterable<K> keySet();

    /**
     * Returns an iterable collection of values
     * @return
     */
    Iterable<V> values();

    /**
     * Returns an iterable collection of entries
     * @return
     */
    Iterable<Entry<K,V>> entrySet();

}
