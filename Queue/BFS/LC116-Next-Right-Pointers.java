import java.util.*;

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            Node previous = null;

            for (int i = 0; i < levelSize; i++) {
                Node current = q.poll();

                if (previous != null) {
                    previous.next = current;
                }

                previous = current;

                if (current.left != null) q.offer(current.left);
                if (current.right != null) q.offer(current.right);
            }
        }

        return root;
    }
}
