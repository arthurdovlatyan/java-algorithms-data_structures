package data_structure.ap_avlMap;

import data_structure.aj_trees.Position;
import data_structure.ao_map.Entry;
import data_structure.ao_map.TreeMap;
import java.util.*;

public class AVLMap<K,V> extends TreeMap<K,V> {

    public AVLMap() {}

    public AVLMap(Comparator<K> comp) {
        super(comp);
    }

    protected int height(Position<Entry<K,V>> position) {
        return tree.getAux(position);
    }

    protected void recomputeHeight(Position<Entry<K,V>> position) {
        tree.setAux(position, 1 + Math.max(height(left(position)), height(right(position))));
    }

    protected boolean isBalanced(Position<Entry<K,V>> position){
        return Math.abs(height(left(position)) - height(right(position)))  <= 1;
    }

    protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> position) {
        if (height(left(position)) > height(right(position))) {
            return left(position);
        }
        if (height(left(position)) < height(right(position))) {
            return right(position);
        }

        if (isRoot(position)) {
            return left(position);
        } else if (position == left(parent(position))){
            return left(position);
        }else {
            return right(position);
        }
    }

    protected void rebalance(Position<Entry<K,V>> position) {
        int oldHeight, newHeight;
        do {
            oldHeight = height(position);
            if (!isBalanced(position)) {
                position = restructure(tallerChild(tallerChild(position)));
                recomputeHeight(left(position));
                recomputeHeight(right(position));
            }
            recomputeHeight(position);
            newHeight = height(position);
            position = parent(position);
        }while (oldHeight != newHeight && position != null);
    }

    protected void rebalanceInsert(Position<Entry<K,V>> position) {
        rebalance(position);
    }

    protected void rebalanceDelete(Position<Entry<K,V>> position) {
        rebalance(position);
    }

    protected void rebalanceAccess(Position<Entry<K,V>> position) {

        rebalance(position);
    }
}

