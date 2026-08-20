// LeetCode 994 - Rotting Oranges

import java.util.*;

/*
 * Logic:
 * This is a multi-source BFS problem.
 * Every rotten orange is a starting point and can rot adjacent fresh oranges
 * simultaneously. Each BFS level represents one minute.
 *
 * Algorithm:
 * 1. Add all rotten oranges to the queue and count fresh oranges.
 * 2. Process the queue level by level; each level represents one minute.
 * 3. For every rotten orange, check its four neighbours.
 * 4. When a fresh orange is found, mark it rotten, decrease fresh count,
 *    and add it to the queue.
 * 5. If fresh oranges remain after BFS, return -1; otherwise return minutes.
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */

class LC994RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = 0;

        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {

                        grid[newRow][newCol] = 2;
                        fresh--;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}