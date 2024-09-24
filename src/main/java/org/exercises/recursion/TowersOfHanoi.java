package org.exercises.recursion;

public class TowersOfHanoi {

    private static void hanoi(int n, char start, char end, char helper) {
        if (n == 1) {
            System.out.printf("Move %d from %s to %s%n", n, start, end);
            return;
        }

        hanoi(n - 1, start, helper, end);
        System.out.printf("Move %d from %s to %s%n", n, start, end);
        hanoi(n - 1, helper, end, start);
    }
    public static void main(String[] args) {
        hanoi(2, 'A', 'C', 'B');
    }
}
