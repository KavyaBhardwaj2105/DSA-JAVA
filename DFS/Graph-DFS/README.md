# Graph DFS — Data Structures & Algorithms

This section covers **Depth First Search (DFS) on graphs** using Java. The goal is to understand graph representation, DFS traversal, connected components, cycle detection, path finding, and common interview patterns built on DFS.

---

## 1. What is Graph DFS?

Depth First Search is a graph traversal algorithm that explores a path as deeply as possible before backtracking.

Starting from a source vertex:

1. Visit the current vertex.
2. Mark it as visited.
3. Explore each unvisited neighbor recursively.
4. When no unvisited neighbor remains, backtrack.

DFS naturally follows the pattern:

**Visit → Explore → Backtrack**

Unlike BFS, DFS does not use a queue. Recursive DFS uses the **call stack**, while iterative DFS uses an explicit **Stack**.

---

## 2. Graph Representation

Graphs are commonly represented using an **adjacency list**.

For a graph with `V` vertices:

```text
0 → 1, 2
1 → 0, 3
2 → 0, 3
3 → 1, 2
```

In Java, this can be represented as:

```java
List<List<Integer>> graph = new ArrayList<>();
```

An adjacency list is generally preferred for sparse graphs because it uses `O(V + E)` space.

### Important graph terms

- **Vertex / Node:** An individual element in the graph.
- **Edge:** Connection between two vertices.
- **Degree:** Number of edges connected to a vertex.
- **Directed graph:** Edges have a direction.
- **Undirected graph:** Edges work in both directions.
- **Connected graph:** Every vertex is reachable from every other vertex.
- **Component:** A maximal group of mutually reachable vertices.
- **Cycle:** A path that eventually returns to an already visited vertex.

---

## 3. Basic Recursive DFS Template

For a graph represented with an adjacency list:

```java
void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
    visited[node] = true;

    for (int neighbor : graph.get(node)) {
        if (!visited[neighbor]) {
            dfs(neighbor, graph, visited);
        }
    }
}
```

### What each part does

`visited[node] = true` prevents the algorithm from visiting the same node repeatedly.

The loop examines every neighbor of the current node.

The recursive call continues DFS into an unvisited neighbor.

When a node has no unvisited neighbors, recursion returns to the previous node. This is the **backtracking** part of DFS.

---

## 4. Why Do We Need `visited[]`?

Graphs can contain cycles.

Example:

```text
0 → 1 → 2
↑       ↓
└───────┘
```

Without `visited[]`, DFS could repeatedly travel:

`0 → 1 → 2 → 0 → 1 → 2 ...`

Therefore, for a general graph, the standard DFS implementation needs a way to remember which nodes have already been processed.

For `V` vertices, `boolean[V]` provides `O(V)` visited storage.

---

## 5. Disconnected Graphs

A common mistake is starting DFS from only node `0`.

That works only when the graph is guaranteed to be connected.

For a disconnected graph, run DFS from every unvisited vertex:

```java
for (int node = 0; node < V; node++) {
    if (!visited[node]) {
        dfs(node, graph, visited);
    }
}
```

This pattern is the foundation for **Connected Components**.

---

## 6. Connected Components

A connected component is a group of vertices where every vertex is reachable from the others within that group.

To count components:

```text
components = 0

for every vertex:
    if vertex is unvisited:
        DFS(vertex)
        components++
```

The important observation is:

> Every new DFS started from an unvisited node represents one new connected component.

---

## 7. DFS for Path Existence

DFS can be used to determine whether a path exists between two vertices.

Basic idea:

```text
DFS(source)
    ↓
visit reachable nodes
    ↓
if destination is reached → path exists
```

The visited array prevents cycles from causing infinite traversal.

For path reconstruction, store a `parent` array:

```text
parent[child] = current
```

After reaching the destination, follow parent pointers backward to reconstruct the path.

---

## 8. Cycle Detection in an Undirected Graph

In an undirected graph, simply finding a visited neighbor does **not** automatically mean there is a cycle.

Example:

```text
0 — 1 — 2
```

When DFS is at `1`, it sees `0` as visited. That is not a cycle because `0` is the parent of `1`.

Therefore, DFS needs to track the parent:

```java
void dfs(int node, int parent, ...) {
    visited[node] = true;

    for (int neighbor : graph.get(node)) {
        if (!visited[neighbor]) {
            dfs(neighbor, node, ...);
        } else if (neighbor != parent) {
            // Cycle detected
        }
    }
}
```

### Core condition

```text
visited[neighbor] && neighbor != parent
```

means a back edge exists and a cycle is present.

---

## 9. Cycle Detection in a Directed Graph

Directed graphs require a different technique because the parent concept is not sufficient.

Use three states:

```text
0 = unvisited
1 = currently in DFS path
2 = completely processed
```

The important case is:

```text
neighbor is currently in the DFS path
```

which means we found a back edge and therefore a directed cycle.

Another common implementation uses:

```java
boolean[] visited;
boolean[] pathVisited;
```

where `pathVisited[node]` tells us whether the node is part of the current recursion path.

---

## 10. DFS on Directed Graphs

For directed graphs, only follow edges in their given direction.

If:

```text
0 → 1
```

DFS from `0` can reach `1`, but DFS from `1` cannot automatically reach `0`.

This distinction becomes important in problems involving:

- Reachability
- Course prerequisites
- Dependency graphs
- Cycle detection
- Topological ordering

---

## 11. Topological Sort Using DFS

Topological sorting applies to a **Directed Acyclic Graph (DAG)**.

DFS approach:

1. Visit a node.
2. DFS all unvisited neighbors.
3. After all neighbors are processed, add the node to the result.
4. Reverse the result.

The important concept is **postorder**:

```text
DFS neighbors first
        ↓
process current node after neighbors
        ↓
add current node
```

A directed cycle makes a valid topological ordering impossible, so cycle detection must be handled when required.

---

## 12. DFS and Backtracking

DFS and backtracking are closely related but should not be treated as identical.

### DFS

Usually means exploring a graph/tree structure systematically.

### Backtracking

Usually means exploring choices, undoing a choice, and trying another possibility.

Examples of backtracking include:

- Subsets
- Permutations
- Combination Sum
- N-Queens
- Word Search

Graph DFS can use backtracking concepts when the problem asks for paths or all possible routes.

---

## 13. Grid DFS vs Graph DFS

A grid is effectively a graph where each cell is a node and neighboring cells are connected by implicit edges.

### Grid DFS

Neighbors are usually determined by directions:

```java
int[] dr = {-1, 1, 0, 0};
int[] dc = {0, 0, -1, 1};
```

### Graph DFS

Neighbors come from the adjacency list:

```java
for (int neighbor : graph.get(node))
```

The underlying idea is the same:

```text
current node
    ↓
find neighbors
    ↓
visit unvisited neighbor
    ↓
DFS deeper
    ↓
backtrack
```

---

## 14. Implementation Check

Before writing code, ask three questions:

### 1. What needs to be stored?

Typical choices:

- `visited[]`
- `parent`
- `pathVisited[]`
- component count
- distance / depth
- result list
- graph adjacency list

### 2. When should it be updated?

Usually:

```text
Mark visited when entering the node.
```

For postorder problems:

```text
Update result after processing neighbors.
```

### 3. What update operation is needed?

Depending on the problem:

- Mark node visited
- Count component
- Store parent
- Detect cycle
- Add node to result
- Accumulate a value
- Backtrack a state

---

## 15. Complexity

For an adjacency-list graph:

### Time

```text
O(V + E)
```

Each vertex is visited at most once, and each edge is examined a constant number of times.

### Space

```text
O(V + E)
```

for the graph representation, plus:

```text
O(V)
```

for visited/state arrays and recursion stack in the worst case.

When discussing auxiliary space separately, say:

```text
Auxiliary space: O(V)
```

assuming the graph itself is not counted.

---

## 16. Common Mistakes

### Mistake 1: Forgetting `visited[]`

Can cause infinite recursion on cyclic graphs.

### Mistake 2: Starting DFS only from node `0`

Fails for disconnected graphs.

### Mistake 3: Marking visited too late

Prefer marking the node when entering DFS rather than after processing all neighbors.

### Mistake 4: Confusing directed and undirected graphs

Cycle detection logic is different.

### Mistake 5: Treating every visited neighbor as a cycle

In an undirected graph, the parent edge must be ignored.

### Mistake 6: Using BFS logic inside DFS

DFS uses recursion/stack; BFS uses a queue.

### Mistake 7: Ignoring recursion depth

A very deep graph can produce a large recursion stack. In Java, iterative DFS may be preferable when recursion depth can become excessive.

---

## 17. Interview Pattern Checklist

Before considering Graph DFS complete, be comfortable with:

- [ ] Graph representation using adjacency lists
- [ ] Recursive DFS
- [ ] Iterative DFS using Stack
- [ ] `visited[]`
- [ ] Disconnected graphs
- [ ] Connected components
- [ ] Path existence
- [ ] Path reconstruction
- [ ] Undirected cycle detection
- [ ] Directed cycle detection
- [ ] DFS on directed graphs
- [ ] Topological sort using DFS
- [ ] DFS with parent tracking
- [ ] DFS with multiple state arrays
- [ ] Postorder DFS
- [ ] Time and space complexity

---

## 18. Core Mental Model

Do not memorize individual solutions. Recognize the structure:

```text
GRAPH DFS
   |
   +-- Traversal
   |     +-- Recursive DFS
   |     +-- Iterative DFS
   |
   +-- Components
   |     +-- Connected Components
   |     +-- Reachability
   |
   +-- Cycles
   |     +-- Undirected + Parent
   |     +-- Directed + Recursion State
   |
   +-- Paths
   |     +-- Existence
   |     +-- Reconstruction
   |     +-- All Paths
   |
   +-- Ordering
         +-- Topological Sort
```

The goal of this folder is to build the ability to identify which DFS pattern a new problem is using rather than memorizing a solution for every LeetCode question.

---

## Recommended Problem Progression

The problems in this folder should progress from fundamentals to interview-level variations:

1. Basic graph DFS traversal
2. Connected Components
3. Number of Provinces
4. Path existence / path reconstruction
5. Undirected cycle detection
6. Directed cycle detection
7. All Paths From Source to Target
8. Course Schedule / prerequisite graph
9. Course Schedule II / topological ordering
10. Advanced DFS state / dependency problems

Each problem should be accompanied by:

- Approach
- Implementation check
- Java solution
- Time complexity
- Space complexity
- Key pattern learned
- Important edge cases

---

## Key Takeaway

Graph DFS is not a collection of unrelated tricks.

The core template remains:

```text
Start at node
      ↓
Mark visited
      ↓
Process current node
      ↓
Explore unvisited neighbors
      ↓
DFS deeper
      ↓
Backtrack
```

Most interview variations change **what you store, when you update it, and what condition you use while exploring neighbors**.

That is the pattern to master.
