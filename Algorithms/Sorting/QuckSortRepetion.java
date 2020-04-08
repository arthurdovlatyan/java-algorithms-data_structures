package data_structure.newStructure.Algorithms.Sorting;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Random;

public class QuckSortRepetion {


	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] array, int start, int end) {
		if (start <= end) {
			Pair<Integer, Integer> pi = partition(array, start, end);
			quickSort(array, start, pi.getKey());
			quickSort(array, pi.getValue(), end);
		}
	}

	private static Pair<Integer, Integer> partition(int[] array, int start, int end) {
		int lowerIndex = start;
		int greaterIndex = end;
		int pivot = array[end];
		int i = start;

		while (i <= greaterIndex) {
			if (array[i] < pivot)
				swap(array, lowerIndex++, i++);
			else if (array[i] > pivot)
				swap(array, i, greaterIndex--);
			else
				i++;
		}
		Pair<Integer, Integer> pi = new Pair<>(lowerIndex - 1, greaterIndex + 1);
		return pi;
	}

	private static void swap(int[] array, int i, int j) {
		int a = array[i];
		array[i] = array[j];
		array[j] = a;
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

	public static void randomFill(int arr[]) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10);
		}
	}

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

	public static boolean checkDecreasing(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					return true;
				}
			}
		}
		return false;
	}


}
