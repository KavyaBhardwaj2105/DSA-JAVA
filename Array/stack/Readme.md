# Stack — DSA with Java

Stack is a linear data structure that follows:

> LIFO — Last In, First Out

The element inserted last is removed first.

Example:

    Push: 10
    Push: 20
    Push: 30

    Stack:

        30  ← Top
        20
        10

    Pop → 30

---

# 1. Core Stack Operations

## Push

Adds an element to the top.

    push(x)

## Pop

Removes the top element.

    pop()

## Peek

Returns the top element without removing it.

    peek()

## isEmpty

Checks whether the stack contains no elements.

---

# 2. Why Stack?

Stack is useful whenever the problem has a:

- Last-in-first-out relationship
- Matching/open-close relationship
- Previous/next greater or smaller relationship
- Need to temporarily store unresolved elements
- Nested structure

Common applications:

- Parentheses validation
- Expression evaluation
- Undo operations
- Browser history
- DFS
- Monotonic Stack
- Next Greater Element
- Next Smaller Element

---

# 3. Stack in Java

Java provides:

    Stack<Integer>

Common operations:

    stack.push(x)

    stack.pop()

    stack.peek()

    stack.isEmpty()

Important:

> Never call `pop()` or `peek()` without considering whether the stack is empty.

Otherwise an exception can occur.

---

# 4. LeetCode 20 — Valid Parentheses

## Problem

Given a string containing:

    ()
    []
    {}

determine whether the brackets are valid.

Example:

    "([])"

is valid.

Example:

    "([)]"

is invalid.
## 503 - Next Greater Element II

Pattern: Monotonic Decreasing Stack

Key Concepts:
- Circular array
- 2n traversal
- i % n
- Stack of indices
- Push indices only during first pass

Time Complexity: O(n)
Space Complexity: O(n)

Status: Completed


## 901 - Online Stock Span

Pattern: Monotonic Decreasing Stack

Key Concepts:
- Stack stores (price, span)
- Pop smaller/equal previous prices
- Accumulate their stored spans
- Avoid repeated backward traversal

Time Complexity: O(n) amortized
Space Complexity: O(n)

Status: Concept studied — implementation pending

---

# 5. WHY Stack?

The most recently opened bracket must be the first bracket to close.

Example:

    ( [ ]

When `]` appears, it must match:

    [

not:

    (

This is exactly a LIFO relationship.

Therefore:

> Nested bracket matching naturally maps to a Stack.

---

# 6. WHAT needs to be stored?

Store opening brackets:

    (
    [
    {

When a closing bracket appears:

    )
    ]
    }

compare it with the top of the stack.

---

# 7. HOW?

General logic:

    Opening bracket
        ↓
    Push

    Closing bracket
        ↓
    Check stack
        ↓
    Compare with top
        ↓
    Match → Pop
    Mismatch → Invalid

At the end:

    Stack empty → Valid

    Stack not empty → Invalid

---

# 8. Important Edge Cases

### Empty string

Usually considered valid.

### Closing bracket with empty stack

Invalid.

### Mismatched brackets

Invalid.

Example:

    "(]"

### Remaining opening brackets

Invalid.

Example:

    "((("

---

# 9. Complexity

Time:

    O(n)

Space:

    O(n)

In the worst case, all characters can be opening brackets.

---

# 10. Next Greater Element

Now we move from basic Stack to an important advanced pattern:

> Monotonic Stack

The Next Greater Element problem asks:

> For each element, find the first greater element to its right.

Example:

    [2, 1, 2, 4, 3]

For `2`:

    Next greater = 4

For `1`:

    Next greater = 2

For `4`:

    No greater element

---

# 11. Why Brute Force Is Expensive

For every element:

    Look to the right
    Find the first greater element

In the worst case:

    O(n²)

because many elements may repeatedly scan the same elements.

Monotonic Stack optimizes this.

---

# 12. Monotonic Stack

A Monotonic Stack maintains elements in a particular order.

Two common types:

## Monotonic Increasing Stack

Elements remain increasing.

Example:

    1
    3
    5
    7

## Monotonic Decreasing Stack

Elements remain decreasing.

Example:

    7
    5
    3
    1

Which one we use depends on the problem.

---

# 13. Core Monotonic Stack Idea

For Next Greater Element:

When a new element is greater than the stack's top:

    Current element is the answer
    for the element at the top.

Therefore:

    while stack is not empty
    AND
    current > stack top

        pop

Every popped element has just found its next greater element.

---

# 14. WHY Do We Pop?

Suppose:

    stack:
    2
    4
    6

Current element:

    8

Since:

    8 > 6

6 has found its next greater element.

Pop 6.

Then:

    8 > 4

4 has also found its next greater element.

Pop 4.

Then:

    8 > 2

2 has also found its next greater element.

Pop 2.

The current 8 resolves all smaller unresolved elements.

---

# 15. The Important Concept: Unresolved Elements

A stack in Monotonic Stack problems can be thought of as:

> Elements whose answer has not been found yet.

When a new element arrives and satisfies their condition:

    They are resolved
    → Pop them

This is the key mental model.

---

# 16. LeetCode 496 — Next Greater Element I

## Problem

For each element in `nums1`, find its next greater element in `nums2`.

Example:

    nums1 = [4,1,2]

    nums2 = [1,3,4,2]

Answers:

    4 → -1
    1 → 3
    2 → -1

---

# 17. Core Approach

We process `nums2`.

Maintain a monotonic stack of unresolved elements.

When:

    current > stack.top

the current value becomes the next greater element for the popped value.

Store the relationship:

    value → nextGreater

A HashMap is useful for this mapping.

Then process `nums1` and look up each answer.

---

# 18. WHAT needs to be stored?

Two things:

### Stack

Stores unresolved values.

### HashMap

Stores:

    element → next greater element

---

# 19. WHY HashMap?

After processing `nums2`, we need to quickly answer:

> What is the next greater element of this particular value?

HashMap provides average:

    O(1)

lookup.

---

# 20. Important Observation

Once an element gets its next greater element:

    We never need to process that element again.

Therefore:

    Pop it permanently.

This is why the algorithm remains linear.

---

# 21. Complexity — LC 496

Each element enters the stack once.

Each element leaves the stack at most once.

Therefore:

Time:

    O(n)

Space:

    O(n)

for the stack and HashMap.

---

# 22. LeetCode 739 — Daily Temperatures

## Problem

For every day, find how many days we have to wait until a warmer temperature.

Example:

    temperatures = [73,74,75,71,69,72,76,73]

Answer:

    [1,1,4,2,1,1,0,0]

---

# 23. Why Monotonic Stack?

For every temperature, we need:

> The first greater temperature to its right.

This is a Next Greater Element pattern.

The difference is:

Instead of returning the greater temperature itself, we need:

    Number of days between them

Therefore, we need indices.

---

# 24. WHAT needs to be stored?

This is one of the most important decisions.

We store:

    INDEX

not just temperature values.

Why?

Because the answer is:

    futureIndex - currentIndex

Example:

    current index = 2
    warmer index = 5

Answer:

    5 - 2 = 3 days

Therefore, indices are required.

---

# 25. Core Logic

For each index `i`:

    current temperature = temperatures[i]

While:

    stack is not empty
    AND
    temperatures[i] > temperatures[stack.peek()]

the current temperature is warmer.

Therefore:

    prevIndex = stack.pop()

and:

    answer[prevIndex] = i - prevIndex

After resolving all possible previous days:

    push current index

---

# 26. WHY Does the Stack Contain Indices?

Because we need two things:

1. Temperature comparison

    temperatures[index]

2. Distance calculation

    currentIndex - previousIndex

If we stored only temperatures, we would lose the position.

Therefore:

> When the answer depends on distance/position, storing indices is usually necessary.

---

# 27. Example

Consider:

    [73, 74, 75, 71, 69, 72, 76, 73]

When `72` arrives:

    72 > 69

So:

    69 → answer = 1 day

Then:

    72 > 71

So:

    71 → answer = 2 days

But:

    72 < 75

Therefore 75 remains unresolved.

This demonstrates the key idea:

> A new element can resolve multiple previous elements.

---

# 28. Why Do We Pop Multiple Elements?

Suppose unresolved temperatures are:

    72
    74
    75

Current temperature:

    76

Then:

    76 > 75
    76 > 74
    76 > 72

Therefore all three are resolved.

The stack becomes empty.

This is why the condition is:

    while

not merely:

    if

---

# 29. The Stack Represents

At any point:

> The stack contains indices whose next greater temperature has NOT been found yet.

This is the most useful mental model for LC 739.

---

# 30. Why Is LC 739 O(n)?

At first glance:

    for loop
        +
    while loop

looks like O(n²).

But each index:

    is pushed once
    is popped at most once

Therefore total stack operations are:

    O(n)

So:

    Time = O(n)

Space:

    O(n)

---

# 31. Monotonic Stack Pattern

For Next Greater Element:

    Process current element

    while:
        stack not empty
        AND
        current satisfies greater condition

        pop unresolved element
        assign answer

    push current element

The exact comparison depends on the problem.

---

# 32. Index vs Value — Important Interview Concept

One of the most common questions is:

> Should I store the value or the index?

Ask:

### Does the answer require position/distance?

If yes:

    Store INDEX

Example:

    LC 739

Because:

    answer = currentIndex - previousIndex

---

### Do I only need the element itself?

Then storing:

    VALUE

may be sufficient.

Example:

    LC 496

where we need:

    value → nextGreaterValue

---

# 33. Current Progress

## Basic Stack

- [x] Stack fundamentals
- [x] LIFO
- [x] Push
- [x] Pop
- [x] Peek
- [x] Empty-stack handling

## Stack Problems

- [x] LC 20 — Valid Parentheses

## Monotonic Stack

- [x] Monotonic Stack concept
- [x] Next Greater Element concept
- [x] LC 496 — Next Greater Element I
- [x] LC 739 — Daily Temperatures

---

# 34. Complexity Summary

| Problem | Pattern | Time | Space |
|---|---|---:|---:|
| LC 20 | Stack | O(n) | O(n) |
| LC 496 | Monotonic Stack + HashMap | O(n) | O(n) |
| LC 739 | Monotonic Stack + Indices | O(n) | O(n) |

---

# 35. Common Mistakes

## Mistake 1 — Popping without checking empty

Always consider:

    stack.isEmpty()

before accessing:

    stack.peek()
    stack.pop()

---

## Mistake 2 — Using `if` instead of `while`

In Monotonic Stack:

A current element may resolve multiple previous elements.

Therefore:

    while

is often required.

---

## Mistake 3 — Storing values when indices are required

If the answer requires:

    distance
    position
    index difference

store indices.

---

## Mistake 4 — Forgetting unresolved elements

If no greater element appears:

    answer remains default

For LC 739:

    0

For LC 496:

    -1

---

## Mistake 5 — Thinking every popped element is discarded uselessly

A popped element is actually being resolved.

When it is popped:

> Its required answer has been found.

---

# 36. Stack vs Monotonic Stack

## Normal Stack

Used when we mainly care about:

    LIFO

Example:

    Valid Parentheses

---

## Monotonic Stack

Used when we additionally need:

    Ordered unresolved elements
    +
    Next greater/smaller relationship

Examples:

    Next Greater Element
    Daily Temperatures
    Next Smaller Element
    Stock Span
    Largest Rectangle in Histogram

---

# 37. Problem Recognition

When you see:

- Next greater
- Next smaller
- Previous greater
- Previous smaller
- First greater element to the right
- First smaller element to the right
- Waiting until a larger value appears

Immediately consider:

> Monotonic Stack

Then ask:

1. What direction are we looking?
2. Greater or smaller?
3. Should the stack be increasing or decreasing?
4. Do I need values or indices?
5. What does an unresolved element mean?

---

# 38. Interview-Level Questions

I should be able to answer these without looking at notes:

1. What is a Stack?
2. Explain LIFO.
3. What are push, pop, and peek?
4. Why is Stack useful for nested structures?
5. Why does Valid Parentheses use Stack?
6. What happens if a closing bracket appears when the stack is empty?
7. What is a Monotonic Stack?
8. What is the difference between increasing and decreasing Monotonic Stack?
9. What is the Next Greater Element pattern?
10. Why do we pop elements in a Monotonic Stack?
11. What does an unresolved element mean?
12. Why is `while` usually required instead of `if`?
13. Why is LC 496 O(n)?
14. Why is LC 739 O(n) despite having a nested while loop?
15. Why does LC 739 store indices?
16. Why can LC 496 store values?
17. When should we store indices instead of values?
18. What happens to elements that never find a greater element?
19. How do you decide between increasing and decreasing Monotonic Stack?
20. What is the invariant maintained by the stack?
21. How would you identify a Monotonic Stack problem?
22. What is the difference between Next Greater and Next Smaller Element?
23. How would you solve Previous Greater Element?
24. How would you solve Previous Smaller Element?
25. What information does the stack represent at any point?

---

# 39. Three-Step Implementation Check

Before coding every Stack / Monotonic Stack problem:

## 1. WHAT needs to be stored?

Ask:

    Values?
    Indices?
    Unresolved elements?
    Frequency?
    Mapping?

---

## 2. WHEN should it be updated?

Ask:

    When does an element enter the stack?
    When does it become resolved?
    When should it be removed?

---

## 3. WHAT update operation is required?

Examples:

    push()

    pop()

    answer[index] = ...

    map.put(...)

Do not start coding until these three questions are clear.

---

# 40. Final Mental Model

Normal Stack:

    Last In
       ↓
    First Out

Monotonic Stack:

    Keep unresolved elements
            ↓
    New element arrives
            ↓
    Can it resolve previous elements?
            ↓
        YES → POP
            ↓
    Assign their answers
            ↓
    Push current element

The most important idea:

> The stack is not storing random previous elements. It is storing elements whose answers are still unresolved.

---

# 41. Mastery Standard

Stack is NOT considered mastered just because LC 20, 496, and 739 are solved.

I should be able to:

- Explain LIFO.
- Identify when Stack is appropriate.
- Handle empty-stack cases.
- Recognize Next Greater/Smaller patterns.
- Build increasing/decreasing Monotonic Stacks.
- Decide whether to store values or indices.
- Explain why elements are popped.
- Explain why `while` is required.
- Prove O(n) amortized complexity.
- Handle unresolved elements.
- Identify the stack invariant.
- Solve unfamiliar Monotonic Stack variations.
- Explain the approach before writing code.

Target:

**5–10+ representative Stack/Monotonic Stack problems covering parentheses, next greater, next smaller, previous greater/smaller, indices, distances, and important edge cases.**

---

# 42. Current Status

Stack foundation:

    COMPLETED

Valid Parentheses:

    COMPLETED

Monotonic Stack foundation:

    COMPLETED

LC 496:

    COMPLETED

LC 739:

    COMPLETED

Advanced Monotonic Stack:

    IN PROGRESS

Future topics will be added to this README as they are mastered.