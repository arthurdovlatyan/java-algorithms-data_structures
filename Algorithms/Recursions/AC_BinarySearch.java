package data_structure.ac_recursion;

public class AC_BinarySearch {
    public static void main(String[] args) {
        boolean exists = binarySearchRecurcive(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,44,57,88,99}, 9, 0, 10);
        System.out.println(exists);

        exists = binarySearchIterative(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,44,57,88,99}, 9);
        System.out.println(exists);
    }


    /**
     * *****************ONLY FOR SORTED ARRAYS*******************
     *
     * Returns true if the target value is found in the indicated portion of the data array.
     * This search only considers the array portion from data[low] to data[high] inclusive.
     * @param arr The given sequence
     * @param target The item which is being searched
     * @param start The start index of the array
     * @param end The end index point of the array
     * @return `true` if matches and `false` otherwise
     */
    private static boolean binarySearchRecurcive(int[] arr, int target, int start, int end) {

        // Interval empty; no match
        if (start > end) {
            return false;
        }

        else {
            int mid = (start + end) / 2;

            // Found a match
            if (target == arr[mid]) {
                return true;
            }

            // Recur left of the middle
            else if (target < arr[mid]) {
                return binarySearchRecurcive(arr, target, start, mid - 1);
            }

            // Recur right of the middle
            else {
                return binarySearchRecurcive(arr, target, mid + 1, end);
            }
        }
    }

    /**
     * *****************ONLY FOR SORTED ARRAYS*******************
     *
     * Returns true if the target value is found in the indicated portion of the data array.
     * This search only considers the array portion from data[low] to data[high] inclusive.
     * @param arr The given sequence
     * @param target The item which is being searched
     * @return `true` if matches and `false` otherwise
     */
    private static boolean binarySearchIterative(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) /2;

            // Found a match
            if (target == arr[mid]) {
                return true;
            }

            // Only consider values left of mid
            else if (target < arr[mid]) {
                high = mid - 1;
            }

            // Only consider values left of mid
            else {
                low = mid + 1;
            }
        }
        return false;
    }
}
