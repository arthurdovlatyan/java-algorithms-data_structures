package data_structure.newStructure.Algorithms.Searching;

import java.util.Arrays;
import java.util.Random;

public class LinearSearch {

	/*
	 * Searches the array for the target key
	 */
	public static String linearSearch(int[] arr, int target) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				return target + " is present at index " + i;
			}
		}
		return target + " is not present";
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

		Random rand = new Random();
		System.out.print(Arrays.toString(arr0) + "     " + linearSearch(arr0, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr1) + "     " + linearSearch(arr1, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr2) + "     " + linearSearch(arr2, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr3) + "     " + linearSearch(arr3, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr4) + "     " + linearSearch(arr4, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr5) + "     " + linearSearch(arr5, rand.nextInt() % 10));
		System.out.println();
		System.out.println(Arrays.toString(arr6) + "     " + linearSearch(arr6, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr7) + "     " + linearSearch(arr7, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr8) + "     " + linearSearch(arr8, rand.nextInt() % 10));
		System.out.println();
		System.out.print(Arrays.toString(arr9) + "     " + linearSearch(arr9, rand.nextInt() % 10));
		System.out.println();

	}

	// supporting functions

	/**
	 * Takes in an arr and fills the array with random numbers in range 10
	 *
	 * @param arr the array to be filled with random numbers
	 */
	public static void randomFill(int arr[]) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt() % 10;
		}
	}
}
