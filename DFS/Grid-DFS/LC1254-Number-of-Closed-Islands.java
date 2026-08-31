// LeetCode 1254 - Number of Closed Islands

class LC1254NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dfs(grid, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        // Reaching outside means this island touches the boundary.
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return false;
        }

        // Water does not break the closed-island condition.
        if (grid[r][c] == 1) {
            return true;
        }

        // Mark visited land as water.
        grid[r][c] = 1;

        boolean up = dfs(grid, r - 1, c);
        boolean down = dfs(grid, r + 1, c);
        boolean left = dfs(grid, r, c - 1);
        boolean right = dfs(grid, r, c + 1);

        return up && down && left && right;
    }
}

/*
 * Logic:
 * Every unvisited land cell (0) starts a DFS for one connected island.
 * DFS returns true only when the complete island stays inside the grid.
 * Reaching outside the grid returns false, meaning the island touches the
 * boundary and therefore is not closed.
 *
 * Pattern:
 * Boolean-return DFS + boundary detection + component validation.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C) worst case recursion stack.
 */