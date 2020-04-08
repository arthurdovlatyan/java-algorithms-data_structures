package data_structure.am_priority_queus;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    private Comparator<K> comparator;

    protected AbstractPriorityQueue(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    protected int compare(Entry<K,V> a, Entry<K,V> b) {
        return comparator.compare(a.getKey(), b.getKey());
    }

    /**
     * Determines whether a key is valid
     */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comparator.compare(key, key) == 0); // see if key can be comapred with itself
        }
        catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    //------------------------------ Nested PQEntry class ------------------------
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K key;
        private V value;

        public PQEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        protected void setKey(K key) {
            this.key = key;
        }

        protected void setValue(V value) {
            this.value = value;
        }
    } //--------------------------END of nested PQEntry class-----------------------
}
