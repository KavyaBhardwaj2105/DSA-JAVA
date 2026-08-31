// LeetCode 1020 - Number of Enclaves

class LC1020NumberOfEnclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Remove all land connected to the top and bottom boundaries.
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                dfs(grid, 0, j);
            }
            if (grid[m - 1][j] == 1) {
                dfs(grid, m - 1, j);
            }
        }

        // Remove all land connected to the left and right boundaries.
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                dfs(grid, i, 0);
            }
            if (grid[i][n - 1] == 1) {
                dfs(grid, i, n - 1);
            }
        }

        // Remaining land cells are enclaves.
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return;
        }

        // Mark boundary-connected land as visited.
        grid[r][c] = 0;

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}

/*
 * Logic:
 * Start DFS from every boundary land cell. Any land connected to the
 * boundary cannot be an enclave, so mark it as 0. After removing all
 * boundary-connected land, the remaining 1 cells are enclaves.
 *
 * Pattern:
 * Boundary DFS + elimination + final counting.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C) worst case recursion stack.
 */