# Sliding Window — DSA with Java

Sliding Window is a technique used mainly for problems involving a **continuous subarray or substring**.

The central idea is:

> Instead of calculating every possible subarray/substring from scratch, maintain a window and update it as it moves.

This often converts an O(n²) brute-force approach into O(n).

---

# 1. What is Sliding Window?

A window represents a continuous range:

    [left ........ right]

Example:

    [2, 1, 5, 1, 3, 2]

A possible window:

    [2, 1, 5]

When the window moves:

    [2, 1, 5, 1]
     ↑         ↑
    left      right

Then the left side can move forward:

    [1, 5, 1]

The important property is:

> The elements inside the window are continuous.

---

# 2. WHY Sliding Window?

Suppose we need the maximum sum of every subarray of size `k`.

### Brute Force

Generate every subarray of size `k` and calculate its complete sum.

This repeatedly calculates elements that were already included in the previous window.

Potential complexity:

    O(nk)

### Sliding Window

Calculate the first window once.

When moving the window:

    Remove outgoing element
    Add incoming element

Therefore, we avoid recalculating the entire window.

Complexity:

    O(n)

### Main Optimization

The key idea is:

> Reuse the information from the previous window instead of calculating the new window from scratch.

---

# 3. Two Main Types of Sliding Window

## A. Fixed-Size Window

The size is predetermined.

Example:

    k = 3

Windows:

    [1,2,3]
      ↓
    [2,3,4]
        ↓
    [3,4,5]

The condition is:

    window size = k

Examples:

- LC 643
- LC 1456
- LC 438
- LC 567

---

## B. Variable-Size Window

The size changes according to a condition.

Example:

    Find the longest valid substring.

General movement:

    Expand with right
            ↓
    Check condition
            ↓
    Invalid?
            ↓
    Shrink with left
            ↓
    Valid again
            ↓
    Update answer

Examples:

- LC 3
- LC 76

---

# 4. Three-Step Implementation Check

Before coding any Sliding Window problem:

## 1. What needs to be stored?

Depending on the problem:

- Sum
- Count
- Frequency
- Number of distinct elements
- Required characters
- Current window size
- Number of satisfied requirements

---

## 2. When should it be updated?

When an element enters:

    Update state

When an element leaves:

    Remove/update state

---

## 3. What update operation is required?

Examples:

    sum += incoming

    sum -= outgoing

or:

    frequency[incoming]++

    frequency[outgoing]--

The state must always represent the **current window**.

---

# 5. Fixed-Size Window Template

Conceptually:

    left = 0

    for right:

        add current element

        if window size > k:
            remove left element
            left++

        if window size == k:
            process answer

The exact implementation depends on the problem.

---

# 6. Variable-Size Window Template

Conceptually:

    left = 0

    for right:

        add current element

        while window is invalid:

            remove left element
            left++

        update answer

The important question is:

> What makes the window valid or invalid?

That condition drives the entire algorithm.

---

# 7. LeetCode 643 — Maximum Average Subarray I

## Problem

Find the contiguous subarray of length `k` having the maximum average.

---

## Pattern

**Fixed-Size Sliding Window**

Because:

    window size = k

---

## Brute Force

For every possible starting position:

1. Take `k` elements.
2. Calculate their sum.
3. Compare the result.

Repeated work occurs between overlapping windows.

Complexity:

    O(nk)

---

## Optimized Logic

Calculate the first window's sum.

Then move the window:

    currentSum
        -
    outgoing element
        +
    incoming element

For every shift, the sum is updated in O(1).

Finally:

    maximumSum / k

gives the maximum average.

---

## WHY does this work?

Adjacent windows overlap.

Example:

    [1,2,3]
     ↓
    [2,3,4]

Only two things changed:

    1 left
    4 entered

There is no reason to recalculate:

    2 + 3

---

## Complexity

Time:

    O(n)

Space:

    O(1)

---

# 8. LeetCode 1456 — Maximum Number of Vowels in a Substring of Given Length

## Problem

Find the maximum number of vowels in any substring of length `k`.

---

## Pattern

**Fixed-Size Sliding Window**

---

## What needs to be stored?

Only:

    current vowel count
    maximum vowel count

---

## Logic

When a character enters:

    If vowel:
        increase count

When a character leaves:

    If vowel:
        decrease count

Then:

    maximum = max(maximum, currentCount)

---

## WHY?

Every new window differs from the previous window by only:

    One outgoing character
    One incoming character

Therefore, we can update the count instead of recalculating it.

---

## Complexity

Time:

    O(n)

Space:

    O(1)

---

# 9. LeetCode 438 — Find All Anagrams in a String

## Problem

Given strings `s` and `p`, find all starting indices in `s` where an anagram of `p` occurs.

Example:

    s = "cbaebabacd"
    p = "abc"

Anagrams:

    "cba"
    "bac"

Answer:

    [0, 6]

---

## Pattern

**Fixed-Size Sliding Window + Frequency Counting**

Because every anagram of `p` must have exactly:

    p.length()

characters.

Therefore:

    window size = p.length()

---

## What needs to be stored?

We need:

    Required character frequencies
    Current window frequencies

---

## Logic

1. Build frequency information for `p`.
2. Create a window of size `p.length()`.
3. Add the incoming character.
4. Remove the outgoing character.
5. Compare frequency states.
6. If frequencies match, record the starting index.

---

## WHY Frequency?

Anagrams can have different ordering.

For example:

    "abc"
    "bca"
    "cab"

Their order is different, but their frequencies are identical.

Therefore:

> Anagram detection is fundamentally a frequency-matching problem.

---

## Complexity

Time:

    O(n)

Space:

    O(k)

where `k` is the character set being tracked.

---

# 10. LeetCode 567 — Permutation in String

## Problem

Given strings `s1` and `s2`, determine whether `s2` contains a permutation of `s1`.

Example:

    s1 = "ab"
    s2 = "eidbaooo"

The substring:

    "ba"

is a permutation of:

    "ab"

Therefore:

    true

---

## Pattern

**Fixed-Size Sliding Window + Frequency Counting**

---

## Why Fixed Size?

Any permutation of `s1` must contain exactly:

    s1.length()

characters.

Therefore the window size is fixed.

---

## What needs to be stored?

We need:

    Frequency of characters in s1
    Frequency of characters in current window

---

## Logic

1. Build the target frequency state.
2. Create a window of size `s1.length()`.
3. Add incoming characters.
4. Remove outgoing characters.
5. Compare the current frequency state with the target.
6. If they match, return true.

---

## Core Connection

LC 438 and LC 567 use almost the same underlying pattern.

Both use:

    Fixed Window
        +
    Frequency Matching

Difference:

### LC 438

Find **all** valid windows.

### LC 567

Determine whether **at least one** valid window exists.

---

## Complexity

Time:

    O(n)

Space:

    O(k)

where `k` is the tracked character set.

---

# 11. LeetCode 3 — Longest Substring Without Repeating Characters

## Problem

Find the length of the longest substring without repeating characters.

Example:

    "abcabcbb"

Longest valid substring:

    "abc"

Answer:

    3

---

## Pattern

**Variable-Size Sliding Window + Hashing**

---

## Why Sliding Window?

We are dealing with a:

    substring

A substring is continuous.

Therefore, a window can represent the current substring.

---

## What needs to be stored?

We need to know whether a character already exists inside the current window.

Possible structures:

    HashSet
    HashMap

---

## Window Invariant

The current window must satisfy:

> Every character appears at most once.

---

## Logic

Expand using `right`.

When the new character creates a duplicate:

    Shrink from left

Continue shrinking until the duplicate is removed.

Then update:

    maximum window length

---

## Example

    abcabcbb

Initially:

    [a]
    [a,b]
    [a,b,c]

Next:

    a

creates:

    [a,b,c,a]

This violates the invariant.

Therefore:

    Move left

until the window becomes valid again.

---

## WHY is it O(n)?

`right` moves forward at most `n` times.

`left` also moves forward at most `n` times.

Therefore:

    O(n + n)
    = O(n)

---

## Complexity

Time:

    O(n)

Space:

    O(k)

where `k` is the number of tracked characters.

---

# 12. LeetCode 76 — Minimum Window Substring

## Problem

Find the minimum-length substring of `s` that contains all characters of `t` with their required frequencies.

---

## Pattern

**Variable-Size Sliding Window + Frequency Counting**

This is a more advanced Sliding Window problem.

---

## What needs to be stored?

We need:

    Required frequencies
    Current window frequencies
    Number of satisfied requirements

---

## Expansion

Move `right`.

When a required character enters:

    Update its frequency

If its required frequency is satisfied:

    Update satisfied count

Continue until the window becomes valid.

---

## Shrinking

Once the window is valid:

    Move left

Try to remove unnecessary characters.

Every valid window is a candidate answer.

Continue shrinking while the window remains valid.

When it becomes invalid:

    Stop shrinking
    Resume expansion

---

## Core Pattern

    Expand until valid
            ↓
    Record answer
            ↓
    Shrink while valid
            ↓
    Become invalid
            ↓
    Expand again

---

## Important Difference from LC 3

LC 3 asks:

    Longest valid window

So we generally update the answer after maintaining validity.

LC 76 asks:

    Shortest valid window

So we want to shrink as much as possible while maintaining validity.

---

## WHY is it O(n)?

Even though we have nested pointer movement:

    right ≤ n
    left ≤ n

Both pointers only move forward.

Therefore:

    O(2n)
    = O(n)

---

## Complexity

Time:

    O(n)

Space:

    O(k)

where `k` is the number of distinct characters tracked.

---

# 13. Fixed Window vs Variable Window

| Feature | Fixed Window | Variable Window |
|---|---|---|
| Window size | Constant | Changes |
| Control | `k` | Condition |
| Shrinking | Usually after window reaches size | Based on validity |
| Examples | 643, 1456, 438, 567 | 3, 76 |
| Common state | Sum / count / frequency | Frequency / condition |

---

# 14. Sliding Window + Hashing

Many advanced Sliding Window problems require:

    HashMap
    HashSet
    Frequency Array

Why?

The window tells us:

    Which elements are inside?

Hashing tells us:

    How many times?
    Does it exist?
    Is its frequency sufficient?

Therefore:

    Sliding Window
        +
    Hashing

is a powerful combination.

---

# 15. Window Invariant

An invariant is a condition that must remain true for the current window.

### LC 3

    No duplicate characters.

### LC 76

    Window contains all required characters with required frequencies.

### LC 438

    Window has the same frequency distribution as p.

### LC 567

    Window has the same frequency distribution as s1.

### Fixed Window Problems

    Window size = k

The algorithm works by maintaining the correct invariant.

---

# 16. Longest vs Shortest Window

This distinction is extremely important.

## Longest Valid Window

Example:

    LC 3

General idea:

    Expand
    Make valid
    Update maximum
    Shrink when invalid

---

## Shortest Valid Window

Example:

    LC 76

General idea:

    Expand
    Make valid
    Update minimum
    Shrink while valid

The objective determines when and how aggressively the window should shrink.

---

# 17. Common Mistakes

## 1. Forgetting to remove outgoing state

When `left` moves:

    Remove arr[left]

from the current window state.

Otherwise the stored state no longer represents the actual window.

---

## 2. Using presence instead of frequency

For LC 76, knowing:

    "A exists"

is not enough.

If the target requires:

    A → 2

then the window needs:

    A → 2

not just:

    A → 1

---

## 3. Using `if` when `while` is required

Sometimes one left movement is not enough to restore validity.

Therefore:

    while (invalid)

may be necessary.

---

## 4. Updating the answer at the wrong time

For every problem ask:

> Should I update the answer before shrinking, after shrinking, or while the window is valid?

This depends on whether we are finding:

    longest
    shortest
    maximum count
    minimum count
    all valid windows
    any valid window

---

## 5. Confusing substring and subsequence

Substring:

    Continuous

Subsequence:

    Not necessarily continuous

Sliding Window naturally works with continuous ranges.

---

# 18. Why Two Loops Can Still Be O(n)

A common misconception:

> "There is a for loop and a while loop, so the complexity must be O(n²)."

Not necessarily.

Consider:

    right → moves forward n times

    left → moves forward n times

Neither pointer moves backward.

Therefore total movement is:

    n + n
    = 2n

Ignoring constants:

    O(n)

This is amortized analysis.

---

# 19. How to Recognize Sliding Window

When reading a new problem, ask:

### Question 1

Is the problem about a:

    subarray
    OR
    substring?

If yes, think Sliding Window.

### Question 2

Is the range continuous?

If yes, Sliding Window becomes more likely.

### Question 3

Is the size fixed?

If yes:

    Fixed Window

If no:

    Variable Window

### Question 4

What information must the window maintain?

Possible answers:

    sum
    count
    frequency
    distinct elements
    satisfied conditions

### Question 5

What makes the window invalid?

This is usually the most important question.

---

# 20. Problem Recognition Map

    Continuous Range
          ↓
    Sliding Window
          ↓
    ┌────────────────────────────┐
    │                            │
    Fixed Size                Variable Size
    │                            │
    │                            │
    ├── Sum                      ├── Longest
    ├── Count                    ├── Shortest
    └── Frequency                └── Constraint
    │                            │
    ↓                            ↓
    643                         3
    1456                        76
    438
    567

---

# 21. Problems Covered

## Fixed-Size Sliding Window

- [x] 643 — Maximum Average Subarray I
- [x] 1456 — Maximum Number of Vowels in a Substring of Given Length
- [x] 438 — Find All Anagrams in a String
- [x] 567 — Permutation in String

## Variable-Size Sliding Window

- [x] 3 — Longest Substring Without Repeating Characters
- [x] 76 — Minimum Window Substring

---

# 22. Complexity Summary

| Problem | Pattern | Time | Space |
|---|---|---:|---:|
| LC 643 | Fixed Window | O(n) | O(1) |
| LC 1456 | Fixed Window | O(n) | O(1) |
| LC 438 | Fixed + Frequency | O(n) | O(k) |
| LC 567 | Fixed + Frequency | O(n) | O(k) |
| LC 3 | Variable + Hashing | O(n) | O(k) |
| LC 76 | Variable + Frequency | O(n) | O(k) |

Here:

    n = input size

    k = number of distinct characters/elements being tracked

---

# 23. Interview-Level Questions

I should be able to answer these without looking at notes:

1. What is Sliding Window?
2. When should you think about Sliding Window?
3. What is the difference between fixed and variable windows?
4. Why does Sliding Window often reduce O(n²) to O(n)?
5. Why are two pointers commonly used?
6. What information should be stored for a window?
7. When should the left pointer move?
8. Why can the left pointer usually move only forward?
9. Why can a nested while loop still produce O(n)?
10. What is a window invariant?
11. Why is LC 643 a fixed-size window?
12. Why is LC 1456 a fixed-size window?
13. Why does LC 438 require frequency matching?
14. Why does LC 567 require a fixed-size window?
15. What is the difference between LC 438 and LC 567?
16. Why is LC 3 a variable-size window?
17. Why does LC 3 need a Set/Map?
18. Why does LC 76 need frequency information?
19. Why can't presence alone solve LC 76?
20. What is the difference between longest and shortest valid windows?
21. When should the answer be updated?
22. Why can `while` be required instead of `if`?
23. What happens to the state when an element leaves the window?
24. What is the difference between substring and subsequence?
25. How do you identify Sliding Window in an unfamiliar problem?
26. How would you combine Sliding Window with HashMap?
27. Why is LC 438 fundamentally a frequency-matching problem?
28. Why is LC 567 essentially the same pattern as LC 438?
29. Explain the invariant for LC 3.
30. Explain the invariant for LC 76.

---

# 24. Problem-Solving Framework

Before coding any Sliding Window problem:

    1. Identify the continuous range.

    2. Decide:
       Fixed or Variable?

    3. Define:
       What does the window represent?

    4. Identify:
       What state needs to be stored?

    5. Define:
       What makes the window valid?

    6. Expand:
       Move right.

    7. Maintain:
       Update the state.

    8. Shrink:
       Move left when required.

    9. Update:
       Maximum / minimum / count / indices.

    10. Analyze:
        Time + Space complexity.

---

# 25. Final Mental Model

Do not memorize individual solutions.

Remember:

    RIGHT → EXPAND

    LEFT → SHRINK

    STATE → represents the current window

    INVARIANT → tells whether the window is valid

    ANSWER → depends on the problem objective

General flow:

    Find continuous range
            ↓
    Define window
            ↓
    Decide what state to maintain
            ↓
    Expand with right
            ↓
    Maintain validity
            ↓
    Shrink with left when required
            ↓
    Update answer

---

# 26. Mastery Standard

Sliding Window is NOT considered mastered just because the template is memorized.

I should be able to:

- Identify Sliding Window from a new problem.
- Distinguish fixed vs variable window.
- Decide what state needs to be maintained.
- Define the window invariant.
- Know when to expand.
- Know when to shrink.
- Correctly update state when elements enter and leave.
- Handle duplicates.
- Handle frequencies.
- Combine Sliding Window with HashMap/HashSet.
- Explain amortized O(n) complexity.
- Handle edge cases.
- Solve unfamiliar variations.
- Explain the approach before writing code.

Target:

**5–10+ representative problems covering fixed windows, variable windows, frequency-based windows, longest/shortest windows, and important edge cases.**

The goal is not to memorize:

    LC 643
    LC 1456
    LC 438
    LC 567
    LC 3
    LC 76

The goal is to recognize the **common Sliding Window structure behind all of them**.