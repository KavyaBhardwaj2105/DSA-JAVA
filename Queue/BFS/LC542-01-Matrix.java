// LeetCode 542 - 01 Matrix

import java.util.*;

/*
 * Logic:
 * This is a multi-source BFS problem.
 * Every 0 is a starting point, so all 0 cells are added to the queue first.
 * BFS then expands outward and assigns each 1 its shortest distance from a 0.
 *
 * Algorithm:
 * 1. Add every 0 cell to the queue and set its distance to 0.
 * 2. Treat all 0 cells as already visited.
 * 3. Process the queue level by level using 4 directions.
 * 4. When an unvisited 1 is found, its distance is current distance + 1.
 * 5. Add that cell to the queue.
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */

class LC54201Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    queue.offer(new int[]{r, c});
                } else {
                    mat[r][c] = -1;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    mat[newRow][newCol] == -1) {

                    mat[newRow][newCol] = mat[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return mat;
    }
}