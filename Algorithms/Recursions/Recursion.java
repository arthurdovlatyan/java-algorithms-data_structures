package data_structure.ac_recursion;

public class Recursion {
    public static void main(String[] args) {
        int i = recTets(8);
    }

    private static int recTets(int n) {
        if (n == 0) {
            return 0;
        }
        System.out.println("Before recursion");
        int res = n + recTets(n - 1);
        System.out.println(res);
        return res;

    }
}
