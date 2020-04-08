package data_structure.newStructure.DataStructures.Heap;

import data_structure.newStructure.DataStructures.DefaultComparator;

import java.util.ArrayList;
import java.util.Comparator;

public class MaxHeap<E> {

	private ArrayList<E> heap = new ArrayList<>();
	private Comparator<E> comp;

	public MaxHeap() {
		comp = new DefaultComparator<>();
	}

	public MaxHeap(Comparator<E> c) {
		comp = c;
	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public E max() {
		return heap.get(0);
	}

	public E insert(E value) {
		heap.add(value);
		upHeap(heap.size() - 1);
		return value;
	}

	public E removeMax() {
		if (heap.isEmpty()) {
			return null;
		}
		E answer = heap.get(0);
		swap(0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		downHeap(0);
		return answer;
	}

	protected void upHeap(int i) {
		while (i > 0) {
			int p = parent(i);
			if (comp.compare(heap.get(i), heap.get(p)) <= 0) {
				break;
			} else {
				swap(i, p);
				i = p;
			}
		}
	}

	protected void downHeap(int i) {
		while (hasLeft(i)) {
			int leftChild = left(i);
			int greaterIndex = leftChild;
			if (hasRight(i)) {
				int rightChild = right(i);
				if (comp.compare(heap.get(greaterIndex), heap.get(rightChild)) > 0) {
					greaterIndex = rightChild;
				}
			}
			if (comp.compare(heap.get(greaterIndex), heap.get(i)) <= 0) {
				break;
			} else {
				swap(i, greaterIndex);
				i = greaterIndex;
			}
		}
	}

	protected int parent(int i) {
		return i / 2;
	}

	protected int left(int i) {
		return 2 * i;
	}

	protected int right(int i) {
		return 2 * i + 1;
	}

	protected boolean hasLeft(int i) {
		return left(i) < heap.size();
	}

	protected boolean hasRight(int i) {
		return right(i) < heap.size();
	}

	protected void swap(int i, int j) {
		E tmp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tmp);
	}

	protected void heapify() {
		int startIndex = parent(size() - 1);
		for (int j = startIndex; j >= 0; j--) {
			downHeap(j);
		}
	}
}
