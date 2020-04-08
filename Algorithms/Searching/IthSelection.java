package data_structure.newStructure.Algorithms.Searching;

import java.util.Random;

public class IthSelection {
	public static int ithSelection(int[] arr, int i) {
		return ithSelection(arr, 0, arr.length - 1, i);
	}

	public static int partition(int[] arr, int start, int end) {
		int i = (start - 1);  // index of smaller element
		for (int j = start; j < end; j++) {
			if (arr[j] < arr[end]) {
				i++;
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		int tmp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = tmp;
		return i + 1;
	}

	public static int ithSelection(int[] arr, int start, int end, int i) {
		if (start == end) {
			return arr[start];
		}
		int q = partition(arr, start, end);
		int k = q - start + 1;
		if (i == k) {
			return arr[i];
		} else if (i < k) {
			return ithSelection(arr, start, q - 1, i);
		} else {
			return ithSelection(arr, q + 1, end, i - k);
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[100];

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			randomFill(arr);
			int j = rand.nextInt(arr.length);
			System.out.println("I is " + j + " result is  " + ithSelection(arr, j));
		}

	}


	// Fills the array with elements from [1 ... 10^6] and shuffles them
	public static void randomFill(int arr[]) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
//        for (int i = 0; i < arr.length;i++){
//            int swap = rand.nextInt(arr.length);
//            swap(arr,i,swap);
//        }
	}

	// swaps the i'th and j'th element of the array
	private static void swap(int[] array, int i, int j) {
		int a = array[i];
		array[i] = array[j];
		array[j] = a;
	}
}
