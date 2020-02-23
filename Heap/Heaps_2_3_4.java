package DataStructures.Heap;

public class Heaps_2_3_4 <E,V>{
    public class Node<E,V> {
        private int degree = 0;
        private E key;
        public Node<E,V> children[] = (Node<E,V>[]) new Object[3];
        private Node<E,V> parent;

        public Node(E key, Node<E,V> parent){
            this.key = key;
            this.parent = parent;
        }

        public int getDegree() {
            return degree;
        }

        public E getKey() {
            return key;
        }

        public Node<E, V>[] getChildren() {
            return children;
        }

        public Node<E, V> getParent() {
            return parent;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public void setKey(E key) {
            this.key = key;
        }

        public void setParent(Node<E, V> parent) {
            this.parent = parent;
        }
    }

    public Heaps_2_3_4(){}


}
