import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                if (i == levelSize - 1) {
                    result.add(node.val);
                }

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return result;
    }
}
