import java.util.*;

class Solution {
    public String frequencySort(String s) {

        // 1. Store character frequencies
        HashMap<Character, Integer> freq = new HashMap<>();

        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // 2. Max Heap based on frequency
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
            (a, b) -> freq.get(b) - freq.get(a)
        );

        // 3. Add all characters to heap
        for (char ch : freq.keySet()) {
            maxHeap.offer(ch);
        }

        // 4. Build answer
        StringBuilder ans = new StringBuilder();

        while (!maxHeap.isEmpty()) {

            char ch = maxHeap.poll();

            int count = freq.get(ch);

            while (count > 0) {
                ans.append(ch);
                count--;
            }
        }

        return ans.toString();
    }
}