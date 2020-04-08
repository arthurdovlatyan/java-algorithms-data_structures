package data_structure.ac_recursion;

public class AE_SumOfArray {
    public static void main(String[] args) {
        int[] arr = new int[]{12, 34, 56, 78, 9};
        System.out.println(linearSum(arr, 5));

        System.out.println(binarySum(arr, 0, 4));

    }

    /**
     * Returns the sum of the first n integers of the given array.
     * @param arr The array
     * @param n The first n
     * @return The sum
     */
    private static int linearSum(int[] arr, int n) {
        if (n == 0) {
            return 0;
        }
        else {
            return linearSum(arr, n - 1) + arr[n - 1];
        }
    }

    /**
     * Returns the sum of subarray data[low] through data[high] inclusive.
     * @param arr The array
     * @param low The smallest index
     * @param high The largest index
     * @return The sum
     */
    private static int binarySum(int[] arr, int low, int high) {
        if (low > high) {
            return 0;
        }
        else if (low == high) {
            return arr[low];
        }
        else {
            int mid = (low + high) / 2;
            return binarySum(arr, low, mid) + binarySum(arr, mid + 1, high);
        }
    }
}
