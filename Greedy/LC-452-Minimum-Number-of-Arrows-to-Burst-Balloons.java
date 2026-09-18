import java.util.*;

class Solution {
    public int findMinArrowShots(int[][] points) {

        // Sort intervals by ending point
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;

        // Position of first arrow
        int arrowPosition = points[0][1];

        for (int i = 1; i < points.length; i++) {

            // Current balloon starts after arrow position
            // So current arrow cannot burst it
            if (points[i][0] > arrowPosition) {

                arrows++;
                arrowPosition = points[i][1];
            }
        }

        return arrows;
    }
}
