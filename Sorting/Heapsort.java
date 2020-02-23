package Sorting;

import java.util.Arrays;
import java.util.Random;

public class Heapsort {

    /*
    * Performs heapsort on the given arr
     */
    public static void heapSort(int[] arr) {
        // make a maxheap out of the array
        // since all the keys down after arr.length/2 - 1 are maxheaps no need to heapify
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }

        // move the element at index 0 to the end of the array
        // heapify the array once more but with less length( -1 )
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    /*
    * For the given array checks wheather the root can be pushed down the heap
    *
     */
    public static void heapify(int arr[], int size, int root) {
        int largestEl = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        if (left < size && arr[left]> arr[largestEl]) {
            largestEl = left;
        }
        if (right < size && arr[right] > arr[largestEl]) {
            largestEl = right;
        }
        if (largestEl != root) {
            int tmp = arr[root];
            arr[root] = arr[largestEl];
            arr[largestEl] = tmp;
            heapify(arr, size, largestEl);
        }
    }
    public static void main(String[] args) {
        int arr0[] = new int [10];
        int arr1[] = new int [10];
        int arr2[] = new int [10];
        int arr3[] = new int [10];
        int arr4[] = new int [10];
        int arr5[] = new int [10];
        int arr6[] = new int [10];
        int arr7[] = new int [10];
        int arr8[] = new int [10];
        int arr9[] = new int [10];

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
        heapSort(arr0);
        System.out.println(Arrays.toString(arr0) + checkIncreasing(arr0));

        System.out.println(Arrays.toString(arr1));
        heapSort(arr1);
        System.out.println(Arrays.toString(arr1) + checkIncreasing(arr1));

        System.out.println(Arrays.toString(arr2));
        heapSort(arr2);
        System.out.println(Arrays.toString(arr2) + checkIncreasing(arr2));

        System.out.println(Arrays.toString(arr3));
        heapSort(arr3);
        System.out.println(Arrays.toString(arr3) + checkIncreasing(arr3));

        System.out.println(Arrays.toString(arr4));
        heapSort(arr4);
        System.out.println(Arrays.toString(arr4) + checkIncreasing(arr4));

        System.out.println(Arrays.toString(arr5));
        heapSort(arr5);
        System.out.println(Arrays.toString(arr5) + checkIncreasing(arr5));

        System.out.println(Arrays.toString(arr6));
        heapSort(arr6);
        System.out.println(Arrays.toString(arr6) + checkIncreasing(arr6));

        System.out.println(Arrays.toString(arr7));
        heapSort(arr7);
        System.out.println(Arrays.toString(arr7) + checkIncreasing(arr7));

        System.out.println(Arrays.toString(arr8));
        heapSort(arr8);
        System.out.println(Arrays.toString(arr8) + checkIncreasing(arr8));

        System.out.println(Arrays.toString(arr9));
        heapSort(arr9);
        System.out.println(Arrays.toString(arr9) + checkIncreasing(arr9));
    }

    /*
    * Fill the array with random numbers in the range of 10
     */
    public static void randomFill(int arr[]){
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++){
            arr[i] = rand.nextInt() % 10;
        }
    }

    /*
    * Checks wheather the array is in increasing order
     */
    public static boolean checkIncreasing(int arr[]){
        for (int i =0 ;i < arr.length; i++){
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[i]){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * Checks wheather the array is in decreasing order
     */
    public static  boolean checkDecreasing(int arr[]){
        for (int i =0 ;i < arr.length; i++){
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] > arr[i]){
                    return false;
                }
            }
        }
        return true;
    }

}
