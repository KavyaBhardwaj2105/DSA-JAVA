# Tree DFS

Tree DFS explores a tree deeply before backtracking. Recursive DFS uses the call stack automatically; iterative DFS uses an explicit stack.

## Core Recursive Template

```java
void dfs(TreeNode node) {
    if (node == null) {
        return;
    }

    // process node
    dfs(node.left);
    dfs(node.right);
}
```

## Traversal Orders

- Preorder: **Root → Left → Right**
- Inorder: **Left → Root → Right**
- Postorder: **Left → Right → Root**

The DFS engine is the same; only the position of `process node` changes.

## LC144 — Binary Tree Preorder Traversal

**Pattern:** Tree DFS + Recursive Preorder

**Logic:** Process current node first, then recursively traverse left and right.

```text
process node → DFS left → DFS right
```

**3-Step Check:**
1. Store node values in `List<Integer>`.
2. Update before either child traversal.
3. Use `result.add(root.val)`.

**Complexity:** Time `O(N)`, Space `O(H)`.

## LC94 — Binary Tree Inorder Traversal

**Pattern:** Tree DFS + Recursive Inorder

**Logic:** Traverse left subtree, process current node, then traverse right subtree.

```text
DFS left → process node → DFS right
```

**3-Step Check:**
1. Store node values in `List<Integer>`.
2. Update after the left subtree and before the right subtree.
3. Use `result.add(root.val)`.

**Complexity:** Time `O(N)`, Space `O(H)`.

## LC145 — Binary Tree Postorder Traversal

**Pattern:** Tree DFS + Recursive Postorder

**Logic:** Traverse both children first, then process the current node.

```text
DFS left → DFS right → process node
```

**3-Step Check:**
1. Store node values in `List<Integer>`.
2. Update after both subtree traversals.
3. Use `result.add(root.val)`.

**Complexity:** Time `O(N)`, Space `O(H)`.

## LC104 — Maximum Depth of Binary Tree

### Pattern

**Tree DFS + Return Value / Height Calculation**

Unlike traversal problems, we do not store every node. Each recursive call returns the depth of its subtree.

### Logic

```text
             node
            /    \
       leftDepth rightDepth
            \    /
             max
              + 1
```

For every node:

```text
leftDepth  = depth(left)
rightDepth = depth(right)
answer = max(leftDepth, rightDepth) + 1
```

### Base Case

```java
if (root == null) {
    return 0;
}
```

A null subtree has depth `0`; a leaf therefore has depth `1`.

### 3-Step Implementation Check

1. **Store:** `leftDepth` and `rightDepth`.
2. **Update when:** both child depths have been calculated.
3. **Operation:** `Math.max(leftDepth, rightDepth) + 1`.

### Java Solution

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

### Complexity

- Time: **O(N)** — every node is visited once.
- Space: **O(H)** for the recursion stack.
- Balanced tree: **O(log N)** auxiliary stack space.
- Skewed tree: **O(N)** auxiliary stack space.

### Interview Takeaway

This introduces an important DFS pattern:

```text
Child answers → combine answers → return answer for current node
```

This return-value pattern will be reused in problems such as tree diameter and other bottom-up calculations.

## Problem Set

| Problem | Pattern | Status |
|---|---|---|
| LC144 | Preorder + Recursive DFS | Completed |
| LC94 | Inorder + Recursive DFS | Completed |
| LC145 | Postorder + Recursive DFS | Completed |
| LC104 | DFS + Height / Return Value | Completed |
| LC100 | Simultaneous DFS on Two Trees | Next |

More Tree DFS problems will be added as they are completed.
