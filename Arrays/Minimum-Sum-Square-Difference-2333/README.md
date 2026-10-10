# 2333. Minimum Sum of Squared Difference

**LeetCode:** [2333. Minimum Sum of Squared Difference](https://leetcode.com/problems/minimum-sum-of-squared-difference/)  
**Difficulty:** Medium  
**Topics:** Array, Greedy, Binary Search  
**Language:** Java  
**Status:** Solution approach documented

---

## Problem Statement

You are given two integer arrays, `nums1` and `nums2`, of equal length, along with two integers `k1` and `k2`.

In one operation, you can increase or decrease an element of either array by `1`. You can perform at most `k1` operations on `nums1` and at most `k2` operations on `nums2`.

Return the minimum possible sum of squared differences between corresponding elements of the arrays.

The squared difference at index `i` is:

`(nums1[i] - nums2[i])²`

The objective is to minimize:

`Σ (nums1[i] - nums2[i])²`

---

## Approach: Binary Search + Greedy

### Key Idea

Instead of reducing the largest differences one operation at a time, use binary search to find the smallest possible maximum difference achievable using the available operations.

For every index, calculate:

`diff[i] = abs(nums1[i] - nums2[i])`

Let:

- `k = k1 + k2`, the total available operations.
- `maxDiff`, the maximum initial difference.
- `target`, the minimum maximum difference found using binary search.

### Algorithm

1. Calculate the absolute difference for every corresponding pair and find `maxDiff`.
2. If the total number of operations is at least the sum of all differences, return `0`.
3. Binary search for the smallest achievable maximum difference, `target`.
4. For each difference greater than `target`, reduce it to `target` and count the operations consumed.
5. Calculate the sum of squares of the resulting differences.
6. Use any remaining operations to reduce additional differences by one, updating the squared sum accordingly.

---

## Java Solution

```java
import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];

        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (k >= Arrays.stream(diff).asLongStream().sum()) {
            return 0L;
        }

        int left = 0, right = maxDiff;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int target = left;
        long remaining = k;
        long answer = 0;

        for (int d : diff) {
            if (d > target) {
                remaining -= d - target;
                d = target;
            }
            answer += (long) d * d;
        }

        for (int d : diff) {
            if (remaining == 0) break;
            if (d >= target && target > 0) {
                answer -= (long) target * target
                        - (long) (target - 1) * (target - 1);
                remaining--;
            }
        }

        return answer;
    }
}
```

---

## Complexity Analysis

Let `n` be the length of the arrays and `D` be the maximum initial absolute difference.

- **Time Complexity:** `O(n log D)` — binary search performs `O(log D)` iterations, each scanning all `n` differences.
- **Auxiliary Space Complexity:** `O(n)` — the `diff` array stores all absolute differences.

---

## Key Learnings

- Convert the two input arrays into an array of absolute differences.
- Squaring makes large differences disproportionately expensive, so reducing larger differences is generally more beneficial.
- Binary search can find the smallest achievable maximum difference.
- Use `long` for the total operation count and squared-difference sum to avoid overflow.
- The final sum must be calculated using `long` arithmetic.

## Pattern

**Greedy + Binary Search on the Answer + Array Transformation**

## Folder Structure

```text
Arrays/
└── Minimum-Sum-Square-Difference-2333/
    ├── README.md
    └── Solution.java
```

## Tags

`Java` `LeetCode` `Array` `Greedy` `Binary Search` `Optimization` `Medium`
