package org.exercises.hashmap;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class HashMapSolution {

    public static void main(String[] args) {
        log.info("Prices {}", iceCreamParlor(12, List.of(1, 2, 7, 4, 5)));
        log.info("Is {}", isColorful("3245") ? "Colorful" : "Not Colorful");
    }

    /**
     * Ice Cream Parlor <a href="https://www.hackerrank.com/challenges/icecream-parlor/problem">HackerRank Link</a>
     *
     * @param m   The amount of money they have to spend
     * @param arr The cost of each flavor of ice cream
     * @return The indices of the prices of the two flavors they buy, sorted ascending
     */
    public static List<Integer> iceCreamParlor(int m, List<Integer> arr) {
        Map<Integer, Integer> diffIndexMap = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            Integer price = arr.get(i);
            if (diffIndexMap.isEmpty()) {
                diffIndexMap.put(m - price, i);
            } else if (diffIndexMap.get(price) != null) {
                return Arrays.asList(diffIndexMap.get(price) + 1, i + 1);
            } else {
                diffIndexMap.put(m - price, i);
            }
        }

        return Collections.emptyList();
    }

    /**
     * Colorful Numbers <a href="https://tutorialhorizon.com/algorithms/colorful-numbers/">Tutorial Horizon Link</a>
     *
     * @param number The number to evaluate as colorful or not
     * @return True if the number is colorful, False otherwise.
     */
    public static boolean isColorful(String number) {
        List<Integer> digits = number.chars()
                .map(i -> i - 48)
                .boxed()
                .toList();
        Set<Integer> digitSet = new HashSet<>(digits);

        if (digitSet.size() != digits.size()) {
            return false;
        }

        int i = 0;
        Set<Integer> products = new HashSet<>();
        while (i < number.length()) {
            for (int j = i + 1; j <= number.length(); j++) {
                List<Integer> subDigits = digits.subList(i, j);

                if (subDigits.equals(digits)) continue;

                Integer product = subDigits.stream().reduce(1, (x, y) -> x * y);
                if (products.contains(product)) {
                    return false;
                } else {
                    products.add(product);
                }
            }
            i++;
        }

        return true;
    }
}
