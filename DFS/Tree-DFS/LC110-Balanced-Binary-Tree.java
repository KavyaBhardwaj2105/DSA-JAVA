// LeetCode 110 - Balanced Binary Tree

class LC110BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        if (left == -1) {
            return -1;
        }

        int right = height(root.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return 1 + Math.max(left, right);
    }
}

/*
 * Logic:
 * The recursive function normally returns subtree height. It returns -1 as
 * a sentinel value when an unbalanced subtree is found, allowing imbalance
 * to propagate immediately to the root.
 *
 * 3-Step Implementation Check:
 * 1. Store: subtree height; -1 represents an unbalanced subtree.
 * 2. Update when: both child heights are available.
 * 3. Operation: if abs(left - right) > 1 return -1; otherwise return height.
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack.
 */