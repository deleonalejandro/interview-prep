package org.exercises.c3ai;

/**
 * This problem is basically doing the bucket tool from Microsoft Paint.
 * Given an image (matrix) n x m
 * And coordinates x and y
 * And a color
 * Then color all the coordinates that are connected 4-directionally to the given coordinate
 * and have the same color as the origin.
 * Example:
 * Input
 * Image
 * {1 1 1
 *  1 1 0
 *  1 0 1}
 * Coordinate (1, 1)
 * Color 2
 * Output:
 * {2 2 2
 *  2 2 0
 *  2 0 1}
 */
public class FloodFill {

    public static void main(String[] args) {
        int[][] testImage = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        printImage(testImage);
        floodFill(testImage, 1, 1, 2, testImage[1][1]);
        System.out.println("--------COLORING---------");
        printImage(testImage);
    }

    public static void floodFill(int [][] image, int x, int y, int newColor, int originalColor) {
        if (isOutOfBounds(image, x, y) || image[x][y] != originalColor) {
            return;
        }

        image[x][y] = newColor;

        // Color right
        floodFill(image, x + 1, y, newColor, originalColor);
        // Color down
        floodFill(image, x, y + 1, newColor, originalColor);
        // Color left
        floodFill(image, x - 1, y, newColor, originalColor);
        // Color up
        floodFill(image, x, y - 1, newColor, originalColor);
    }

    private static boolean isOutOfBounds(int[][] image, int x, int y) {
        return x < 0 || x >= image[0].length || y < 0 || y >= image.length;
    }

    private static void printImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }
}
