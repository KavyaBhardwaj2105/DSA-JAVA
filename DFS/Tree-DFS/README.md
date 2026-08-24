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

### Logic

```text
process current node
        ↓
   DFS left subtree
        ↓
   DFS right subtree
```

### 3-Step Implementation Check

1. **Store:** `List<Integer>` stores node values in preorder sequence.
2. **Update when:** current node is processed before either child.
3. **Operation:** `result.add(root.val)`.

### Complexity

- Time: **O(N)**
- Space: **O(H)** recursion stack.

## LC94 — Binary Tree Inorder Traversal

### Pattern

**Tree DFS + Recursive Inorder Traversal**

### Logic

Inorder means **Left → Root → Right**. The current node is processed only after its left subtree has been completely traversed and before the right subtree is traversed.

```text
   DFS left subtree
          ↓
   process current node
          ↓
   DFS right subtree
```

### 3-Step Implementation Check

1. **Store:** `List<Integer>` stores node values in inorder sequence.
2. **Update when:** after completing the left subtree and before traversing the right subtree.
3. **Operation:** `result.add(root.val)`.

### Complexity

- Time: **O(N)**
- Space: **O(H)** recursion stack.

## LC145 — Binary Tree Postorder Traversal

### Pattern

**Tree DFS + Recursive Postorder Traversal**

### Logic

Postorder means **Left → Right → Root**. The current node is processed only after both subtrees have been completely traversed.

```text
   DFS left subtree
          ↓
   DFS right subtree
          ↓
   process current node
```

### 3-Step Implementation Check

1. **Store:** `List<Integer>` stores node values in postorder sequence.
2. **Update when:** after completing both left and right subtree traversals.
3. **Operation:** `result.add(root.val)`.

### Algorithm

1. Create an empty result list.
2. Start recursive DFS from the root.
3. If the current node is `null`, return.
4. Recursively traverse the left child.
5. Recursively traverse the right child.
6. Add the current node's value to the result.
7. Return the result list.

### Java Solution

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }
}
```

### Complexity

- Time: **O(N)** — every node is visited once.
- Space: **O(H)** for the recursive call stack, where `H` is tree height.
- Balanced tree: **O(log N)** auxiliary stack space.
- Skewed tree: **O(N)** auxiliary stack space.

### Interview Traps

- Postorder is **Left → Right → Root**.
- The current node must be processed after both recursive calls.
- Tree DFS normally does not need a `visited[]` array because a tree has no cycles in its parent-child structure.
- Recursive DFS uses the call stack; iterative DFS uses an explicit stack.

## Problem Set

| Problem | Pattern | Status |
|---|---|---|
| LC144 | Preorder + Recursive DFS | Completed |
| LC94 | Inorder + Recursive DFS | Completed |
| LC145 | Postorder + Recursive DFS | Completed |

More Tree DFS problems will be added as they are completed.
