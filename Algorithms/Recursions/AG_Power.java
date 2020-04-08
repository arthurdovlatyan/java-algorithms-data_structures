package data_structure.ac_recursion;

public class AG_Power {
    public static void main(String[] args) {
        double xToPowerNLinear = linearPower(2.0, 5);
        System.out.println("Linear: " + xToPowerNLinear);
        double xToPowerNAlgoritmic = algoritmicPower(2.0, 5);
        System.out.println("Algoritmic: " + xToPowerNAlgoritmic);

    }

    /**
     * Computes the value of x raised to the nth power, for nonnegative integer n.
     * @param x base
     * @param n exponent
     * @return x ^ n
     */
    private static double linearPower(double x, int n) {
        if (n == 0) {
            return 1;
        }
        else {
            return x * linearPower(x, n - 1);
        }
    }


    /**
     * Computes the value of x raised to the nth power, for nonnegative integer n.
     * @param x base
     * @param n exponent
     * @return x ^ n
     */
    private static double algoritmicPower(double x, int n) {
        if (n == 0) {
            return 1;
        }
        else {
            // Rely on truncated division of n
            double partial = algoritmicPower(x, n / 2);
            double result = partial * partial;

            // If n odd, include extra factor of x
            if (n % 2 == 1) {
                result *= x;
            }
            return result;
        }
    }


}
