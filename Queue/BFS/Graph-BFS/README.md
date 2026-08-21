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

### Algorithm

1. Create `visited[]` and set `provinces = 0`.
2. Iterate through every city.
3. If the city is already visited, skip it.
4. If it is unvisited, increment `provinces`.
5. Start BFS from that city.
6. For every city removed from the queue, scan its entire adjacency-matrix row.
7. If a neighbour is connected and unvisited, mark it visited and enqueue it.
8. Continue until all cities have been considered.
9. Return `provinces`.

### Java Implementation

```java
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

            for (int neighbour = 0;
                 neighbour < isConnected.length;
                 neighbour++) {

                if (isConnected[city][neighbour] == 1
                        && !visited[neighbour]) {

                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
    }
}
```

### Example

```text
1 1 0
1 1 0
0 0 1
```

Graph:

```text
0 ---- 1       2
```

- First unvisited city: `0` → `provinces = 1` → BFS visits `0, 1`.
- City `1` is already visited.
- City `2` is unvisited → `provinces = 2` → BFS visits `2`.

Answer: `2`

### 3-Step Implementation Check

**1. What needs to be stored?**

- Queue stores city/node numbers.
- `visited[]` prevents repeated processing.
- `provinces` stores the number of connected components.

**2. When should it be updated?**

- Increment `provinces` when the outer loop finds an unvisited city.
- Mark a city visited when it is added to the queue.

**3. What update operation is needed?**

```java
provinces++;
visited[neighbour] = true;
q.offer(neighbour);
```

### Complexity

Because the graph is provided as an `n x n` adjacency matrix and each relevant row is scanned:

- Time: **O(n²)**
- Space: **O(n)**

### Interview Traps

- Do not increment the province count for every city.
- Do not run only one BFS; the graph can be disconnected.
- Do not create a second `visited[]` inside BFS. Pass the same array so all BFS runs share traversal state.
- Mark neighbours visited when enqueueing them, not when dequeueing.
- Do not confuse a province with a city. A province is a connected component.

### Pattern to Remember

```text
Unvisited node
      ↓
New connected component
      ↓
count++
      ↓
BFS entire component
      ↓
Continue outer loop
```
