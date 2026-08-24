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

### Pattern

**Tree DFS + Recursive Preorder Traversal**

Preorder means the current node is processed before its left and right subtrees.

### Logic

```text
process current node
        ↓
   DFS left subtree
        ↓
   DFS right subtree
```

### 3-Step Implementation Check

**1. What needs to be stored?**

A `List<Integer>` stores node values in preorder sequence.

**2. When should it be updated?**

The current node is added before traversing either child.

**3. What update operation is needed?**

```java
result.add(root.val);
```

### Algorithm

1. Create an empty result list.
2. Start recursive DFS from the root.
3. If the current node is `null`, return.
4. Add the current node's value to the result.
5. Recursively traverse the left child.
6. Recursively traverse the right child.
7. Return the result list.

### Java Solution

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }
}
```

### Complexity

- Time: **O(N)** — every node is visited once.
- Space: **O(H)** for the recursive call stack, where `H` is tree height.
- Balanced tree: **O(log N)** auxiliary stack space.
- Skewed tree: **O(N)** auxiliary stack space.

### Interview Traps

- Preorder is **Root → Left → Right**.
- `process node` must happen before both recursive calls.
- Tree DFS normally does not need a `visited[]` array because a tree has no cycles in its parent-child structure.
- Recursive DFS uses the call stack; iterative DFS uses an explicit stack.

## Problem Set

| Problem | Pattern | Status |
|---|---|---|
| LC144 | Preorder + Recursive DFS | Completed |
| LC94 | Inorder DFS | Next |

More Tree DFS problems will be added as they are completed.
