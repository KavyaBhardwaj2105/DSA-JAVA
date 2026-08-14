

```text
DSA-JAVA/
└── Queue/
    ├── README.md
    └── BFS/
        ├── README.md
        ├── LC102-Level-Order-Traversal.java
        └── LC994-Rotting-Oranges.java
```

`Queue/README.md` mein ye **complete documentation** paste karo:

````md
# Queue

Queue is a linear data structure that follows the FIFO principle.

FIFO = First In, First Out.

The element inserted first is removed first.

---

## 1. Basic Structure

Example:

[10] [20] [30] [40]
 ↑                  ↑
Front              Rear

10 will be removed first because it entered first.

---

## 2. Core Operations

### offer()

Adds an element to the rear of the queue.

```java
Queue<Integer> q = new ArrayDeque<>();

q.offer(10);
q.offer(20);
q.offer(30);
````

Queue:

[10, 20, 30]

---

### poll()

Removes and returns the front element.

```java
int x = q.poll();
```

If the queue was:

[10, 20, 30]

After poll:

[20, 30]

Returned value:

10

---

### peek()

Returns the front element without removing it.

```java
int x = q.peek();
```

Queue:

[20, 30]

peek() returns:

20

Queue remains:

[20, 30]

---

### isEmpty()

Checks whether the queue is empty.

```java
q.isEmpty();
```

Returns:

true or false.

---

### size()

Returns the number of elements.

```java
q.size();
```

---

# 3. Java Queue

Queue is an interface in Java.

A common implementation for DSA is ArrayDeque.

```java
Queue<Integer> q = new ArrayDeque<>();
```

Basic example:

```java
Queue<Integer> q = new ArrayDeque<>();

q.offer(10);
q.offer(20);
q.offer(30);

System.out.println(q.poll());  // 10
System.out.println(q.peek());  // 20
```

---

# 4. Queue Operations Complexity

| Operation | Complexity |
| --------- | ---------- |
| offer()   | O(1)       |
| poll()    | O(1)       |
| peek()    | O(1)       |
| isEmpty() | O(1)       |
| size()    | O(1)       |

---

# 5. Queue vs Stack

## Stack

Stack follows LIFO.

LIFO = Last In, First Out.

Example:

[10, 20, 30]

pop() removes:

30

## Queue

Queue follows FIFO.

Example:

[10, 20, 30]

poll() removes:

10

| Data Structure | Principle | Removal        |
| -------------- | --------- | -------------- |
| Stack          | LIFO      | Last inserted  |
| Queue          | FIFO      | First inserted |

---

# 6. Deque

Deque = Double Ended Queue.

Unlike a normal Queue, insertion and deletion can happen from both ends.

```text
Front                     Rear
 ↓                         ↓
[10] [20] [30] [40]
 ↑                         ↑
add/remove              add/remove
```

Java:

```java
Deque<Integer> dq = new ArrayDeque<>();
```

---

## Deque Operations

### addFirst()

Adds at the front.

```java
dq.addFirst(10);
```

### addLast()

Adds at the rear.

```java
dq.addLast(20);
```

### removeFirst()

Removes from the front.

```java
dq.removeFirst();
```

### removeLast()

Removes from the rear.

```java
dq.removeLast();
```

### peekFirst()

Returns the front element.

```java
dq.peekFirst();
```

### peekLast()

Returns the rear element.

```java
dq.peekLast();
```

---

# 7. Deque Example

```java
Deque<Integer> dq = new ArrayDeque<>();

dq.addLast(20);
dq.addLast(30);

dq.addFirst(10);

System.out.println(dq);
```

Deque:

[10, 20, 30]

Then:

```java
dq.removeLast();
```

Result:

[10, 20]

---

# 8. Deque Can Also Work Like a Stack

A Deque can be used as a Stack.

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

System.out.println(stack.pop());
```

Output:

20

For modern Java DSA implementations, `ArrayDeque` is often preferred over the legacy `Stack` class.

---

# 9. Breadth-First Search

BFS = Breadth-First Search.

BFS explores nodes level by level.

The core data structure used by BFS is a Queue.

Example tree:

```
    1
   / \
  2   3
 / \   \
4   5   6
```

BFS traversal:

1 → 2 → 3 → 4 → 5 → 6

---

# 10. How BFS Works

Start with the root.

Queue:

[1]

Process 1.

Add its children:

[2, 3]

Process 2.

Add its children:

[3, 4, 5]

Process 3.

Add its child:

[4, 5, 6]

Continue until the queue becomes empty.

The Queue guarantees FIFO order.

---

# 11. Basic Tree BFS Template

```java
Queue<TreeNode> q = new ArrayDeque<>();

q.offer(root);

while (!q.isEmpty()) {

    TreeNode node = q.poll();

    // process node

    if (node.left != null) {
        q.offer(node.left);
    }

    if (node.right != null) {
        q.offer(node.right);
    }
}
```

---

# 12. Level Order BFS

Sometimes we need each level separately.

For example:

```
    3
   / \
  9   20
     /  \
    15   7
```

Output:

[
[3],
[9, 20],
[15, 7]
]

We use:

```java
int levelSize = q.size();
```

The important idea is that `levelSize` stores the number of nodes belonging to the current level.

Then process exactly that many nodes.

Template:

```java
while (!q.isEmpty()) {

    int levelSize = q.size();

    List<Integer> level = new ArrayList<>();

    for (int i = 0; i < levelSize; i++) {

        TreeNode node = q.poll();

        level.add(node.val);

        if (node.left != null) {
            q.offer(node.left);
        }

        if (node.right != null) {
            q.offer(node.right);
        }
    }

    result.add(level);
}
```

---

# 13. BFS in Graphs

Graphs can contain cycles.

Example:

1 → 2 → 3
↑   |
└───┘

Without tracking visited nodes, BFS can process the same node repeatedly.

Therefore graph BFS generally uses:

Queue + Visited

Example:

```java
Queue<Integer> q = new ArrayDeque<>();
boolean[] visited = new boolean[n];

q.offer(start);
visited[start] = true;

while (!q.isEmpty()) {

    int node = q.poll();

    for (int neighbour : graph[node]) {

        if (!visited[neighbour]) {

            visited[neighbour] = true;
            q.offer(neighbour);
        }
    }
}
```

Important:

Mark a node visited when adding it to the Queue, not when removing it.

This prevents the same node from being added multiple times.

---

# 14. Multi-Source BFS

Sometimes there are multiple starting points.

Instead of putting one node into the Queue, put all starting nodes into the Queue initially.

Example:

```text
Source     Source
   ↓          ↓
   └── BFS ───┘
```

This is called Multi-Source BFS.

Common applications:

* Rotting Oranges
* Distance from nearest source
* Fire spreading
* Infection spreading
* Grid shortest-distance problems

---

# 15. BFS and Shortest Path

For an unweighted graph, BFS can find the shortest path in terms of number of edges.

Why?

Because BFS explores:

```text
distance 0
    ↓
distance 1
    ↓
distance 2
    ↓
distance 3
```

Therefore the first time we reach a node, we have reached it using the minimum number of edges.

---

# 16. When Should I Think of BFS?

Strong BFS signals:

* Level-by-level traversal
* Shortest path in an unweighted graph
* Minimum number of moves
* Minimum number of steps
* Nearest/closest node
* Grid problems with equal movement cost
* Multiple starting points spreading simultaneously

---

# 17. BFS Complexity

For a tree with n nodes:

Time:

O(n)

Space:

O(n)

For a graph with V vertices and E edges:

Time:

O(V + E)

Space:

O(V)

For an m × n grid:

Time:

O(m × n)

Space:

O(m × n)

---

# 18. Important BFS Rules

1. Queue is the core data structure.
2. Tree BFS normally does not require visited.
3. Graph BFS generally requires visited.
4. Multi-source BFS starts with multiple nodes in the Queue.
5. `queue.size()` can be used to separate levels.
6. One BFS level can represent one unit of time in spreading problems.
7. BFS gives shortest path in unweighted graphs.
8. Always identify what the Queue stores:

   * TreeNode
   * graph node
   * coordinates
   * state

````

Now `Queue/BFS/README.md` mein **problem-specific documentation** rakho:

```md
# BFS Problems

This section contains Breadth-First Search problems and their implementations.

---

# LC 102 — Binary Tree Level Order Traversal

## Problem

Given the root of a binary tree, return its level order traversal.

Each level should be returned as a separate list.

Example:

        3
       / \
      9   20
         /  \
        15   7

Output:

[
    [3],
    [9, 20],
    [15, 7]
]

---

## Pattern

This is a classic:

Queue + Level-Order BFS

---

## Core Idea

Use a Queue to process nodes in FIFO order.

At the beginning of every level:

```java
int levelSize = q.size();
````

This tells us how many nodes belong to the current level.

Process exactly `levelSize` nodes.

While processing them, add their children to the Queue.

Those children belong to the next level.

---

## Algorithm

1. Create result list.
2. If root is null, return result.
3. Add root to Queue.
4. While Queue is not empty:

   * Store current Queue size.
   * Create a new list for the current level.
   * Process exactly `levelSize` nodes.
   * Add each node's value to current level.
   * Add non-null children to Queue.
   * Add current level to result.
5. Return result.

---

## Java Solution

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {

            int levelSize = q.size();

            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {

                TreeNode node = q.poll();

                level.add(node.val);

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            result.add(level);
        }

        return result;
    }
}
```

---

## Why levelSize is important

Suppose:

```text
Queue = [9, 20]
```

Then:

```java
levelSize = 2;
```

We process exactly 2 nodes.

While processing 20, we may add:

```text
15, 7
```

Queue becomes:

```text
[15, 7]
```

But 15 and 7 must NOT be processed in the current level.

They belong to the next level.

Therefore we store the original Queue size before processing.

---

## Complexity

Time:

O(n)

Every node is processed once.

Space:

O(n)

The Queue and result can contain O(n) elements.

---

# LC 994 — Rotting Oranges

## Problem

A grid contains:

```text
0 → empty
1 → fresh orange
2 → rotten orange
```

Every minute, a rotten orange makes its adjacent fresh oranges rotten.

Adjacent means:

```text
up
down
left
right
```

Return the minimum number of minutes required for all oranges to become rotten.

If impossible, return -1.

---

## Pattern

Multi-Source BFS

---

## Core Idea

There can be multiple rotten oranges initially.

Put ALL rotten oranges into the Queue before starting BFS.

Also maintain:

```text
fresh = number of fresh oranges
minutes = elapsed BFS levels
```

Every BFS level represents one minute.

---

## What Does the Queue Store?

The Queue stores coordinates:

```text
(row, column)
```

because we need the position of every rotten orange to inspect its four neighbours.

---

## Algorithm

1. Traverse the entire grid.
2. Add every rotten orange to the Queue.
3. Count all fresh oranges.
4. Start BFS.
5. For every BFS level:

   * Process all currently rotten oranges.
   * Check their four directions.
   * If a neighbour is fresh:

     * make it rotten
     * decrease fresh count
     * add it to Queue
6. After processing a level, increase minutes.
7. If fresh becomes zero, return minutes.
8. If Queue becomes empty while fresh oranges remain, return -1.

---

## Directions

For each cell:

```text
up    → row - 1, col
down  → row + 1, col
left  → row, col - 1
right → row, col + 1
```

A common Java representation:

```java
int[][] directions = {
    {-1, 0},
    {1, 0},
    {0, -1},
    {0, 1}
};
```

---

## Java Solution

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
                }

                else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        int minutes = 0;

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        while (!q.isEmpty() && fresh > 0) {

            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {

                int[] current = q.poll();

                int r = current[0];
                int c = current[1];

                for (int[] direction : directions) {

                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (nr >= 0 && nr < rows &&
                        nc >= 0 && nc < cols &&
                        grid[nr][nc] == 1) {

                        grid[nr][nc] = 2;

                        fresh--;

                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}
```

---

## Dry Run

Input:

```text
2 1
1 1
```

Initial:

```text
Queue = [(0,0)]
fresh = 3
minutes = 0
```

Minute 1:

```text
2 2
2 1
```

Now:

```text
fresh = 1
Queue = [(0,1), (1,0)]
minutes = 1
```

Minute 2:

```text
2 2
2 2
```

Now:

```text
fresh = 0
minutes = 2
```

Answer:

```text
2
```

---

## Why Multi-Source BFS?

Suppose there are three rotten oranges initially.

They all start spreading at the same time.

If we process them separately, we could incorrectly count time.

Instead:

```text
Queue = [rotten1, rotten2, rotten3]
```

All three are considered part of BFS level 0.

Their newly rotten neighbours form level 1.

Those neighbours form level 2.

Therefore BFS naturally models simultaneous spreading.

---

## Important Edge Cases

### No fresh oranges

If:

```text
fresh = 0
```

answer is:

```text
0
```

because nothing needs to rot.

### Fresh oranges cannot be reached

Example:

```text
2 0 1
```

The fresh orange cannot be reached.

Therefore:

```text
-1
```

### Multiple rotten oranges

Use Multi-Source BFS.

### Single cell

Handle normally through the same logic.

---

## Complexity

For an m × n grid:

Time:

O(m × n)

Every cell is processed at most once.

Space:

O(m × n)

The Queue can contain many coordinates.

---

# BFS Interview Checklist

Before solving a BFS problem, ask:

1. What does the Queue store?
2. Is this single-source or multi-source BFS?
3. Do I need a visited structure?
4. Does each BFS level represent distance or time?
5. Do I need `levelSize = q.size()`?
6. What are the neighbours?
7. What is the stopping condition?
8. What happens if the target cannot be reached?

---

# Problems Completed

* LC 102 — Binary Tree Level Order Traversal
* LC 994 — Rotting Oranges

Concepts covered:

* Queue
* Deque
* BFS
* Level-order BFS
* Multi-Source BFS
* Grid BFS
* BFS time/level tracking

````