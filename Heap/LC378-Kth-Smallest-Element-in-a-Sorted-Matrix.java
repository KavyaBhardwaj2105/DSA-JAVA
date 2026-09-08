import java.util.PriorityQueue;

class Solution {
    public int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;

        // {value, row, column}
        PriorityQueue<int[]> minHeap =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Put first element of every row
        for (int row = 0; row < n; row++) {
            minHeap.offer(new int[]{matrix[row][0], row, 0});
        }

        int answer = 0;

        for (int i = 0; i < k; i++) {
            int[] current = minHeap.poll();

            int value = current[0];
            int row = current[1];
            int col = current[2];

            answer = value;

            // Move right in the same row
            if (col + 1 < n) {
                minHeap.offer(
                    new int[]{matrix[row][col + 1], row, col + 1}
                );
            }
        }

        return answer;
    }
}
