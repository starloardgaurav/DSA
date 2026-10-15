# 2913. Subarrays Distinct Element Sum of Squares I

**LeetCode:** [2913. Subarrays Distinct Element Sum of Squares I](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/)  
**Difficulty:** Easy  
**Topics:** Array, HashSet, Enumeration  
**Language:** Java  
**Status:** Solved ✅

---

## Problem Statement

Given an integer array `nums`, consider every non-empty contiguous subarray.

For each subarray, calculate the number of distinct elements and square that number.

Return the sum of these squared counts across all subarrays.

Mathematically,

\[
\text{ans} = \sum_{\text{all subarrays } S} |\text{distinct}(S)|^2
\]

## Example

```text
Input: nums = [1, 2, 1]
Output: 15
```

Explanation:

| Subarray | Distinct count | Squared count |
|---|---:|---:|
| `[1]` | 1 | 1 |
| `[1, 2]` | 2 | 4 |
| `[1, 2, 1]` | 2 | 4 |
| `[2]` | 1 | 1 |
| `[2, 1]` | 2 | 4 |
| `[1]` | 1 | 1 |

Total = `1 + 4 + 4 + 1 + 4 + 1 = 15`.

---

## Approach: Brute Force + HashSet

### Intuition

Every subarray can be identified by its starting and ending indices.

For each starting index `i`, create an empty `HashSet` and extend the subarray one element at a time using index `j`.

As each new element is added to the set, the set automatically maintains the distinct elements in the current subarray.

Add the square of the set's size to the answer after every extension.

### Algorithm

1. Initialize `ans = 0`.
2. Iterate over every starting index `i`.
3. Create an empty `HashSet<Integer>`.
4. Extend the subarray by iterating `j` from `i` to `n - 1`.
5. Add `nums[j]` to the set.
6. Add `set.size() * set.size()` to `ans`.
7. Return `ans` after processing every subarray.

---

## Java Solution

```java
import java.util.List;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            Set<Integer> s = new HashSet<>();

            for (int j = i; j < n; j++) {
                s.add(nums.get(j));
                ans += s.size() * s.size();
            }
        }

        return ans;
    }
}
```

---

## Dry Run

**Input:** `nums = [1, 2, 1]`

| Starting index `i` | Current subarray | Set size | Contribution | Running total |
|---:|---|---:|---:|---:|
| 0 | `[1]` | 1 | 1 | 1 |
| 0 | `[1, 2]` | 2 | 4 | 5 |
| 0 | `[1, 2, 1]` | 2 | 4 | 9 |
| 1 | `[2]` | 1 | 1 | 10 |
| 1 | `[2, 1]` | 2 | 4 | 14 |
| 2 | `[1]` | 1 | 1 | 15 |

**Output:** `15`

---

## Complexity Analysis

Let `n` be the length of `nums`.

- **Time Complexity:** `O(n²)` expected, because there are `O(n²)` subarrays and each set insertion takes expected `O(1)` time.
- **Auxiliary Space Complexity:** `O(n)` for the `HashSet` in the worst case.

## Key Learnings

- Two nested loops enumerate all non-empty contiguous subarrays.
- A `HashSet` tracks distinct elements efficiently.
- The set can be reused for each starting index, but it must be cleared or recreated before extending a new starting position.
- The contribution of each subarray is the square of its distinct-element count.

## Pattern

**Array + Enumeration + HashSet**

## Folder Structure

```text
Arrays/
└── Subarrays-Distinct-Element-Sum-of-Squares-I-2913/
    ├── README.md
    └── Solution.java
```

## Tags

`Java` `LeetCode` `Array` `HashSet` `Brute Force` `Enumeration` `Easy`
