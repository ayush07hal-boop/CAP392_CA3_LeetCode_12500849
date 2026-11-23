class Solution {

    // Recursive method
    public static int fibRecursive(int n) {
        if (n <= 1) {
            return n;
        }

        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    // Iterative method
    public static int fibIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int a = 0;
        int b = 1;

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b;
    }

    // LeetCode method
    public int fib(int n) {
        return fibIterative(n);
    }

    // Method overloading
    public static long fib(long n) {
        if (n <= 1) {
            return n;
        }

        long a = 0;
        long b = 1;

        for (long i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}
