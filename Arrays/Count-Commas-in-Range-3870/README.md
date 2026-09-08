# 3870. Count Commas in Range

**LeetCode:** [Count Commas in Range](https://leetcode.com/problems/count-commas-in-range/)

**Difficulty:** Easy

**Topics:** Math, Counting

---

## Problem

You are given an integer `n`.

Return the total number of commas used when writing all integers from `[1, n]` in standard number formatting.

A comma is inserted after every three digits from the right.

Numbers with fewer than 4 digits contain no commas.

### Example

For `n = 1002`:

```text
1,000 → 1 comma
1,001 → 1 comma
1,002 → 1 comma

Total = 3
```

---

## Examples

### Example 1

```text
Input: n = 1002
Output: 3
```

Explanation:

```text
1,000
1,001
1,002
```

Each number contains one comma.

Therefore:

```text
Answer = 3
```

---

### Example 2

```text
Input: n = 998
Output: 0
```

All numbers from `1` to `998` contain fewer than 4 digits, so none of them contains a comma.

---

## Approach 1: Brute Force

The first solution was to iterate through every number starting from `1000` up to `n`.

```java
int count = 0;

if (n < 1000) {
    return 0;
}

for (int i = 1000; i <= n; i++) {
    count++;
}

return count;
```

### Why it works

Every number from `1000` onwards contains exactly one comma for the given constraint `n <= 10^5`.

Therefore, counting the numbers from `1000` to `n` gives the answer.

### Complexity

- Time: `O(n)`
- Space: `O(1)`

This solution is correct but performs unnecessary iterations.

---

## Approach 2: Mathematical Observation — Optimized

Instead of iterating through all numbers, we can directly calculate how many numbers contain a comma.

### Key Observation

Numbers from:

```text
1 → 999
```

contain **0 commas**.

Numbers from:

```text
1000 → n
```

contain **1 comma each** because `n <= 10^5`.

So the number of comma-containing numbers is:

```text
n - 999
```

However, when `n < 1000`, the answer must be `0`.

Therefore:

```text
answer = max(n - 999, 0)
```

---

## Dry Run

### Case 1

```text
n = 1002
```

Calculate:

```text
n - 999
= 1002 - 999
= 3
```

Therefore:

```text
Answer = 3
```

The numbers are:

```text
1,000
1,001
1,002
```

---

### Case 2

```text
n = 998
```

Calculate:

```text
n - 999
= 998 - 999
= -1
```

We cannot return a negative count.

Therefore:

```text
max(-1, 0) = 0
```

Answer:

```text
0
```

---

### Case 3

```text
n = 1000
```

Calculate:

```text
1000 - 999 = 1
```

Only:

```text
1,000
```

contains a comma.

Therefore:

```text
Answer = 1
```

---

## Optimized Java Solution

```java
class Solution {
    public int countCommas(int n) {
        return Math.max(n - 999, 0);
    }
}
```

---

## Complexity Analysis

### Time Complexity

```text
O(1)
```

There is no loop. The answer is calculated directly.

### Space Complexity

```text
O(1)
```

Only a constant amount of extra space is used.

---

## Why `Math.max(n - 999, 0)`?

Consider two cases.

### Case 1: `n >= 1000`

At least one number contains a comma.

```text
n - 999
```

gives the exact count.

### Case 2: `n < 1000`

No number contains a comma.

```text
n - 999
```

would be negative, so:

```java
Math.max(n - 999, 0)
```

ensures the answer is never negative.

---

## Key Learning

The main learning from this problem is:

> Do not always iterate when a mathematical pattern can give the answer directly.

The first solution simulates the counting process.

The optimized solution identifies the range of numbers that contain commas and calculates the count directly.

### Important Pattern

```text
Find the threshold
        ↓
Identify the valid range
        ↓
Calculate the count mathematically
        ↓
Avoid unnecessary iteration
```

---

## Comparison

| Approach | Time | Space | Comment |
|---|---:|---:|---|
| Brute Force | O(n) | O(1) | Correct but unnecessary loop |
| Mathematical | O(1) | O(1) | Optimal |

---

## Interview Explanation

If asked to explain the solution in an interview:

> Numbers below 1000 do not contain commas. Since the constraint is `n <= 10^5`, every number from 1000 to n contains exactly one comma. Therefore, the number of commas is simply the count of numbers from 1000 to n, which is `n - 999`. For values below 1000, the answer is zero, so I use `Math.max(n - 999, 0)`. This gives an O(1) time and O(1) space solution.

---

## Key Pattern

**Mathematical Observation / Counting**

---

## LeetCode

- Problem: **3870. Count Commas in Range**
- Difficulty: **Easy**
- Status: **Solved**
- Optimal Complexity: **O(1) Time, O(1) Space**
