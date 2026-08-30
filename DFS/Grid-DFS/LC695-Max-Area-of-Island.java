// LeetCode 695 - Max Area of Island

class LC695MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length ||
            c < 0 || c >= grid[0].length ||
            grid[r][c] == 0) {
            return 0;
        }

        grid[r][c] = 0;

        int area = 1;
        area += dfs(grid, r - 1, c);
        area += dfs(grid, r + 1, c);
        area += dfs(grid, r, c - 1);
        area += dfs(grid, r, c + 1);

        return area;
    }
}

/*
 * Logic:
 * Scan every cell. When an unvisited land cell (1) is found, DFS consumes
 * the complete connected island and returns its total area. The main loop
 * keeps the maximum area seen so far.
 *
 * 3-Step Implementation Check:
 * 1. Store: maxArea and the area returned by DFS.
 * 2. Update: when a new unvisited land cell is found.
 * 3. Operation: mark the cell as 0, explore all four directions, return
 *    1 + the areas returned by the four recursive calls.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C) worst case recursion stack.
 */