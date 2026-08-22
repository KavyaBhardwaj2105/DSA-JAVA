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

        // check bounds and whether the neighbour is valid
    }
}
```

## LC994 — Rotting Oranges

### Pattern

**Multi-Source BFS + Level/Minute Tracking**

All initially rotten oranges are BFS sources. Every BFS level represents one minute because all currently rotten oranges spread simultaneously.

### 3-Step Implementation Check

**1. What needs to be stored?**

- Queue stores rotten orange coordinates `(row, col)`.
- `fresh` stores the number of fresh oranges remaining.
- `minutes` stores elapsed BFS levels.

**2. When should it be updated?**

When a fresh orange is reached by a rotten orange, immediately mark it rotten, decrement `fresh`, and enqueue it for the next minute.

**3. What update operation is needed?**

```java
grid[nr][nc] = 2;
fresh--;
q.offer(new int[]{nr, nc});
```

### Algorithm

1. Scan the entire grid.
2. Add every initially rotten orange to the queue.
3. Count every fresh orange.
4. While the queue is not empty and fresh oranges remain, process one complete queue level.
5. Increment `minutes` once for that level.
6. For each rotten orange, check its four neighbours.
7. If a neighbour is fresh, make it rotten, decrement `fresh`, and enqueue it.
8. Return `minutes` if `fresh == 0`; otherwise return `-1`.

### Java Implementation

```java
class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    q.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        int minutes = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty() && fresh > 0) {
            int size = q.size();
            minutes++;

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();

                for (int[] dir : directions) {
                    int nr = cell[0] + dir[0];
                    int nc = cell[1] + dir[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                            && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return fresh == 0 ? minutes : -1;
    }
}
```

### Why `q.size()` matters

The queue contains oranges from multiple minutes. `int size = q.size()` freezes the current BFS level. Only those oranges are allowed to spread during the current minute. Newly rotten oranges wait in the queue for the next level.

### Complexity

For `R` rows and `C` columns:

- Time: **O(R × C)**
- Space: **O(R × C)** in the worst case.

### Edge Cases

- No fresh oranges → answer is `0`.
- Fresh oranges exist but no rotten source can reach them → answer is `-1`.
- Multiple rotten oranges initially → process all of them simultaneously using multi-source BFS.

## LC1091 — Shortest Path in Binary Matrix

### Pattern

**Single-Source Grid BFS + Shortest Path**

Each `0` cell is traversable. Movement is allowed in all **8 directions**. The task is to find the shortest path from `(0,0)` to `(n-1,n-1)`.

### 3-Step Implementation Check

**1. What needs to be stored?**

- Queue stores `{row, col}` coordinates.
- The grid itself can act as the visited structure by changing visited `0` cells to `1`.
- `distance` stores the current BFS level/path length.

**2. When should it be updated?**

- Mark `(0,0)` visited when it is enqueued.
- Mark a neighbour visited immediately when it is enqueued.
- Increase `distance` after completing one BFS level.

**3. What update operation is needed?**

```java
grid[newRow][newCol] = 1;
q.offer(new int[]{newRow, newCol});
```

### Algorithm

1. If the start or destination is blocked, return `-1`.
2. Define all 8 movement directions.
3. Add `(0,0)` to the queue and mark it visited.
4. Set `distance = 1`.
5. Process the queue level by level using `size = q.size()`.
6. For each cell, check all 8 neighbours.
7. If a neighbour is inside the grid and is an unvisited `0`, mark it visited and enqueue it.
8. When the destination is reached, return the current distance.
9. If BFS ends without reaching it, return `-1`.

### Java Implementation

```java
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        grid[0][0] = 1;
        int distance = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

                if (row == n - 1 && col == n - 1) {
                    return distance;
                }

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow >= 0 && newRow < n
                            && newCol >= 0 && newCol < n
                            && grid[newRow][newCol] == 0) {
                        grid[newRow][newCol] = 1;
                        q.offer(new int[]{newRow, newCol});
                    }
                }
            }

            distance++;
        }

        return -1;
    }
}
```

### Why `q.size()` matters

`q.size()` freezes the current BFS level. Every cell in that level has the same shortest distance from the start. Newly discovered cells belong to the next level, so `distance` increases only after the current level is completely processed.

### Complexity

For an `n x n` grid:

- Time: **O(n²)**
- Space: **O(n²)** in the worst case.

### Interview Traps

- Check blocked start/end before BFS.
- There are **8 directions**, not 4.
- Mark a cell visited when enqueueing it.
- The first time BFS reaches the destination gives the shortest path.
- `distance` starts at `1` because the starting cell counts as part of the path.
