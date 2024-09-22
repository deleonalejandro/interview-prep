package org.exercises.amazon;

import java.util.*;

public class OptimalWeightPacket {

    private static List<Integer> getMaxWeightSet(List<Integer> weights) {

        if (Objects.isNull(weights) || weights.isEmpty()) {
            return Collections.emptyList();
        }

        if (new HashSet<>(weights).size() == 1) {
            return weights;
        }

        Collections.sort(weights);

        int length = weights.size() - 1;
        long sumA = weights.get(length);
        long sumB = sum(weights) - sumA;
        int pointer = length;

        while (pointer > 0 && (isWeightConditionNotMet(sumA, sumB) || isBContainingA(weights, pointer))) {
            pointer--;
            sumA += weights.get(pointer);
            sumB -= weights.get(pointer);
        }

        return weights.subList(pointer, weights.size());
    }

    private static boolean isBContainingA(List<Integer> weights, int pointer) {
        return Objects.equals(weights.get(pointer), weights.get(pointer - 1));
    }

    private static boolean isWeightConditionNotMet(long sumA, long sumB) {
        return sumA <= sumB;
    }

    private static long sum(List<Integer> subList) {
        return subList.parallelStream()
                .mapToLong(Long::valueOf)
                .reduce(0, Long::sum);
    }

    public static void main(String[] args) {
        System.out.println(getMaxWeightSet(Arrays.asList(1, 1, 1, 1, 1, 6)));
    }
}
