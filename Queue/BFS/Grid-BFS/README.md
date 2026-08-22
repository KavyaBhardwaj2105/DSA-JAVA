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
