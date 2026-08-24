// LeetCode 104 - Maximum Depth of Binary Tree

class LC104MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}

/*
 * Logic:
 * DFS recursively calculates the depth of both subtrees, takes the larger
 * depth, and adds 1 for the current node.
 *
 * 3-Step Implementation Check:
 * 1. Store: leftDepth and rightDepth.
 * 2. Update when: both child depths have been calculated.
 * 3. Operation: Math.max(leftDepth, rightDepth) + 1.
 *
 * Base case:
 * null node -> depth 0
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack, where H is tree height.
 */