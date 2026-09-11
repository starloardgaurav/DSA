# 3483. Unique 3-Digit Even Numbers

**LeetCode:** [Unique 3-Digit Even Numbers](https://leetcode.com/problems/unique-3-digit-even-numbers/)

**Difficulty:** Easy

**Topics:** Array, Backtracking, Hash Set

---

## Problem

You are given an array `digits` where each element is a digit.

Return the number of **unique three-digit even numbers** that can be formed using the digits.

Each digit can be used at most once for each number.

### Important Conditions

A valid number must:

1. Have exactly 3 digits.
2. Not start with `0`.
3. End with an even digit.
4. Use each array element at most once.
5. Be unique.

---

## Example

```text
Input:
digits = [2, 1, 3, 0]

Output:
6
```

Some valid numbers include:

```text
102
120
130
210
230
302
```

---

## Approach

### Backtracking + HashSet

We generate all possible 3-digit numbers using backtracking.

At every step:

- Choose an unused digit.
- Mark its index as used.
- Add the digit to the current combination.
- Recursively choose the next digit.
- Backtrack by removing the digit and marking the index unused.

Once 3 digits have been selected, we check whether the last digit is even.

If it is even, we construct the number and add it to a `HashSet`.

The `HashSet` ensures that duplicate numbers are counted only once.

---

## Why Do We Need `used[]`?

The input can contain duplicate digits.

For example:

```text
digits = [1, 1, 2]
```

The two `1`s are at different indices, but they can produce the same number.

The `used[]` array keeps track of which **indices** are currently being used.

```java
boolean[] used = new boolean[digits.length];
```

If:

```java
used[i] == true
```

that index cannot be selected again for the current number.

---

## Why Do We Need a `HashSet`?

Even though we track used indices, duplicate numbers can still be generated when the input contains duplicate digits.

For example:

```text
digits = [1, 1, 2]
```

Using either `1` can produce the same number.

Therefore:

```java
Set<Integer> set = new HashSet<>();
```

and:

```java
set.add(num);
```

automatically removes duplicates.

Finally:

```java
return set.size();
```

gives the number of unique valid numbers.

---

## Important Conditions

### 1. First Digit Cannot Be Zero

A three-digit number cannot start with `0`.

Therefore:

```java
if(comb.size() == 0 && digits[i] == 0)
    continue;
```

For example:

```text
012
```

is not a three-digit number.

---

### 2. Last Digit Must Be Even

An even number always ends with:

```text
0, 2, 4, 6, 8
```

So after selecting 3 digits:

```java
if(comb.get(2) % 2 == 0)
```

we check whether the last digit is even.

---

## Constructing the Number

After selecting three digits:

```text
a, b, c
```

the three-digit number is:

```text
a × 100 + b × 10 + c
```

In Java:

```java
int num = comb.get(0) * 100
        + comb.get(1) * 10
        + comb.get(2);
```

For example:

```text
[2, 3, 4]

= 2 × 100 + 3 × 10 + 4
= 234
```

---

## Algorithm

1. Create a `boolean[] used` array.
2. Create a list `comb` to store the current combination.
3. Create a `HashSet` to store unique valid numbers.
4. Start backtracking.
5. If 3 digits are selected:
   - Check whether the last digit is even.
   - Construct the number.
   - Add it to the `HashSet`.
6. Otherwise:
   - Iterate through all digits.
   - Skip already-used indices.
   - Skip `0` when selecting the first digit.
   - Mark the digit as used.
   - Add it to the combination.
   - Recursively continue.
   - Backtrack.
7. Return the size of the `HashSet`.

---

## Dry Run

Consider:

```text
digits = [2, 1, 3, 0]
```

We start with:

```text
comb = []
```

Choose `2`:

```text
comb = [2]
```

Choose `1`:

```text
comb = [2, 1]
```

Choose `3`:

```text
comb = [2, 1, 3]
```

Last digit is `3`.

```text
3 % 2 != 0
```

So `213` is not valid.

Backtrack.

Choose `0`:

```text
comb = [2, 1, 0]
```

Last digit is `0`.

```text
0 % 2 == 0
```

Therefore:

```text
210 ✓
```

Add `210` to the set.

The process continues for all possible combinations.

---

## Backtracking Visualization

```text
                         []
              /     /     \     \
             2      1      3      0
           / | \   / | \   ... 
          ...       ...
           
        Choose 3 digits
              ↓
       Check last digit
              ↓
       Even? → Yes
              ↓
       Construct number
              ↓
        Add to HashSet
```

---

## Java Solution

```java
class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] used = new boolean[digits.length];
        List<Integer> comb = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        isEven(digits, comb, set, used);
        return set.size();
    }

    void isEven(int[] digits, List<Integer> comb,
                Set<Integer> set, boolean[] used) {

        if(comb.size() == 3) {
            if(comb.get(2) % 2 == 0) {
                int num = comb.get(0) * 100
                        + comb.get(1) * 10
                        + comb.get(2);

                set.add(num);
            }
            return;
        }

        for(int i = 0; i < digits.length; i++) {

            if(used[i])
                continue;

            // First digit cannot be zero
            if(comb.size() == 0 && digits[i] == 0)
                continue;

            used[i] = true;
            comb.add(digits[i]);

            isEven(digits, comb, set, used);

            // Backtrack
            comb.remove(comb.size() - 1);
            used[i] = false;
        }
    }
}
```

---

## Complexity Analysis

Let `n` be the number of digits.

At most three positions are selected, so the number of possible index permutations is:

```text
n × (n - 1) × (n - 2)
```

Therefore:

### Time Complexity

```text
O(n³)
```

Since the constraint is very small (`n <= 10`), this is efficient.

### Space Complexity

The recursion and temporary combination require:

```text
O(n)
```

The `HashSet` stores unique valid numbers:

```text
O(k)
```

where `k` is the number of unique valid three-digit numbers.

Overall:

```text
O(n + k)
```

---

## Why This Approach Works

Backtracking systematically explores every possible selection of three digits.

The conditions are checked exactly where they matter:

```text
First digit ≠ 0
Last digit = even
Each index used at most once
```

Finally, the `HashSet` removes duplicate numbers.

Therefore, every valid unique three-digit even number is counted exactly once.

---

## Key Learning

### 1. Backtracking

Backtracking is useful when we need to generate combinations or permutations under constraints.

General pattern:

```text
Choose
  ↓
Explore
  ↓
Undo choice
  ↓
Choose next option
```

---

### 2. `used[]` for Index Tracking

When elements can be used only once, a boolean array is a simple way to track selected indices.

---

### 3. HashSet for Uniqueness

When duplicate values can be generated but only unique results are required:

```java
HashSet
```

is a useful tool.

---

### 4. Validate Constraints Early

Instead of generating invalid three-digit numbers and checking them later, we prevent invalid choices early.

For example:

```java
if(comb.size() == 0 && digits[i] == 0)
    continue;
```

This is a useful backtracking technique.

---

## Interview Explanation

> I use backtracking to generate all possible three-digit numbers from the given digits. I maintain a `used` array so that the same index is not reused within one number. I skip zero when choosing the first digit because the number must have exactly three digits. After selecting three digits, I check whether the last digit is even. If valid, I construct the number and store it in a HashSet to avoid counting duplicates. Finally, the size of the set gives the number of unique valid numbers.

---

## Key Pattern

**Backtracking + Used Array + HashSet**

---

## LeetCode

- Problem: **3483. Unique 3-Digit Even Numbers**
- Difficulty: **Easy**
- Status: **Solved**
- Approach: **Backtracking**
- Complexity: **O(n³) Time, O(n + k) Space**
