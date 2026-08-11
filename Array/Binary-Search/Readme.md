# Binary Search — DSA with Java

Binary Search is not just a searching algorithm. The real skill is recognizing when a problem has an **ordered or monotonic search space** and eliminating half of that search space at every step.

---

# 1. Core Idea

Binary Search works by repeatedly dividing the search space into two halves.

Instead of checking every element:

    O(n)

we eliminate half of the possibilities after every comparison:

    O(log n)

Basic idea:

    Search Space
    [------------------------]
              ↓
            mid
          /     \
       left     right

If the answer cannot exist in one half, eliminate that half.

---

# 2. WHY Binary Search?

Suppose:

    arr = [1, 3, 5, 7, 9, 11, 13]

We want to find `9`.

Linear Search:

    1 → 3 → 5 → 7 → 9

Potentially O(n).

Binary Search:

    Check middle
        ↓
    Eliminate half
        ↓
    Check remaining middle
        ↓
    Eliminate half again

Time:

    O(log n)

The important optimization is:

> We are not searching faster by moving faster. We are searching faster by eliminating impossible possibilities.

---

# 3. WHAT needs to be maintained?

For standard Binary Search:

    left
    right
    mid

The search space is:

    [left ........ right]

Middle:

    mid = left + (right - left) / 2

Using this form avoids potential integer overflow compared with:

    (left + right) / 2

---

# 4. Standard Binary Search

## LeetCode 704 — Binary Search

### Problem

Given a sorted array and a target, return the target's index.

If the target does not exist, return `-1`.

### WHY Binary Search?

The array is sorted.

Because of the sorted order, after checking the middle element we know which half can still contain the target.

### Logic

If:

    arr[mid] == target

Answer found.

If:

    arr[mid] < target

Target must be on the right.

Therefore:

    left = mid + 1

If:

    arr[mid] > target

Target must be on the left.

Therefore:

    right = mid - 1

### Complexity

Time:

    O(log n)

Space:

    O(1)

### Core Lesson

The key condition is not "the array is an array."

The key condition is:

> The search space is ordered, so one half can be eliminated.

---

# 5. Search Insert Position

## LeetCode 35 — Search Insert Position

### Problem

Given a sorted array and a target, return:

- the target's existing index, or
- the index where it should be inserted to maintain sorted order.

### WHY is this different from LC 704?

In LC 704, we only care whether the target exists.

Here, even if the target does not exist, we need to determine its correct position.

Therefore, we cannot simply stop after failing to find the target.

We need to reason about the final search boundary.

### Important Concept

At the end of Binary Search:

    left

represents the position where the target can be inserted.

This is a major Binary Search concept.

### Example

    arr = [1, 3, 5, 6]
    target = 2

Correct insertion position:

    [1, 2, 3, 5, 6]
       ↑
       1

Answer:

    1

### Complexity

Time:

    O(log n)

Space:

    O(1)

---

# 6. Search in Rotated Sorted Array

## LeetCode 33 — Search in Rotated Sorted Array

### Problem

A sorted array has been rotated.

Example:

    Original:
    [0,1,2,4,5,6,7]

    Rotated:
    [4,5,6,7,0,1,2]

Find the target in O(log n).

### WHY can Binary Search still work?

The entire array is no longer sorted.

But there is an important property:

> At least one half of the current search space is always sorted.

Example:

    [4,5,6,7,0,1,2]
     --------
     sorted half

or:

    [4,5,6,7,0,1,2]
             ------
             sorted half

### Core Reasoning

At every iteration:

1. Find `mid`.
2. Determine which half is sorted.
3. Check whether the target lies inside that sorted half.
4. If yes, search there.
5. Otherwise, eliminate that half and search the other side.

### Core Question

Do not ask:

> "Where is the target?"

Ask:

> "Which half is sorted, and can the target exist inside that sorted range?"

That is the actual trick.

### Complexity

Time:

    O(log n)

Space:

    O(1)

---

# 7. Binary Search on Answer

This is one of the most important Binary Search concepts.

The search space does not always have to be an array.

Sometimes we are searching for:

- minimum possible value
- maximum possible value
- minimum capacity
- minimum speed
- minimum number of days
- maximum feasible distance

The search space becomes a range of possible answers.

---

# 8. The Core Idea of Binary Search on Answer

Suppose we have possible answers:

    1 2 3 4 5 6 7 8 9 10

For each candidate answer, we ask:

    Is this answer possible?

The result often looks like:

    NO NO NO NO YES YES YES YES

This is called a:

**Monotonic condition**

Once the answer becomes feasible, all larger values may also be feasible.

Or:

    YES YES YES NO NO NO

for the opposite type of problem.

That monotonicity allows Binary Search.

---

# 9. LeetCode 875 — Koko Eating Bananas

### Problem

Koko has piles of bananas and has `h` hours.

Find the minimum eating speed that allows her to finish all bananas within `h` hours.

### WHY Binary Search?

The possible eating speed lies within a range:

    1 → maximum pile

We are not searching an array.

We are searching the **answer space**.

For a candidate speed `k`, calculate:

    total hours required

Then ask:

    Can Koko finish within h hours?

### Monotonic Behaviour

If speed `k` works:

    k + 1
    k + 2
    k + 3

will also work.

So:

    slow speeds → impossible
    fast speeds → possible

Example:

    1  2  3  4  5  6  7  8
    N  N  N  N  Y  Y  Y  Y

We need the **first YES**.

### Core Concept

This is:

**Binary Search on Answer + Feasibility Check**

### Complexity

Let:

    n = number of piles
    M = maximum pile size

Each feasibility check costs:

    O(n)

Binary Search performs approximately:

    O(log M)

checks.

Total:

    O(n log M)

Space:

    O(1)

---

# 10. LeetCode 1011 — Capacity To Ship Packages Within D Days

### Problem

Find the minimum ship capacity required to ship all packages within a given number of days.

### WHY Binary Search?

Possible capacity lies between:

    maximum package weight
        ↓
    sum of all package weights

Why?

Minimum possible capacity:

    max(weights)

because the ship must at least carry the heaviest package.

Maximum possible capacity:

    sum(weights)

because with that capacity everything could theoretically be shipped in one day.

### Search Space

    [maxWeight ........ sumWeight]

For each candidate capacity:

    Simulate shipping.

Then ask:

    Can all packages be shipped within D days?

### Monotonic Behaviour

Small capacity:

    impossible

Large enough capacity:

    possible

Therefore:

    N N N N Y Y Y Y

We need the first feasible capacity.

### Core Pattern

    Search Answer
        ↓
    Feasibility Function
        ↓
    First Valid Answer

### Complexity

Let:

    S = sum of weights
    n = number of packages

Each feasibility check:

    O(n)

Binary Search:

    O(log S)

Total:

    O(n log S)

Space:

    O(1)

---

# 11. LeetCode 1283 — Find the Smallest Divisor Given a Threshold

### Problem

Find the smallest divisor such that the sum of rounded-up divisions is less than or equal to the threshold.

### WHY Binary Search?

Possible divisor:

    1 → maximum element

For every divisor, we can check whether it satisfies the threshold.

### Monotonic Behaviour

Smaller divisor:

    larger sum

Larger divisor:

    smaller sum

Eventually:

    invalid invalid invalid valid valid valid

We need the **smallest valid divisor**.

### Core Pattern

Again:

    Binary Search on Answer
    +
    Feasibility Check
    +
    Find First Valid Value

### Complexity

Let:

    n = number of elements
    M = maximum element

Time:

    O(n log M)

Space:

    O(1)

---

# 12. LeetCode 1482 — Minimum Number of Days to Make m Bouquets

### Problem

Find the minimum day on which it is possible to make `m` bouquets using `k` adjacent flowers.

### WHY Binary Search?

The possible answer is a day.

Search space:

    minimum bloom day → maximum bloom day

For a candidate day:

    Can we make at least m bouquets?

### Monotonic Behaviour

If we can make the required bouquets on day `d`, then we can also make them on every later day.

Therefore:

    impossible impossible impossible possible possible possible

We need the first possible day.

### Important Detail

This problem contains another challenge:

The flowers used for one bouquet must be:

**adjacent**

So the feasibility check must correctly track consecutive bloomed flowers.

### Core Pattern

    Binary Search on Answer
            +
    Greedy Feasibility Check

### Complexity

Let:

    n = number of flowers
    M = maximum bloom day

Time:

    O(n log M)

Space:

    O(1)

---

# 13. LeetCode 410 — Split Array Largest Sum

### Problem

Split an array into `k` non-empty continuous subarrays such that the largest subarray sum is minimized.

### Why Binary Search?

We are not directly searching for the split.

We are searching for:

> What is the minimum possible value of the largest subarray sum?

Possible answer range:

    maximum element
            ↓
    sum of entire array

For a candidate maximum allowed sum:

    Can we split the array into at most k parts?

### Monotonic Behaviour

If a maximum sum `X` is possible, then every larger maximum sum is also possible.

Therefore:

    impossible impossible possible possible possible

Find the first possible value.

### Feasibility Logic

Traverse the array and keep adding elements to the current subarray.

If adding the next element would exceed the candidate maximum:

    start a new subarray

Count how many parts are required.

Then:

    parts <= k
        → feasible

    parts > k
        → not feasible

### Complexity

Let:

    n = array size
    S = sum of elements

Time:

    O(n log S)

Space:

    O(1)

---

# 14. LeetCode 1552 — Magnetic Force Between Two Balls

### Problem

Place `m` balls in positions such that the minimum distance between any two balls is maximized.

### WHY Binary Search?

We are searching for:

**maximum possible minimum distance**

Possible distance:

    1 → maximum position difference

For a candidate distance `d`, ask:

> Can we place at least m balls such that every consecutive chosen ball is at least d apart?

### Greedy Feasibility

Sort positions first.

Place the first ball at the earliest possible position.

Then repeatedly place the next ball at the earliest position satisfying:

    currentPosition - lastPlacedPosition >= d

If we can place at least `m` balls:

    d is feasible

Otherwise:

    d is impossible

### Monotonic Behaviour

If distance `d` is possible, every smaller distance is also possible.

Therefore:

    YES YES YES YES NO NO NO

We want the **maximum YES**.

### Core Pattern

This problem combines:

    Sorting
    +
    Greedy
    +
    Binary Search on Answer

### Complexity

Sorting:

    O(n log n)

Binary Search:

    O(log M)

Feasibility check:

    O(n)

Overall:

    O(n log n + n log M)

Space:

    O(1) auxiliary
    depending on sorting implementation

---

# 15. The Most Important Binary Search Concept

Binary Search has two major forms.

## Form 1 — Search in a Sorted Structure

Examples:

    LC 704
    LC 35
    LC 33

We search for an element/position.

---

## Form 2 — Search on Answer

Examples:

    LC 875
    LC 1011
    LC 1283
    LC 1482
    LC 410
    LC 1552

We search through possible answers.

The common structure is:

    low
      ↓
    search space
      ↓
    high

For every `mid`:

    Is mid feasible?

Then eliminate half.

---

# 16. First Valid vs Last Valid

This is an extremely important interview concept.

### First Valid

Pattern:

    NO NO NO YES YES YES

We want:

    first YES

Examples:

    LC 875
    LC 1011
    LC 1283
    LC 1482
    LC 410

---

### Last Valid

Pattern:

    YES YES YES NO NO NO

We want:

    last YES

Example:

    LC 1552

The exact implementation depends on which boundary the problem asks for.

---

# 17. Binary Search on Answer — Universal Template

Do not memorize code. Understand the structure:

    Define search space

        ↓

    Find mid

        ↓

    Check whether mid is feasible

        ↓

    If feasible:
        move toward better answer

    Else:
        eliminate impossible side

        ↓

    Continue until search space collapses

The hardest part is usually NOT Binary Search itself.

The difficult part is:

> Designing the correct feasibility function.

---

# 18. How to Identify Binary Search on Answer

When reading a problem, ask:

### Question 1

Is the answer numeric?

Examples:

    speed
    capacity
    days
    divisor
    distance
    maximum sum

### Question 2

Can I define a range of possible answers?

Example:

    minimum possible answer → maximum possible answer

### Question 3

Can I test one candidate answer?

    Can X work?

### Question 4

Is the result monotonic?

Example:

    NO NO NO YES YES YES

or:

    YES YES YES NO NO NO

If all four are true:

**Think Binary Search on Answer.**

---

# 19. Binary Search Reasoning Checklist

Before writing code:

### 1. What is my search space?

Array indices?

Or possible answers?

### 2. What is `low`?

The smallest possible answer.

### 3. What is `high`?

The largest possible answer.

### 4. What does `mid` represent?

A candidate answer.

### 5. What is my feasibility condition?

Can `mid` actually work?

### 6. What happens if `mid` works?

Do I want a smaller answer or larger answer?

### 7. What happens if `mid` fails?

Which half becomes impossible?

---

# 20. Common Mistakes

## Mistake 1 — Applying Binary Search just because an array exists

An array does NOT automatically mean Binary Search.

There must be:

    ordering
    OR
    monotonicity

---

## Mistake 2 — Choosing the wrong search boundaries

For answer-based problems, boundaries must represent the actual minimum and maximum possible answer.

---

## Mistake 3 — Wrong feasibility condition

Binary Search may be perfectly implemented but still produce a wrong answer if:

    can(mid)

is incorrect.

This is one of the most common mistakes in Binary Search on Answer.

---

## Mistake 4 — Confusing first valid and last valid

Always determine:

    first YES?

or:

    last YES?

before deciding how to move the boundaries.

---

## Mistake 5 — Forgetting overflow

For large values, avoid blindly using:

    (low + high) / 2

Prefer:

    low + (high - low) / 2

Also consider whether sums can exceed the `int` range.

---

# 21. Complexity Summary

| Problem | Pattern | Time |
|---|---|---:|
| LC 704 | Standard Binary Search | O(log n) |
| LC 35 | Search Insert Position | O(log n) |
| LC 33 | Rotated Sorted Array | O(log n) |
| LC 875 | Binary Search on Answer | O(n log M) |
| LC 1011 | Binary Search on Answer | O(n log S) |
| LC 1283 | Binary Search on Answer | O(n log M) |
| LC 1482 | Binary Search on Answer | O(n log M) |
| LC 410 | Binary Search on Answer | O(n log S) |
| LC 1552 | Sort + Binary Search + Greedy | O(n log n + n log M) |

Where:

    n = input size
    M = maximum relevant value
    S = sum of relevant values

---

# 22. Problems Covered

### Standard Binary Search

- [x] 704 — Binary Search
- [x] 35 — Search Insert Position
- [x] 33 — Search in Rotated Sorted Array

### Binary Search on Answer

- [x] 875 — Koko Eating Bananas
- [x] 1011 — Capacity To Ship Packages Within D Days
- [x] 1283 — Find the Smallest Divisor Given a Threshold
- [x] 1482 — Minimum Number of Days to Make m Bouquets
- [x] 410 — Split Array Largest Sum
- [x] 1552 — Magnetic Force Between Two Balls

---

# 23. Interview-Level Questions

I should be able to answer these without looking at notes:

1. Why does Binary Search require ordering or monotonicity?
2. Why is Binary Search O(log n)?
3. Why do we use `low + (high-low)/2`?
4. What is the difference between standard Binary Search and Binary Search on Answer?
5. How do you identify Binary Search on Answer?
6. What is a monotonic predicate?
7. What does "first valid answer" mean?
8. What does "last valid answer" mean?
9. How do you determine the search boundaries?
10. Why is the feasibility function often more difficult than Binary Search itself?
11. Why does LC 33 still allow O(log n) despite the array being rotated?
12. Why is one half always sorted in LC 33?
13. Why does LC 875 use `max(pile)` as the upper bound?
14. Why does LC 1011 use `max(weights)` as the lower bound?
15. Why does LC 410 use `sum(array)` as the upper bound?
16. Why does LC 1552 require sorting?
17. Why does the greedy feasibility check work in LC 1552?
18. Why does LC 1482 require tracking consecutive flowers?
19. How can a Binary Search solution be O(n log M) instead of O(log n)?
20. What would make Binary Search invalid for a problem?

---

# 24. Final Mental Model

Do not memorize:

    "This problem uses Binary Search."

Instead ask:

    What is my search space?
            ↓
    Is it ordered or monotonic?
            ↓
    What does mid represent?
            ↓
    Can I test mid?
            ↓
    Is the result monotonic?
            ↓
    Do I need first valid or last valid?
            ↓
    Eliminate half.

That is the actual Binary Search skill.

---

# 25. Mastery Standard

Binary Search is considered mastered only when I can:

- Recognize standard Binary Search.
- Recognize Binary Search on Answer.
- Define valid search boundaries.
- Build a correct feasibility function.
- Identify monotonicity.
- Handle first-valid and last-valid problems.
- Explain why half the search space can be eliminated.
- Derive complexity.
- Handle overflow and edge cases.
- Solve unfamiliar variations without memorizing a template.

The goal is not to memorize 704, 875, 1011, 1283, 1482, 410 and 1552 individually.

The goal is to recognize the **common structure behind all of them**.