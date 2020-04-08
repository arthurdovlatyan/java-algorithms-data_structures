package data_structure.newStructure.Algorithms.Sorting;

import java.util.Arrays;
import java.util.Random;

public class SelectionSort {

	// Selection sort algorithm
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			int j = i + 1;
			while (j < arr.length) {
				if (arr[j] < arr[min]) {
					min = j;
				}
				j++;
			}
			int tmp = arr[i];
			arr[i] = arr[min];
			arr[min] = tmp;
		}
	}

	public static void main(String[] args) {
		int arr0[] = new int[10];
		int arr1[] = new int[10];
		int arr2[] = new int[10];
		int arr3[] = new int[10];
		int arr4[] = new int[10];
		int arr5[] = new int[10];
		int arr6[] = new int[10];
		int arr7[] = new int[10];
		int arr8[] = new int[10];
		int arr9[] = new int[10];

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
		selectionSort(arr0);
		System.out.println(Arrays.toString(arr0) + checkIncreasing(arr0));

		System.out.println(Arrays.toString(arr1));
		selectionSort(arr1);
		System.out.println(Arrays.toString(arr1) + checkIncreasing(arr1));

		System.out.println(Arrays.toString(arr2));
		selectionSort(arr2);
		System.out.println(Arrays.toString(arr2) + checkIncreasing(arr2));

		System.out.println(Arrays.toString(arr3));
		selectionSort(arr3);
		System.out.println(Arrays.toString(arr3) + checkIncreasing(arr3));

		System.out.println(Arrays.toString(arr4));
		selectionSort(arr4);
		System.out.println(Arrays.toString(arr4) + checkIncreasing(arr4));

		System.out.println(Arrays.toString(arr5));
		selectionSort(arr5);
		System.out.println(Arrays.toString(arr5) + checkIncreasing(arr5));

		System.out.println(Arrays.toString(arr6));
		selectionSort(arr6);
		System.out.println(Arrays.toString(arr6) + checkIncreasing(arr6));

		System.out.println(Arrays.toString(arr7));
		selectionSort(arr7);
		System.out.println(Arrays.toString(arr7) + checkIncreasing(arr7));

		System.out.println(Arrays.toString(arr8));
		selectionSort(arr8);
		System.out.println(Arrays.toString(arr8) + checkIncreasing(arr8));

		System.out.println(Arrays.toString(arr9));
		selectionSort(arr9);
		System.out.println(Arrays.toString(arr9) + checkIncreasing(arr9));
	}

	// Fills the array with random elements in the range of 10
	public static void randomFill(int[] arr) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt() % 10;
		}
	}

	// Checks wheather the array is sorted in increasing order
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

	// Checks wheather the array is sorted in decreasing order
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
