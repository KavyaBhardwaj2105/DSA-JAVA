import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minReorder(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : connections) {
            int from = edge[0];
            int to = edge[1];

            // Original direction: from -> to
            graph.get(from).add(new int[]{to, 1});

            // Reverse traversal: to -> from
            graph.get(to).add(new int[]{from, 0});
        }

        boolean[] visited = new boolean[n];

        return dfs(0, graph, visited);
    }

    private int dfs(int city,
                    List<List<int[]>> graph,
                    boolean[] visited) {

        visited[city] = true;

        int count = 0;

        for (int[] edge : graph.get(city)) {
            int neighbour = edge[0];
            int direction = edge[1];

            if (visited[neighbour]) {
                continue;
            }

            // Original road is going away from 0
            if (direction == 1) {
                count++;
            }

            count += dfs(neighbour, graph, visited);
        }

        return count;
    }
}
