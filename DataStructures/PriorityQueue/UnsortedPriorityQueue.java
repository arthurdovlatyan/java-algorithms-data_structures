package data_structure.am_priority_queus;


import data_structure.ah_positional_list.Position;
import data_structure.ah_positional_list.PositionalList;
import data_structure.ai_iterator.LinkedPositionalListIterator;

import java.util.Comparator;

/**
 * An implementation of a proirity queue with an unsorted list
 */
public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    private LinkedPositionalListIterator<Entry<K,V>> list = new LinkedPositionalListIterator<>();

    public UnsortedPriorityQueue() {
        super();
    }

    public UnsortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the Position of an entry having minimal key
     */
    private Position<Entry<K,V>> findMin() { // only called when nonempty
        Position<Entry<K,V>> small = list.first();
        for (Position<Entry<K,V>> walk: list.positions()) {
            if (compare(walk.getElement(), small.getElement()) < 0) {
                small = walk;
            }
        }
        return small;
    }

    public int size() {
        return list.size();
    }

    public Entry<K, V> insert(K key, V value) {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    public Entry<K,V> min() {
        if (list.isEmpty()) {return null;}
        return findMin().getElement();
    }

    public Entry<K,V> removeMin() {
        if (list.isEmpty()) {return null;}
        return list.remove(findMin());
    }
}
