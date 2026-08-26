// LeetCode 236 - Lowest Common Ancestor of a Binary Tree

class LC236LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        return right;
    }
}

/*
 * Logic:
 * Recursively search both subtrees for p and q. If both sides return a
 * non-null node, the current node is their lowest common ancestor. If only
 * one side returns a node, propagate that node upward.
 *
 * 3-Step Implementation Check:
 * 1. Store: results returned by left and right DFS calls.
 * 2. Update when: both subtree searches have returned.
 * 3. Operation: combine the two results; return current root when both sides
 *    contain a target, otherwise propagate the non-null result.
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack.
 */