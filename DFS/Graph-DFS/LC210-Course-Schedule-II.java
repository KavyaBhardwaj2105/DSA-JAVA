class Solution {

    List<Integer> order;
    boolean[] visited;
    boolean[] pathVisited;
    List<Integer>[] graph;

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] prerequisite : prerequisites) {

            int course = prerequisite[0];
            int pre = prerequisite[1];

            graph[pre].add(course);
        }

        visited = new boolean[numCourses];
        pathVisited = new boolean[numCourses];

        order = new ArrayList<>();

        // DFS every unvisited course
        for (int i = 0; i < numCourses; i++) {

            if (!visited[i]) {

                if (dfs(i)) {
                    return new int[0];
                }
            }
        }

        // Reverse because we add node after DFS
        Collections.reverse(order);

        int[] result = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            result[i] = order.get(i);
        }

        return result;
    }

    private boolean dfs(int node) {

        visited[node] = true;
        pathVisited[node] = true;

        for (int neighbour : graph[node]) {

            if (!visited[neighbour]) {

                if (dfs(neighbour)) {
                    return true;
                }

            } else if (pathVisited[neighbour]) {

                // Cycle detected
                return true;
            }
        }

        // Current DFS path se bahar
        pathVisited[node] = false;

        // All dependencies processed
        order.add(node);

        return false;
    }
}
