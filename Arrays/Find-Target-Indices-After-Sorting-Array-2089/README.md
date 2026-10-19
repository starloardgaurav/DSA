# 2089. Find Target Indices After Sorting Array

**LeetCode:** [2089. Find Target Indices After Sorting Array](https://leetcode.com/problems/find-target-indices-after-sorting-array/)  
**Difficulty:** Easy  
**Topics:** Array, Sorting, Binary Search  
**Language:** Java  
**Status:** Solved ✅

---

## Problem Statement

You are given a 0-indexed integer array `nums` and an integer `target`.

After sorting `nums` in non-decreasing order, return a list containing all indices where `nums[i] == target`.

If the target does not exist in the array, return an empty list.

## Examples

### Example 1

```text
Input: nums = [1, 2, 5, 2, 3], target = 2
Output: [1, 2]
```

Explanation:

After sorting, the array becomes `[1, 2, 2, 3, 5]`.

The target `2` occurs at indices `1` and `2`.

### Example 2

```text
Input: nums = [1, 2, 5, 2, 3], target = 3
Output: [3]
```

Explanation:

After sorting, the array becomes `[1, 2, 2, 3, 5]`.

The target `3` occurs at index `3`.

### Example 3

```text
Input: nums = [1, 2, 5, 2, 3], target = 5
Output: [4]
```

---

## Approach: Sorting + Linear Search

### Intuition

Sorting places all equal elements next to one another. After sorting, we can traverse the array and collect every index whose value equals `target`.

Since Java arrays use zero-based indexing, the first element is at index `0`.

### Algorithm

1. Sort `nums` in ascending order.
2. Create an empty `ArrayList<Integer>` named `ans`.
3. Traverse the sorted array from index `0` to `n - 1`.
4. Whenever `nums[i] == target`, add index `i` to `ans`.
5. Return `ans`.

---

## Java Solution

```java
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
            }
        }

        return ans;
    }
}
```

---

## Dry Run

**Input:** `nums = [1, 2, 5, 2, 3]`, `target = 2`

### Step 1: Sort the array

```text
Original: [1, 2, 5, 2, 3]
Sorted:   [1, 2, 2, 3, 5]
```

### Step 2: Traverse the array

| Index `i` | `nums[i]` | Equals target? | Result |
|---:|---:|---|---|
| 0 | 1 | No | `[]` |
| 1 | 2 | Yes | `[1]` |
| 2 | 2 | Yes | `[1, 2]` |
| 3 | 3 | No | `[1, 2]` |
| 4 | 5 | No | `[1, 2]` |

**Output:** `[1, 2]`

---

## Complexity Analysis

Let `n` be the length of `nums` and `k` be the number of occurrences of `target`.

- **Time Complexity:** `O(n log n)` for sorting, followed by `O(n)` for traversal. Overall: `O(n log n)`.
- **Auxiliary Space Complexity:** Depends on the sorting implementation; Java's primitive-array sorting uses implementation-dependent stack space. The returned list requires `O(k)` space.

**Note:** `Arrays.sort(nums)` modifies the original input array.

---

## Optimized Approach: Counting + Index Calculation

Because the target indices are requested after sorting, we do not necessarily need to sort the array.

We can count:

- `less`: Number of elements smaller than `target`.
- `equal`: Number of elements equal to `target`.

After sorting, all target elements occupy consecutive indices starting at `less`.

Therefore, the answer contains:

`less, less + 1, ..., less + equal - 1`

### Complexity

- **Time:** `O(n + k)` — one traversal to count elements and another to build the answer.
- **Auxiliary Space:** `O(k)` for the result list, excluding the input array.

This approach is useful when only the target indices are needed, rather than the sorted array itself.

---

## Key Learnings

- Sorting places equal elements in consecutive positions.
- A linear scan is a simple way to find all matching indices.
- Indices returned by this problem are zero-based.
- If only the target positions are required, counting elements smaller than the target can avoid sorting.
- Binary search can also locate the first and last occurrence of the target in a sorted array.

## Pattern

**Array + Sorting + Searching**

## Folder Structure

```text
Arrays/
├── README.md
└── Find-Target-Indices-After-Sorting-Array-2089/
    ├── README.md
    └── Solution.java
```

## Tags

`Java` `LeetCode` `Array` `Sorting` `Linear Search` `Binary Search` `Easy`
