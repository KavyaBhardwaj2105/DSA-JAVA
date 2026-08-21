# HashMap + Graph BFS

## LC133 — Clone Graph

### Why this belongs to both topics

LC133 combines two important patterns:

- **Graph BFS:** BFS is used to traverse the graph and handle cycles safely.
- **HashMap:** `Map<Node, Node>` stores the relationship between every original node and its cloned node. The map also acts as the visited structure.

### Core Idea

```text
Original Node → Cloned Node
```

For example:

```text
1 → 1'
2 → 2'
3 → 3'
```

When a neighbour has not been cloned, create it and store it in the map. Whether the neighbour is new or already cloned, connect the current clone to the neighbour clone using the map.

### 3-Step Implementation Check

**1. What needs to be stored?**

- Queue stores original graph nodes.
- HashMap stores `Original Node → Cloned Node`.
- The HashMap also tells us whether a node has already been cloned.

**2. When should it be updated?**

When a new neighbour is discovered, clone it, put the mapping into the HashMap, and enqueue the original neighbour.

**3. What update operation is needed?**

```java
map.put(neighbour, neighbourClone);
q.offer(neighbour);
map.get(current).neighbors.add(map.get(neighbour));
```

### Algorithm

1. If the input node is `null`, return `null`.
2. Create a `HashMap<Original, Clone>` and a queue.
3. Clone the starting node and store the mapping.
4. Start BFS from the original starting node.
5. For each neighbour, check whether it already exists in the map.
6. If not, create its clone, store it, and enqueue the original neighbour.
7. Add the mapped clone of the neighbour to the current clone's neighbour list.
8. Return the clone of the starting node.

### Java Solution

```java
class Solution {
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
```

### Complexity

Let `V` be the number of nodes and `E` the number of edges.

- Time: **O(V + E)**
- Space: **O(V)**

### Interview Traps

- A `boolean[] visited` is not enough because we need the actual cloned node for every original node.
- The mapping must be stored before enqueueing the newly discovered node to prevent duplicate clones in cyclic graphs.
- Never connect original nodes into the cloned graph; always use `map.get(...)` for the clone.
- Handle `node == null`.

### Pattern

```text
Graph BFS
   +
HashMap<Original, Clone>
   ↓
Traverse original graph
   ↓
Create each clone once
   ↓
Use mapping to rebuild edges
```
