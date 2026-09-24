# 🔢 Smallest Index With Digit Sum Equal to Index

## 📌 Problem

Given an integer array `nums`, find the **smallest index `i`** such that the sum of digits of `nums[i]` is equal to `i`.

If no such index exists, return `-1`.

---

## 🧠 Example

### Example 1

```text
Input:
nums = [1, 3, 2, 1, 4, 2]

Index:       0  1  2  3  4  5
Value:       1  3  2  1  4  2
Digit Sum:   1  3  2  1  4  2

i = 0 → digit sum = 1 ❌
i = 1 → digit sum = 3 ❌
i = 2 → digit sum = 2 ✅
```

Therefore:

```text
Answer = 2
```

---

## 💡 Key Observation

For every index `i`:

1. Take `nums[i]`
2. Calculate the sum of its digits
3. Check whether:

```text
digitSum(nums[i]) == i
```

Since we need the **smallest index**, we can traverse the array from left to right.

The first valid index we find is automatically the smallest one.

---

## 🔍 How to Calculate Digit Sum?

Suppose:

```text
nums[i] = 123
```

We can extract digits using `% 10`:

```text
123 % 10 = 3
12  % 10 = 2
1   % 10 = 1
```

Therefore:

```text
Digit Sum = 3 + 2 + 1 = 6
```

After extracting a digit, use:

```java
n /= 10;
```

to remove the last digit.

---

## 🚀 Approach

We traverse the array from left to right.

For every index `i`:

```text
1. Store nums[i] in a temporary variable.
2. Calculate the digit sum.
3. If digit sum == i:
       return i
4. If no index satisfies the condition:
       return -1
```

---

## 🧪 Dry Run

Consider:

```text
nums = [1, 3, 2, 10, 5]
```

### i = 0

```text
nums[0] = 1
digit sum = 1

1 != 0
```

❌ Not valid.

---

### i = 1

```text
nums[1] = 3
digit sum = 3

3 != 1
```

❌ Not valid.

---

### i = 2

```text
nums[2] = 2
digit sum = 2

2 == 2
```

✅ Valid.

Because we are scanning from left to right, this is automatically the smallest valid index.

```text
Answer = 2
```

---

## 💻 Java Solution

```java
class Solution {
    public int smallestIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++) {

            int n = nums[i];
            int sum = 0;

            while(n > 0) {
                sum += n % 10;
                n /= 10;
            }

            if(sum == i) {
                return i;
            }
        }

        return -1;
    }
}
```

---

## 🔎 Code Explanation

### 1. Traverse the array

```java
for(int i = 0; i < nums.length; i++)
```

We check every index from `0` to `n-1`.

---

### 2. Store the current number

```java
int n = nums[i];
```

We use a temporary variable because its value will be modified while extracting digits.

---

### 3. Calculate digit sum

```java
while(n > 0) {
    sum += n % 10;
    n /= 10;
}
```

`n % 10` gives the last digit.

`n /= 10` removes the last digit.

For example:

```text
n = 456

456 % 10 = 6
45  % 10 = 5
4   % 10 = 4

sum = 15
```

---

### 4. Check the condition

```java
if(sum == i) {
    return i;
}
```

If the digit sum equals the current index, return the index immediately.

This avoids unnecessary traversal.

---

## ⏱️ Complexity Analysis

Let:

- `n` = number of elements
- `d` = maximum number of digits in an element

### Time Complexity

For every element, we may process all of its digits.

```text
O(n × d)
```

If the number of digits is bounded by the constraints, this is effectively:

```text
O(n)
```

### Space Complexity

We only use a few variables:

```text
O(1)
```

---

## ⚖️ Why We Don't Need `min_sum`

An alternative implementation may keep:

```java
int min_sum = Integer.MAX_VALUE;
```

and compare:

```java
sum == i && sum < min_sum
```

But this is unnecessary.

Why?

Because we are checking indices in increasing order:

```text
0 → 1 → 2 → 3 → ...
```

The first valid index is automatically the smallest index.

Therefore, we can simply:

```java
if(sum == i) {
    return i;
}
```

---

## 🧩 Pattern

**Digit Manipulation + Array Traversal**

Important digit-manipulation techniques used:

```text
n % 10  → extract last digit
n / 10  → remove last digit
```

---

## 🎯 Key Learning

- Traverse from left to right when the problem asks for the **smallest index**.
- Use `% 10` to extract digits.
- Use `/ 10` to remove digits.
- Return immediately when the first valid index is found.
- Avoid maintaining unnecessary variables when traversal order already guarantees the answer.

---

## 🗣️ Interview Explanation

> "I traverse the array from left to right. For each element, I calculate its digit sum using modulo 10 and integer division by 10. If the digit sum equals the current index, I immediately return that index. Since the traversal is from the smallest index to the largest, the first matching index is guaranteed to be the smallest one. If no index matches, I return -1."

---

## 🔗 LeetCode

[Smallest Index With Digit Sum Equal to Index](https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/)

---

## 📌 Complexity

| Metric | Complexity |
|---|---|
| Time | O(n × d) |
| Space | O(1) |

---

## ✅ Status

- [x] Problem Solved
- [x] Optimized Solution
- [x] Dry Run
- [x] Complexity Analysis
- [x] Interview Explanation
