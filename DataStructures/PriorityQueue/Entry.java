package data_structure.am_priority_queus;

/** Interface for key-value pair */
public interface Entry<K, V> {
    K getKey();             // returns the key stored in this entry
    V getValue();           // returns the value stored in this entry
}
