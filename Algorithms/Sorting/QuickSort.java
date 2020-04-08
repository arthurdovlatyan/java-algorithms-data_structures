package data_structure.newStructure.Algorithms.Sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

	// QuickSort algorithm runner
	public static void quickSort(int[] arr) {
		randomQuickSort(arr, 0, arr.length - 1);
	}

	// QuickSort algorithm with random pivot selection
	public static void randomQuickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = random_partition(arr, low, high);
			randomQuickSort(arr, low, pi - 1);
			randomQuickSort(arr, pi + 1, high);
		}
	}

	// QuickSort algorithm
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	// selects the random pivot and then passes the work to the partition function`
	private static int random_partition(int[] arr, int start, int end) {
		Random rand = new Random();
		int i = rand.nextInt(end - start) + start;
		int tmp = arr[i];
		arr[i] = arr[end - 1];
		arr[end - 1] = tmp;
		return partition(arr, start, end);
	}

	// sorts the array according to the pivot
	private static int partition(int[] arr, int start, int end) {
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


	public static void main(String[] args) {
		int arr0[] = new int[9];
		int arr1[] = new int[9];
		int arr2[] = new int[9];
		int arr3[] = new int[9];
		int arr4[] = new int[9];
		int arr5[] = new int[9];
		int arr6[] = new int[9];
		int arr7[] = new int[9];
		int arr8[] = new int[9];
		int arr9[] = new int[9];

		randomFill(arr0);
		randomFill(arr1);
		randomFill(arr2);
		randomFill(arr3);
		randomFill(arr4);
		randomFill(arr5);
		randomFill(arr6);
		randomFill(arr7);
		randomFill(arr8);
		randomFill(arr9);

		System.out.println(Arrays.toString(arr0));
		quickSort(arr0);
		System.out.println(Arrays.toString(arr0) + checkIncreasing(arr0));

		System.out.println(Arrays.toString(arr1));
		quickSort(arr1);
		System.out.println(Arrays.toString(arr1) + checkIncreasing(arr1));

		System.out.println(Arrays.toString(arr2));
		quickSort(arr2);
		System.out.println(Arrays.toString(arr2) + checkIncreasing(arr2));

		System.out.println(Arrays.toString(arr3));
		quickSort(arr3);
		System.out.println(Arrays.toString(arr3) + checkIncreasing(arr3));

		System.out.println(Arrays.toString(arr4));
		quickSort(arr4);
		System.out.println(Arrays.toString(arr4) + checkIncreasing(arr4));

		System.out.println(Arrays.toString(arr5));
		quickSort(arr5);
		System.out.println(Arrays.toString(arr5) + checkIncreasing(arr5));

		System.out.println(Arrays.toString(arr6));
		quickSort(arr6);
		System.out.println(Arrays.toString(arr6) + checkIncreasing(arr6));

		System.out.println(Arrays.toString(arr7));
		quickSort(arr7);
		System.out.println(Arrays.toString(arr7) + checkIncreasing(arr7));

		System.out.println(Arrays.toString(arr8));
		quickSort(arr8);
		System.out.println(Arrays.toString(arr8) + checkIncreasing(arr8));

		System.out.println(Arrays.toString(arr9));
		quickSort(arr9);
		System.out.println(Arrays.toString(arr9) + checkIncreasing(arr9));
	}

	// Fills the array with random numbers in the range of 10
	public static void randomFill(int[] arr) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt() % 10;
		}
	}

	// Chechs wheather the array is sorted in increasing order
	public static boolean checkIncreasing(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					return false;
				}
			}
		}
		return true;
	}

	// Checks wheather the array is in the decreasing order
	public static boolean checkDecreasing(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[i]) {
					return false;
				}
			}
		}
		return true;
	}
}


