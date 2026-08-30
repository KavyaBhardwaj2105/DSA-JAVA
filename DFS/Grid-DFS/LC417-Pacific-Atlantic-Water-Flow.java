// LeetCode 417 - Pacific Atlantic Water Flow

class LC417PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Pacific: top + left boundaries
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j);
        }

        // Atlantic: bottom + right boundaries
        for (int i = 0; i < m; i++) {
            dfs(heights, atlantic, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, atlantic, m - 1, j);
        }

        // Cells reachable from both oceans
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] heights, boolean[][] visited, int r, int c) {
        if (visited[r][c]) {
            return;
        }

        visited[r][c] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= heights.length ||
                nc < 0 || nc >= heights[0].length) {
                continue;
            }

            // Reverse flow: move from lower/equal to higher/equal cells.
            if (heights[nr][nc] >= heights[r][c]) {
                dfs(heights, visited, nr, nc);
            }
        }
    }
}

/*
 * Logic:
 * Instead of starting DFS from every cell, reverse the flow. Start DFS from
 * each ocean boundary and move to cells of equal or greater height. This
 * identifies every cell from which water could flow to that ocean in the
 * original direction.
 *
 * Two reachability matrices are maintained: one for Pacific and one for
 * Atlantic. Their intersection gives the final answer.
 *
 * Pattern:
 * Reverse DFS + boundary traversal + multiple reachability states.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C) for reachability matrices and recursion stack.
 */