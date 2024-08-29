package org.exercises.sorting;

import lombok.extern.slf4j.Slf4j;

/**
 * Divide and conquer algorithm, which uses recursion to sort left and right halves and then sort them.
 * O(nlog(n)) time complexity
 * O(n) space complexity
 */
@Slf4j
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {1, 4, 8, 10, 3, 2, 43, 23};

        // The arguments of the function are the actual indices.
        mergesort(array, new int[array.length], 0, array.length - 1);
        log.info("Sorted array: {}", array);
    }

    static void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) {
        // Return if partitions are very small
        if (leftStart >= rightEnd) {
            return;
        }

        // Divide
        int middle = (leftStart + rightEnd) / 2; // or array.length / 2 + leftStart
        // Sort Left
        mergesort(array, temp, leftStart, middle);
        // Sort right
        mergesort(array, temp, middle + 1, rightEnd);
        // Merge
        mergeHalves(array, temp, leftStart, rightEnd);
    }

    static void mergeHalves(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;

        int k = leftStart;

        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[k] = array[left];
                left++;
            } else {
                temp[k] = array[right];
                right++;
            }
            k++;
        }

        while (left <= leftEnd) {
            temp[k] = array[left];
            left++;
            k++;
        }

        while (right <= rightEnd) {
            temp[k] = array[right];
            right++;
            k++;
        }

        System.arraycopy(temp, leftStart, array, leftStart, size);
    }

}
