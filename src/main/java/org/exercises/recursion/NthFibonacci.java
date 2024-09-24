package org.exercises.recursion;

public class NthFibonacci {

    /*
        We know that:
        - fibonacci(0) = 0
        - fibonacci(1) = 1
        - fibonacci(n) = fibonacci(n - 1) + fibonacci(n - 2)
     */
    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fib(5));
    }
}
