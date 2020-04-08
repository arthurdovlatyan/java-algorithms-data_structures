package data_structure.newStructure.Algorithms.Sorting;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {

	/**
	 * Performs bubble sort Runtime is O(n^2)
	 *
	 * @param arr to be sorted
	 */
	public static void bubbleSort(int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean checked = false;
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = arr[j];
					checked = true;
				}
			}
			if (!checked) {
				return;
			}
		}
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
		bubbleSort(arr0);
		System.out.println(Arrays.toString(arr0) + checkIncreasing(arr0));

		System.out.println(Arrays.toString(arr1));
		bubbleSort(arr1);
		System.out.println(Arrays.toString(arr1) + checkIncreasing(arr1));

		System.out.println(Arrays.toString(arr2));
		bubbleSort(arr2);
		System.out.println(Arrays.toString(arr2) + checkIncreasing(arr2));

		System.out.println(Arrays.toString(arr3));
		bubbleSort(arr3);
		System.out.println(Arrays.toString(arr3) + checkIncreasing(arr3));

		System.out.println(Arrays.toString(arr4));
		bubbleSort(arr4);
		System.out.println(Arrays.toString(arr4) + checkIncreasing(arr4));

		System.out.println(Arrays.toString(arr5));
		bubbleSort(arr5);
		System.out.println(Arrays.toString(arr5) + checkIncreasing(arr5));

		System.out.println(Arrays.toString(arr6));
		bubbleSort(arr6);
		System.out.println(Arrays.toString(arr6) + checkIncreasing(arr6));

		System.out.println(Arrays.toString(arr7));
		bubbleSort(arr7);
		System.out.println(Arrays.toString(arr7) + checkIncreasing(arr7));

		System.out.println(Arrays.toString(arr8));
		bubbleSort(arr8);
		System.out.println(Arrays.toString(arr8) + checkIncreasing(arr8));

		System.out.println(Arrays.toString(arr9));
		bubbleSort(arr9);
		System.out.println(Arrays.toString(arr9) + checkIncreasing(arr9));


	}

	// supporting functions

	/**
	 * Fills the array with random numbers in range 10
	 *
	 * @param arr
	 */
	public static void randomFill(int arr[]) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt() % 10;
		}
	}

	/**
	 * Checks wheather the array is in increasing order
	 */
	public static boolean checkIncreasing(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Checks wheather the array is sorted in decreasing order
	 */
	public static boolean checkDecreasing(int arr[]) {
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
