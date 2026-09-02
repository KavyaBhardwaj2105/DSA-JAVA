class Solution {
 
    public int[] findRedundantConnection(int[][] edges) { 
 
        int n = edges.length; 
 
        List<Integer>[] graph = new ArrayList[n + 1]; 
 
        for (int i = 1; i <= n; i++) { 
            graph[i] = new ArrayList<>(); 
        } 
 
        for (int[] edge : edges) { 
 
            int u = edge[0]; 
            int v = edge[1]; 
 
            boolean[] visited = new boolean[n + 1]; 
 
            // Check if u and v are already connected 
            if (dfs(u, v, graph, visited)) { 
                return edge; 
            } 
 
            // No path exists → safely add edge 
            graph[u].add(v); 
            graph[v].add(u); 
        } 
 
        return new int[0]; 
    } 
 
    private boolean dfs( 
        int node, 
        int target, 
        List<Integer>[] graph, 
        boolean[] visited 
    ) { 
 
        if (node == target) { 
            return true; 
        } 
 
        visited[node] = true; 
 
        for (int neighbour : graph[node]) { 
 
            if (!visited[neighbour]) { 
 
                if (dfs(neighbour, target, graph, visited)) { 
                    return true; 
                } 
            } 
        } 
 
        return false; 
    } 
}