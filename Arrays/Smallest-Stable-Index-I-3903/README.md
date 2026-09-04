# Smallest Stable Index I

**LeetCode Problem:** 3903  
**Difficulty:** Easy  
**Topic:** Array, Prefix Maximum, Suffix Minimum  
**Language:** Java

---

## Problem

You are given an integer array `nums` of length `n` and an integer `k`.

For each index `i`, define its **instability score** as:

```text
max(nums[0..i]) - min(nums[i..n-1])
```

In other words:

- `max(nums[0..i])` is the largest value from index `0` to `i`.
- `min(nums[i..n-1])` is the smallest value from index `i` to `n-1`.

An index `i` is called **stable** if its instability score is less than or equal to `k`.

The task is to return the **smallest stable index**.

If no stable index exists, return `-1`.

---

## Examples

### Example 1

**Input:**

```text
nums = [5, 0, 1, 4]
k = 3
```

**Output:**

```text
3
```

**Explanation:**

For each index:

```text
Index 0:
max([5]) = 5
min([5,0,1,4]) = 0
instability = 5 - 0 = 5

Index 1:
max([5,0]) = 5
min([0,1,4]) = 0
instability = 5 - 0 = 5

Index 2:
max([5,0,1]) = 5
min([1,4]) = 1
instability = 5 - 1 = 4

Index 3:
max([5,0,1,4]) = 5
min([4]) = 4
instability = 5 - 4 = 1
```

Since `1 <= 3`, index `3` is stable.

Therefore, the answer is:

```text
3
```

---

### Example 2

**Input:**

```text
nums = [3, 2, 1]
k = 1
```

**Output:**

```text
-1
```

**Explanation:**

For every index, the instability score is:

```text
3 - 1 = 2
```

Since `2 > 1`, no index is stable.

Therefore, the answer is:

```text
-1
```

---

### Example 3

**Input:**

```text
nums = [0]
k = 0
```

**Output:**

```text
0
```

**Explanation:**

At index `0`:

```text
max([0]) = 0
min([0]) = 0

instability = 0 - 0 = 0
```

Since `0 <= 0`, index `0` is stable.

Therefore, the answer is:

```text
0
```

---

# Approach 1 - Brute Force

## Intuition

For every index `i`, we need two things:

1. Maximum element from `nums[0]` to `nums[i]`.
2. Minimum element from `nums[i]` to `nums[n-1]`.

The simplest approach is to calculate both values separately for every index.

For every `i`:

```text
max(nums[0..i])
```

is calculated using one loop.

Then:

```text
min(nums[i..n-1])
```

is calculated using another loop.

Finally:

```text
instability = max - min
```

If:

```text
instability <= k
```

then `i` is the first stable index, so we return it.

---

## Algorithm

1. Initialize `res = -1`.
2. Traverse every index `i` from `0` to `n-1`.
3. Find the maximum value from index `0` to `i`.
4. Find the minimum value from index `i` to `n-1`.
5. Calculate:

```text
instability = max - min
```

6. If `instability <= k`, return `i`.
7. If no stable index is found, return `-1`.

---

## Code - Approach 1

```java
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int res = -1;
        int n = nums.length;

        for(int i = 0; i < n; i++){

            int max = nums[0];

            for(int j = 0; j <= i; j++){
                if(nums[j] > max){
                    max = nums[j];
                }
            }

            int min = nums[i];

            for(int l = i; l < n; l++){
                if(nums[l] < min){
                    min = nums[l];
                }
            }

            int is = max - min;

            if(is <= k){
                res = i;
                break;
            }
        }

        return res;
    }
}
```

---

## Complexity - Approach 1

### Time Complexity

**O(n²)**

For every index `i`, we perform two additional traversals:

- One to find the prefix maximum.
- One to find the suffix minimum.

Therefore, the overall complexity is:

```text
O(n²)
```

### Space Complexity

**O(1)**

Only a constant number of variables are used.

```text
max
min
is
i
j
l
res
```

No extra array or data structure is used.

---

# Approach 2 - Prefix Maximum + Suffix Minimum

## Intuition

The first approach repeatedly calculates maximum and minimum values.

We can avoid some repeated calculations by observing that:

### Prefix Maximum

While traversing from left to right, we can maintain:

```text
max(nums[0..i])
```

using a variable:

```text
maxEl
```

For every new element:

```text
maxEl = Math.max(maxEl, nums[i])
```

---

### Suffix Minimum

For every index `i`, we also need:

```text
min(nums[i..n-1])
```

We can precompute these values from right to left.

For example:

```text
nums = [5, 0, 1, 4]
```

The suffix minimum values are:

```text
index:       0  1  2  3
nums:        5  0  1  4
suffix min:  0  0  1  4
```

Then while traversing from left to right:

```text
instability = prefixMax - suffixMin
```

If:

```text
instability <= k
```

we immediately return the current index.

Because we are traversing from left to right, the first valid index is automatically the smallest stable index.

---

## Algorithm

### Step 1 - Calculate Suffix Minimum

Traverse the array from right to left.

Maintain:

```text
minEl
```

For every index:

```text
minEl = Math.min(minEl, nums[i])
```

Store the minimum value for that index.

---

### Step 2 - Calculate Prefix Maximum

Traverse the array from left to right.

Maintain:

```text
maxEl
```

For every index:

```text
maxEl = Math.max(maxEl, nums[i])
```

---

### Step 3 - Calculate Instability

For every index:

```text
instability = maxEl - minFromIndex[i]
```

If:

```text
instability <= k
```

return `i`.

If no index satisfies the condition, return:

```text
-1
```

---

# Dry Run - Approach 2

Consider:

```text
nums = [5, 0, 1, 4]
k = 3
```

## Step 1 - Suffix Minimum

Starting from the right:

```text
nums = [5, 0, 1, 4]
```

At index `3`:

```text
min = 4
```

At index `2`:

```text
min = min(4, 1)
    = 1
```

At index `1`:

```text
min = min(1, 0)
    = 0
```

At index `0`:

```text
min = min(0, 5)
    = 0
```

Therefore:

```text
suffix minimum = [0, 0, 1, 4]
```

---

## Step 2 - Prefix Maximum

Now traverse from left to right.

### Index 0

```text
maxEl = 5
suffixMin = 0

instability = 5 - 0
            = 5
```

Since:

```text
5 > 3
```

index `0` is not stable.

---

### Index 1

```text
maxEl = max(5, 0)
      = 5

suffixMin = 0

instability = 5 - 0
            = 5
```

Again:

```text
5 > 3
```

Not stable.

---

### Index 2

```text
maxEl = max(5, 1)
      = 5

suffixMin = 1

instability = 5 - 1
            = 4
```

Since:

```text
4 > 3
```

not stable.

---

### Index 3

```text
maxEl = max(5, 4)
      = 5

suffixMin = 4

instability = 5 - 4
            = 1
```

Since:

```text
1 <= 3
```

index `3` is stable.

Therefore:

```text
answer = 3
```

---

# Code - Approach 2

```java
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        List<Integer> minFromIndex = new ArrayList<>();

        int minEl = Integer.MAX_VALUE;
        int maxEl = Integer.MIN_VALUE;

        int n = nums.length;

        for(int i = n - 1; i >= 0; i--){
            minEl = Math.min(minEl, nums[i]);
            minFromIndex.add(0, minEl);
        }

        for(int i = 0; i < n; i++){
            maxEl = Math.max(maxEl, nums[i]);

            if((maxEl - minFromIndex.get(i)) <= k){
                return i;
            }
        }

        return -1;
    }
}
```

---

# Complexity - Approach 2

There is an important detail in this implementation.

The idea of **prefix maximum + suffix minimum** can be done in `O(n)` time.

However, this particular implementation uses:

```java
minFromIndex.add(0, minEl);
```

Adding an element at index `0` in an `ArrayList` requires shifting existing elements.

Therefore, this implementation can take:

**Time Complexity: O(n²)**

The `ArrayList` also stores `n` elements, so:

**Space Complexity: O(n)**

---

# Optimized Version of Approach 2

We can improve the implementation by using an array and filling it from right to left.

```java
class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;
        int[] minFromIndex = new int[n];

        int minEl = Integer.MAX_VALUE;

        for(int i = n - 1; i >= 0; i--){
            minEl = Math.min(minEl, nums[i]);
            minFromIndex[i] = minEl;
        }

        int maxEl = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++){
            maxEl = Math.max(maxEl, nums[i]);

            if(maxEl - minFromIndex[i] <= k){
                return i;
            }
        }

        return -1;
    }
}
```

### Complexity of Optimized Version

**Time Complexity: O(n)**

The array is traversed twice:

```text
Right → Left
Left → Right
```

So:

```text
O(n) + O(n) = O(n)
```

**Space Complexity: O(n)**

The suffix minimum array stores `n` values.

---

# Comparison of Both Approaches

| Approach | Time | Space | Main Idea |
|----------|------|-------|-----------|
| Brute Force | O(n²) | O(1) | Recalculate max and min for every index |
| Approach 2 - Current Code | O(n²) | O(n) | Prefix max + suffix min using ArrayList |
| Approach 2 - Optimized | O(n) | O(n) | Prefix max + suffix min using array |

---

# Key Learning

## 1. Avoid Repeated Work

In the brute-force solution, the same maximum and minimum calculations are repeated many times.

Whenever we see repeated calculations, we should ask:

> Can I precompute this information?

---

## 2. Prefix and Suffix Techniques

For problems involving:

```text
nums[0..i]
```

think about **Prefix** techniques.

For problems involving:

```text
nums[i..n-1]
```

think about **Suffix** techniques.

Here we need both:

```text
max(nums[0..i])
min(nums[i..n-1])
```

So:

```text
Prefix Maximum + Suffix Minimum
```

is a natural solution.

---

## 3. Traversal Direction Matters

To calculate suffix minimum efficiently, traverse from:

```text
Right → Left
```

To calculate prefix maximum, traverse from:

```text
Left → Right
```

---

## 4. First Stable Index

We need the **smallest** stable index.

Therefore, after calculating the required values, we traverse from left to right.

The first index satisfying:

```text
instability <= k
```

is automatically the smallest stable index.

---

# Important Pattern

```text
For index i:

Prefix Maximum
      ↓
max(nums[0..i])

Suffix Minimum
      ↓
min(nums[i..n-1])

      ↓

Instability Score
      ↓

Prefix Max - Suffix Min
      ↓
      ┌───────────────┐
      │               │
      ↓               ↓
  <= k              > k
      ↓               ↓
Stable             Not Stable
      ↓
Return i
```

---

# Takeaway

The main idea learned from this problem is:

```text
Repeated Range Calculation
          ↓
    Precompute Values
          ↓
Prefix Maximum + Suffix Minimum
          ↓
       O(n) Time
```

The brute-force solution is useful for understanding the problem, while the optimized prefix/suffix technique is the better approach for interviews and larger input sizes.

---

## LeetCode Result

**Status:** Accepted ✅

**Problem:** 3903. Smallest Stable Index I

**Difficulty:** Easy

**Primary Pattern:** Prefix Maximum + Suffix Minimum

---

## Status

- [x] Problem Solved
- [x] Brute Force Approach
- [x] Optimized Approach
- [x] Prefix Maximum Understood
- [x] Suffix Minimum Understood
- [x] Dry Run Completed
- [x] Time Complexity Analyzed
- [x] Space Complexity Analyzed
- [x] Learned Prefix/Suffix Pattern
- [x] Learned Optimization by Precomputation
