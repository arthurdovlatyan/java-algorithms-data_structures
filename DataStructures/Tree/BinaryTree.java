package data_structure.al_tree_traversals;

import data_structure.aj_trees.Position;
import data_structure.aj_trees.Tree;


public interface BinaryTree<E> extends Tree<E> {

    Position<E> left(Position<E> p) throws IllegalArgumentException;

    Position<E> right(Position<E> p) throws IllegalArgumentException;

    Position<E> sibling(Position<E> p) throws IllegalArgumentException;


}
