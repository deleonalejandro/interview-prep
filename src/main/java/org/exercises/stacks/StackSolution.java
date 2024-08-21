package org.exercises.stacks;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class StackSolution {

    private static final Map<String, String> BRACKET_TYPES = Map.of(
            "(", ")",
            "{", "}",
            "[", "]"
    );

    public static void main(String[] args) {
        log.info("Is it balanced: {}", isBalanced("{[()]}"));
        queueUsing2Stacks();
    }

    /**
     * Balanced Brackets <a href="https://www.hackerrank.com/challenges/balanced-brackets/problem">HackerRank Link</a>
     * @param s String of brackets to be checked
     * @return True if balanced, False otherwise
     */
    public static boolean isBalanced(String s) {
        String[] brackets = s.split("");
        Deque<String> bracketStack = new ArrayDeque<>();

        for (String current : brackets) {
            if (bracketStack.isEmpty()) {
                bracketStack.push(current);
            } else if (current.equals(BRACKET_TYPES.get(bracketStack.peek()))) {
                bracketStack.pop();
            } else {
                bracketStack.push(current);
            }
        }
        return bracketStack.isEmpty();
    }

    /**
     * Queue using Two Stacks <a href="https://www.hackerrank.com/challenges/queue-using-two-stacks/problem">HackerRank Link</a>
     * The add() and remove() methods are very useful for this exercise. They add to the tail and remove from the head.
     */
    public static void queueUsing2Stacks() {
        Scanner scanner = new Scanner(System.in);
        int numQueries = scanner.nextInt();

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numQueries; i++) {
            int operation = scanner.nextInt();
            switch (operation) {
                case 1 -> {
                    int value = scanner.nextInt();
                    queue.add(value);
                }
                case 2 -> queue.remove();
                case 3 -> log.info(String.valueOf(queue.peek()));
                default -> throw new UnsupportedOperationException();
            }
        }
        scanner.close();
    }
}
