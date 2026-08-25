// LeetCode 112 - Path Sum

class LC112PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        int remaining = targetSum - root.val;

        if (root.left == null && root.right == null) {
            return remaining == 0;
        }

        return hasPathSum(root.left, remaining)
                || hasPathSum(root.right, remaining);
    }
}

/*
 * Logic:
 * Track the remaining target sum while performing DFS from root to leaf.
 * Subtract the current node's value from the target at every step.
 * Only a leaf can complete a valid root-to-leaf path.
 *
 * 3-Step Implementation Check:
 * 1. Store: remaining target sum.
 * 2. Update when: visiting each non-null node.
 * 3. Operation: remaining = targetSum - root.val.
 *
 * Base cases:
 * - null node -> false
 * - leaf node -> remaining == 0
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack.
 */