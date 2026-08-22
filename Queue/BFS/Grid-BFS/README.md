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

Running a separate BFS from every `1` repeats work. Instead:

```text
All 0s
 ↓
BFS simultaneously
 ↓
Nearest cells get distance 1
 ↓
Next cells get distance 2
 ↓
...
```

Because BFS expands in layers, the first time a `1` is reached is through its nearest `0`.

### 3-Step Implementation Check

**1. What needs to be stored?** Queue stores coordinates. Every `0` is initially placed in the queue. The matrix stores the final distance and can represent visited state.

**2. When should it be updated?** Put all `0`s into the queue at initialization. Mark every `1` as unvisited using `-1`. When an unvisited neighbour is reached, assign its distance and enqueue it.

**3. What update operation is needed?**

```java
mat[nr][nc] = mat[row][col] + 1;
q.offer(new int[]{nr, nc});
```

### Algorithm

1. Scan the matrix.
2. Add every `0` cell to the queue.
3. Change every `1` to `-1` to represent an unvisited cell.
4. Run BFS using four directions.
5. When an unvisited cell is reached, its distance is the current cell's distance plus `1`.
6. Store that distance and enqueue the cell.
7. Return the matrix.

### Java Implementation

```java
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        Queue<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    q.offer(new int[]{r, c});
                } else {
                    mat[r][c] = -1;
                }
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] dir : directions) {
                int nr = row + dir[0];
                int nc = col + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && mat[nr][nc] == -1) {
                    mat[nr][nc] = mat[row][col] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return mat;
    }
}
```

### Example

Input:

```text
0 0 0
0 1 0
1 1 1
```

Output:

```text
0 0 0
0 1 0
1 2 1
```

### Complexity

For `R × C` cells:

- Time: **O(R × C)** — every cell enters the queue at most once.
- Space: **O(R × C)** worst case for the queue.

### Interview Traps

- Do not start BFS separately from every `1`.
- All zeros must be added to the queue initially.
- Mark `1`s as unvisited before BFS.
- Mark/update a cell when enqueueing it so it is not processed multiple times.
- This is **multi-source BFS**, not ordinary single-source BFS.

## BFS Pattern Summary

```text
994  → Multi-source BFS + time
1091 → Single-source BFS + shortest path
542  → Multi-source BFS + nearest distance
```
