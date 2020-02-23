package Sorting;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    // Performs mergesort on the given array
    public static void mergeSort(int arr[]){
        if(arr.length < 2) {
            return;
        }
        int middle = arr.length/2;
        int arr1[] = Arrays.copyOfRange(arr,0,middle);
        int arr2[] = Arrays.copyOfRange(arr,middle,arr.length);

        mergeSort(arr1);
        mergeSort(arr2);
        merge(arr1,arr2,arr);
    }

    // Mergis two arrays into one array
    public static void merge(int arr1[],int arr2[],int orig[]){
        int i = 0, j = 0;
        while (i + j < arr1.length + arr2.length){
            if(j >= arr2.length || (i < arr1.length && arr1[i] < arr2[j])){
                orig[i + j] = arr1[i++];
            }else {
                orig[i + j] = arr2[j++];
            }
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
        mergeSort(arr0);
        System.out.println(Arrays.toString(arr0) + checkIncreasing(arr0));

        System.out.println(Arrays.toString(arr1));
        mergeSort(arr1);
        System.out.println(Arrays.toString(arr1) + checkIncreasing(arr1));

        System.out.println(Arrays.toString(arr2));
        mergeSort(arr2);
        System.out.println(Arrays.toString(arr2) + checkIncreasing(arr2));

        System.out.println(Arrays.toString(arr3));
        mergeSort(arr3);
        System.out.println(Arrays.toString(arr3) + checkIncreasing(arr3));

        System.out.println(Arrays.toString(arr4));
        mergeSort(arr4);
        System.out.println(Arrays.toString(arr4) + checkIncreasing(arr4));

        System.out.println(Arrays.toString(arr5));
        mergeSort(arr5);
        System.out.println(Arrays.toString(arr5) + checkIncreasing(arr5));

        System.out.println(Arrays.toString(arr6));
        mergeSort(arr6);
        System.out.println(Arrays.toString(arr6) + checkIncreasing(arr6));

        System.out.println(Arrays.toString(arr7));
        mergeSort(arr7);
        System.out.println(Arrays.toString(arr7) + checkIncreasing(arr7));

        System.out.println(Arrays.toString(arr8));
        mergeSort(arr8);
        System.out.println(Arrays.toString(arr8) + checkIncreasing(arr8));

    }

    // fills the array with random integers in the range of 10
    public static void randomFill(int arr[]){
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++){
            arr[i] = rand.nextInt() % 10;
        }
    }

    // Checks wheather the elements in the array are sorted in increasing order
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

    // Checks wheather the elements in the array are sorted in decreasing order
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
