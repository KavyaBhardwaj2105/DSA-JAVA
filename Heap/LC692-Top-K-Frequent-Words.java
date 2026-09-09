import java.util.*;
class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(
            new Comparator<String>() {
                @Override
                public int compare(String a, String b) {

                    if (freq.get(a).equals(freq.get(b))) {
                        return b.compareTo(a);
                    }

                    return Integer.compare(freq.get(a), freq.get(b));
                }
            }
        );

        for (String word : freq.keySet()) {

            pq.offer(word);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> ans = new ArrayList<>();

        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }

        Collections.reverse(ans);

        return ans;
    }
}
