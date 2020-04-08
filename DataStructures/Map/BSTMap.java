package data_structure.aq_bstMap;

import data_structure.aj_trees.Position;
import data_structure.aj_trees.*;
import data_structure.ag_list.List;
import data_structure.ao_map.AbstractSortedMap;
import data_structure.ao_map.Entry;
import data_structure.ak_binary_tree.LinkedBinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.EmptyStackException;


public class BSTMap<K,V> extends AbstractSortedMap<K,V> {

    private LinkedBinaryTree<Entry<K,V>> tree = new LinkedBinaryTree<Entry<K,V>>();

    public BSTMap() {
        super();
        tree.addRoot(null);
    }

    public BSTMap(Comparator<K> comp){
        super(comp);
        tree.addRoot(null);
    }

    public int size() {
        return ((tree.size() - 1 ) / 2);
    }

    // utility methods


    private void expandExternal(Position<Entry<K,V>> position, Entry<K,V> entry) {
        tree.set(position, entry);
        tree.addLeft(position,null);
        tree.addRight(position,null);
    }

    protected Position<Entry<K,V>> root() {
        return tree.root();
    }

    protected Position<Entry<K,V>> parent(Position<Entry<K,V>> p){
        return tree.parent(p);
    }

    protected Position<Entry<K,V>> left(Position<Entry<K,V>> p){
        return tree.left(p);
    }

    protected Position<Entry<K,V>> right(Position<Entry<K,V>> p){
        return tree.right(p);
    }

    protected Position<Entry<K,V>> sibling(Position<Entry<K,V>> p){
        return tree.sibling(p);
    }

    protected boolean isRoot(Position<Entry<K,V>> p){
        return tree.isRoot(p);
    }

    protected boolean isExternal(Position<Entry<K,V>> p){
        return tree.isExternal(p);
    }

    protected boolean isInternal(Position<Entry<K,V>> p){
        return tree.isInternal(p);
    }

    protected void set(Position<Entry<K,V>> p, Entry<K,V> e){
        tree.set(p,e);
    }

    protected Entry<K,V> remove(Position<Entry<K,V>> p){
        return tree.remove(p);
    }

    protected Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p) {
        Position<Entry<K,V>> walk = p;
        while (isInternal(walk)) {
            walk = left(walk);
        }
        return parent(walk);
    }

    protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) {
        Position<Entry<K,V>> walk = p;
        while (isInternal(walk)) {
            walk = right(walk);
        }
        return parent(walk);
    }

    private void subMapRecursive (K fromKey, K toKey, Position<Entry<K,V>> position, ArrayList<Entry<K,V>> buffer){
        if (isInternal(position)) {
            if (compare(position.getElement(),fromKey) < 0) {
                subMapRecursive(fromKey,toKey,right(position),buffer);
            }else {
                subMapRecursive(fromKey,toKey,left(position),buffer);
                if (compare(position.getElement(),toKey) < 0) {
                    buffer.add(position.getElement());
                    subMapRecursive(fromKey,toKey,right(position),buffer);
                }
            }
        }
    }
    private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> position, K key){
        if (isExternal(position)) {
            return position;
        }
        int comp = compare(key, position.getElement());
        if (comp == 0) { return position; }
        else if (comp < 0) { return treeSearch(left(position),key); }
        else { return treeSearch(right(position), key); }
    }

    public V get(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> position = treeSearch(root(),key);
        if (isExternal(position)) { return null; }
        return position.getElement().getValue();
    }

    public V put(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newEntry = new MapEntry<>(key,value);
        Position<Entry<K,V>> position = treeSearch(root(),key);

        if (isExternal(position)) {
            expandExternal(position,newEntry);
            return null;
        }else {
            V old = position.getElement().getValue();
            set(position,newEntry);
            return old;
        }
    }

    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> position = treeSearch(root(),key);

        if (isExternal(position)) {
            return null;
        }else {
            V old = position.getElement().getValue();

            if (isInternal(left(position)) && isExternal(right(position))) {
                Position<Entry<K,V>> replacement = treeMax(left(position));
                set(position,replacement.getElement());
                position = replacement;
            }
            Position<Entry<K,V>> leaf = isExternal(left(position)) ? left(position) : right(position);
            Position<Entry<K,V>> sibling = sibling(leaf);
            remove(leaf);
            remove(position);
            return old;

        }
    }
    public Entry<K,V> firstEntry() {
        if (isEmpty()) {
            return null;
        }

        return treeMin(root()).getElement();
    }


    public Entry<K,V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return treeMax(root()).getElement();
    }

    public Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(),key);
        if (isInternal(p)){ return p.getElement(); }
        while (!isRoot(p)) {
            if (p == left(parent(p))) {
                return parent(p).getElement();
            }else {
                p = parent(p);
            }
        }
        return null;
    }

    public Entry<K,V> floorEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> position = treeSearch(root(),key);

        if (isInternal(position)) {
            return position.getElement();
        }

        while (!isRoot(position)) {
            if (position == right(parent(position))){
                return parent(position).getElement();
            } else {
                position = parent(position);
            }
        }
        return null;
    }

    public Entry<K,V> higherEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(),key);
        if (isInternal(p) && isInternal(right(p))) {
            return treeMin(right(p)).getElement();
        }
        while (!isRoot(p)){
            if (p == left(parent(p))) {
                return parent(p).getElement();
            }else {
                p = parent(p);
            }
        }
        return null;
    }
    public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> position = treeSearch(root(), key);

        if (isInternal(position) && isInternal(left(position))) {
            return treeMax(left(position)).getElement();
        }

        while (!isRoot(position)){
            if (position == right(parent(position))) {
                return parent(position).getElement();
            }else {
                position = parent(position);
            }
        }
        return null;
    }

    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<Entry<K,V>>(size());

        for (Iterator<Position<Entry<K,V>>> position = tree.inorder().iterator(); position.hasNext();){
            Position<Entry<K,V>> it = position.next();
            if (isInternal(it)) {
                buffer.add(it.getElement());
            }
        }
        
        return buffer;
    }

    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        ArrayList<Entry<K,V>> buffer = new ArrayList<Entry<K,V>>(size());

        if (compare(fromKey,toKey) < 0) {
            subMapRecursive(fromKey,toKey,root(),buffer);
        }
        return buffer;
    }
}
