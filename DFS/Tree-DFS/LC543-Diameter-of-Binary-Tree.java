// LeetCode 543 - Diameter of Binary Tree

class LC543DiameterOfBinaryTree {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}

/*
 * Logic:
 * DFS returns the height of each subtree. At every node, the path passing
 * through that node has length left height + right height, so update the
 * global diameter with that value.
 *
 * 3-Step Implementation Check:
 * 1. Store: subtree heights through the recursive return value; global best
 *    diameter in the diameter variable.
 * 2. Update when: both left and right subtree heights are available.
 * 3. Operation: diameter = max(diameter, left + right).
 *
 * Important distinction:
 * - height is returned to the parent.
 * - diameter is the best answer found anywhere in the tree.
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack.
 */