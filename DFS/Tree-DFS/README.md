# Tree DFS

Tree DFS explores a tree deeply before backtracking. Recursive DFS uses the call stack automatically; iterative DFS uses an explicit stack.

## Core Recursive Template

```java
void dfs(TreeNode node) {
    if (node == null) return;
    dfs(node.left);
    dfs(node.right);
}
```

## Traversal Orders
- Preorder: **Root → Left → Right**
- Inorder: **Left → Root → Right**
- Postorder: **Left → Right → Root**

## Completed Problems

### LC144 — Binary Tree Preorder Traversal
**Pattern:** Recursive preorder DFS

**Logic:** Process root, then left subtree, then right subtree.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC94 — Binary Tree Inorder Traversal
**Pattern:** Recursive inorder DFS

**Logic:** Left subtree → process root → right subtree.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC145 — Binary Tree Postorder Traversal
**Pattern:** Recursive postorder DFS

**Logic:** Left subtree → right subtree → process root.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC104 — Maximum Depth of Binary Tree
**Pattern:** DFS + return value

**Logic:** Get both child depths, take the maximum, and add `1` for the current node.

**3-Step Check:**
1. Store: `leftDepth`, `rightDepth`.
2. Update: after both child DFS calls return.
3. Operation: `Math.max(leftDepth, rightDepth) + 1`.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC100 — Same Tree
**Pattern:** Simultaneous DFS on two trees

**Logic:** Both null → true; one null → false; different values → false; otherwise both left and right subtree comparisons must be true.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC226 — Invert Binary Tree
**Pattern:** DFS + tree modification / swapping

**Logic:** At every node, swap left and right child references, then recursively process both subtrees.

**3-Step Check:**
1. Store: `root.left` in `temp`.
2. Update: at every non-null node.
3. Operation: swap `root.left` and `root.right`.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC112 — Path Sum
**Pattern:** DFS + remaining target state + root-to-leaf validation

**Logic:** Subtract each node value from the remaining target. Only a leaf can complete a valid path, so return whether the remaining target is `0` at a leaf.

**3-Step Check:**
1. Store: remaining target sum.
2. Update: `remaining = targetSum - root.val`.
3. Operation: pass the updated remaining target to both children.

**Base cases:** null → `false`; leaf → `remaining == 0`.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC257 — Binary Tree Paths
**Pattern:** DFS + path tracking + root-to-leaf collection

**Logic:** Carry the current root-to-node path as a `String`. When a leaf is reached, add the complete path to the result list.

**3-Step Check:**
1. Store: current path and completed paths.
2. Update: append the current node value while visiting it.
3. Operation: add the path to the result when a leaf is reached.

**Backtracking:** Explicit undo is not required because `String` is immutable; each recursive call receives its own path value.

**Complexity:** Time `O(N × L)` worst case due to path-string construction, Space `O(H)` recursion stack excluding output.

### LC543 — Diameter of Binary Tree
**Pattern:** DFS + return value + global maximum

**Logic:** Each DFS call returns the subtree height. At every node, the diameter passing through that node is `left + right`; update the global best diameter with that value. Then return the height to the parent.

**3-Step Check:**
1. Store: subtree height through the recursive return value; global best diameter in `diameter`.
2. Update: after both child heights are available.
3. Operation: `diameter = Math.max(diameter, left + right)`.

**Important distinction:** `height()` returns information to the parent; `diameter` stores the best answer found anywhere in the tree.

**Complexity:** Time `O(N)`, Space `O(H)`.

### LC110 — Balanced Binary Tree
**Pattern:** DFS + return value + sentinel value

**Logic:** The recursive function normally returns subtree height. If any subtree is unbalanced, it returns `-1` as a sentinel value. That signal is propagated immediately upward. A node is unbalanced when `Math.abs(left - right) > 1`.

```text
normal subtree → height
unbalanced subtree → -1
```

**3-Step Check:**
1. Store: subtree height; `-1` represents an unbalanced subtree.
2. Update: after both child heights are available.
3. Operation: if `Math.abs(left - right) > 1`, return `-1`; otherwise return `1 + Math.max(left, right)`.

**Why `-1` works:** Valid subtree heights are non-negative, so `-1` can safely represent an error/unbalanced state.

**Complexity:** Time `O(N)`, Space `O(H)`.

## Problem Set

| Problem | Pattern | Status |
|---|---|---|
| LC144 | Preorder + Recursive DFS | Completed |
| LC94 | Inorder + Recursive DFS | Completed |
| LC145 | Postorder + Recursive DFS | Completed |
| LC104 | DFS + Height / Return Value | Completed |
| LC100 | Simultaneous DFS on Two Trees | Completed |
| LC226 | DFS + Tree Modification / Swap | Completed |
| LC112 | DFS + Remaining Target State | Completed |
| LC257 | DFS + Path Tracking | Completed |
| LC543 | DFS + Return Value + Global Maximum | Completed |
| LC110 | DFS + Return Value + Sentinel | Completed |

More Tree DFS problems will be added as they are completed.
