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

### Core Idea

```text
All land cells
      ↓
Multi-source BFS
      ↓
nearest water cells → distance 1
      ↓
next water layer → distance 2
      ↓
...
      ↓
last layer = maximum distance
```

The problem asks for the maximum distance of a water cell from the **nearest** land cell. Multi-source BFS naturally computes that nearest distance for every water cell.

### 3-Step Implementation Check

**1. What needs to be stored?**

- Queue stores coordinates of all land cells initially.
- The grid acts as the visited structure by changing reached water cells from `0` to `1`.
- `distance` tracks the BFS layer.

**2. When should it be updated?**

- Add every land cell to the queue before BFS starts.
- When an unvisited water cell is reached, mark it visited immediately and enqueue it.
- Increase `distance` once per BFS level.

**3. What update operation is needed?**

```java
grid[newRow][newCol] = 1;
q.offer(new int[]{newRow, newCol});
```

### Algorithm

1. Scan the grid and enqueue every land cell.
2. Count the land cells.
3. If there is no land or no water, return `-1`.
4. Run BFS simultaneously from all land cells.
5. Process the queue level by level using `size = q.size()`.
6. For each cell, explore its four neighbours.
7. If a neighbour is an unvisited water cell, mark it visited and enqueue it.
8. Increment `distance` for each completed BFS layer.
9. Return the final `distance`.

### Java Implementation

```java
class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> q = new ArrayDeque<>();
        int landCount = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    q.offer(new int[]{row, col});
                    landCount++;
                }
            }
        }

        if (landCount == 0 || landCount == n * n) {
            return -1;
        }

        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        int distance = -1;

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

                    if (newRow >= 0 && newRow < n
                            && newCol >= 0 && newCol < n
                            && grid[newRow][newCol] == 0) {
                        grid[newRow][newCol] = 1;
                        q.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return distance;
    }
}
```

### Example

```text
1 0 0
0 0 0
0 0 1
```

BFS starts from both `1`s simultaneously. The center region is reached from the nearest land source first. The final BFS layer gives the maximum distance from land.

### Why Multi-Source BFS?

If we ran BFS separately from every water cell, we would repeat a large amount of work. Starting from every land cell at once means every water cell is reached at its minimum distance from the nearest land.

### Edge Cases

- **All water:** no land exists, so answer is `-1`.
- **All land:** no water exists, so answer is `-1`.
- **Mixed grid:** BFS expands from all land cells and the final level is the answer.

### Complexity

For an `n × n` grid:

- Time: **O(n²)** — each cell is processed at most once.
- Space: **O(n²)** worst case for the queue.

### Interview Traps

- This is **multi-source BFS**, not one BFS from one arbitrary land cell.
- The distance is to the **nearest** land, and we want the maximum among those distances.
- Do not forget the all-land and all-water cases.
- Mark water visited when enqueueing it.
- The final BFS level gives the maximum distance.

## BFS Pattern Summary

```text
994  → Multi-source BFS + time
1091 → Single-source BFS + shortest path
542  → Multi-source BFS + nearest distance to zero
1162 → Multi-source BFS + maximum distance from land
```
