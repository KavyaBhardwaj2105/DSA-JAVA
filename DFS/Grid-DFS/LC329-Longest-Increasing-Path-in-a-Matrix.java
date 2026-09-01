class Solution {

    int rows;
    int cols;

    public int longestIncreasingPath(int[][] matrix) {

        rows = matrix.length;
        cols = matrix[0].length;

        int[][] dp = new int[rows][cols];

        int answer = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                answer = Math.max(
                    answer,
                    dfs(matrix, i, j, dp)
                );
            }
        }

        return answer;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] dp) {

        // Already calculated
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int longest = 1;

        // Up
        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            longest = Math.max(
                longest,
                1 + dfs(matrix, i - 1, j, dp)
            );
        }

        // Down
        if (i < rows - 1 && matrix[i + 1][j] > matrix[i][j]) {
            longest = Math.max(
                longest,
                1 + dfs(matrix, i + 1, j, dp)
            );
        }

        // Left
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            longest = Math.max(
                longest,
                1 + dfs(matrix, i, j - 1, dp)
            );
        }

        // Right
        if (j < cols - 1 && matrix[i][j + 1] > matrix[i][j]) {
            longest = Math.max(
                longest,
                1 + dfs(matrix, i, j + 1, dp)
            );
        }

        dp[i][j] = longest;

        return longest;
    }
}
