// LeetCode 130 - Surrounded Regions

import java.util.ArrayDeque;
import java.util.Queue;

class LC130SurroundedRegions {
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Queue<int[]> q = new ArrayDeque<>();

        // Add all boundary O's as BFS sources.
        for (int row = 0; row < rows; row++) {
            if (board[row][0] == 'O') {
                q.offer(new int[]{row, 0});
                board[row][0] = '#';
            }
            if (cols > 1 && board[row][cols - 1] == 'O') {
                q.offer(new int[]{row, cols - 1});
                board[row][cols - 1] = '#';
            }
        }

        for (int col = 0; col < cols; col++) {
            if (board[0][col] == 'O') {
                q.offer(new int[]{0, col});
                board[0][col] = '#';
            }
            if (rows > 1 && board[rows - 1][col] == 'O') {
                q.offer(new int[]{rows - 1, col});
                board[rows - 1][col] = '#';
            }
        }

        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        // Mark every boundary-connected O as safe.
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < rows
                        && newCol >= 0 && newCol < cols
                        && board[newRow][newCol] == 'O') {
                    board[newRow][newCol] = '#';
                    q.offer(new int[]{newRow, newCol});
                }
            }
        }

        // Enclosed O's become X; safe cells are restored to O.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                } else if (board[row][col] == '#') {
                    board[row][col] = 'O';
                }
            }
        }
    }
}

/*
 * Logic:
 * Do not start BFS from every O. Instead, start from boundary O's because
 * any O connected to the boundary cannot be surrounded.
 *
 * Boundary-connected O's are temporarily marked '#'. After BFS, remaining
 * O's are enclosed and can safely be changed to X. Finally, '#' is restored
 * to O.
 *
 * Complexity:
 * Time: O(rows * cols)
 * Space: O(rows * cols) worst case for the queue.
 */