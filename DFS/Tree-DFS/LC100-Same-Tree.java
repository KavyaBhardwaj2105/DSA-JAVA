// LeetCode 100 - Same Tree

class LC100SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both nodes are null, so this pair is equal.
        if (p == null && q == null) {
            return true;
        }

        // One node is null and the other is not.
        if (p == null || q == null) {
            return false;
        }

        // Corresponding node values must match.
        if (p.val != q.val) {
            return false;
        }

        // Both corresponding subtrees must also be identical.
        return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}

/*
 * Logic:
 * Perform DFS on both trees simultaneously and compare corresponding nodes.
 *
 * 3-Step Implementation Check:
 * 1. Store: No collection is required; the recursive call returns a boolean.
 * 2. Check when: Compare each pair of corresponding nodes before going deeper.
 * 3. Operation: Both left and right subtree comparisons must be true using &&.
 *
 * Base cases:
 * - Both null -> true
 * - Exactly one null -> false
 * - Different values -> false
 *
 * Complexity:
 * Time: O(N) in the worst case.
 * Space: O(H) recursion stack, where H is the tree height.
 */