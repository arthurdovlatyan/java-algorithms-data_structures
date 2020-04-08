package data_structure.ac_recursion;

public class AA_FactorialRecursion {
    public static void main(String[] args) {
        long fact = factorial(20);
        System.out.println(fact);
    }

    private static long factorial(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException();                //argument must be nonnegative
        }
        else if (n == 0) {
            return 1;
        }
        else {
            return n * factorial(n - 1);
        }
    }
}
