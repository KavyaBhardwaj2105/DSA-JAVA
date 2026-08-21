// LeetCode 841 - Keys and Rooms

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class LC841KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(0);
        visited[0] = true;
        int visitedCount = 1;

        while (!q.isEmpty()) {
            int room = q.poll();

            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    visitedCount++;
                    q.offer(key);
                }
            }
        }

        return visitedCount == n;
    }
}

/*
 * Logic:
 * Treat each room as a graph node and each key as a directed edge from
 * the current room to the room that the key opens.
 * Room 0 is the only initially accessible room, so it is the BFS source.
 *
 * BFS visits every room reachable from room 0. If the number of visited
 * rooms equals the total number of rooms, every room can be opened.
 *
 * Algorithm:
 * 1. Create visited[] and a queue.
 * 2. Start BFS from room 0 and mark it visited.
 * 3. Remove a room from the queue.
 * 4. Examine every key in that room.
 * 5. For each unvisited room unlocked by a key, mark it visited and enqueue it.
 * 6. After BFS ends, compare the number of visited rooms with n.
 *
 * Pattern:
 * Single-source graph BFS + reachability.
 *
 * Time Complexity: O(V + E), where V is the number of rooms and E is the
 * total number of keys/edges.
 * Space Complexity: O(V) for visited[] and the queue.
 */