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

**Pattern:** Tree DFS + Simultaneous DFS on Two Trees

Compare corresponding nodes recursively. Both corresponding subtrees must also be identical.

```text
both null → true
one null → false
values different → false
otherwise → left same && right same
```

**3-Step Check:**
1. Store nothing; the recursive function returns a boolean.
2. Compare each corresponding pair before going deeper.
3. Require both subtree comparisons to be true using `&&`.

**Complexity:** Time `O(N)`, Space `O(H)`.

## LC226 — Invert Binary Tree

**Pattern:** Tree DFS + Tree Modification / Pointer Swapping

At every non-null node, swap its left and right child references, then recursively invert both resulting subtrees.

```text
current node
    ↓
swap left ↔ right
    ↓
DFS left
    ↓
DFS right
```

### 3-Step Implementation Check

1. **Store:** Save `root.left` in a temporary `TreeNode` reference.
2. **Update when:** Process each non-null node during DFS.
3. **Operation:** Swap `root.left` and `root.right` using `temp`.

### Java Solution

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
```

### Important Point

A specific traversal order is not required. The key requirement is that every node is processed once. The swap operation is independent at each node.

### Complexity

- Time: **O(N)** — every node is visited once.
- Space: **O(H)** for the recursion stack.
- Balanced tree: **O(log N)** auxiliary stack space.
- Skewed tree: **O(N)** auxiliary stack space.

### Interview Takeaway

This is a DFS **modification pattern**: recursively visit the tree while changing the structure at each node.

## Problem Set

| Problem | Pattern | Status |
|---|---|---|
| LC144 | Preorder + Recursive DFS | Completed |
| LC94 | Inorder + Recursive DFS | Completed |
| LC145 | Postorder + Recursive DFS | Completed |
| LC104 | DFS + Height / Return Value | Completed |
| LC100 | Simultaneous DFS on Two Trees | Completed |
| LC226 | DFS + Tree Modification / Swap | Completed |

More Tree DFS problems will be added as they are completed.
