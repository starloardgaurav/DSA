# Two Sum II - Input Array Is Sorted

**LeetCode Problem:** 167  
**Difficulty:** Medium  
**Topic:** Array, Two Pointers  
**Language:** Java

---

## Problem

Given a **1-indexed** array of integers `numbers` that is already sorted in **non-decreasing order**, find two numbers such that they add up to the given `target`.

Return the indices of the two numbers as:

`[index1, index2]`

where:

- `1 <= index1 < index2 <= numbers.length`
- The array is sorted.
- The same element cannot be used twice.
- There is exactly one solution.
- The solution must use **constant extra space**.

---

## Example

### Example 1

```text
Input:
numbers = [2,7,11,15]
target = 9

Output:
[1,2]

### Output

[1, 2]

### Explanation

2 + 7 = 9

The required elements are at positions `1` and `2`.

---

# Approach - Two Pointers

## Intuition

The most important observation in this problem is that the array is **already sorted**.

We can use two pointers:

- `i` → starts from the beginning of the array.
- `j` → starts from the end of the array.
