package org.exercises.stacks;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

@Slf4j
public class StackSolution {

    private static final Map<String, String> BRACKET_TYPES = Map.of(
            "(", ")",
            "{", "}",
            "[", "]"
    );

    public static void main(String[] args) {
        log.info("Is it balanced: {}", isBalanced("{[()]}"));
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
}
