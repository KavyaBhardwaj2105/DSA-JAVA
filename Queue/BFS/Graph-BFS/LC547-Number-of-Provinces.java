// LeetCode 547 - Number of Provinces

import java.util.ArrayDeque;
import java.util.Queue;

class LC547NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                bfs(i, isConnected, visited);
            }
        }

        return provinces;
    }

    private void bfs(int start, int[][] isConnected, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int city = q.poll();

            for (int neighbour = 0; neighbour < isConnected.length; neighbour++) {
                if (isConnected[city][neighbour] == 1 && !visited[neighbour]) {
                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
    }
}

/*
 * Logic:
 * A province is a connected component of the graph.
 * The input is an adjacency matrix where isConnected[i][j] == 1 means
 * city i and city j are directly connected.
 *
 * We scan every city. Whenever we find an unvisited city, it belongs to
 * a new province, so we increment the answer and run BFS from that city.
 * That BFS visits every city belonging to the same connected component.
 *
 * Algorithm:
 * 1. Create a visited array and initialize province count to 0.
 * 2. Iterate through every city.
 * 3. If the city is unvisited, increment province count.
 * 4. Start BFS from that city.
 * 5. During BFS, scan its row in the adjacency matrix.
 * 6. For every connected and unvisited city, mark it visited and enqueue it.
 * 7. Continue until every city has been considered.
 * 8. Return the number of provinces.
 *
 * Pattern:
 * Unvisited node -> new component -> count++ -> BFS entire component.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */