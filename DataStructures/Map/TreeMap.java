package data_structure.ao_map;
import data_structure.ak_binary_tree.LinkedBinaryTree;
import data_structure.aj_trees.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;



public class TreeMap<K,V> extends AbstractSortedMap<K,V> {

    protected BalancableBinaryTree<K,V> tree = new BalancableBinaryTree<>();

    public TreeMap() {
        super();
        tree.addRoot(null);
    }

    public TreeMap(Comparator<K> comp){
        super(comp);
        tree.addRoot(null);
    }

    public int size() {
        return ((tree.size() - 1 ) / 2);
    }

    // utility methods


    private void expandExternal(Position<Entry<K,V>> position,Entry<K,V> entry) {
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

    protected void rotate(Position<Entry<K,V>> p){
        tree.rotate(p);
    }

    protected Position<Entry<K,V>> restructure(Position<Entry<K,V>> x){
        return tree.restructure(x);
    }

    protected void rebalanceInsert(Position<Entry<K,V>> p) {}
    protected void rebalanceDelete(Position<Entry<K,V>> p) {}
    protected void rebalanceAccess(Position<Entry<K,V>> p) {}

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
            if (compare(position.getElement(), fromKey) < 0) {
                subMapRecursive(fromKey, toKey, right(position), buffer);
            }else {
                subMapRecursive(fromKey, toKey, left(position), buffer);
                if (compare(position.getElement(), toKey) < 0) {
                    buffer.add(position.getElement());
                    subMapRecursive(fromKey, toKey, right(position), buffer);
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
        else if (comp < 0) { return treeSearch(left(position), key); }
        else { return treeSearch(right(position), key); }
    }

    public V get(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> position = treeSearch(root(),key);
        rebalanceAccess(position);
        if (isExternal(position)) { return null; }
        return position.getElement().getValue();
    }

    public V put(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newEntry = new MapEntry<>(key,value);
        Position<Entry<K,V>> position = treeSearch(root(),key);

        if (isExternal(position)) {
            expandExternal(position,newEntry);
            rebalanceInsert(position);
            return null;
        }else {
            V old = position.getElement().getValue();
            set(position,newEntry);
            rebalanceAccess(position);
            return old;
        }
    }

    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> position = treeSearch(root(),key);

        if (isExternal(position)) {
            rebalanceAccess(position);
            return null;
        }else {
            V old = position.getElement().getValue();

            if (isInternal(left(position)) && isInternal(right(position))) {
                Position<Entry<K,V>> replacement = treeMax(left(position));
                set(position,replacement.getElement());
                position = replacement;
            }
            Position<Entry<K,V>> leaf = isExternal(left(position)) ? left(position) : right(position);
            Position<Entry<K,V>> sibling = sibling(leaf);
            remove(leaf);
            remove(position);
            rebalanceDelete(sibling);
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
        Position<Entry<K,V>> p = treeSearch(root(), key);
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
            subMapRecursive(fromKey, toKey,root(), buffer);
        }
        return buffer;
    }

    protected static class BalancableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>>{

        protected static class BSTNode<E> extends Node<E> {
            int aux = 0;

            BSTNode(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild){
                super(element,parent,leftChild,rightChild);
            }

            public int getAux() { return aux; }

            public void setAux(int aux) { this.aux = aux; }
        }

        public int getAux(Position<Entry<K,V>> position) {
            return ((BSTNode<Entry<K,V>>) position).getAux();
        }

        public void setAux(Position<Entry<K,V>> position,int value){
            ((BSTNode<Entry<K,V>>) position).setAux(value);
        }

        protected Node<Entry<K,V>> createNode(Entry<K,V> element, Node<Entry<K,V>> parent, Node<Entry<K,V>> leftChild, Node<Entry<K,V>> rightChild){
            return new BSTNode<>(element,parent,leftChild,rightChild);
        }

        private void relink(Node<Entry<K,V>> parent, Node<Entry<K,V>> child, boolean makeLeftChild){
            child.setRight(parent);
            if (makeLeftChild) {
                parent.setLeft(child);
            }else {
                parent.setRight(child);
            }
        }

        public void rotate(Position<Entry<K,V>> position){
            Node <Entry<K,V>> x = validate(position);
            Node <Entry<K,V>> y = x.getParent();
            Node <Entry<K,V>> z = y.getParent();

            if (z == null) {
                root = x;
                x.setParent(null);
            }else {
                boolean makeLeftChild = (y == z.getLeft());
                relink(z, x, makeLeftChild);
            }

            if (x == y.getLeft()){
                relink(y, x.getRight(),true);
                relink(x, y,false);
            }else {
                relink(y, x.getLeft(),false);
                relink(x, y,true);
            }
        }

        public Position<Entry<K,V>> restructure(Position<Entry<K,V>> x){
            Position<Entry<K,V>> y = parent(x);
            Position<Entry<K,V>> z = parent(y);

            if ((x == right(y)) == (y == right(z))){
                rotate(y);
                return y;
            }else {
                rotate(x);
                rotate(x);
                return x;
            }
        }
    }


}
