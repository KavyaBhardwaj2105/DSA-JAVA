// LeetCode 1926 - Nearest Exit from Entrance in Maze

import java.util.ArrayDeque;
import java.util.Queue;

class LC1926NearestExitFromEntranceInMaze {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{entrance[0], entrance[1]});
        maze[entrance[0]][entrance[1]] = '+';

        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                        continue;
                    }

                    if (maze[newRow][newCol] != '.') {
                        continue;
                    }

                    if (newRow == 0 || newRow == rows - 1
                            || newCol == 0 || newCol == cols - 1) {
                        return distance;
                    }

                    maze[newRow][newCol] = '+';
                    q.offer(new int[]{newRow, newCol});
                }
            }
        }

        return -1;
    }
}

/*
 * Logic:
 * Start BFS from the entrance. Every BFS level represents one step.
 * The first boundary cell reached is the nearest exit.
 * The entrance itself cannot be an exit, so we only check boundary status
 * for newly discovered cells.
 *
 * The maze is modified in place to mark visited cells.
 *
 * Algorithm:
 * 1. Put the entrance into the queue and mark it visited.
 * 2. Process the queue level by level.
 * 3. Explore the four neighbouring cells.
 * 4. Ignore walls, visited cells, and out-of-bounds positions.
 * 5. If a newly discovered open cell is on the boundary, return the distance.
 * 6. Otherwise mark it visited and enqueue it.
 * 7. If BFS ends without finding an exit, return -1.
 *
 * Complexity:
 * Time: O(R * C)
 * Space: O(R * C)
 */