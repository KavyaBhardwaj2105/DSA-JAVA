// LeetCode 200 - Number of Islands

class LC200NumberOfIslands {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length ||
            c < 0 || c >= grid[0].length ||
            grid[r][c] != '1') {
            return;
        }

        grid[r][c] = '0';

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}

/*
 * Logic:
 * Scan every cell. When an unvisited land cell ('1') is found, a new island
 * has been discovered, so increment count and run DFS to mark the entire
 * connected island as visited by changing its cells to '0'.
 *
 * 3-Step Implementation Check:
 * 1. Store: island count.
 * 2. Update: when the main traversal finds an unvisited '1'.
 * 3. Operation: increment count and DFS through all four directions while
 *    marking visited land as '0'.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C) worst case recursion stack for a fully connected grid.
 */