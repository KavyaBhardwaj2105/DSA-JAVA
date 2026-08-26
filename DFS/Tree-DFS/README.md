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
**3-Step Check:** Store child depths → update after both DFS calls → return `Math.max(leftDepth, rightDepth) + 1`.
**Complexity:** Time `O(N)`, Space `O(H)`.

### LC100 — Same Tree
**Pattern:** Simultaneous DFS on two trees
**Logic:** Both null → true; one null → false; different values → false; otherwise both subtree comparisons must be true.
**Complexity:** Time `O(N)`, Space `O(H)`.

### LC226 — Invert Binary Tree
**Pattern:** DFS + tree modification / swapping
**Logic:** Swap left and right child references at every node, then recursively process both subtrees.
**Complexity:** Time `O(N)`, Space `O(H)`.

### LC112 — Path Sum
**Pattern:** DFS + remaining target state + root-to-leaf validation
**Logic:** Subtract each node value from the remaining target. At a leaf, check whether the remaining target is `0`.
**Complexity:** Time `O(N)`, Space `O(H)`.

### LC257 — Binary Tree Paths
**Pattern:** DFS + path tracking + root-to-leaf collection
**Logic:** Carry the current root-to-node path as a String and add it when a leaf is reached. Explicit backtracking is not required because String is immutable.
**Complexity:** Time `O(N × L)` worst case, Space `O(H)` excluding output.

### LC543 — Diameter of Binary Tree
**Pattern:** DFS + return value + global maximum
**Logic:** Each DFS returns subtree height. At every node, update the global diameter with `left + right`, then return the height.
**Complexity:** Time `O(N)`, Space `O(H)`.

### LC110 — Balanced Binary Tree
**Pattern:** DFS + return value + sentinel value
**Logic:** Return subtree height normally; return `-1` when an imbalance is found and propagate it upward.
**Complexity:** Time `O(N)`, Space `O(H)`.

### LC236 — Lowest Common Ancestor of a Binary Tree
**Pattern:** DFS + parent-child relationship + returned node information
**Logic:** Return a target when found. If left and right both return non-null, current node is the LCA; otherwise propagate the non-null result.
**Complexity:** Time `O(N)`, Space `O(H)`.

### LC113 — Path Sum II
**Pattern:** DFS + mutable path + backtracking

Track the current root-to-node path in a mutable `List<Integer>`. When a leaf satisfies the target sum, add a copy of the path to the answer. After exploring both children, remove the current node to restore the path for the sibling branch.

**3-Step Check:**
1. Store: current path and all valid paths.
2. Update: add the current node; add a path when a valid leaf is found.
3. Operation: recurse into children, then `path.remove(path.size() - 1)` to backtrack.

**Important distinction:** `new ArrayList<>(path)` is required when storing a valid path because the original `path` list is mutable and will continue changing during backtracking.

**Complexity:** Time `O(N × H)` in the worst case due to copying paths, Space `O(H)` recursion/path state excluding output.

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
| LC236 | DFS + Returned Node Information | Completed |
| LC113 | DFS + Mutable Path + Backtracking | Completed |

More Tree DFS problems will be added as they are completed.
