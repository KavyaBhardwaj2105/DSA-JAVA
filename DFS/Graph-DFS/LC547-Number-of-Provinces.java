class Solution {
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
                if (isConnected[city][neighbour] == 1
                        && !visited[neighbour]) {
                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
    }
}