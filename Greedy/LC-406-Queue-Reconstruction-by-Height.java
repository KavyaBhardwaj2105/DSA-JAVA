import java.util.*;

class Solution {
    public int[][] reconstructQueue(int[][] people) {

        // Step 1: Sort
        Arrays.sort(people, (a, b) -> {

            // Height descending
            if (a[0] != b[0]) {
                return b[0] - a[0];
            }

            // Same height -> k ascending
            return a[1] - b[1];
        });

        // Step 2: Insert according to k
        List<int[]> list = new ArrayList<>();

        for (int[] person : people) {
            list.add(person[1], person);
        }

        // Step 3: Convert List to array
        return list.toArray(new int[people.length][]);
    }
}
