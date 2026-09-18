import java.util.*; 
 
class Solution { 
    public int eraseOverlapIntervals(int[][] intervals) { 
 
        if (intervals.length == 0) { 
            return 0; 
        } 
 
        // Sort by ending point 
        Arrays.sort(intervals, (a, b) -> 
            Integer.compare(a[1], b[1]) 
        ); 
 
        int removed = 0; 
 
        // End of the interval we are currently keeping 
        int currentEnd = intervals[0][1]; 
 
        for (int i = 1; i < intervals.length; i++) { 
 
            // Overlap 
            if (intervals[i][0] < currentEnd) { 
                removed++; 
            } 
            else { 
                // No overlap → keep this interval 
                currentEnd = intervals[i][1]; 
            } 
        } 
 
        return removed; 
    } 
}