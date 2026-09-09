class Solution {

    List<Integer>[] graph;
    int[] state;
    int destination;

    public boolean leadsToDestination(
        int n,
        int[][] edges,
        int source,
        int destination
    ) {

        this.destination = destination;

        graph = new ArrayList[n];
        state = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        return dfs(source);
    }

    private boolean dfs(int node) {

        // Current path mein already hai → cycle
        if (state[node] == 1) {
            return false;
        }

        // Already completely checked
        if (state[node] == 2) {
            return true;
        }

        // Dead end
        if (graph[node].isEmpty()) {
            return node == destination;
        }

        // Mark as currently visiting
        state[node] = 1;

        for (int next : graph[node]) {

            if (!dfs(next)) {
                return false;
            }
        }

        // All paths from this node are valid
        state[node] = 2;

        return true;
    }
}