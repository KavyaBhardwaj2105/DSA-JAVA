// LeetCode 113 - Path Sum II

class LC113PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, path, ans);

        return ans;
    }

    private void dfs(TreeNode root, int targetSum,
                     List<Integer> path,
                     List<List<Integer>> ans) {
        if (root == null) {
            return;
        }

        path.add(root.val);

        if (root.left == null && root.right == null
                && targetSum == root.val) {
            ans.add(new ArrayList<>(path));
        }

        dfs(root.left, targetSum - root.val, path, ans);
        dfs(root.right, targetSum - root.val, path, ans);

        path.remove(path.size() - 1);
    }
}

/*
 * Logic:
 * Track the current root-to-node path using a mutable List. When a valid
 * root-to-leaf path is found, add a copy of the path to the answer.
 * After exploring both children, remove the current node to restore the
 * path for the sibling branch.
 *
 * 3-Step Implementation Check:
 * 1. Store: current path and all valid paths.
 * 2. Update when: visiting a node and when a valid leaf path is found.
 * 3. Operation: add the node, recurse, then remove it for backtracking.
 *
 * Complexity:
 * Time: O(N * H) in the worst case because path copies can cost O(H).
 * Space: O(H) recursion/path state, excluding output storage.
 */