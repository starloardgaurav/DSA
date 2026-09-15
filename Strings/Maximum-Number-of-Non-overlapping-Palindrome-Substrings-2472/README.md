# 2472. Maximum Number of Non-overlapping Palindrome Substrings

**LeetCode:** [Maximum Number of Non-overlapping Palindrome Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/)

**Difficulty:** Hard

**Topics:** String, Greedy, Palindrome

---

## Problem

You are given a string `s` and a positive integer `k`.

Select the maximum number of **non-overlapping substrings** such that:

1. Each substring is a palindrome.
2. Each substring has length at least `k`.
3. Selected substrings do not overlap.

Return the maximum number of such substrings.

---

## Example 1

```text
Input:
s = "abaccdbbd"
k = 3

Output:
2
```

We can select:

```text
"aba"
"dbbd"
```

Both are palindromes and have length at least `3`.

They do not overlap.

Therefore:

```text
Answer = 2
```

---

## Example 2

```text
Input:
s = "adbcda"
k = 2

Output:
0
```

There is no palindromic substring with length at least `2`.

Therefore:

```text
Answer = 0
```

---

# Approach

## Greedy + Palindrome Checking

The main idea is to scan the string from left to right.

For every possible right endpoint `r`, we try palindrome substrings ending at `r`.

We start checking lengths from the **smallest allowed length `k`**.

```java
for(int len = k; len <= r - start + 1; len++)
```

For a fixed ending position `r`, the smallest valid palindrome is preferred.

As soon as we find one:

```java
if(check(s, l, r)){
    ans++;
    start = r + 1;
    break;
}
```

we select it and move `start` after the selected palindrome.

---

# Why Greedy Works

The objective is to maximize the **number** of non-overlapping palindromes.

Whenever we find a valid palindrome ending at the current earliest possible position, we select it immediately.

This leaves the maximum possible remaining portion of the string available for future palindromes.

### Important Idea

```text
Choose the earliest possible ending palindrome
                    ↓
Free the remaining string as early as possible
                    ↓
More space for future substrings
                    ↓
Maximum number of non-overlapping palindromes
```

The algorithm does not need to choose the longest palindrome.

It is better to finish a valid palindrome as early as possible.

---

# How `start` Works

`start` represents the first index that is available for the next palindrome.

Initially:

```java
int start = 0;
```

Suppose we select:

```text
s[l ... r]
```

Then the next palindrome must start after `r`.

Therefore:

```java
start = r + 1;
```

This guarantees that selected substrings never overlap.

---

# How `r` Works

The outer loop:

```java
for(int r = k - 1; r < n; r++)
```

starts from `k - 1`.

Why?

Because the smallest allowed palindrome length is `k`.

The first possible substring of length `k` ends at index:

```text
k - 1
```

For example, if:

```text
k = 3
```

the first possible substring is:

```text
indices: 0 1 2
         └─────┘
            r = 2
```

---

# How `len` Works

For each right endpoint `r`, we try possible lengths starting from `k`.

```java
for(int len = k; len <= r - start + 1; len++)
```

Starting from `k` means we check the shortest valid substring first.

The left boundary is calculated as:

```java
int l = r - len + 1;
```

For example:

```text
r = 5
len = 3

l = 5 - 3 + 1
  = 3
```

So the substring is:

```text
s[3...5]
```

---

# Palindrome Checking

The helper method:

```java
private boolean check(String s, int l, int r)
```

uses the standard two-pointer technique.

We compare:

```text
left character
        ↓
right character
```

If they are different:

```java
if(s.charAt(l) != s.charAt(r)){
    return false;
}
```

Otherwise, move both pointers inward:

```java
l++;
r--;
```

If all corresponding characters match, the substring is a palindrome.

---

# Palindrome Example

Consider:

```text
"aba"
```

Pointers:

```text
a b a
↑   ↑
l   r
```

Compare:

```text
a == a
```

Move inward:

```text
a b a
  ↑
 l/r
```

Now:

```text
l >= r
```

Therefore:

```text
"aba" → palindrome
```

---

# Dry Run

Consider:

```text
s = "abaccdbbd"
k = 3
```

Indexes:

```text
0 1 2 3 4 5 6 7 8
a b a c c d b b d
```

---

## Step 1

Start:

```text
start = 0
```

First possible right endpoint:

```text
r = 2
```

Length:

```text
len = 3
```

Substring:

```text
s[0...2] = "aba"
```

Check:

```text
a == a
```

Therefore:

```text
"aba" is palindrome
```

Select it.

```text
ans = 1
start = 3
```

---

## Step 2

Now only the substring starting from index `3` can be considered.

```text
3 4 5 6 7 8
c c d b b d
```

As we scan forward, we eventually find:

```text
s[5...8] = "dbbd"
```

Check:

```text
d == d
b == b
```

Therefore:

```text
"dbbd" is palindrome
```

Select it.

```text
ans = 2
start = 9
```

---

## Final Answer

```text
2
```

Selected palindromes:

```text
"aba"
"dbbd"
```

---

# Algorithm

1. Initialize:
   ```text
   ans = 0
   start = 0
   ```

2. Iterate through possible right endpoints `r`.

3. For every `r`, try substring lengths starting from `k`.

4. Calculate:
   ```text
   l = r - len + 1
   ```

5. Check whether `s[l...r]` is a palindrome.

6. If it is a palindrome:
   - Increment `ans`.
   - Set:
     ```text
     start = r + 1
     ```
   - Stop checking more lengths for this `r`.

7. Continue scanning.

8. Return `ans`.

---

# Java Solution

```java
class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int ans = 0;
        int start = 0;

        for(int r = k - 1; r < n; r++){
            for(int len = k; len <= r - start + 1; len++){

                int l = r - len + 1;

                if(check(s, l, r)){
                    ans++;
                    start = r + 1;
                    break;
                }
            }
        }

        return ans;
    }

    private boolean check(String s, int l, int r){
        while(l < r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }

            l++;
            r--;
        }

        return true;
    }
}
```

---

# Complexity Analysis

Let:

```text
n = s.length()
```

The algorithm considers multiple ending positions and multiple substring lengths.

For each candidate substring, `check()` can take up to:

```text
O(n)
```

time in the worst case.

Therefore, the overall worst-case complexity of this implementation is:

### Time Complexity

```text
O(n³)
```

### Space Complexity

```text
O(1)
```

Apart from the input string, only a constant number of variables are used.

The recursion is not used.

---

# Why This Solution Is Efficient Enough

The constraint is:

```text
1 <= s.length <= 2000
```

Although the theoretical worst-case complexity is `O(n³)`, the algorithm stops checking lengths immediately after finding a valid palindrome and also moves `start` forward after selecting one.

Therefore, in practice, the solution can perform well for the given constraints.

---

# Important Edge Cases

### Case 1: `k = 1`

Every single character is a palindrome.

For example:

```text
s = "abc"
k = 1
```

We can select:

```text
"a"
"b"
"c"
```

Answer:

```text
3
```

---

### Case 2: No Valid Palindrome

```text
s = "adbcda"
k = 2
```

No valid palindrome of length at least `2`.

Answer:

```text
0
```

---

### Case 3: Entire String Is a Palindrome

```text
s = "racecar"
k = 3
```

The entire string is a valid palindrome.

Answer can be:

```text
1
```

---

### Case 4: Multiple Small Palindromes

If smaller valid palindromes can be selected earlier, choosing them can leave more space for additional palindromes.

This is why the greedy strategy prefers an earlier valid ending.

---

# Key Learning

## 1. Greedy Thinking

When the goal is to maximize the number of non-overlapping intervals/substrings, finishing a valid selection as early as possible can leave more room for future selections.

---

## 2. Two Pointers for Palindrome

The standard palindrome check is:

```text
left →      ← right
```

Compare both characters and move inward.

---

## 3. Non-overlapping Constraint

The variable:

```java
start = r + 1;
```

is what prevents overlapping selections.

---

## 4. Stop After Finding the First Valid Candidate

This line is important:

```java
break;
```

Once a palindrome ending at the current earliest possible `r` is found, we do not need to search for another palindrome ending at the same position.

---

# Pattern Recognition

When you see:

```text
Maximum number of non-overlapping
+
substring/interval
+
choose as many as possible
```

think about:

```text
Greedy
```

When you see:

```text
Palindrome substring
```

think about:

```text
Two Pointers
```

This problem combines both:

```text
Greedy
   +
Palindrome Checking
   +
Two Pointers
```

---

# Interview Explanation

> I scan the string from left to right and consider each position as the ending position of a possible palindrome. For each ending position, I try substring lengths starting from `k`, so the first valid palindrome I find ends as early as possible. Once I find a valid palindrome, I increment the answer and move `start` to `r + 1`, which guarantees that future substrings do not overlap. The palindrome check itself uses two pointers from both ends. This gives a greedy solution with O(n³) worst-case time and O(1) auxiliary space.

---

# Key Pattern

**Greedy + Two Pointers + Palindrome Checking**

---

# LeetCode

- Problem: **2472. Maximum Number of Non-overlapping Palindrome Substrings**
- Difficulty: **Hard**
- Status: **Solved**
- Approach: **Greedy + Two Pointer Palindrome Check**
- Time Complexity: **O(n³)**
- Space Complexity: **O(1)**
