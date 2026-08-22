// LeetCode 542 - 01 Matrix

import java.util.ArrayDeque;
import java.util.Queue;

class LC54201Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        Queue<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    q.offer(new int[]{r, c});
                } else {
                    mat[r][c] = -1;
                }
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] dir : directions) {
                int nr = row + dir[0];
                int nc = col + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && mat[nr][nc] == -1) {
                    mat[nr][nc] = mat[row][col] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return mat;
    }
}

/*
 * Logic:
 * Every 0 is a source. We start BFS simultaneously from all zeros.
 * The first time a 1-cell is reached, it is reached through the nearest 0,
 * so its assigned distance is the minimum distance to any zero.
 *
 * Algorithm:
 * 1. Add every zero cell to the queue with distance 0.
 * 2. Mark every non-zero cell as unvisited using -1.
 * 3. Run multi-source BFS in four directions.
 * 4. When an unvisited cell is reached, set its distance to current + 1
 *    and enqueue it.
 * 5. Return the distance matrix.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C)
 */