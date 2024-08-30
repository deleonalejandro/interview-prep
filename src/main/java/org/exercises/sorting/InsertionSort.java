package org.exercises.sorting;

import lombok.extern.slf4j.Slf4j;

/**
 * Insertion sort algorithm is based on inserting values in their corresponding positions. These positions are evaluated
 * for each individual element, comparing its value to the elements before them. This algorithm assumes first element is
 * already sorted.
 * O(n2) time complexity
 * O(1) space complexity
 */
@Slf4j
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {1, 4, 8, 10, 3, 2, 43, 23};
        insertionSort(array);
        log.info("Sorted array: {}", array);
    }

    private static void insertionSort(int[] array) {
        int size = array.length;

        for (int i = 1; i < size; i++) {
            int comparingIdx = i - 1;
            int insertValue = array[i];

            // For each value in the array we traverse back the array to compare it with the rest of them.
            // If the pointer reaches 0 or the value is not less than the comparing value, we stop and insert.
            while (comparingIdx >= 0 && insertValue < array[comparingIdx]) {
                array[comparingIdx + 1] = array[comparingIdx];
                comparingIdx--;
            }
            array[comparingIdx + 1] = insertValue;
        }
    }
}
