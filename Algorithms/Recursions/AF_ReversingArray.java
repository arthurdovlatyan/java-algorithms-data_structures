package data_structure.ac_recursion;

import java.util.Arrays;

public class AF_ReversingArray {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,8,9};
        reverseArrayRecursive(array, 0, 8);
        System.out.println(Arrays.toString(array));


        int[] array1 = new int[]{5,6,7,8,9,10,11,12};
        reverseIterative(array1);
        System.out.println(Arrays.toString(array1));
    }


    /**
     *  Reverses the contents of the given array
     * @param arr The array
     * @param low The smallest index
     * @param high The greatest index
     */
    private static void reverseArrayRecursive(int[] arr, int low, int high) {

        // If at least two elements in subarray
        if (low < high) {
            // Swap data[low] and data[high]
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;

            // Recur on the rest
            reverseArrayRecursive(arr, low + 1, high - 1);
        }

    }


    /**
     *  Reverses the contents of the given array
     * @param arr The array
     */
    private static void reverseIterative(int[] arr) {
        int low = 0, high = arr.length - 1;

        // Swap data[low] and data[high]
        while (low < high) {
            int temp = arr[low];

            // Post-increment of low
            arr[low++] = arr[high];

            // Post-decrement of high
            arr[high--] = temp;
        }
    }


}
