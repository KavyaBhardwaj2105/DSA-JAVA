import java.util.*;

class Solution {

    public boolean validPath(
        int n,
        int[][] edges,
        int source,
        int destination
    ) {

        // Source and destination are the same
        if (source == destination) {
            return true;
        }

        // Build adjacency list
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (int neighbor : graph[current]) {

                if (neighbor == destination) {
                    return true;
                }

                if (!visited[neighbor]) {

                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }
}