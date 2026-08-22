# Grid BFS

Grid BFS treats each cell as a graph node. Movement is defined by direction vectors, and BFS is used for shortest distance, multi-source spreading, reachability, or boundary-based traversal.

## Core Template

```java
Queue<int[]> q = new ArrayDeque<>();
int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
q.offer(new int[]{startRow, startCol});
while (!q.isEmpty()) {
    int[] cell = q.poll();
    for (int[] dir : directions) {
        int nr = cell[0] + dir[0];
        int nc = cell[1] + dir[1];
        // check bounds and whether neighbour is valid
    }
}
```

## LC994 — Rotting Oranges

**Pattern:** Multi-Source BFS + Level/Minute Tracking.

All initially rotten oranges are BFS sources. Every BFS level represents one minute because all currently rotten oranges spread simultaneously.

### 3-Step Implementation Check

**1. What needs to be stored?** Queue stores rotten orange coordinates; `fresh` stores remaining fresh oranges; `minutes` stores elapsed levels.

**2. When should it be updated?** When a fresh orange is reached, immediately mark it rotten, decrement `fresh`, and enqueue it.

**3. What update operation is needed?**

```java
grid[nr][nc] = 2;
fresh--;
q.offer(new int[]{nr, nc});
```

### Algorithm

1. Scan the grid, enqueue all rotten oranges and count fresh oranges.
2. Process the queue level by level while fresh oranges remain.
3. Each level represents one minute.
4. Spread to the four valid fresh neighbours.
5. Return `minutes` if all fresh oranges rot; otherwise `-1`.

### Complexity

- Time: **O(R × C)**
- Space: **O(R × C)** worst case.

## LC1091 — Shortest Path in Binary Matrix

**Pattern:** Single-Source Grid BFS + Shortest Path.

Each `0` cell is traversable. Movement is allowed in all **8 directions**. BFS guarantees the first time the destination is reached is the shortest path.

### 3-Step Implementation Check

**1. What needs to be stored?** Queue stores `{row, col}`; the grid can act as visited; `distance` tracks the current BFS level.

**2. When should it be updated?** Mark cells visited when enqueuing them. Increase `distance` after completing one level.

**3. What update operation is needed?**

```java
grid[newRow][newCol] = 1;
q.offer(new int[]{newRow, newCol});
```

### Complexity

- Time: **O(n²)**
- Space: **O(n²)** worst case.

## LC542 — 01 Matrix

### Pattern

**Multi-Source BFS + Distance to Nearest Zero**

For every cell containing `1`, find the distance to the nearest `0`. Every `0` is a BFS source, so we start BFS from all zeros simultaneously.

### Why Multi-Source BFS?

Running a separate BFS from every `1` repeats work. Instead, all zeros enter the queue initially and BFS expands outward together. Because BFS expands in layers, the first time a `1` is reached is through its nearest `0`.

### 3-Step Implementation Check

**1. What needs to be stored?** Queue stores coordinates. Every `0` is initially placed in the queue. The matrix stores the final distance and can represent visited state.

**2. When should it be updated?** Put all `0`s into the queue at initialization. Mark every `1` as unvisited using `-1`. When an unvisited neighbour is reached, assign its distance and enqueue it.

**3. What update operation is needed?**

```java
mat[nr][nc] = mat[row][col] + 1;
q.offer(new int[]{nr, nc});
```

### Complexity

- Time: **O(R × C)**
- Space: **O(R × C)** worst case.

## LC1162 — As Far from Land as Possible

### Pattern

**Multi-Source BFS + Maximum Distance from Land**

Every land cell (`1`) is a BFS source. We expand from all land cells simultaneously into water cells (`0`). The final BFS layer represents the water cells that are farthest from their nearest land.

### 3-Step Implementation Check

**1. What needs to be stored?** Queue stores coordinates of all land cells initially; the grid acts as visited; `distance` tracks the BFS layer.

**2. When should it be updated?** Add every land cell before BFS. When an unvisited water cell is reached, mark it visited and enqueue it. Increase `distance` once per level.

**3. What update operation is needed?**

```java
grid[newRow][newCol] = 1;
q.offer(new int[]{newRow, newCol});
```

### Algorithm

1. Scan the grid and enqueue every land cell.
2. If there is no land or no water, return `-1`.
3. Run BFS simultaneously from all land cells.
4. Process the queue level by level.
5. Enqueue every unvisited water neighbour and mark it visited.
6. The final BFS level is the maximum distance.

### Complexity

- Time: **O(n²)**
- Space: **O(n²)** worst case.

## LC1926 — Nearest Exit from Entrance in Maze

### Pattern

**Single-Source BFS + Shortest Path + Boundary Exit**

The entrance is the BFS source. Each `.` cell is traversable and each move costs one step. An exit is any open cell on the boundary, except the entrance itself. Since BFS explores level by level, the first boundary cell discovered is the nearest exit.

### Core Idea

```text
Entrance
   ↓
BFS level 1
   ↓
BFS level 2
   ↓
...
   ↓
First boundary '.' reached = nearest exit
```

The entrance is marked visited immediately, so even if it lies on the boundary, it cannot be incorrectly returned as an exit.

### 3-Step Implementation Check

**1. What needs to be stored?**

- Queue stores `{row, col}` coordinates.
- `distance` stores the current BFS level.
- The maze itself stores visited state by changing visited `.` cells to `+`.

**2. When should it be updated?**

When a valid unvisited `.` neighbour is discovered, check whether it is a boundary exit. If it is not an exit, mark it visited and enqueue it.

**3. What update operation is needed?**

```java
maze[newRow][newCol] = '+';
q.offer(new int[]{newRow, newCol});
```

### Algorithm

1. Add the entrance to the queue.
2. Mark the entrance visited.
3. Process the queue level by level.
4. Explore the four adjacent cells.
5. Ignore out-of-bounds, walls, and visited cells.
6. If a newly discovered open cell is on the boundary, return the current distance.
7. Otherwise mark it visited and enqueue it.
8. If BFS finishes without finding an exit, return `-1`.

### Java Implementation

```java
class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{entrance[0], entrance[1]});
        maze[entrance[0]][entrance[1]] = '+';

        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                        continue;
                    }

                    if (maze[newRow][newCol] != '.') {
                        continue;
                    }

                    if (newRow == 0 || newRow == rows - 1
                            || newCol == 0 || newCol == cols - 1) {
                        return distance;
                    }

                    maze[newRow][newCol] = '+';
                    q.offer(new int[]{newRow, newCol});
                }
            }
        }

        return -1;
    }
}
```

### Complexity

For an `R × C` maze:

- Time: **O(R × C)** — each cell is processed at most once.
- Space: **O(R × C)** worst case for the queue.

### Interview Traps

- The **entrance itself is never an exit**.
- Mark cells visited when enqueueing them.
- The first boundary cell discovered by BFS gives the shortest exit distance.
- Movement is only in four directions.
- Return `-1` if no exit is reachable.

## BFS Pattern Summary

```text
994  → Multi-source BFS + time
1091 → Single-source BFS + shortest path + 8 directions
542  → Multi-source BFS + nearest distance to zero
1162 → Multi-source BFS + maximum distance from land
1926 → Single-source BFS + nearest boundary exit
```
