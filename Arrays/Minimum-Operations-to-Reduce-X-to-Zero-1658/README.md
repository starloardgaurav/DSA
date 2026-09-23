# 1658. Minimum Operations to Reduce X to Zero

**LeetCode:** [1658. Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/)

**Difficulty:** Medium

**Pattern:** Sliding Window / Prefix Sum

**Language:** Java

---

## 📌 Problem

You are given an integer array `nums` and an integer `x`.

In one operation, you can remove an element from either the left or the right side of the array.

The goal is to remove elements whose sum is exactly `x`.

Return the minimum number of operations required.

If it is impossible, return `-1`.

---

## 💡 Key Observation

Let:

```text
total = sum of all elements
```

If the removed elements have sum `x`, then the elements that remain have sum:

```text
total - x
```

Therefore, instead of finding the minimum number of elements to remove, we can find:

> The longest contiguous subarray whose sum is `total - x`.

If the longest valid subarray has length `maxLen`, then:

```text
minimum operations = n - maxLen
```

---

## 🧠 Transformation

Original problem:

```text
Remove elements from both ends
whose sum = x
```

Transform it into:

```text
Keep a contiguous subarray
whose sum = total - x
```

Then:

```text
Answer = n - longest valid subarray length
```

---

## 🔹 Why Sliding Window Works

All elements in `nums` are positive.

Therefore:

- Increasing `right` increases the window sum.
- Increasing `left` decreases the window sum.

This monotonic behavior allows us to use the sliding window technique.

Whenever:

```text
sum > target
```

we move `left` forward until:

```text
sum <= target
```

Whenever:

```text
sum == target
```

we update the maximum window length.

---

## 🔄 Algorithm

1. Calculate the total sum of the array.
2. Calculate:

```text
target = total - x
```

3. If `target < 0`, return `-1`.
4. If `target == 0`, we must remove all elements, so return `n`.
5. Use a sliding window to find the longest subarray whose sum equals `target`.
6. Return:

```text
n - maxLen
```

7. If no valid subarray exists, return `-1`.

---

## 🔍 Dry Run

Consider:

```text
nums = [1,1,4,2,3]
x = 5
```

### Step 1: Total Sum

```text
total = 1 + 1 + 4 + 2 + 3
      = 11
```

### Step 2: Target

```text
target = total - x
       = 11 - 5
       = 6
```

Now find the longest subarray with sum `6`.

### Sliding Window

Start:

```text
left = 0
sum = 0
```

Add `1`:

```text
[1]
sum = 1
```

Add another `1`:

```text
[1,1]
sum = 2
```

Add `4`:

```text
[1,1,4]
sum = 6
```

We found a valid subarray.

```text
maxLen = 3
```

The remaining elements are:

```text
[2,3]
```

Their sum is:

```text
2 + 3 = 5
```

Therefore:

```text
minimum operations = 5 - 3
                   = 2
```

---

## 💻 Java Solution

```java
class Solution {
    public int minOperations(int[] nums, int x) {

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return nums.length;
        }

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            while (sum > target) {
                sum -= nums[left];
                left++;
            }

            if (sum == target) {
                maxLen = Math.max(
                    maxLen,
                    right - left + 1
                );
            }
        }

        return maxLen == -1
                ? -1
                : nums.length - maxLen;
    }
}
```

---

## ⚖️ Approach 1: Prefix Sum + HashMap

Another valid solution is to use prefix sums.

We want:

```text
currentPrefix - previousPrefix = target
```

Therefore:

```text
previousPrefix = currentPrefix - target
```

A HashMap stores the earliest index for each prefix sum.

### Complexity

```text
Time  = O(n)
Space = O(n)
```

---

## ⚖️ Approach 2: Sliding Window

Because all numbers are positive, we can use a sliding window.

Maintain:

```text
[left ... right]
```

and its sum.

If:

```text
sum > target
```

move `left`.

If:

```text
sum == target
```

update the maximum length.

### Complexity

```text
Time  = O(n)
Space = O(1)
```

---

## 📊 Comparison

| Approach | Time | Space | Main Idea |
|---|---:|---:|---|
| Prefix Sum + HashMap | O(n) | O(n) | Prefix sum lookup |
| Sliding Window | O(n) | O(1) | Longest subarray with target sum |

The sliding window approach is preferred here because the array contains positive integers and therefore the window sum changes monotonically.

---

## 🧠 Why the Formula Works

Suppose:

```text
n = 7
```

and the longest valid subarray has length:

```text
5
```

Then:

```text
elements removed = 7 - 5
                 = 2
```

So maximizing the number of elements kept automatically minimizes the number of operations.

---

## 🎯 Edge Cases

### Case 1: `x > total`

It is impossible to remove elements whose sum is `x`.

```text
target < 0
```

Return:

```text
-1
```

---

### Case 2: `x == total`

We need to remove every element.

```text
target = 0
```

Therefore:

```text
answer = n
```

---

### Case 3: No subarray with sum `target`

Return:

```text
-1
```

---

### Case 4: Entire array is the valid subarray

If:

```text
target = total
```

then we can keep the entire array.

Therefore:

```text
maxLen = n
answer = 0
```

---

## 🔑 Key Learning

The most important trick is to convert a **minimum removal problem** into a **maximum subarray problem**.

Instead of:

```text
Find minimum elements to remove with sum x
```

think:

```text
Find maximum elements to keep with sum total - x
```

This transformation is extremely useful in array problems.

---

## 🧩 Important Pattern

### Sliding Window

The reusable pattern is:

```text
Positive numbers
       ↓
Find longest subarray with target sum
       ↓
Sliding Window
```

Combined with:

```text
total - x
```

we transform the original problem into a standard longest-subarray problem.

---

## 🎯 Interview Explanation

> First, I calculate the total sum of the array. If the elements removed from both ends have sum `x`, then the remaining contiguous subarray must have sum `total - x`. Therefore, instead of minimizing the number of removed elements, I maximize the length of a contiguous subarray whose sum is `total - x`. Since all numbers are positive, I can use a sliding window in O(n) time and O(1) extra space. If the longest valid subarray has length `maxLen`, the answer is `n - maxLen`.

---

## ⏱️ Complexity

```text
Time Complexity: O(n)

Space Complexity: O(1)
```

Every element enters and leaves the sliding window at most once.

---

## 🚀 Takeaway

The main idea is:

```text
Minimum removals
       ↓
Maximum elements kept
       ↓
total - x
       ↓
Longest subarray with target sum
       ↓
Sliding Window
```

This is a classic example of transforming a problem into a more recognizable pattern.
