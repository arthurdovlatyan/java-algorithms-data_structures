package data_structure.ac_recursion;

import java.util.Arrays;

public class AH_Fibonacci {
    public static void main(String[] args) {
        long[] fibonacciSequence = fibonacciGood(9);
        System.out.println(Arrays.toString(fibonacciSequence));
    }


    /**
     * Returns array containing the pair of Fibonacci numbers, F(n) and F(n−1).
     * @param n The number
     * @return The array containing the pair of Fibonacci numbers, F(n) and F(n−1).
     */
    private static long[] fibonacciGood(int n) {
        if (n <= 1) {
            return new long[]{n, 0};
        }
        long[] temp = fibonacciGood(n - 1);
        return new long[]{temp[0] + temp[1], temp[0]};
    }
}
