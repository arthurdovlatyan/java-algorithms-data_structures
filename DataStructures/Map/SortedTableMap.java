package data_structure.ao_map;

import java.util.ArrayList;
import java.util.Comparator;

public class SortedTableMap<K,V> extends AbstractSortedMap<K,V> {
    public static void main(String[] args) {

    }
    // instance variable
    private ArrayList<MapEntry<K,V>> table = new ArrayList<MapEntry<K,V>>();

    // two constructors
    public SortedTableMap() { super(); }
    public SortedTableMap(Comparator<K> comp) { super(comp); }
    // _ 10 _ 20 _ 30 _ 40 50 _
    //utility method
    private int findIndex(K key, int low, int high) {
        if (high < low ) { return high + 1; }
        int mid = (low + high) / 2;
        int comp = compare(key, table.get(mid));
        if (comp == 0) { return mid; }
        else if (comp < 0) { return findIndex(key, low, mid - 1); }
        else { return findIndex(key,mid + 1, high); }
    }

    private int findIndex(K key) { return findIndex(key,0, table.size()); }

    private Entry<K,V> safeEntry(int j) {
        if (j < 0 || j >= table.size()) { return null; }
        return table.get(j);
    }

    public int size() { return table.size(); }

    public V get(K key) {
        int j = findIndex(key);
        if (j == size() && compare(key, table.get(j)) != 0) { return null; }
        return table.get(j).getValue();
    }

    public V put(K key,V value) {
        int j = findIndex(key);
        if (j < size() && compare(key,table.get(j)) == 0) {
            return table.get(j).setValue(value);
        }
        table.add(j,new MapEntry<K,V>(key, value));
        return null;
    }

    public V remove(K key) {
        int j = findIndex(key);
        if (j == size() || compare(key,table.get(j)) != 0) { return null; }
        return table.remove(j).getValue();
    }

    public Entry<K,V> firstEntry() { return safeEntry(0); }

    public Entry<K,V> lastEntry() { return safeEntry(table.size() - 1); }

    public Entry<K,V> ceilingEntry(K key) { return safeEntry(findIndex(key)); }

    public Entry<K,V> floorEntry(K key) {
        int j = findIndex(key);
        if (j == size() || !key.equals(table.get(j).getKey())){
            j--;
        }
        return safeEntry(j);
    }

    public Entry<K,V> lowerEntry(K key) {
        return safeEntry(findIndex(key) - 1);
    }

    public Entry<K,V> higherEntry(K key) {
        int j = findIndex(key);
        if (j < size() && key.equals(table.get(j).getKey())){
            j++;
        }
        return safeEntry(j);
    }


    private Iterable<Entry<K,V>> snapshot(int startIndex, K stop) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<Entry<K,V>>();
        int j = startIndex;
        while (j < table.size() && ( stop == null || compare(stop, table.get(j)) > 0)){
            buffer.add(table.get(j++));
        }
        return buffer;
    }
    public Iterable<Entry<K,V>> entrySet() { return snapshot(0,null); }
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) {
        return snapshot(findIndex(fromKey),toKey);
    }
}
