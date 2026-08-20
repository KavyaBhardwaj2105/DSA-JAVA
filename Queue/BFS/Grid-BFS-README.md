# Grid BFS

Grid BFS applies Breadth-First Search to matrix problems where movement has equal cost.

## Core Pattern

- Queue stores grid coordinates: `(row, col)`.
- BFS processes cells level by level.
- For equal-cost movement, the first time a cell is reached is its shortest distance from the source.
- Mark cells visited when adding them to the queue to prevent duplicate processing.
- Use a direction array instead of writing each neighbour check separately.

## Direction Variants

### 4 Directions

Used in most grid BFS problems:

```text
    up
     |
left-cell-right
     |
   down
```

### 8 Directions

Used in LC 1091:

```text
↖  ↑  ↗
←  cell  →
↙  ↓  ↘
```

## Single-Source vs Multi-Source BFS

### Single-Source BFS

One starting cell is inserted into the queue.

Examples:
- LC 1091 — Shortest Path in Binary Matrix
- LC 1926 — Nearest Exit from Entrance in Maze

### Multi-Source BFS

All starting cells are inserted into the queue before BFS begins.

Examples:
- LC 994 — Rotting Oranges
- LC 542 — 01 Matrix
- LC 1162 — As Far from Land as Possible

## Problem Set

| Problem | Pattern | Key Idea |
|---|---|---|
| LC 994 | Multi-source BFS | Each level = one minute |
| LC 1091 | Single-source BFS | Shortest path with 8 directions |
| LC 542 | Multi-source BFS | Distance to nearest 0 |
| LC 1162 | Multi-source BFS | Maximum distance from land |
| LC 1926 | Single-source BFS | First boundary cell is nearest exit |

## Interview Checklist

Before coding a Grid BFS problem, ask:

1. What are the starting cells?
2. Is this single-source or multi-source BFS?
3. What does the queue store?
4. Are moves 4-directional or 8-directional?
5. When should a cell be marked visited?
6. Does each BFS level represent time, distance, or path length?
7. When should the answer be updated or returned?
8. What happens if the destination is unreachable?
9. What are the all-source/all-destination edge cases?

## Complexity

For an `m x n` grid, each cell is normally visited at most once.

- Time: `O(m * n)`
- Space: `O(m * n)` in the worst case for the queue.

## Key Takeaway

The problems above are variations of the same BFS machinery. The important skill is identifying the source set, movement rules, level meaning, and stopping condition rather than memorizing five separate solutions.