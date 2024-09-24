package org.exercises.amazon;

import java.util.*;

public class DiscardGifts {

    /*
        From the list of prices find the least amount of gifts that should be discarded so that
        groups of size k can be formed without exceeding the threshold.
     */

    private static int discardGift(List<Integer> prices, int k, int threshold) {

        if (Objects.isNull(prices) || prices.size() < k) {
            return 0;
        }

        if (prices.stream().allMatch(p -> p > threshold)) {
            return prices.size() - k + 1;
        }

        prices.sort(Integer::compareTo);
        int length = prices.size();
        long sum = prices.subList(length - k, length).stream().mapToLong(Long::valueOf).sum();
        int discardedGifts = 0;

        while (length - k > 0 && sum > threshold) {
            prices.remove(length - 1);
            System.out.println(prices);
            discardedGifts++;
            length = prices.size();
            sum = prices.subList(length - k, length).stream().mapToLong(Long::valueOf).sum();
        }

        if (sum > threshold) {
            return discardedGifts + 1;
        }

        return discardedGifts;
    }
    public static void main(String[] args) {
        System.out.println(discardGift(new ArrayList<>(List.of(2,2,2,2,2)), 4, 1));
    }
}
