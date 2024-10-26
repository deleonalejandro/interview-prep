package org.exercises.graphs;

public class NumberOfIslands {

    /*
    Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
    return the number of islands.

    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    Input: grid = [
    ["1","1","1","1","0"],
    ["1","1","0","1","0"],
    ["1","1","0","0","0"],
    ["0","0","0","0","0"]
    ]
    Output: 1
    */

    // Time Complexity: O(n x m)
    public int numIslands(char[][] grid) {
        int counter = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    traverseIsland(i, j, m, n, grid);
                    counter++;
                }
            }
        }

        return counter;
    }

    private static void traverseIsland(int x, int y, int m, int n, char[][] grid) {
        if (isOutOfBounds(x, y, m, n) || grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0';

        traverseIsland(x + 1, y, m, n, grid);
        traverseIsland(x, y + 1, m, n, grid);
        traverseIsland(x - 1, y, m, n, grid);
        traverseIsland(x, y - 1, m, n, grid);
    }

    private static boolean isOutOfBounds(int x, int y, int m, int n) {
        return x < 0 || x > n || y < 0 || y > m;
    }
}
