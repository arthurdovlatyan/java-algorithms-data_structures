package DataStructures.Heap;

import DataStructures.DefaultComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MinHeap<E> {
    private ArrayList<E> heap = new ArrayList<>();
    private Comparator<E> comp;
    public MinHeap(){
        comp = new DefaultComparator<>();
    }
    public MinHeap(Comparator<E> cmp){
        comp = cmp;
    }

    public int size(){
        return heap.size();
    }
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    public E min(){
        return heap.get(0);
    }
    public E insert(E value)throws IllegalArgumentException{
        heap.add(value);
        upHeap(heap.size() - 1);
        return value;
    }
    public E removeMin(){
        if (heap.isEmpty()) { return null; }
        E answer = heap.get(0);
        swap(0,heap.size() - 1);
        heap.remove(heap.size() - 1);
        downHeap(0);
        return answer;
    }
    public void upHeap(int i){
        while (i > 0){
            int p = parent(i);
            if (comp.compare(heap.get(i),heap.get(p)) >= 0) {
                break;
            }else {
                swap(i,p);
                i = p;
            }
        }
    }
    public void downHeap(int i){
        while (hasLeft(i)){
            int leftChild = left(i);
            int smallChild = leftChild;
            if (hasRight(i)){
                int rightChild = right(i);
                if(comp.compare(heap.get(leftChild),heap.get(rightChild)) > 0){
                    smallChild = rightChild;
                }
            }
            if (comp.compare(heap.get(smallChild),heap.get(i)) >= 0){
                break;
            }else {
                swap(i,smallChild);
                i = smallChild;
            }
        }
    }
    protected int parent(int i) {
        return i/2;
    }
    protected int left(int i){
        return 2 * i;
    }
    protected int right(int i){
        return 2 * i + 1;
    }
    protected boolean hasLeft(int i){
        return left(i) < heap.size();
    }
    protected boolean hasRight(int i){
        return right(i) < heap.size();
    }
    protected void swap(int i, int j){
        E tmp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,tmp);
    }
    protected void heapify(){
        int startIndex = parent(size() - 1);
        for (int j = startIndex; j >= 0; j--){
            downHeap(j);
        }
    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();

        heap.insert(16);
        heap.insert(14);
        heap.insert(10);
        heap.insert(8);
        heap.insert(7);
        heap.insert(9);
        heap.insert(3);
        heap.insert(2);
        heap.insert(4);
        heap.insert(1);

        System.out.println(Arrays.toString(heap.heap.toArray()));
    }
}
