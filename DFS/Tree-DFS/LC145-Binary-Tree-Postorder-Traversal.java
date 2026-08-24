// LeetCode 145 - Binary Tree Postorder Traversal

import java.util.ArrayList;
import java.util.List;

class LC145BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        // Postorder: Left -> Right -> Root
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }
}

/*
 * Logic:
 * Postorder DFS processes the current node only after both its left and
 * right subtrees have been completely traversed.
 *
 * 3-Step Implementation Check:
 * 1. Store: result list stores node values in postorder sequence.
 * 2. Update when: after completing both left and right subtree traversals.
 * 3. Operation: result.add(root.val).
 *
 * Complexity:
 * Time: O(N)
 * Space: O(H) recursion stack, where H is tree height.
 */