package org.exercises.c3ai;

import java.util.List;

/**
 * We are given an array of coral heights [1, 3, 4, 8, 5].
 * Also an amount of water available for the tank A = 5
 * Find the max tank height H >= 1 that will not surpass the available water
 */
public class CoralTankHeightMaximization {

    public static void main(String[] args) {
        int maxHeight = findTankMaxHeight(List.of(1, 3, 4, 8, 5), 10);
        System.out.println(maxHeight);
    }

    private static int findTankMaxHeight(List<Integer> coralHeights, int waterLimit) {
        int neededWater = 0;
        int tankHeight = 0;

        while (neededWater <= waterLimit) {
            tankHeight++;
            neededWater = 0;

            for (int coralHeight : coralHeights) {
                if (coralHeight >= tankHeight) {
                    continue;
                }
                neededWater += tankHeight - coralHeight;
            }
        }

        return tankHeight - 1;
    }
}
