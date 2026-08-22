// LeetCode 994 - Rotting Oranges

import java.util.ArrayDeque;
import java.util.Queue;

class LC994RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    q.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        int minutes = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty() && fresh > 0) {
            int size = q.size();
            minutes++;

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();

                for (int[] dir : directions) {
                    int nr = cell[0] + dir[0];
                    int nc = cell[1] + dir[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                            && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return fresh == 0 ? minutes : -1;
    }
}

/*
 * Logic:
 * This is multi-source BFS. Every rotten orange is an initial source.
 * All rotten oranges spread simultaneously, so one BFS level represents
 * one minute. Fresh oranges are counted first, and each fresh orange that
 * becomes rotten is marked immediately and decrements the fresh count.
 *
 * Algorithm:
 * 1. Put all initially rotten oranges into the queue.
 * 2. Count all fresh oranges.
 * 3. Process the queue level by level.
 * 4. Each level represents one minute.
 * 5. Convert adjacent fresh oranges to rotten, decrement fresh, and enqueue them.
 * 6. Return minutes if all fresh oranges rot; otherwise return -1.
 *
 * Complexity:
 * Time: O(rows * cols)
 * Space: O(rows * cols)
 */