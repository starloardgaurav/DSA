# 1111. Maximum Nesting Depth of Two Valid Parentheses Strings

## 🧩 Problem Statement

Given a valid parentheses string `seq`, split it into two disjoint subsequences `A` and `B` such that:

- Both `A` and `B` are valid parentheses strings.
- Every character of `seq` belongs to exactly one of the two subsequences.
- `max(depth(A), depth(B))` is minimized.

Return an array `answer` where:

```text
answer[i] = 0
```

if `seq[i]` belongs to `A`, otherwise:

```text
answer[i] = 1
```

Multiple valid answers are possible.

---

## 📌 LeetCode

**Problem:** [1111. Maximum Nesting Depth of Two Valid Parentheses Strings](https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/)

**Difficulty:** Medium

**Topics:**

- String
- Stack
- Bracket Sequences
- Greedy
- Depth Tracking

---

## 💡 Intuition

The main idea is to distribute nested parentheses between two groups.

If we divide parentheses according to the **parity of their nesting depth**, then:

```text
Depth 1 → Group 1
Depth 2 → Group 0
Depth 3 → Group 1
Depth 4 → Group 0
...
```

Therefore, we can simply use:

```text
depth % 2
```

to decide which group a parenthesis belongs to.

This keeps the nesting depth of each group balanced and minimizes the maximum depth.

---

## 🔑 Key Observation

While traversing the string:

### Opening Parenthesis

For:

```text
(
```

increase the current depth first:

```java
depth++;
```

Then assign:

```java
ans[i] = depth % 2;
```

### Closing Parenthesis

For:

```text
)
```

the bracket is closing the current depth.

Therefore, assign its group **before decreasing the depth**:

```java
ans[i] = depth % 2;
depth--;
```

This ordering is important.

---

## 🛠️ Approach

1. Create an answer array of size `seq.length()`.
2. Maintain a variable `depth`.
3. Traverse the string from left to right.
4. If the current character is `'('`:
   - Increase `depth`.
   - Assign `depth % 2` to the current position.
5. If the current character is `')'`:
   - Assign `depth % 2`.
   - Decrease `depth`.
6. Return the answer array.

---

## 🔄 Dry Run

### Input

```text
seq = "(()())"
```

Initial:

```text
depth = 0
```

| Index | Character | Depth Change | Group |
|---:|:---:|---:|---:|
| 0 | `(` | 0 → 1 | 1 |
| 1 | `(` | 1 → 2 | 0 |
| 2 | `)` | 2 → 1 | 0 |
| 3 | `(` | 1 → 2 | 0 |
| 4 | `)` | 2 → 1 | 0 |
| 5 | `)` | 1 → 0 | 1 |

Result:

```text
[1, 0, 0, 0, 0, 1]
```

This is one valid answer.

Another valid answer can also be:

```text
[0, 1, 1, 1, 1, 0]
```

because the problem allows multiple valid splits.

---

## 💻 Java Solution

```java
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            if (seq.charAt(i) == '(') {
                depth++;
                ans[i] = depth % 2;
            } else {
                ans[i] = depth % 2;
                depth--;
            }
        }

        return ans;
    }
}
```

---

## ⏱️ Complexity Analysis

Let:

```text
n = seq.length()
```

### Time Complexity

We traverse the string exactly once.

```text
O(n)
```

### Space Complexity

The answer array requires:

```text
O(n)
```

Auxiliary space apart from the output:

```text
O(1)
```

Therefore:

```text
Time:  O(n)
Space: O(n)
```

or

```text
Auxiliary Space: O(1)
```

---

## ⚖️ Why No Stack Is Required?

A stack is not necessary because we only need the **current nesting depth**.

We do not need to match or store individual parentheses.

We only maintain:

```text
depth
```

and use:

```text
depth % 2
```

to determine the group.

This reduces the auxiliary space from `O(n)` to `O(1)`.

---

## 🧠 Important Pattern

### Depth / Balance Tracking

Whenever a problem involves parentheses and asks about nesting depth, first consider maintaining:

```text
depth
```

Rules:

```text
'(' → depth++
')' → depth--
```

If the problem involves splitting nested structures, parity can sometimes be useful:

```text
depth % 2
```

---

## 🎯 Key Learning

- Track nesting depth using a simple counter.
- No stack is required when only the depth matters.
- Use `depth % 2` to divide nested parentheses between two groups.
- For a closing parenthesis, assign the group before decreasing the depth.
- Multiple valid outputs can exist.

---

## 🗣️ Interview Explanation

> I traverse the parentheses string while maintaining the current nesting depth.  
> For every opening parenthesis, I first increase the depth and assign the parenthesis to `depth % 2`.  
> For every closing parenthesis, I assign it to the current depth's group and then decrease the depth.  
> This alternates nested levels between the two groups, keeping the maximum nesting depth balanced.  
> The solution takes O(n) time and uses O(1) auxiliary space.

---

## 🚫 Common Mistakes

### 1. Decreasing depth before assigning a closing bracket

Incorrect:

```java
depth--;
ans[i] = depth % 2;
```

The closing bracket belongs to the nesting level it is closing.

Correct:

```java
ans[i] = depth % 2;
depth--;
```

### 2. Using a stack unnecessarily

A stack can track parentheses, but this problem only requires the current depth.

A counter is sufficient.

### 3. Assuming only one output is correct

The problem allows multiple valid answers.

The important requirement is that the resulting two subsequences are valid and minimize the maximum depth.

---

## 📂 File Structure

```text
Maximum-Nesting-Depth-of-Two-Valid-Parentheses-Strings-1111/
│
├── README.md
└── Solution.java
```

---

## ⭐ Pattern Summary

```text
Problem
   ↓
Parentheses
   ↓
Track Current Depth
   ↓
Use Depth Parity
   ↓
Assign Group 0 / Group 1
   ↓
O(n) Time
O(1) Auxiliary Space
```
