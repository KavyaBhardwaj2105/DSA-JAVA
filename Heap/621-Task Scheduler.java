import java.util.Arrays;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        int maxFreq = Arrays.stream(count).max().getAsInt();
        int maxCount = 0;
        for (int freq : count) {
            if (freq == maxFreq) {
                maxCount++;
            }
        }

        // Calculate slots needed for the most frequent tasks
        int slots = (maxFreq - 1) * (n + 1) + maxCount;

        // The result is either the calculated slots or total tasks
        return Math.max(tasks.length, slots);
    }
}