# Greedy Algorithms

Greedy algorithms make the best feasible local choice at each step with the goal of reaching a globally optimal solution.

The key skill is not memorizing a greedy trick. For every problem, we should understand **why the local choice is safe** and when the greedy strategy can fail.

## Core Pattern

**Sort / arrange → make the best local choice → update state → continue**

Common tools:
- Sorting
- Two pointers
- Intervals
- Running maximum/minimum
- Careful proof of the greedy choice

## 7-Day Learning Plan

| Day | Focus | LeetCode |
|---|---|---|
| Day 1 | Greedy fundamentals + sorting + two pointers | [455 - Assign Cookies](https://leetcode.com/problems/assign-cookies/) |
| Day 2 | Interval Greedy | [435 - Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) |
| Day 3 | Interval overlap + optimal choice | [452 - Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) |
| Day 4 | Reachability Greedy | [55 - Jump Game](https://leetcode.com/problems/jump-game/) |
| Day 5 | Minimum jumps + greedy range expansion | [45 - Jump Game II](https://leetcode.com/problems/jump-game-ii/) |
| Day 6 | Greedy simulation + proof | [134 - Gas Station](https://leetcode.com/problems/gas-station/) |
| Day 7 | Revision + pattern recognition + variants | [763 - Partition Labels](https://leetcode.com/problems/partition-labels/), [1029 - Two City Scheduling](https://leetcode.com/problems/two-city-scheduling/) |

## Day 1 — LC 455: Assign Cookies

### Pattern

**Sort both arrays + two pointers.**

- `i` → current child
- `j` → current cookie
- If `s[j] >= g[i]`, the cookie can satisfy the child: increase the answer and move both pointers.
- If `s[j] < g[i]`, the cookie is too small: discard it and move only `j`.

### Greedy Insight

If the smallest available cookie cannot satisfy the least greedy remaining child, it cannot satisfy any more greedy child either. Therefore, skipping that cookie is safe.

### Complexity

- Sorting: `O(n log n + m log m)`
- Two-pointer traversal: `O(n + m)`
- Extra space: `O(1)` apart from the sorting implementation details.

## What We Must Be Able to Explain

For every Greedy problem:
1. What is the local choice?
2. Why is that choice safe?
3. What state changes after the choice?
4. Why can discarded options never become useful later?
5. Time and space complexity.

## Progress

- [ ] Day 1 — LC 455
- [ ] Day 2 — LC 435
- [ ] Day 3 — LC 452
- [ ] Day 4 — LC 55
- [ ] Day 5 — LC 45
- [ ] Day 6 — LC 134
- [ ] Day 7 — LC 763 + LC 1029 + revision
