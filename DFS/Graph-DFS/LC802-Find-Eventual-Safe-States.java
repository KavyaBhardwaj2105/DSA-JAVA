import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        // 0 = unvisited
        // 1 = visiting / current path
        // 2 = safe

        int[] state = new int[n];

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (dfs(i, graph, state)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean dfs(int node, int[][] graph, int[] state) {

        // Current path mein already hai
        // => cycle
        if (state[node] == 1) {
            return false;
        }

        // Already determined safe
        if (state[node] == 2) {
            return true;
        }

        // Mark as currently visiting
        state[node] = 1;

        for (int neighbour : graph[node]) {

            if (!dfs(neighbour, graph, state)) {
                return false;
            }
        }

        // Saare neighbours safe hain
        state[node] = 2;

        return true;
    }
}
