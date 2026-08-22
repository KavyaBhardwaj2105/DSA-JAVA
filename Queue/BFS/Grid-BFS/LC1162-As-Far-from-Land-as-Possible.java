// LeetCode 1162 - As Far from Land as Possible

import java.util.ArrayDeque;
import java.util.Queue;

class LC1162AsFarFromLandAsPossible {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> q = new ArrayDeque<>();
        int landCount = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    q.offer(new int[]{row, col});
                    landCount++;
                }
            }
        }

        if (landCount == 0 || landCount == n * n) {
            return -1;
        }

        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        int distance = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

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
        }

        return distance;
    }
}

/*
 * Logic:
 * This is multi-source BFS. Every land cell (1) is a starting source.
 * BFS expands from all land cells simultaneously. The last water layer
 * reached has the maximum distance from the nearest land cell.
 *
 * The grid is modified in place: when a water cell is enqueued, it is
 * changed to 1 so it is not visited again.
 *
 * Algorithm:
 * 1. Add every land cell to the queue.
 * 2. If the grid contains only land or only water, return -1.
 * 3. Run BFS simultaneously from all land cells.
 * 4. For every level, visit adjacent water cells and mark them visited.
 * 5. Increment distance once per BFS level.
 * 6. The final distance is the maximum distance from any water cell to land.
 *
 * Complexity:
 * Time: O(n^2)
 * Space: O(n^2)
 */