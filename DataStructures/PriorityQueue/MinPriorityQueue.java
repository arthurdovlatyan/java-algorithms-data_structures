package data_structure.newStructure.DataStructures.PriorityQueue;

import data_structure.newStructure.DataStructures.DefaultComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinPriorityQueue<E> {

	// instance variables
	private List<E> queue = new ArrayList<>();
	private Comparator<E> comp;
	private int size = 0;

	// constructors
	public MinPriorityQueue() {
		comp = new DefaultComparator<>();
	}

	public MinPriorityQueue(Comparator<E> cmp) {

		comp = cmp;
	}

	public int size() {

		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public E min() {
		if (isEmpty()) {
			return null;
		}
		return queue.get(0);
	}

	public E extractMin() {
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
			if (comp.compare(element, queue.get(i)) < 0) {
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
			while (j > 0 && comp.compare(queue.get(j - 1), tmp) > 0) {
				queue.set(j, queue.get(j - 1));
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
		MinPriorityQueue<Integer> minq = new MinPriorityQueue<>();


		System.out.println(minq.size());
		System.out.println(minq.isEmpty());

		minq.insert(9);
		minq.insert(8);
		minq.insert(7);
		minq.insert(6);
		minq.insert(5);
		minq.insert(4);
		minq.insert(3);
		minq.insert(2);
		minq.insert(1);
		minq.insert(10);

		// check for order
		System.out.println(minq.queue);

		// check for decrease key
		minq.decreaseKey(10, 5);
		System.out.println(minq.queue);

		// check for min
		System.out.println(minq.min());

		// check for extractMin()
		minq.extractMin();
		System.out.println(minq.min());
	}
}
