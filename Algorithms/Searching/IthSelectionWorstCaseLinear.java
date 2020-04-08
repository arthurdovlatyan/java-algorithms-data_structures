package data_structure.newStructure.Algorithms.Searching;

import java.util.Arrays;

public class IthSelectionWorstCaseLinear {

	public static int findMedian(int arr[], int i, int n) {
		if (i <= n) {
			Arrays.sort(arr, i, n);
		} else {
			Arrays.sort(arr, n, i);
		}
		return arr[n / 2];
	}

	public static int kthSmallest(int arr[], int start, int end, int k) {
		if (k > 0 && k < end - start + 1) {
			int n = end - start + 1;
			int i;
			int median[] = new int[(n + 4) / 5];
			for (i = 0; i < n / 5; i++) {
				median[i] = findMedian(arr, start + i * 5, 5);
			}
			if (i * 5 < n) {
				median[i] = findMedian(arr, start + i * 5, n % 5);
			}
			int medOfMed = (i == 1) ? median[i - 1] : kthSmallest(median, 0, i - 1, i / 2);
			int part = partition(arr, start, end, medOfMed);
			if (part - start == k - 1) {
				return arr[part];
			} else if (part - start > k - 1) {
				return kthSmallest(arr, start, part - 1, k);
			} else {
				return kthSmallest(arr, part + 1, end, k - part + start - 1);
			}
		}
		return -1;
	}

	static int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}

	public static int partition(int[] arr, int start, int end, int medOfMed) {
		for (int d = start; d < end; d++) {
			if (arr[d] == medOfMed) {
				swap(arr, d, end);
				break;
			}
		}
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
		int arr[] = {12, 3, 5, 7, 4, 19, 26};
		int n = arr.length, k = 3;
		System.out.println("K'th smallest element is "
				+ kthSmallest(arr, 0, n - 1, k));
	}

}
