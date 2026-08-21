// LeetCode 1091 - Shortest Path in Binary Matrix

import java.util.*;
/* i skipped lc for a day and guess who is solving easy level ques for 2 hours straight */
/*
 * Logic:
 * BFS is used because every move has the same cost (1).
 * Starting from (0, 0), BFS explores the grid level by level.
 * The first time we reach (n-1, n-1), we have found the shortest path.
 *
 * In this problem, movement is allowed in all 8 directions.
 * The grid itself is used to mark visited cells by changing 0 -> 1.
 *
 * Algorithm:
 * 1. If the start or destination is blocked, return -1.
 * 2. Put (0, 0) in the queue with path length 1.
 * 3. Process cells level by level.
 * 4. For each cell, check all 8 neighbours.
 * 5. If a neighbour is open (0), mark it visited and add it to the queue.
 * 6. When the destination is reached, return the current path length.
 * 7. If BFS finishes without reaching it, return -1.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 */

class LC1091ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1;

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},   {1, 1}
        };

        int pathLength = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

                if (row == n - 1 && col == n - 1) {
                    return pathLength;
                }

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow >= 0 && newRow < n &&
                        newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] == 0) {

                        grid[newRow][newCol] = 1;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            pathLength++;
        }

        return -1;
    }
}
