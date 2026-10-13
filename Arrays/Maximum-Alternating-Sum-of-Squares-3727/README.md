# 3727. Maximum Alternating Sum of Squares

**LeetCode:** [3727. Maximum Alternating Sum of Squares](https://leetcode.com/problems/maximum-alternating-sum-of-squares/)
**Difficulty:** Medium
**Topics:** Array, Greedy, Sorting
**Language:** Java
**Status:** Solved

---

## Problem Statement

You are given an integer array `nums`. You may rearrange its elements in any order.

The alternating score of an array is defined as:

`score = nums[0]² - nums[1]² + nums[2]² - nums[3]² + ...`

Return the maximum possible alternating score after rearranging the array.

## Example 1

```text
Input: nums = [1, 2, 3, 4]
Output: 20
```

Explanation:

Square the elements to obtain `[1, 4, 9, 16]`.

Arrange the squared values so that the larger values occupy positive positions and the smaller values occupy negative positions.

`16 + 9 - 4 - 1 = 20`

## Example 2

```text
Input: nums = [1, -1, 2, -2, 3, -3]
Output: 16
```

Explanation:

One optimal arrangement is `[-3, -1, -2, 1, 3, 2]`.

Its alternating score is:

`9 - 1 + 4 - 1 + 9 - 4 = 16`

---

## Approach: Greedy + Sorting

### Intuition

The alternating score has positive and negative positions.

- Positive positions contribute the square of an element.
- Negative positions subtract the square of an element.

To maximize the score, place the largest squared values in positive positions and the smallest squared values in negative positions.

After sorting the squared values in ascending order:

- The first `n / 2` values are subtracted.
- The remaining values are added.

This produces the maximum possible alternating score.

### Algorithm

1. Calculate the square of every element.
2. Sort the array in ascending order.
3. Initialize `min = 0` and `max = 0`.
4. Add the first `n / 2` squared values to `min`.
5. Add the remaining squared values to `max`.
6. Return `max - min`.

---

## Java Solution

```java
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }

        Arrays.sort(nums);

        long max = 0;
        long min = 0;

        for (int i = 0; i < n / 2; i++) {
            min += nums[i];
        }

        for (int i = n / 2; i < n; i++) {
            max += nums[i];
        }

        return max - min;
    }
}
```

---

## Dry Run

**Input:** `nums = [1, 2, 3, 4]`

### Step 1: Square every element

`[1, 4, 9, 16]`

### Step 2: Sort in ascending order

`[1, 4, 9, 16]`

### Step 3: Calculate the two sums

Array length: `n = 4`

| Index | Squared value | Operation | Running sum |
|---:|---:|---|---:|
| 0 | 1 | Subtract group | `min = 1` |
| 1 | 4 | Subtract group | `min = 5` |
| 2 | 9 | Add group | `max = 9` |
| 3 | 16 | Add group | `max = 25` |

### Step 4: Calculate the answer

`answer = max - min`

`answer = 25 - 5 = 20`

**Output:** `20`

---

## Complexity Analysis

Let `n` be the length of the array.

- **Time Complexity:** `O(n log n)` because sorting dominates the runtime.
- **Auxiliary Space Complexity:** `O(log n)` for the sorting implementation's stack space, excluding the input array.

The solution sorts the input array in place.

## Key Learnings

- Greedy strategies can maximize a sum by assigning larger values to positive positions.
- Squaring removes the effect of the original sign on each element's contribution.
- Sorting helps separate the smaller values from the larger values.
- Use `long` for accumulated sums to reduce the risk of integer overflow.
- The solution modifies the original array, so make a copy first if the original values must be preserved.

## Pattern

**Greedy + Sorting + Array Transformation**

## Folder Structure

```text
Arrays/
└── Maximum-Alternating-Sum-of-Squares-3727/
    ├── README.md
    └── Solution.java
```

## Tags

`Java` `LeetCode` `Arrays` `Greedy` `Sorting` `Medium`
