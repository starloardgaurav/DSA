# 3524. Find X Value of Array I

**LeetCode:** [3524. Find X Value of Array I](https://leetcode.com/problems/find-x-value-of-array-i/)

**Difficulty:** Medium

**Pattern:** Dynamic Programming / Remainder State DP

**Language:** Java

---

## 📌 Problem

You are given an array of positive integers `nums` and a positive integer `k`.

You can remove a prefix and a suffix from the array, as long as the remaining array is non-empty.

Every possible remaining array is therefore a **contiguous subarray** of `nums`.

For every remainder `x` from `0` to `k - 1`, count how many possible remaining subarrays have:

```text
product of elements % k == x
```

Return an array `result` of size `k`.

---

## 💡 Key Observation

Removing a prefix and suffix while keeping the array non-empty is equivalent to choosing any **non-empty contiguous subarray**.

Therefore, the problem becomes:

> Count the number of contiguous subarrays whose product has each possible remainder modulo `k`.

Since `k <= 5`, we can maintain DP states for only `k` possible remainders.

---

## 🧠 Approach

We maintain:

```java
long[] dp = new long[k];
```

### Meaning of `dp[r]`

`dp[r]` represents:

> The number of subarrays ending at the previous index whose product modulo `k` is `r`.

For every new number `num`, we create:

```java
long[] newDp = new long[k];
```

This stores the states for subarrays ending at the current index.

---

## 🔹 Step 1: Start a New Subarray

The current element itself forms a subarray:

```text
[num]
```

Its remainder is:

```java
num % k
```

So:

```java
int rem = num % k;
newDp[rem]++;
```

---

## 🔹 Step 2: Extend Previous Subarrays

Suppose a previous subarray has:

```text
product % k = r
```

When we append `num`:

```text
new product = old product × num
```

Therefore:

```text
new remainder = (r × num) % k
```

In code:

```java
int newRem = (int) ((r * (long) num) % k);
newDp[newRem] += dp[r];
```

All subarrays represented by `dp[r]` are extended by the current number.

---

## 🔹 Step 3: Add Current States to Answer

`newDp` contains all subarrays ending at the current index.

Therefore, add them to the final answer:

```java
for (int r = 0; r < k; r++) {
    ans[r] += newDp[r];
}
```

---

## 🔄 Dry Run

Consider:

```text
nums = [1, 2, 3]
k = 3
```

### Process `1`

Single-element subarray:

```text
[1]

1 % 3 = 1
```

So:

```text
dp = [0, 1, 0]
ans = [0, 1, 0]
```

---

### Process `2`

Start new subarray:

```text
[2]

2 % 3 = 2
```

Extend `[1]`:

```text
1 × 2 = 2

2 % 3 = 2
```

Therefore:

```text
newDp = [0, 0, 2]
```

The two subarrays are:

```text
[2]     → remainder 2
[1,2]   → remainder 2
```

Update answer:

```text
ans = [0, 1, 2]
```

---

### Process `3`

Start new subarray:

```text
[3]

3 % 3 = 0
```

Extend previous subarrays:

```text
[2,3]     → 2 × 3 = 6 → 0
[1,2,3]   → 2 × 3 = 6 → 0
```

So:

```text
newDp = [3, 0, 0]
```

The three subarrays ending at `3` are:

```text
[3]
[2,3]
[1,2,3]
```

All have remainder `0`.

Final answer:

```text
[3, 1, 2]
```

---

## 🔍 Why This Works

Every non-empty contiguous subarray has a unique ending position.

When processing `nums[i]`, every subarray ending at `i` is either:

1. The single element:

```text
[nums[i]]
```

or

2. A previous subarray ending at `i - 1` extended with `nums[i]`.

Therefore, `newDp` captures **every subarray ending at the current index exactly once**.

Adding `newDp` into `ans` counts every possible subarray exactly once.

---

## 💻 Java Solution

```java
class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // Start a new subarray with the current element
            int rem = num % k;
            newDp[rem]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] > 0) {
                    int newRem = (int) ((r * (long) num) % k);
                    newDp[newRem] += dp[r];
                }
            }

            // Add current states to the final answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}
```

---

## ⏱️ Time Complexity

For every element of `nums`, we iterate over all `k` remainder states.

Therefore:

```text
Time Complexity = O(n × k)
```

where:

- `n = nums.length`
- `k = given modulus`

Since `k <= 5`, this is very efficient.

---

## 💾 Space Complexity

We maintain:

```text
dp[k]
newDp[k]
ans[k]
```

All arrays have size `k`.

Therefore:

```text
Space Complexity = O(k)
```

---

## ⚖️ Brute Force vs Optimized

### Brute Force

Generate every contiguous subarray and calculate its product modulo `k`.

There are:

```text
O(n²)
```

possible subarrays.

This becomes too expensive for large `n`.

---

### Optimized DP

Instead of generating every subarray, we only remember:

```text
How many previous subarrays have each remainder?
```

There are only `k` possible remainder states:

```text
0, 1, 2, ..., k - 1
```

So the complexity becomes:

```text
O(n × k)
```

---

## 🔑 Important Concept

The important idea is **state compression using modulo**.

We don't need the complete product of every subarray.

We only need:

```text
product % k
```

Because for the next element:

```text
(product × num) % k
```

depends only on:

```text
product % k
```

This allows us to store only `k` states.

---

## 🧩 DP State

```text
dp[r] =
number of subarrays ending at the previous index
whose product % k == r
```

Transition:

```text
newDp[(r × num) % k] += dp[r]
```

And start a new subarray:

```text
newDp[num % k]++
```

---

## 🎯 Interview Explanation

> I treat every possible remaining array as a non-empty contiguous subarray. I maintain a DP array of size `k`, where `dp[r]` stores the number of subarrays ending at the previous position whose product has remainder `r` modulo `k`. For each new element, I start a new subarray with that element and extend every previous subarray. Since only the remainder modulo `k` matters, there are only `k` states. Finally, I add the states for each position into the answer. The time complexity is `O(nk)` and the space complexity is `O(k)`.

---

## 🧠 Key Learning

- A prefix/suffix removal operation can sometimes be transformed into a **subarray problem**.
- When only a value modulo `k` matters, maintain **remainder states** instead of the complete value.
- DP states can represent counts of subarrays ending at the previous position.
- Every current subarray is either:
  - started from the current element, or
  - obtained by extending a previous subarray.
- Since `k` is very small, an `O(nk)` DP solution is efficient.

---

## 🔥 Pattern

**Dynamic Programming + Modular Arithmetic + Subarray Counting**

The reusable pattern is:

```text
Previous remainder states
          ↓
Extend with current element
          ↓
New remainder states
          ↓
Add to answer
```

---

## 📌 Complexity Summary

| Approach | Time | Space |
|---|---:|---:|
| Brute Force | O(n²) or worse | O(1) |
| Remainder DP | O(n × k) | O(k) |

---

## 🚀 Takeaway

The main trick is not to calculate every subarray separately.

Instead, compress all subarrays into only `k` remainder states.

```text
Huge number of subarrays
          ↓
     Only k states
          ↓
    Efficient DP
```

This is a useful technique whenever:

```text
The exact value is large
        but
Only value % k matters
```
