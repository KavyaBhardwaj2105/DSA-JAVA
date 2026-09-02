class Solution {

    public boolean hasCycle(int n, List<Integer>[] graph) {

        boolean[] visited = new boolean[n];
        boolean[] pathVisited = new boolean[n];

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                if (dfs(i, graph, visited, pathVisited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(
        int node,
        List<Integer>[] graph,
        boolean[] visited,
        boolean[] pathVisited
    ) {

        visited[node] = true;
        pathVisited[node] = true;

        for (int neighbour : graph[node]) {

            if (!visited[neighbour]) {

                if (dfs(neighbour, graph, visited, pathVisited)) {
                    return true;
                }

            } else if (pathVisited[neighbour]) {

                return true;
            }
        }

        // Backtracking
        pathVisited[node] = false;

        return false;
    }
}