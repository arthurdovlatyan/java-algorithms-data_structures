package data_structure.ao_map;

public interface Entry<K,V> {

    /**
     * Returns the key of the entry
     * @return
     */
    K getKey();

    /**
     * Returns the value of the given entry
     * @return
     */
    V getValue();
}
