package org.exercises.amazon;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DominantString {

    private static int dominantStrings(String s) {
        Set<String> dominants = new HashSet<>();
        for (int i = 0; i <= s.length() - 2; i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                String substring = s.substring(i, j);
                System.out.println(substring);
                if (!dominants.contains(substring) && isDominant(substring)) {
                    dominants.add(substring);
                }
            }
        }
        System.out.println(dominants);
        return dominants.size();
    }

    private static boolean isDominant(String s) {
        int halfLength = s.length() / 2;
        return s.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count == halfLength);
    }

    public static void main(String[] args) {
        System.out.println(dominantStrings("aaaacid"));
    }
}
