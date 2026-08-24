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

**Pattern:** Tree DFS + Return Value / Height Calculation

Each recursive call returns the depth of its subtree. The current node combines the two child answers using the larger depth and adds `1` for itself.

```text
leftDepth  = depth(left)
rightDepth = depth(right)
answer = max(leftDepth, rightDepth) + 1
```

**Base case:** `null → 0`.

**3-Step Check:**
1. Store `leftDepth` and `rightDepth`.
2. Update after both child depths are calculated.
3. Use `Math.max(leftDepth, rightDepth) + 1`.

**Complexity:** Time `O(N)`, Space `O(H)`.

### Interview Takeaway

```text
Child answers → combine answers → return answer for current node
```

This bottom-up return-value pattern will be reused in problems such as tree diameter.

## LC100 — Same Tree

### Pattern

**Tree DFS + Simultaneous DFS on Two Trees**

Instead of traversing one tree, DFS receives a corresponding pair of nodes from two trees and compares them recursively.

### Logic

```text
both null
   ↓
 true

one null
   ↓
 false

values different
   ↓
 false

otherwise
   ↓
left subtree same && right subtree same
```

### 3-Step Implementation Check

1. **Store:** No collection is required; the recursive function returns a boolean.
2. **Check when:** Compare each corresponding pair of nodes before going deeper.
3. **Operation:** Both left and right subtree comparisons must be true using `&&`.

### Base Cases

- Both `null` → `true`
- Exactly one `null` → `false`
- Different values → `false`

### Java Solution

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
```

### Complexity

- Time: **O(N)** in the worst case.
- Space: **O(H)** for the recursion stack.

### Interview Takeaway

This is the **two-tree DFS pattern**: compare corresponding nodes and recursively require both corresponding subtrees to match.

## Problem Set

| Problem | Pattern | Status |
|---|---|---|
| LC144 | Preorder + Recursive DFS | Completed |
| LC94 | Inorder + Recursive DFS | Completed |
| LC145 | Postorder + Recursive DFS | Completed |
| LC104 | DFS + Height / Return Value | Completed |
| LC100 | Simultaneous DFS on Two Trees | Completed |

More Tree DFS problems will be added as they are completed.
