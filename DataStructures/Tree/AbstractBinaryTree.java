package data_structure.al_tree_traversals;

import data_structure.aj_trees.AbstractTree;
import data_structure.aj_trees.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) {                               //  p must be the root which has no parrent
            return null;
        }

        if (p == left(parent)) {                            // if p is a left child
            return right(parent);                           // return right child
        }
        return left(parent);                                // else return left child
    }

    public int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }


    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2); // max capacity of 2
        if (left(p) != null) {
            snapshot.add(left(p));
        }
        if (right(p) != null) {
            snapshot.add(right(p));
        }
        return snapshot;
    }
}
