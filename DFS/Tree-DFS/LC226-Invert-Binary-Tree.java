// LeetCode 226 - Invert Binary Tree

class LC226InvertBinaryTree {
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

/*
 * Logic:
 * At every node, swap its left and right child references, then recursively
 * invert both resulting subtrees.
 *
 * 3-Step Implementation Check:
 * 1. Store: Save root.left in a temporary TreeNode reference.
 * 2. Update when: Process each non-null node during DFS.
 * 3. Operation: Swap root.left and root.right using temp.
 *
 * Traversal order is not the core of this problem. Every node must simply be
 * processed once; the swap operation is performed independently at each node.
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack, where H is tree height.
 */