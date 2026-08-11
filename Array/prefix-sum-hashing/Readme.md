# Prefix Sum + Hashing — DSA with Java

Prefix Sum and Hashing are two techniques that become extremely powerful when combined.

The main idea is:

> Store information about what has already appeared so that we can answer subarray/range questions without repeatedly recalculating everything.

This combination is especially useful for:

- Subarray Sum
- Counting subarrays
- Equal 0s and 1s
- Frequency/state tracking
- Finding previous prefix states
- Converting O(n²) solutions into O(n)

---

# 1. What is Prefix Sum?

Prefix Sum stores the cumulative sum of elements up to a particular index.

Example:

    Array:
    [2, 4, 3, 5]

Prefix Sum:

    [2, 6, 9, 14]

Meaning:

    prefix[0] = 2

    prefix[1] = 2 + 4 = 6

    prefix[2] = 2 + 4 + 3 = 9

    prefix[3] = 2 + 4 + 3 + 5 = 14

---

# 2. WHY Prefix Sum?

Suppose we repeatedly need the sum of different subarrays.

Example:

    [2, 4, 3, 5, 6]

If we calculate every subarray sum from scratch, we repeat work.

Prefix Sum allows us to reuse previous information.

For a range:

    l → r

the range sum can be derived from prefix information instead of traversing the entire range again.

The core idea is:

> Convert repeated range calculations into constant-time calculations after preprocessing.

---

# 3. Prefix Sum + HashMap

Prefix Sum becomes much more powerful when combined with a HashMap.

Instead of only storing prefix sums, we can store:

    prefixSum → frequency

or:

    prefixState → earliest index

depending on the problem.

This allows us to quickly ask:

> Have I seen the prefix state that I need before?

Average HashMap lookup:

    O(1)

This is what often gives us an overall:

    O(n)

solution.

---

# 4. The Core Mathematical Idea

Suppose:

    prefix[j] - prefix[i] = target

Then:

    prefix[i] = prefix[j] - target

So while processing the current prefix:

    currentPrefix = prefix[j]

we can ask:

> Have I previously seen `currentPrefix - target`?

If yes, a subarray with sum `target` exists between those positions.

This is the foundation of many Prefix Sum + HashMap problems.

---

# 5. The Three-Step Implementation Check

Before coding:

## 1. What needs to be stored?

Usually:

    Prefix Sum
    +
    HashMap of previous prefix states

---

## 2. When should it be updated?

As we traverse the array:

    Update prefix sum

Then:

    Check required previous state

Then:

    Update HashMap

The exact order matters.

---

## 3. What update operation is needed?

Depending on the problem:

    frequency++

or:

    store earliest index

The problem determines which one is required.

---

# 6. LeetCode 560 — Subarray Sum Equals K

## Problem

Given an integer array and an integer `k`, find the total number of continuous subarrays whose sum equals `k`.

---

## Example

    nums = [1,1,1]
    k = 2

Valid subarrays:

    [1,1]
    [1,1]

Answer:

    2

---

# 7. Brute Force Thinking

One approach:

    Start from every index
    Calculate sums of all subarrays starting there

This can take:

    O(n²)

because many subarrays must be considered.

---

# 8. WHY Prefix Sum + HashMap?

Suppose current prefix sum is:

    currentPrefix

We need a previous prefix:

    previousPrefix

such that:

    currentPrefix - previousPrefix = k

Rearrange:

    previousPrefix = currentPrefix - k

Therefore, for every current prefix sum, we ask:

> How many times have I already seen `currentPrefix - k`?

If it has appeared `x` times, then there are `x` subarrays ending at the current position whose sum is `k`.

This is why we store **frequency**, not just existence.

---

# 9. WHAT needs to be stored?

HashMap:

    prefixSum → frequency

Also maintain:

    currentPrefixSum

---

# 10. HOW does the algorithm work?

For every element:

### Step 1

Update:

    currentPrefixSum

### Step 2

Calculate:

    required = currentPrefixSum - k

### Step 3

Check how many times `required` appeared previously.

If:

    required exists

then those occurrences represent valid starting points.

Add that frequency to the answer.

### Step 4

Store the current prefix sum:

    frequency[currentPrefixSum]++

---

# 11. Why do we initialize prefix sum 0?

This is a very important detail.

We conceptually begin with:

    prefixSum = 0

with frequency:

    1

Why?

Consider:

    nums = [3]
    k = 3

Current prefix:

    3

Required:

    3 - 3 = 0

We need to know that prefix `0` existed before the array started.

This represents a subarray beginning at index `0`.

Therefore:

    frequency[0] = 1

is essential.

---

# 12. Why Frequency Instead of Boolean?

Suppose the same prefix sum appears multiple times.

Example:

    prefix sums:
    0, 2, 2, 2

If we only store:

    2 → exists

we lose information.

But if:

    2 → frequency 3

then a current prefix can use all three previous occurrences as possible starting points.

Therefore:

> LC 560 requires frequency counting, not simple presence checking.

---

# 13. Complexity — LC 560

Each element is processed once.

HashMap operations are average O(1).

Therefore:

Time:

    O(n)

Space:

    O(n)

because in the worst case we may store O(n) distinct prefix sums.

---

# 14. LeetCode 525 — Contiguous Array

## Problem

Given a binary array containing only `0` and `1`, find the maximum length of a contiguous subarray containing equal numbers of `0` and `1`.

---

# 15. Example

    nums = [0,1]

The entire array contains:

    0 → 1
    1 → 1

Therefore:

    Answer = 2

---

# 16. The Key Trick

At first glance, this looks different from a normal Prefix Sum problem.

The trick is to transform the values.

Treat:

    0 → -1

and:

    1 → +1

Now the problem becomes:

> Find the longest subarray whose transformed sum is zero.

Why?

Because:

    equal number of 0s and 1s

means:

    (+1) + (+1) + (-1) + (-1) = 0

So the problem becomes a Prefix Sum problem.

---

# 17. WHY HashMap?

Suppose the same prefix sum occurs at two different indices:

    prefix[i] = prefix[j]

Then:

    prefix[j] - prefix[i] = 0

Therefore the subarray between those positions has sum zero.

After the transformation:

    zero sum
        ↓
    equal number of 0s and 1s

So repeated prefix states tell us where a valid subarray exists.

---

# 18. WHAT needs to be stored?

HashMap:

    prefixSum → earliest index

We store the **first occurrence** of every prefix sum.

---

# 19. WHY Earliest Index?

The problem asks for:

    Maximum length

Suppose the same prefix sum occurs at:

    index 2
    index 7

If we want the longest subarray ending at index 10:

Using index 2 gives:

    10 - 2

Using index 7 gives:

    10 - 7

The earlier index gives the larger length.

Therefore:

> For LC 525, store the earliest occurrence of each prefix sum.

---

# 20. Initial State in LC 525

Again, initialize:

    prefixSum = 0

at:

    index = -1

Why?

Consider:

    nums = [0,1]

After transformation:

    [-1,+1]

Prefix sums:

    -1
     0

When prefix becomes `0` at index `1`, the subarray from index `0` to `1` has sum zero.

Using:

    1 - (-1) = 2

gives the correct length.

Therefore:

    prefixSum 0 → index -1

is essential.

---

# 21. HOW LC 525 Works

For each element:

### Step 1

Transform:

    0 → -1
    1 → +1

### Step 2

Update:

    currentPrefixSum

### Step 3

Check whether the same prefix sum has appeared before.

If yes:

    currentIndex - firstOccurrenceIndex

gives the length of a zero-sum subarray.

Update the maximum length.

### Step 4

If the prefix sum has never appeared:

    Store its current index.

Do NOT overwrite the earliest index.

---

# 22. LC 560 vs LC 525

These problems look different but share the same underlying idea.

| Concept | LC 560 | LC 525 |
|---|---|---|
| Problem | Sum equals K | Equal 0s and 1s |
| Prefix state | Actual sum | Transformed sum |
| HashMap stores | Frequency | Earliest index |
| Goal | Count subarrays | Maximum length |
| Required state | `prefix - k` | Same prefix |
| Initial state | `0 → 1` | `0 → -1` |
| Complexity | O(n) | O(n) |

This comparison is extremely important.

---

# 23. The Deeper Pattern

Both problems use:

    Prefix State
          ↓
    Previously Seen State
          ↓
    HashMap
          ↓
    Current Answer

But the stored information changes according to the question.

### If the problem asks:

    "How many?"

Store:

    Frequency

Example:

    LC 560

---

### If the problem asks:

    "Maximum length?"

Store:

    Earliest index

Example:

    LC 525

This is a very useful interview-level distinction.

---

# 24. Prefix Sum State

Prefix Sum does not always have to mean:

    sum of original values

Sometimes we transform the input into a state.

For LC 525:

    0 → -1
    1 → +1

Then:

    Prefix Sum

becomes the state representing the balance between 0s and 1s.

This idea generalizes to many problems.

---

# 25. Prefix State Recognition

When reading a problem, ask:

> Can I represent the information I care about as a running state?

Examples:

    Running Sum
    Running Balance
    Running Frequency State
    Difference
    Count Difference

Then ask:

> Have I seen this state before?

If yes:

    HashMap

may be useful.

---

# 26. Common Mistakes

## Mistake 1 — Forgetting the initial prefix state

LC 560:

    prefix 0 → frequency 1

LC 525:

    prefix 0 → index -1

These are not arbitrary.

They represent the state before processing the first element.

---

## Mistake 2 — Storing the wrong thing

For LC 560:

    Need frequency

For LC 525:

    Need earliest index

Using the wrong stored information produces incorrect answers.

---

## Mistake 3 — Overwriting earliest index in LC 525

If a prefix sum already exists:

    Do not replace its earliest index.

The earliest index gives the longest possible subarray.

---

## Mistake 4 — Checking after updating the wrong state

The order matters.

Conceptually:

    Update current prefix
          ↓
    Check required previous state
          ↓
    Update stored state

Changing the order carelessly can create incorrect counts.

---

## Mistake 5 — Thinking Prefix Sum only works with positive numbers

Prefix Sum + HashMap works with:

- Positive numbers
- Negative numbers
- Zero
- Mixed values

The HashMap tracks prefix states; it does not require the array to be sorted.

---

# 27. Prefix Sum vs Sliding Window

This distinction matters.

## Sliding Window

Usually works when:

    Window is continuous
    +
    Condition can be maintained while moving pointers

## Prefix Sum + Hashing

Useful when:

    We need previous prefix information
    +
    We need exact sum/state relationships

For example, LC 560 can contain negative numbers.

A normal Sliding Window approach is not generally valid there because the sum does not behave monotonically.

Prefix Sum + HashMap handles it correctly.

---

# 28. Prefix Sum vs Brute Force

Brute Force:

    Enumerate subarrays
        ↓
    Calculate their sums
        ↓
    O(n²)

Prefix Sum + HashMap:

    Maintain running prefix
        ↓
    Search for required previous state
        ↓
    O(n) average

The optimization comes from replacing repeated subarray calculations with stored prefix information.

---

# 29. Problem Recognition Checklist

When you see a subarray problem, ask:

### 1. Is the range continuous?

If yes:

    Think subarray / prefix sum / sliding window.

### 2. Is the problem asking for exact sum?

Think:

    Prefix Sum + HashMap

### 3. Is it asking how many subarrays?

Think:

    Prefix Sum + Frequency Map

### 4. Is it asking for maximum length?

Think:

    Prefix State + Earliest Index

### 5. Can the values be negative?

If yes:

    Be careful with Sliding Window.

Prefix Sum + HashMap may be more appropriate.

### 6. Can I transform the values?

Example:

    0 → -1
    1 → +1

This can turn a balance problem into a zero-sum problem.

---

# 30. Complexity Summary

| Problem | Pattern | Time | Space |
|---|---|---:|---:|
| LC 560 | Prefix Sum + Frequency Map | O(n) | O(n) |
| LC 525 | Prefix Sum + Earliest Index | O(n) | O(n) |

HashMap operations are considered average O(1).

---

# 31. Problems Covered

## Prefix Sum + Hashing

- [x] 560 — Subarray Sum Equals K
- [x] 525 — Contiguous Array

---

# 32. Interview-Level Questions

I should be able to answer these without looking at notes:

1. What is Prefix Sum?
2. Why is Prefix Sum useful?
3. Why combine Prefix Sum with HashMap?
4. Explain the equation `currentPrefix - previousPrefix = target`.
5. Why does LC 560 use `currentPrefix - k`?
6. Why does LC 560 store frequencies?
7. Why is prefix sum `0` initialized with frequency `1` in LC 560?
8. Why does LC 525 transform `0` into `-1`?
9. Why does equal number of 0s and 1s become a zero-sum problem?
10. Why does LC 525 store the earliest index?
11. Why is the initial index `-1` in LC 525?
12. Why should the earliest index not be overwritten?
13. What is the difference between storing frequency and storing earliest index?
14. Can Prefix Sum + HashMap handle negative numbers?
15. Why can Sliding Window fail when negative numbers are present?
16. How do you recognize a Prefix Sum problem?
17. What is a prefix state?
18. Can the prefix state be something other than a normal sum?
19. What is the difference between Prefix Sum and Sliding Window?
20. Why are both LC 560 and LC 525 O(n)?
21. What happens if the same prefix sum occurs multiple times?
22. How would you modify the approach if the problem asked for count instead of maximum length?
23. How would you modify the approach if the problem asked for shortest length?
24. Why does the initial prefix state represent the portion before index 0?
25. What information should be stored before coding a Prefix Sum + HashMap problem?

---

# 33. Three-Step Interview Reasoning

Before coding, explicitly answer:

### WHAT?

What state am I maintaining?

    Prefix Sum?
    Balance?
    Difference?
    Frequency?

### WHY?

Why does a previous occurrence of this state give the answer?

### HOW?

What should the HashMap store?

    Frequency?
    Earliest index?
    Latest index?

This prevents blindly applying a memorized template.

---

# 34. Final Mental Model

Think:

    Current Prefix State
            ↓
    What previous state do I need?
            ↓
    Have I seen it before?
            ↓
        HashMap
            ↓
    Calculate answer

For counting:

    Store frequency

For maximum length:

    Store earliest index

The real skill is not memorizing the code.

The real skill is identifying:

> What prefix state would make the current subarray satisfy the required condition?

---

# 35. Mastery Standard

Prefix Sum + Hashing is not considered mastered just because LC 560 and LC 525 are solved.

I should be able to:

- Explain Prefix Sum from first principles.
- Derive the prefix-sum equation.
- Recognize when HashMap is required.
- Decide whether to store frequency or index.
- Handle negative numbers.
- Understand initial prefix states.
- Transform a problem into a prefix-state problem.
- Explain why the algorithm is O(n).
- Handle duplicates and repeated prefix states.
- Identify edge cases.
- Solve unfamiliar Prefix Sum variations.
- Explain the reasoning before writing code.

Target:

**5–10+ representative problems covering counting, longest/shortest subarrays, negative values, transformed prefix states, and HashMap-based prefix state tracking.**