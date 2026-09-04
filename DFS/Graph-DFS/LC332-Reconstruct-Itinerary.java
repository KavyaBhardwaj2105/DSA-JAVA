import java.util.*;

class Solution {

    public List<String> findItinerary(List<List<String>> tickets) {

        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {

            String from = ticket.get(0);
            String to = ticket.get(1);

            graph
                .computeIfAbsent(from, k -> new PriorityQueue<>())
                .offer(to);
        }

        LinkedList<String> route = new LinkedList<>();

        dfs("JFK", graph, route);

        return route;
    }

    private void dfs(String airport,
                     Map<String, PriorityQueue<String>> graph,
                     LinkedList<String> route) {

        PriorityQueue<String> destinations =
            graph.getOrDefault(airport, new PriorityQueue<>());

        while (!destinations.isEmpty()) {

            String next = destinations.poll();

            dfs(next, graph, route);
        }

        route.addFirst(airport);
    }
}
