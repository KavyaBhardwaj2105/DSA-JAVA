class Solution {

    public int countComponents(int n, int[][] edges) {

        // Create adjacency list
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];

        int count = 0;

        // Check every node
        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                // New component found
                count++;

                dfs(i, graph, visited);
            }
        }

        return count;
    }

    private void dfs(
        int node,
        List<Integer>[] graph,
        boolean[] visited
    ) {

        // Mark current node
        visited[node] = true;

        // Visit neighbours
        for (int neighbour : graph[node]) {

            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited);
            }
        }
    }
}