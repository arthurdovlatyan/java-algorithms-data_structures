package data_structure.ao_map;


public interface SortedMap<K,V> extends Map<K,V> {

    /**
     * Returns an entry with the smallest key value
     * @return
     */
    Entry<K,V> firstEntry();

    /**
     * Returns an entry with the largest key value
     * @return
     */
    Entry<K,V> lastEntry();

    /**
     * Returns the entry with the least key value greater than or equal to K
     * @param k
     * @return
     */
    Entry<K,V> ceilingEntry(K k);

    /**
     * Returns the entry with the greatest key value less or equal to K
     * @param k
     * @return
     */
    Entry<K,V> floorEntry(K k);

    /**
     * Returns the entry with the greatest key value strictly less than K
     * @param k
     * @return
     */
    Entry<K,V> lowerEntry(K k);

    /**
     * Returns the entry with the least key value strictly greater than K
     * @param k
     * @return
     */
    Entry<K,V> higherEntry(K k);

    /**
     * Returns an iteration of all entries with the greater than or equal to K1,but
     * strictly less than k2
     * @param k1
     * @param k2
     * @return
     */
    Iterable<Entry<K,V>> subMap(K k1, K k2);
}
