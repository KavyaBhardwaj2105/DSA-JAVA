class Solution {

    int timer = 0;

    public List<List<Integer>> criticalConnections(
            int n, List<List<Integer>> connections) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge : connections) {

            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] discovery = new int[n];
        int[] low = new int[n];

        Arrays.fill(discovery, -1);

        List<List<Integer>> result = new ArrayList<>();

        dfs(0, -1, graph, discovery, low, result);

        return result;
    }

    private void dfs(
            int node,
            int parent,
            List<List<Integer>> graph,
            int[] discovery,
            int[] low,
            List<List<Integer>> result) {

        discovery[node] = low[node] = timer++;

        for (int neighbour : graph.get(node)) {

            if (neighbour == parent) {
                continue;
            }

            if (discovery[neighbour] != -1) {

                low[node] = Math.min(
                        low[node],
                        discovery[neighbour]
                );

            } else {

                dfs(
                        neighbour,
                        node,
                        graph,
                        discovery,
                        low,
                        result
                );

                low[node] = Math.min(
                        low[node],
                        low[neighbour]
                );

                if (low[neighbour] > discovery[node]) {

                    result.add(
                        Arrays.asList(node, neighbour)
                    );
                }
            }
        }
    }
}
