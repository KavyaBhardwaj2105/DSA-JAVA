// LeetCode 1926 - Nearest Exit from Entrance in Maze

import java.util.*;

/*
 * Logic:
 * BFS finds the nearest exit because every move has equal cost.
 * Start from the entrance and explore the maze level by level.
 * The first boundary cell reached (excluding the entrance itself) is the
 * nearest exit.
 *
 * Algorithm:
 * 1. Add the entrance to the queue and mark it visited.
 * 2. Process cells level by level.
 * 3. Explore the four neighbouring cells.
 * 4. Ignore walls and already visited cells.
 * 5. If a newly reached cell is on the boundary, return the current step count.
 * 6. If no exit is found, return -1.
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */

class LC1926NearestExitFromEntranceInMaze {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{entrance[0], entrance[1]});
        maze[entrance[0]][entrance[1]] = '+';

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        maze[newRow][newCol] == '.') {

                        if (newRow == 0 || newRow == rows - 1 ||
                            newCol == 0 || newCol == cols - 1) {
                            return steps;
                        }

                        maze[newRow][newCol] = '+';
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return -1;
    }
}