package org.exercises.arrays;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ArraySolution {

    public static void main(String[] args) {
        aVeryBigSum();
        pdfViewer();
        log.info("Left Rotation: {}", leftRotation(List.of(1, 2, 3, 4, 5), 34));
    }

    /**
     * A Very Big Sum <br/>
     * <a href="https://www.hackerrank.com/challenges/a-very-big-sum/problem">HackerRank Link</a>
     */
    public static void aVeryBigSum() {
        // Using the correct datatype is crucial to solve this problem Long instead of Integer.
        // Integer is 32 bits two signed datatype, while Long is a 64 bit datatype.
        List<Long> bigNumbers = List.of(12323234234L, 2133000034L);
        Long bigSum = bigNumbers.stream().mapToLong(l -> l).sum();
        log.info("Sum: {}", bigSum);
    }

    /**
     * Designer PDF Viewer
     * <a href="https://www.hackerrank.com/challenges/designer-pdf-viewer/problem">HackerRank Link</a>
     */
    public static void pdfViewer() {
        List<Integer> heights =
                List.of(1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7);
        String word = "zaba";
        int max = word.chars()
                .map(l -> heights.get(l - 97))
                .max()
                .orElse(0);
        log.info("The highlight area is {} mm2", max * word.length());
    }

    /**
     * Arrays: Left Rotation <a href="https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem">HackerRank Link</a>
     * @param a The arraylist to be rotated
     * @param d The number of left rotations
     * @return The rotated arraylist
     */
    public static List<Integer> leftRotation(List<Integer> a, int d) {
        int arraySize = a.size();
        int displacement = d % arraySize;

        if (displacement == 0) {
            return a;
        }

        Integer[] result = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            int position = i - displacement;
            if (position <  0) {
                position = position + arraySize;
            }
            result[position] = a.get(i);
        }

        return Arrays.asList(result);
    }
}
