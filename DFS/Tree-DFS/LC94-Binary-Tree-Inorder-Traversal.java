// LeetCode 94 - Binary Tree Inorder Traversal

import java.util.ArrayList;
import java.util.List;

class LC94BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        // Inorder: Left -> Root -> Right
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }
}

/*
 * Logic:
 * Inorder DFS processes the current node between its left and right subtrees.
 *
 * 3-Step Implementation Check:
 * 1. Store: result list stores node values in inorder sequence.
 * 2. Update when: after completing the left subtree and before the right subtree.
 * 3. Operation: result.add(root.val).
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack, where H is tree height.
 */