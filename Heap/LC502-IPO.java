class Solution {
    public int findMaximizedCapital(
        int k,
        int w,
        int[] profits,
        int[] capital
    ) {

        int[][] projects = new int[profits.length][2];

        for (int i = 0; i < profits.length; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        Arrays.sort(projects, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;

        for (int count = 0; count < k; count++) {

            while (i < projects.length &&
                   projects[i][0] <= w) {

                maxHeap.offer(projects[i][1]);
                i++;
            }

            if (maxHeap.isEmpty()) {
                break;
            }

            w += maxHeap.poll();
        }

        return w;
    }
}
