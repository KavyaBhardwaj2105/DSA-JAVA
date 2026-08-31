// LeetCode 1905 - Count Sub Islands

class LC1905CountSubIslands {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid2.length;
        int n = grid2[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    if (dfs(grid1, grid2, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int r, int c) {
        // Out of bounds or water in grid2 does not invalidate the island.
        if (r < 0 || r >= grid2.length ||
            c < 0 || c >= grid2[0].length ||
            grid2[r][c] == 0) {
            return true;
        }

        // Mark visited land in grid2.
        grid2[r][c] = 0;

        // Every land cell of grid2 must also be land in grid1.
        boolean isSubIsland = (grid1[r][c] == 1);

        // Traverse the complete grid2 island even if this cell is invalid.
        boolean up = dfs(grid1, grid2, r - 1, c);
        boolean down = dfs(grid1, grid2, r + 1, c);
        boolean left = dfs(grid1, grid2, r, c - 1);
        boolean right = dfs(grid1, grid2, r, c + 1);

        return isSubIsland && up && down && left && right;
    }
}

/*
 * Logic:
 * Every unvisited land cell in grid2 starts a DFS for one complete island.
 * Each grid2 land cell is checked against grid1. The whole island is a
 * sub-island only when every corresponding grid1 cell is also land.
 *
 * Important detail:
 * Even when grid1[r][c] is 0, DFS continues through all four directions so
 * the entire grid2 island is marked visited before returning false.
 *
 * Pattern:
 * Boolean-return DFS + two-grid validation + component traversal.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C) worst case recursion stack.
 */