# DFS — Depth-First Search

This folder contains the complete DFS preparation track: fundamentals, Tree DFS, Graph DFS, Grid DFS, problem solutions, algorithms, patterns, and interview notes.

## Structure

```text
DFS/
├── README.md
├── Tree-DFS/
├── Graph-DFS/
└── Grid-DFS/
```

## Core DFS Concepts

- DFS explores as deep as possible before backtracking.
- DFS follows LIFO behavior.
- Recursive DFS uses the call stack implicitly.
- Iterative DFS uses an explicit stack.
- `visited` is essential for graph traversal when cycles may exist.
- Mark a node visited before making the recursive DFS call / before pushing it for traversal.

## Tree DFS Traversals

- Preorder: Root → Left → Right
- Inorder: Left → Root → Right
- Postorder: Left → Right → Root

## Graph DFS

- Reachability
- Connected components
- Cycle detection
- Recursive and iterative DFS
- Adjacency-list traversal
- Visited-state management

## Grid DFS

- Treat each cell as a graph node.
- Use directional movement.
- Check boundaries.
- Mark visited cells in a separate structure or in-place when appropriate.
- Flood fill, islands, regions, and boundary-connected components.

## DFS vs BFS

| Requirement | Usually Prefer |
|---|---|
| Shortest path in unweighted graph | BFS |
| Level-by-level traversal | BFS |
| Explore deeply / backtrack | DFS |
| Recursive tree traversal | DFS |
| Reachability | BFS or DFS |
| Connected components | BFS or DFS |
| Grid region traversal | BFS or DFS |

## Problem Set

Problems will be added to the relevant Tree-DFS, Graph-DFS, and Grid-DFS folders as they are completed.

Each problem will contain:

- Logic
- 3-step implementation check
- Appropriate DFS template
- Java solution
- Algorithm
- Time complexity
- Space complexity
- Edge cases / interview traps

## Progress

**Status: DFS started — fundamentals in progress.**

Next: Tree DFS fundamentals and first representative LeetCode problems.
