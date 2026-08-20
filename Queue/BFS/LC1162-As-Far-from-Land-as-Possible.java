// LeetCode 1162 - As Far from Land as Possible

import java.util.*;

/*
 * Logic:
 * Multi-source BFS is used because every land cell (1) is a starting point.
 * BFS expands simultaneously from all land cells.
 * Therefore, when a water cell is first reached, its distance is the
 * shortest distance to any land cell.
 * The largest assigned distance is the answer.
 *
 * Algorithm:
 * 1. Add every land cell to the queue.
 * 2. If the grid contains only land or only water, return -1.
 * 3. Expand BFS in 4 directions.
 * 4. Mark each newly reached water cell with distance = current + 1.
 * 5. Track the maximum distance reached.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 */

class LC1162AsFarFromLandAsPossible {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    queue.offer(new int[]{r, c});
                }
            }
        }

        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int distance = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

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
        }

        return distance;
    }
}