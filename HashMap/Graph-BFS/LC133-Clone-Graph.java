// LeetCode 133 - Clone Graph

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class LC133CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>();

        Node clone = new Node(node.val);
        map.put(node, clone);
        q.offer(node);

        while (!q.isEmpty()) {
            Node current = q.poll();

            for (Node neighbour : current.neighbors) {
                if (!map.containsKey(neighbour)) {
                    Node neighbourClone = new Node(neighbour.val);
                    map.put(neighbour, neighbourClone);
                    q.offer(neighbour);
                }

                map.get(current).neighbors.add(map.get(neighbour));
            }
        }

        return clone;
    }
}

/*
 * Logic:
 * Treat each original node as a graph node. The HashMap stores
 * Original Node -> Cloned Node. The map also acts as the visited structure.
 * BFS traverses the original graph once and builds the corresponding clone.
 *
 * Algorithm:
 * 1. Handle null input.
 * 2. Create HashMap<Original, Clone> and a BFS queue.
 * 3. Clone the starting node, store the mapping, and enqueue the original node.
 * 4. For each current node, inspect every neighbour.
 * 5. If a neighbour has not been cloned, create it, store the mapping, and enqueue it.
 * 6. Connect the current clone to the neighbour clone using the map.
 * 7. Return the clone of the starting node.
 *
 * Complexity:
 * Time: O(V + E)
 * Space: O(V)
 */