# Queue + Breadth-First Search (BFS)
```really tough for me to understand even an easy level question it takes me 45 min to an hour but worth the grid ofc in the end```
BFS is one of the most important applications of a Queue. The Queue follows FIFO: First In, First Out. BFS uses this property to process nodes in the order in which they are discovered.

## Core Queue Operations

```java
Queue<Integer> q = new ArrayDeque<>();

q.offer(10);   // insert at rear
q.poll();      // remove from front
q.peek();      // see front without removing
q.isEmpty();   // check empty
q.size();      // number of elements
```

| Operation | Complexity |
|---|---:|
| `offer()` | O(1) |
| `poll()` | O(1) |
| `peek()` | O(1) |
| `isEmpty()` | O(1) |
| `size()` | O(1) |

For Java DSA, `ArrayDeque` is generally preferred over the legacy `Stack` class when a stack/queue structure is needed.

---

# BFS Fundamentals

BFS = Breadth-First Search.

BFS explores a tree or graph level by level.

Example:

```text
        1
       / \\
      2   3
     / \\   \\
    4   5   6
```

Traversal:

```text
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

The basic tree BFS template is:

```java
Queue<TreeNode> q = new ArrayDeque<>();
q.offer(root);

while (!q.isEmpty()) {
    TreeNode node = q.poll();

    // process node

    if (node.left != null) q.offer(node.left);
    if (node.right != null) q.offer(node.right);
}
```

## Level-Order BFS

When the output must preserve levels, save the queue size before processing the current level.

```java
while (!q.isEmpty()) {
    int levelSize = q.size();

    for (int i = 0; i < levelSize; i++) {
        TreeNode node = q.poll();
        // process current level node
    }
}
```

`levelSize` is critical. Nodes added while processing the current level belong to the next level and must not be processed immediately.

## BFS in Graphs

Graphs can contain cycles, so graph BFS normally needs `visited`.

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

Mark a node visited when adding it to the queue, not when removing it. This prevents duplicate queue entries.

## Multi-Source BFS

If several nodes are starting points, put all of them into the queue before BFS starts. This pattern is useful for spreading/infection problems and nearest-source problems.

Typical signals for BFS:

- Level-by-level traversal
- Minimum number of steps or moves
- Shortest path in an unweighted graph
- Nearest/closest node
- Grid movement with equal cost
- Simultaneous spreading from multiple sources

For a tree with `n` nodes, BFS is O(n) time and O(n) worst-case space. For a graph with `V` vertices and `E` edges, BFS is O(V + E).

---

# BFS Problem Set

The following problems build the important tree-BFS variants: normal level order, averages per level, right-side view, row-wise maximum, minimum depth, and pointer connection between nodes at the same level.

---

# LC 102 — Binary Tree Level Order Traversal

## Pattern

**Queue + Level-Order BFS**

## Logic

We need every tree level separately. Put the root into the queue. At the start of each BFS round, store `q.size()` as `levelSize`. Process exactly those nodes and add their children to the queue for the next round.

## Step-by-Step

1. Create the result list.
2. Handle `root == null`.
3. Add `root` to the queue.
4. While the queue is not empty, save `levelSize = q.size()`.
5. Process exactly `levelSize` nodes.
6. Add each node value to the current level.
7. Add non-null left and right children to the queue.
8. Add the completed level to the result.

## Java Solution

```java
import java.util.*;

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

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            result.add(level);
        }

        return result;
    }
}
```

## Complexity

Time: O(n), because every node is processed once.

Space: O(n) worst case for the queue and result.

---

# LC 637 — Average of Levels in Binary Tree

## Pattern

**Level-Order BFS + Aggregation**

## Logic

This is almost the same as LC 102. The difference is that instead of storing every value in a level list, calculate the sum of the current level and divide by the number of nodes in that level.

Use `long` for the sum to avoid unnecessary integer overflow concerns when node values are large.

## Step-by-Step

1. Create the answer list.
2. If root is null, return the empty list.
3. Put root into the queue.
4. At the start of each level, store `levelSize = q.size()`.
5. Initialize `sum = 0`.
6. Process exactly `levelSize` nodes.
7. Add each node's value to `sum`.
8. Add its non-null children to the queue.
9. Calculate `sum / levelSize` as a `double`.
10. Add the average to the answer.

## Java Solution

```java
import java.util.*;

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            long sum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                sum += node.val;

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            result.add((double) sum / levelSize);
        }

        return result;
    }
}
```

## Complexity

Time: O(n).

Space: O(n) worst case.

## Key Takeaway

LC 637 is not a new BFS pattern. It is **LC 102 + an aggregation operation per level**.

---

# LC 199 — Binary Tree Right Side View

## Pattern

**Level-Order BFS + Last Node of Each Level**

## Logic

Imagine standing on the right side of the tree. At every level, the visible node is the rightmost node in that level.

BFS already gives us one level at a time. Therefore, while processing a level, the node at index `levelSize - 1` is the answer for that level.

## Step-by-Step

1. Create the result list.
2. If root is null, return it.
3. Add root to the queue.
4. For each level, save `levelSize`.
5. Process exactly `levelSize` nodes.
6. If `i == levelSize - 1`, add that node's value to the result.
7. Add children to the queue.
8. Continue until the queue is empty.

## Java Solution

```java
import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                if (i == levelSize - 1) {
                    result.add(node.val);
                }

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return result;
    }
}
```

## Complexity

Time: O(n).

Space: O(n) worst case.

## Key Takeaway

Do not create an unnecessary second traversal. The right-side view is simply **the last processed node of every BFS level**.

---

# LC 515 — Find Largest Value in Each Tree Row

## Pattern

**Level-Order BFS + Maximum per Level**

## Logic

Again, the BFS structure is unchanged. For each level, initialize `max` to the smallest possible integer and update it for every node in that level.

## Step-by-Step

1. Create the answer list.
2. Handle `root == null`.
3. Add root to the queue.
4. For each level, save `levelSize`.
5. Set `max = Integer.MIN_VALUE`.
6. Process exactly `levelSize` nodes.
7. Update `max = Math.max(max, node.val)`.
8. Add children to the queue.
9. Add `max` to the answer after finishing the level.

## Java Solution

```java
import java.util.*;

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                max = Math.max(max, node.val);

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            result.add(max);
        }

        return result;
    }
}
```

## Complexity

Time: O(n).

Space: O(n) worst case.

## Key Takeaway

LC 515 is **level-order BFS + running maximum**. The traversal pattern does not change.

---

# LC 111 — Minimum Depth of Binary Tree

## Pattern

**BFS + First Leaf Found**

## Logic

Minimum depth means the shortest number of nodes from the root down to a leaf.

BFS explores the tree level by level. Therefore, the first leaf node we encounter is guaranteed to have minimum depth.

A critical detail: a node is a leaf only when **both** children are null.

Do not return merely because `left == null || right == null`; that incorrectly treats a node with one child as a leaf.

## Step-by-Step

1. If root is null, return 0.
2. Put root into the queue.
3. Start `depth = 1` because the root itself counts as one node.
4. Process one complete level at a time.
5. For every node in that level, check whether it is a leaf.
6. If it is a leaf, return the current depth immediately.
7. Otherwise, add its non-null children to the queue.
8. After finishing the level, increase depth.

## Java Solution

```java
import java.util.*;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            depth++;
        }

        return depth;
    }
}
```

## Complexity

Time: O(n) worst case.

Space: O(n) worst case.

## Key Takeaway

For minimum depth in an unweighted tree, BFS is often cleaner than DFS because **the first leaf reached is automatically the shallowest leaf**.

---

# LC 116 — Populating Next Right Pointers in Each Node

## Pattern

**Level-Order BFS + Connect Adjacent Nodes**

## Logic

For every level, nodes should point to the node immediately to their right. The last node of each level must point to `null`.

BFS gives us exactly the nodes of one level. Keep a `previous` node and connect it to the current node.

Because LC 116 uses a perfect binary tree, every non-leaf node has both children. This lets us add `left` and `right` directly to the queue.

## Step-by-Step

1. If root is null, return null.
2. Put root into the queue.
3. For each level, store `levelSize`.
4. Set `previous = null` before processing that level.
5. For each node in the level:
   - If `previous` exists, set `previous.next = node`.
   - Move `previous` to the current node.
   - Add left and right children to the queue.
6. After the level ends, the last node's `next` remains null.
7. Return root.

## Java Solution

```java
import java.util.*;

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            Node previous = null;

            for (int i = 0; i < levelSize; i++) {
                Node current = q.poll();

                if (previous != null) {
                    previous.next = current;
                }

                previous = current;

                if (current.left != null) q.offer(current.left);
                if (current.right != null) q.offer(current.right);
            }
        }

        return root;
    }
}
```

## Complexity

Time: O(n).

Space: O(n) because of the queue.

## Key Takeaway

The important trick is not the tree traversal itself. It is **using one `previous` pointer per level to connect adjacent nodes**.

---

# Pattern Comparison

| Problem | BFS Variant | Extra Operation |
|---|---|---|
| LC 102 | Level Order | Store every node by level |
| LC 637 | Level Order | Average of each level |
| LC 199 | Level Order | Take last node of each level |
| LC 515 | Level Order | Maximum of each level |
| LC 111 | Level Order | Return at first leaf |
| LC 116 | Level Order | Connect adjacent nodes |

The important interview lesson is that these are not six completely different algorithms. They are mostly the **same BFS skeleton with a different operation performed during each level**.

## BFS Mental Template

Before coding, ask:

1. **What does the Queue store?** `TreeNode`, graph node, coordinates, or another state?
2. **When do I add elements?** Usually when their parent/current state is processed.
3. **Do I need level separation?** If yes, save `levelSize = q.size()`.
4. **Do I need visited?** Trees usually no; cyclic graphs usually yes.
5. **What is the per-level operation?** Collect, sum, average, max, last node, connect, or stop at the first valid state.

This is the reusable BFS pattern to remember for interviews.
