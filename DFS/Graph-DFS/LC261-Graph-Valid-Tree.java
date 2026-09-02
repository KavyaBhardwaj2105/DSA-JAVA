class Solution {

    public boolean validTree(int n, int[][] edges) {

        // A tree must have exactly n - 1 edges
        if (edges.length != n - 1) {
            return false;
        }

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

        // Start DFS from node 0
        if (!dfs(0, -1, graph, visited)) {
            return false;
        }

        // Check if every node was visited
        for (boolean node : visited) {
            if (!node) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(
        int node,
        int parent,
        List<Integer>[] graph,
        boolean[] visited
    ) {

        visited[node] = true;

        for (int neighbour : graph[node]) {

            // Parent edge — ignore it
            if (neighbour == parent) {
                continue;
            }

            // Already visited → cycle
            if (visited[neighbour]) {
                return false;
            }

            // DFS
            if (!dfs(neighbour, node, graph, visited)) {
                return false;
            }
        }

        return true;
    }
}