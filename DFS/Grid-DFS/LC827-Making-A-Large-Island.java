import java.util.*;

class Solution {

    int n;

    public int largestIsland(int[][] grid) {
        n = grid.length;

        Map<Integer, Integer> area = new HashMap<>();
        int id = 2;

        // Step 1: Give every island a unique ID
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, id);
                    area.put(id, size);
                    id++;
                }
            }
        }

        int answer = 0;

        // Step 2: Check every 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {

                    Set<Integer> islands = new HashSet<>();

                    if (i > 0 && grid[i - 1][j] > 1)
                        islands.add(grid[i - 1][j]);

                    if (i < n - 1 && grid[i + 1][j] > 1)
                        islands.add(grid[i + 1][j]);

                    if (j > 0 && grid[i][j - 1] > 1)
                        islands.add(grid[i][j - 1]);

                    if (j < n - 1 && grid[i][j + 1] > 1)
                        islands.add(grid[i][j + 1]);

                    int newArea = 1;

                    for (int islandId : islands) {
                        newArea += area.get(islandId);
                    }

                    answer = Math.max(answer, newArea);
                }
            }
        }

        // Step 3: If there was no 0
        if (answer == 0) {
            return n * n;
        }

        return answer;
    }

    private int dfs(int[][] grid, int i, int j, int id) {

        if (i < 0 || i >= n ||
            j < 0 || j >= n ||
            grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = id;

        int size = 1;

        size += dfs(grid, i - 1, j, id);
        size += dfs(grid, i + 1, j, id);
        size += dfs(grid, i, j - 1, id);
        size += dfs(grid, i, j + 1, id);

        return size;
    }
}
