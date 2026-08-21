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

### LC841 — Keys and Rooms

#### Problem Pattern

**Single-Source Graph BFS + Reachability**

Treat each room as a graph node. Every key in room `i` is a directed edge from room `i` to the room opened by that key. Room `0` is the BFS source because it is initially unlocked.

#### Logic

Start BFS from room `0`. Every time a new key points to an unvisited room, mark that room visited and enqueue it. At the end, if every room was visited, all rooms are reachable.

```text
Room 0
  ↓
BFS
  ↓
keys → unvisited rooms
  ↓
mark + enqueue
  ↓
repeat
  ↓
visitedCount == total rooms?
```

#### Algorithm

1. Create `visited[]` and a queue.
2. Put room `0` in the queue and mark it visited.
3. While the queue is not empty, remove one room.
4. Examine every key in that room.
5. For every unvisited room unlocked by a key, mark it visited, increment the count, and enqueue it.
6. Return whether the number of visited rooms equals the total number of rooms.

#### Java Implementation

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(0);
        visited[0] = true;
        int visitedCount = 1;

        while (!q.isEmpty()) {
            int room = q.poll();

            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    visitedCount++;
                    q.offer(key);
                }
            }
        }

        return visitedCount == n;
    }
}
```

#### 3-Step Implementation Check

**1. What needs to be stored?**

- Queue stores room numbers.
- `visited[]` prevents revisiting rooms.
- `visitedCount` tracks how many rooms are reachable from room `0`.

**2. When should it be updated?**

When a key gives access to an unvisited room, mark that room visited immediately before enqueueing it.

**3. What update operation is needed?**

```java
visited[key] = true;
visitedCount++;
q.offer(key);
```

#### Complexity

Let `V` be the number of rooms and `E` the total number of keys.

- Time: **O(V + E)**
- Space: **O(V)**

#### Interview Traps

- Do not start BFS from every room; room `0` is the only initial source.
- Do not count a room just because its key appears; it must actually be reached through BFS.
- Mark rooms visited when enqueueing them.
- The question is reachability, not shortest path.

## LC547 — Number of Provinces: Java Implementation

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

            for (int neighbour = 0; neighbour < isConnected.length; neighbour++) {
                if (isConnected[city][neighbour] == 1 && !visited[neighbour]) {
                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
    }
}
```

### LC547 Example

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

### LC547 3-Step Implementation Check

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

### LC547 Complexity

Because the graph is provided as an `n x n` adjacency matrix and each relevant row is scanned:

- Time: **O(n²)**
- Space: **O(n)**

### Graph BFS Interview Pattern

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
