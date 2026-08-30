// LeetCode 733 - Flood Fill

class LC733FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];

        if (oldColor == color) {
            return image;
        }

        dfs(image, sr, sc, oldColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int color) {
        image[r][c] = color;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < image.length &&
                nc >= 0 && nc < image[0].length &&
                image[nr][nc] == oldColor) {
                dfs(image, nr, nc, oldColor, color);
            }
        }
    }
}

/*
 * Logic:
 * Store the starting cell's original color. DFS changes the current cell
 * to the new color and recursively explores all four adjacent cells that
 * still have the original color.
 *
 * Important edge case:
 * If oldColor == color, return immediately because no transformation is
 * needed and unnecessary recursion should be avoided.
 *
 * Complexity:
 * Time: O(R * C) worst case
 * Space: O(R * C) worst case recursion stack.
 */