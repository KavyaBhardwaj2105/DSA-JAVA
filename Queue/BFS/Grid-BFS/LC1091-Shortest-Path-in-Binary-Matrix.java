// LeetCode 1091 - Shortest Path in Binary Matrix

import java.util.ArrayDeque;
import java.util.Queue;

class LC1091ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        grid[0][0] = 1;
        int distance = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

                if (row == n - 1 && col == n - 1) {
                    return distance;
                }

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow >= 0 && newRow < n
                            && newCol >= 0 && newCol < n
                            && grid[newRow][newCol] == 0) {
                        grid[newRow][newCol] = 1;
                        q.offer(new int[]{newRow, newCol});
                    }
                }
            }

            distance++;
        }

        return -1;
    }
}

/*
 * Logic:
 * Each open cell (0) is a node and movement is allowed in all 8 directions.
 * BFS is ideal because the first time we reach the destination is the shortest path.
 *
 * The grid itself is used as the visited structure: when a 0 is enqueued,
 * it is changed to 1 so it cannot be processed again.
 *
 * Algorithm:
 * 1. If start or destination is blocked, return -1.
 * 2. Put (0,0) into the queue and mark it visited.
 * 3. Process the queue level by level.
 * 4. For each cell, explore all 8 directions.
 * 5. Enqueue every valid unvisited 0-cell and mark it visited immediately.
 * 6. When the destination is reached, return the current distance.
 * 7. If BFS ends without reaching it, return -1.
 *
 * Complexity:
 * Time: O(n^2)
 * Space: O(n^2)
 */