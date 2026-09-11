class Solution {
    public int minMeetingRooms(int[][] intervals) {

        // Sort meetings by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Min Heap stores ending times of ongoing meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : intervals) {

            int start = meeting[0];
            int end = meeting[1];

            // If a room is free, reuse it
            if (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll();
            }

            // Occupy a room for this meeting
            minHeap.offer(end);
        }

        // Number of rooms required
        return minHeap.size();
    }
}
