// LeetCode 130 - Surrounded Regions

class LC130SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        // First and last column
        for (int r = 0; r < rows; r++) {
            dfs(board, r, 0);
            dfs(board, r, cols - 1);
        }

        // First and last row
        for (int c = 0; c < cols; c++) {
            dfs(board, 0, c);
            dfs(board, rows - 1, c);
        }

        // Convert remaining O -> X and safe # -> O
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == '#') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length ||
            c < 0 || c >= board[0].length ||
            board[r][c] != 'O') {
            return;
        }

        // Mark boundary-connected O as safe
        board[r][c] = '#';

        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
}

/*
 * Logic:
 * Instead of finding surrounded O cells directly, start DFS from every
 * boundary O. All O cells connected to the boundary are safe and are marked
 * temporarily as #. After that, remaining O cells are surrounded and become X.
 * Finally, # cells are restored to O.
 *
 * Pattern:
 * Boundary DFS + temporary safe-state marking.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C) worst case recursion stack.
 */