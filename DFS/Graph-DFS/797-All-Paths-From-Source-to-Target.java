import java.util.*; 
 
class Solution { 
 
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) { 
 
        List<List<Integer>> result = new ArrayList<>(); 
 
        List<Integer> path = new ArrayList<>(); 
 
        // Start from node 0 
        path.add(0); 
 
        dfs(0, graph, path, result); 
 
        return result; 
    } 
 
    private void dfs( 
        int node, 
        int[][] graph, 
        List<Integer> path, 
        List<List<Integer>> result 
    ) { 
 
        // Reached destination 
        if (node == graph.length - 1) { 
 
            result.add(new ArrayList<>(path)); 
 
            return; 
        } 
 
        // Try every neighbor 
        for (int neighbor : graph[node]) { 
 
            // Choose 
            path.add(neighbor); 
 
            // Explore 
            dfs(neighbor, graph, path, result); 
 
            // Backtrack 
            path.remove(path.size() - 1); 
        } 
    } 
}