# Heap

This folder contains my Heap and Priority Queue implementations and LeetCode solutions in Java.

## Core Concepts

- Heap is a Complete Binary Tree with a heap-order property.
- Min Heap: parent <= children, so the minimum element is at the root.
- Max Heap: parent >= children, so the maximum element is at the root.
- Heap can be represented efficiently using an array.

## Array Representation (0-based indexing)

```text
parent(i) = (i - 1) / 2
left(i)   = 2 * i + 1
right(i)  = 2 * i + 2
```

## Core Operations

| Operation | Typical Complexity |
|---|---:|
| Peek | O(1) |
| Insert / Offer | O(log n) |
| Remove root / Poll | O(log n) |

## Java

Java provides `PriorityQueue`, which is a Min Heap by default.

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

## LeetCode Problems

- LC 215 - Kth Largest Element in an Array (already completed before this folder)
- LC 347 - Top K Frequent Elements
