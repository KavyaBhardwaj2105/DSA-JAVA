// LeetCode 144 - Binary Tree Preorder Traversal

import java.util.ArrayList;
import java.util.List;

class LC144BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        // Preorder: Root -> Left -> Right
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }
}

/*
 * Logic:
 * DFS explores as deeply as possible. In preorder traversal, the current
 * node is processed before recursively exploring its left and right children.
 *
 * 3-Step Implementation Check:
 * 1. Store: result list stores the values in traversal order.
 * 2. Update when: process the current node before left/right DFS calls.
 * 3. Operation: result.add(root.val).
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack, where H is tree height.
 */