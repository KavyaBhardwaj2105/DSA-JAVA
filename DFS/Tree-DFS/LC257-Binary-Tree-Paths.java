// LeetCode 257 - Binary Tree Paths

class LC257BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }

        if (path.isEmpty()) {
            path = String.valueOf(root.val);
        } else {
            path = path + "->" + root.val;
        }

        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }

        dfs(root.left, path, result);
        dfs(root.right, path, result);
    }
}

/*
 * Logic:
 * Track the current root-to-node path as a String while performing DFS.
 * When a leaf is reached, add the complete path to the result list.
 *
 * 3-Step Implementation Check:
 * 1. Store: Current path String and all completed paths in List<String>.
 * 2. Update when: Visiting each non-null node.
 * 3. Operation: Append the current node value to the path.
 *
 * Explicit backtracking is not required because Java Strings are immutable;
 * each recursive call receives its own updated String value.
 *
 * Complexity:
 * Time: O(N * L) in the worst case due to path-string construction.
 * Space: O(H) recursion stack, excluding output storage.
 */