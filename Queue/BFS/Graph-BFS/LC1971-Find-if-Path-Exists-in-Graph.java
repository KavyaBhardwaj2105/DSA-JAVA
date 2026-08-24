// LeetCode 1971 - Find if Path Exists in Graph

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class LC1971FindIfPathExistsInGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(source);
        visited[source] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            if (node == destination) {
                return true;
            }

            for (int neighbour : graph.get(node)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }

        return false;
    }
}

/*
 * Pattern: Graph BFS + Single-Source Reachability
 *
 * Logic:
 * Build an undirected adjacency list from the edge list. Start BFS from
 * source and explore every reachable node. If destination is reached, a
 * valid path exists.
 *
 * 3-Step Implementation Check:
 * 1. Store: adjacency list + queue + visited array.
 * 2. Update when: a neighbour is discovered and has not been visited.
 * 3. Operation: mark visited, then enqueue the neighbour.
 *
 * Complexity:
 * Time: O(V + E)
 * Space: O(V + E)
 */