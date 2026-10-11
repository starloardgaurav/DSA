# 2778. Sum of Squares of Special Elements

**LeetCode:** [2778. Sum of Squares of Special Elements](https://leetcode.com/problems/sum-of-squares-of-special-elements/)  
**Difficulty:** Easy  
**Topics:** Array, Math  
**Language:** Java  
**Status:** Solved ✅

---

## Problem Statement

Given a 0-indexed integer array `nums`, let `n` be its length.

An element `nums[i]` is considered special if its **1-based index `i + 1` divides `n` evenly**.

Return the sum of the squares of all special elements.

### Example 1

```text
Input: nums = [1,2,3,4]
Output: 21
```

Explanation:

The array length is `4`. Its special 1-based indices are `1`, `2`, and `4`.

Sum = `1² + 2² + 4² = 21`.

### Example 2

```text
Input: nums = [2,7,1,19,18,3]
Output: 63
```

Explanation:

The array length is `6`. Its special 1-based indices are `1`, `2`, `3`, and `6`.

Sum = `2² + 7² + 1² + 3² = 63`.

---

## Approach: Array Traversal + Divisibility

### Intuition

An element is special if its 1-based index divides the array length without a remainder.

For an array of length `n`, check every index `i` from `1` to `n`.

If `n % i == 0`, add the square of `nums[i - 1]` to the answer. We use `i - 1` because Java arrays are zero-indexed.

### Algorithm

1. Initialize `sum = 0`.
2. Store the array length in `n`.
3. Iterate `i` from `1` through `n`.
4. If `n % i == 0`, add `nums[i - 1] * nums[i - 1]` to `sum`.
5. Return `sum`.

---

## Java Solution

```java
class Solution {
    public int sumOfSquares(int[] nums) {
        int sum = 0;
        int n = nums.length;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                sum += nums[i - 1] * nums[i - 1];
            }
        }

        return sum;
    }
}
```

---

## Dry Run

**Input:** `nums = [1, 2, 3, 4]`

Array length: `n = 4`

| `i` | `n % i == 0`? | Element | Contribution | Running sum |
|---:|---|---:|---:|---:|
| 1 | Yes | 1 | 1² = 1 | 1 |
| 2 | Yes | 2 | 2² = 4 | 5 |
| 3 | No | 3 | 0 | 5 |
| 4 | Yes | 4 | 4² = 16 | 21 |

**Output:** `21`

---

## Complexity Analysis

- **Time Complexity:** `O(n)` — each array index is checked once.
- **Auxiliary Space Complexity:** `O(1)` — only a constant number of variables are used.

## Key Learnings

- Convert between 1-based problem indices and 0-based Java array indices carefully.
- Use the modulo operator `%` to test divisibility.
- Only elements at indices dividing the array length contribute to the answer.
- A direct traversal is sufficient; no additional data structure is required.

## Pattern

**Array Traversal + Divisibility / Number Theory**

## Folder Structure

```text
Arrays/
└── Sum-of-Squares-of-Special-Elements-2778/
    ├── README.md
    └── Solution.java
```

## Tags

`Java` `LeetCode` `Arrays` `Math` `Divisibility` `Easy`
