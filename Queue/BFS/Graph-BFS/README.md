# Graph BFS

Graph BFS uses the same core BFS engine as Tree and Grid BFS, but the neighbours come from the graph representation and visited tracking is essential because graphs can contain cycles.

## Core Template

```java
Queue<Integer> q = new ArrayDeque<>();
boolean[] visited = new boolean[n];

q.offer(start);
visited[start] = true;

while (!q.isEmpty()) {
    int node = q.poll();

    for (int neighbour : graph.get(node)) {
        if (!visited[neighbour]) {
            visited[neighbour] = true;
            q.offer(neighbour);
        }
    }
}
```

## LC1971 — Find if Path Exists in Graph

### Problem Pattern

**Single-Source Graph BFS + Reachability**

The graph is undirected. The task is to determine whether `destination` is reachable from `source`.

### Logic

Build an adjacency list from the edge list. Start BFS from `source`. Every newly discovered neighbour is marked visited and added to the queue. If `destination` is reached, a valid path exists.

```text
source
  ↓
BFS
  ↓
unvisited neighbours
  ↓
mark + enqueue
  ↓
repeat until destination or queue empty
```

### 3-Step Implementation Check

**1. What needs to be stored?**

- Adjacency list stores the graph's neighbours.
- Queue stores nodes waiting to be processed.
- `visited[]` prevents cycles and repeated traversal.

**2. When should it be updated?**

When an unvisited neighbour is discovered, mark it visited immediately before enqueueing it.

**3. What update operation is needed?**

```java
visited[neighbour] = true;
q.offer(neighbour);
```

### Algorithm

1. Create an adjacency list for all `n` nodes.
2. Add both directions for every undirected edge.
3. Create `visited[]` and a queue.
4. Add `source` to the queue and mark it visited.
5. Run BFS.
6. For each node, visit every unvisited neighbour.
7. Return `true` if `destination` is reached; otherwise return `false` after BFS ends.

### Java Implementation

```java
class Solution {
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
```

### Complexity

Let `V` be the number of vertices and `E` the number of edges.

- Time: **O(V + E)**
- Space: **O(V + E)** for the adjacency list and BFS structures.

### Interview Traps

- Because the graph is undirected, every edge must be added in both directions.
- Mark a node visited when enqueueing, not when dequeuing.
- This is a reachability problem, so no level/distance tracking is required.
- `source == destination` is immediately reachable.

## LC547 — Number of Provinces

### Problem Pattern

**Connected Components + BFS**

The input is an adjacency matrix. `isConnected[i][j] == 1` means city `i` and city `j` are directly connected.

A province is one connected component of the graph.

### Logic

A single BFS only visits the component containing its starting node. Therefore, an outer loop is required to find every disconnected component.

```text
for every city
    if city is unvisited
        provinces++
        BFS from this city
```

The outer loop answers **how many components exist**. The BFS answers **which cities belong to that component**.

## LC841 — Keys and Rooms

**Pattern:** Single-Source Graph BFS + Reachability.

Room `0` is the only initial source. Keys act as directed edges to other rooms. BFS explores all rooms reachable from room `0`.

## LC133 — Clone Graph

**Pattern:** Graph BFS + HashMap Mapping.

LC133 combines graph traversal with a `HashMap<Node, Node>` mapping. The map acts as both visited tracking and the original-to-clone lookup required to rebuild edges safely in cyclic graphs.

### Key Idea

```text
Original Node → Cloned Node
```

A boolean visited array only tells us whether a node was seen. For cloning, we also need to know which clone corresponds to each original node, so a HashMap is required.

## Graph BFS Pattern Summary

```text
1971 → Single-source BFS + reachability
547  → Connected components + BFS
841  → Single-source BFS + reachability
133  → BFS + HashMap original→clone
```
