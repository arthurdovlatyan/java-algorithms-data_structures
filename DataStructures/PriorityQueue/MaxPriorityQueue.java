package data_structure.newStructure.DataStructures.PriorityQueue;

import data_structure.newStructure.DataStructures.DefaultComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxPriorityQueue<E> {
	// instance variables
	private List<E> queue = new ArrayList<>();
	private Comparator<E> comp;
	private int size = 0;

	// constructors
	public MaxPriorityQueue() {
		comp = new DefaultComparator<>();
	}

	public MaxPriorityQueue(Comparator<E> cmp) {
		comp = cmp;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public E max() {
		if (isEmpty()) {
			return null;
		}
		return queue.get(0);
	}

	public E extractMax() {
		if (isEmpty()) {
			return null;
		}
		size--;
		return queue.remove(0);
	}

	public void insert(E element) {
		if (isEmpty()) {
			queue.add(element);
			size++;
			return;
		}
		for (int i = 0; i < queue.size(); i++) {
			if (comp.compare(element, queue.get(i)) > 0) {
				queue.add(i, element);
				size++;
				return;
			}
		}
		queue.add(element);
		size++;
	}

	public void decreaseKey(E element, E newKey) {
		int desired_index = binarySearch(0, queue.size() - 1, element);
		if (desired_index != queue.size()) {
			queue.set(desired_index, newKey);
			int j = desired_index;
			E tmp = queue.get(desired_index);
			while (j < queue.size() - 1 && comp.compare(queue.get(j + 1), tmp) > 0) {
				queue.set(j, queue.get(j + 1));
				j--;
			}

			queue.set(j, tmp);
		}
	}

	private int binarySearch(int start, int end, E element) {
		if (end < start) {
			return end + 1;
		} else {
			int middle = (end + start) / 2;
			int cmp = comp.compare(element, queue.get(middle));
			if (cmp < 0) {
				return binarySearch(start, middle - 1, element);
			} else if (cmp > 0) {
				return binarySearch(middle + 1, end, element);
			} else {
				return middle;
			}
		}
	}

	public static void main(String[] args) {

		MaxPriorityQueue<Integer> maxq = new MaxPriorityQueue<>();


		System.out.println(maxq.size());
		System.out.println(maxq.isEmpty());

		maxq.insert(9);
		maxq.insert(8);
		maxq.insert(7);
		maxq.insert(6);
		maxq.insert(5);
		maxq.insert(4);
		maxq.insert(3);
		maxq.insert(2);
		maxq.insert(1);
		maxq.insert(10);

		// check for order
		System.out.println(maxq.queue);

		// check for decrease key
		maxq.decreaseKey(10, 5);
		System.out.println(maxq.queue);

		// check for min
		System.out.println(maxq.max());

		// check for extractMin()
		maxq.extractMax();
		System.out.println(maxq.max());
	}
}
