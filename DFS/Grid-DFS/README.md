# Grid DFS

Grid DFS is a graph traversal pattern where each cell in a 2D grid is treated as a node and adjacent cells are connected through valid directions.

## Core Idea

For a grid problem, start DFS from an unvisited cell and recursively explore all reachable cells that satisfy the problem's condition.

The standard four directions are:

```text
(-1, 0)  // up
(1, 0)   // down
(0, -1)  // left
(0, 1)   // right
```

Some problems also allow diagonal movement.

## Basic DFS Template

```java
private void dfs(int row, int col, int[][] grid, boolean[][] visited) {

    if (row < 0 || row >= grid.length ||
        col < 0 || col >= grid[0].length ||
        visited[row][col]) {
        return;
    }

    visited[row][col] = true;

    dfs(row - 1, col, grid, visited);
    dfs(row + 1, col, grid, visited);
    dfs(row, col - 1, grid, visited);
    dfs(row, col + 1, grid, visited);
}
```

The exact base conditions depend on the problem. For example, DFS may only continue through land cells, cells with a particular value, or cells satisfying an increasing/decreasing condition.

## Implementation Check

Before coding:

1. What needs to be stored?
   - Usually the grid, visited state, or information being accumulated during DFS.

2. When should it be updated?
   - Usually when entering a valid cell.

3. What update operation is needed?
   - Mark the cell visited, update the current component/area/count, then explore valid neighbours.

## Common Patterns

### 1. Connected Components

Count separate groups of connected cells by starting DFS from every unvisited valid cell.

Examples:
- LC200 - Number of Islands
- LC1020 - Number of Enclaves
- LC1254 - Number of Closed Islands
- LC1905 - Count Sub Islands

### 2. Flood Fill / Region Traversal

Start from a cell and modify every reachable cell satisfying the traversal condition.

Example:
- LC733 - Flood Fill

### 3. Area / Maximum Component

DFS can return or accumulate the size of a connected component.

Example:
- LC695 - Max Area of Island

### 4. Boundary / Shape Problems

Use DFS to inspect neighbours and determine whether a component touches a boundary or has a particular shape.

Examples:
- LC463 - Island Perimeter
- LC827 - Making A Large Island

Note: LC463 is direct four-direction grid traversal rather than a DFS-based solution.

### 5. Advanced Grid DFS

Some problems add conditions to movement, requiring DFS state or memoization.

Examples:
- LC329 - Longest Increasing Path in a Matrix
- LC417 - Pacific Atlantic Water Flow

## Visited Handling

There are two common approaches:

### Separate visited array

```java
boolean[][] visited = new boolean[m][n];
```

Useful when the original grid must remain unchanged.

### Modify the grid

For example, change visited land cells to another value.

Useful when modifying the input is allowed and saves the extra visited array.

## Complexity

For a grid with `m` rows and `n` columns:

- Time: `O(m × n)` when each cell is processed a constant number of times.
- Space: `O(m × n)` in the worst case for visited state and/or recursion stack.

The exact auxiliary space depends on whether the grid is modified in-place and on the recursion depth.

## Common Mistakes

- Forgetting boundary checks.
- Marking a cell visited too late and causing repeated traversal.
- Forgetting to check whether a cell is a valid traversal cell.
- Accidentally revisiting the previous cell.
- Mixing row and column indices.
- Using the wrong direction set.
- Confusing direct grid traversal with DFS.
- Ignoring recursion-stack depth for large grids.

## Grid DFS vs Graph DFS

Grid DFS:
- Nodes are cells.
- Neighbours are generated using directions.
- Usually no explicit adjacency list is required.

Graph DFS:
- Nodes are vertices.
- Neighbours usually come from an adjacency list or adjacency matrix.
- `visited[]` is indexed by vertex.

The underlying idea is the same:

```text
Mark current node
        ↓
Explore every valid unvisited neighbour
        ↓
Repeat recursively
```

## Interview Checklist

Before submitting a Grid DFS solution, be able to explain:

- Why DFS is appropriate.
- What represents a node.
- What represents a neighbour.
- How boundaries are handled.
- When a cell becomes visited.
- Why cells are not processed repeatedly.
- Whether the grid is modified or a visited array is used.
- Time complexity.
- Auxiliary space and recursion depth.

## Problems Completed

- LC1020 - Number of Enclaves
- LC1254 - Number of Closed Islands
- LC130 - Surrounded Regions
- LC1905 - Count Sub Islands
- LC200 - Number of Islands
- LC417 - Pacific Atlantic Water Flow
- LC463 - Island Perimeter
- LC695 - Max Area of Island
- LC733 - Flood Fill
- LC827 - Making A Large Island
- LC329 - Longest Increasing Path in a Matrix

Grid DFS phase completed. Next graph traversal work continues under `DFS/Graph-DFS`.
