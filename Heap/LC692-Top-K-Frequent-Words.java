import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        // 1. Frequency count
        HashMap<String, Integer> map = new HashMap<>();

        // 2. Min-Heap
        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                // comparator
            }
        );

        // 3. Map ke elements heap mein daalo
        for (String word : map.keySet()) {
            pq.offer(word);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        // 4. Heap se result nikalo
        List<String> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        // 5. Required order
        Collections.reverse(result);

        return result;
    }
}
