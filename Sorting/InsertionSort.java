package Sorting;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

    // Performs insertion sort on the given array
    public static void insertionSort(int arr[]){
        for(int i = 1; i < arr.length; i++){
            int target = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > target){
                arr[j - 1] = arr[j];
                j--;
            }
            arr[j] = target;
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
        insertionSort(arr0);
        System.out.println(Arrays.toString(arr0) + checkIncreasing(arr0));

        System.out.println(Arrays.toString(arr1));
        insertionSort(arr1);
        System.out.println(Arrays.toString(arr1) + checkIncreasing(arr1));

        System.out.println(Arrays.toString(arr2));
        insertionSort(arr2);
        System.out.println(Arrays.toString(arr2) + checkIncreasing(arr2));

        System.out.println(Arrays.toString(arr3));
        insertionSort(arr3);
        System.out.println(Arrays.toString(arr3) + checkIncreasing(arr3));

        System.out.println(Arrays.toString(arr4));
        insertionSort(arr4);
        System.out.println(Arrays.toString(arr4) + checkIncreasing(arr4));

        System.out.println(Arrays.toString(arr5));
        insertionSort(arr5);
        System.out.println(Arrays.toString(arr5) + checkIncreasing(arr5));

        System.out.println(Arrays.toString(arr6));
        insertionSort(arr6);
        System.out.println(Arrays.toString(arr6) + checkIncreasing(arr6));

        System.out.println(Arrays.toString(arr7));
        insertionSort(arr7);
        System.out.println(Arrays.toString(arr7) + checkIncreasing(arr7));

        System.out.println(Arrays.toString(arr8));
        insertionSort(arr8);
        System.out.println(Arrays.toString(arr8) + checkIncreasing(arr8));

        System.out.println(Arrays.toString(arr9));
        insertionSort(arr9);
        System.out.println(Arrays.toString(arr9) + checkIncreasing(arr9));
    }

    // fills the array with random numbers in the range 10
    public static void randomFill(int arr[]){
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++){
            arr[i] = rand.nextInt() % 10;
        }
    }

    // Checks wheather elemetns in the array are sorted in increasing order
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

    // Checks wheather elements in the array are in decreasing order
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
