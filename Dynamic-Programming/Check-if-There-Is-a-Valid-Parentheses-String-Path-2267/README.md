# 🔗 Check if There Is a Valid Parentheses String Path

## 📌 Problem

Given an `m x n` grid containing only:

```text
'('
')'
```

we need to determine whether there exists a path from the top-left cell to the bottom-right cell such that:

- The path starts at `(0, 0)`
- The path ends at `(m - 1, n - 1)`
- We can move only **right** or **down**
- The parentheses formed by the path are a **valid parentheses string**

Return:

```text
true
```

if such a path exists, otherwise return:

```text
false
```

---

## 🧠 Key Observation

A parentheses string is valid when:

1. At every point, the number of `(` seen so far is at least the number of `)`.
2. At the end, the total number of `(` and `)` must be equal.

We can represent this using a variable:

```text
balance
```

### When we see `(`

```text
balance++
```

### When we see `)`

```text
balance--
```

Therefore:

```text
balance < 0
```

means the current path is already invalid.

At the destination:

```text
balance == 0
```

is required.

---

# 🔍 Important Initial Observation

The total number of cells visited by every path is:

```text
m + n - 1
```

A valid parentheses string must have an even length.

Therefore, if:

```text
(m + n - 1) is odd
```

the answer is immediately:

```text
false
```

---

# 🚀 Approach

We use:

```text
DFS + Memoization + Pruning
```

The DFS state is:

```text
(row, column, balance)
```

This means:

> Can we reach the bottom-right cell from `(row, column)` when the current parentheses balance is `balance`?

---

# 🧩 DP State

We use:

```java
Boolean[][][] dp;
```

where:

```text
dp[r][c][balance]
```

stores whether a valid path exists from cell `(r,c)` with the given balance.

Each state is calculated only once.

---

# 🔹 Step 1 — Basic Checks

First calculate the path length:

```java
int len = m + n - 1;
```

If it is odd:

```java
if ((len & 1) == 1) {
    return false;
}
```

A valid parentheses string cannot have odd length.

---

# 🔹 Step 2 — Starting Cell

The first character must be:

```text
(
```

Therefore:

```java
if (grid[0][0] != '(') {
    return false;
}
```

If the path starts with `)`, the balance immediately becomes negative.

---

# 🔹 Step 3 — Ending Cell

The final character must be:

```text
)
```

Therefore:

```java
if (grid[m - 1][n - 1] != ')') {
    return false;
}
```

A valid parentheses sequence must finish by closing an open parenthesis.

---

# 🔹 Step 4 — DFS

We start from:

```text
(0, 0, 0)
```

```java
return dfs(grid, 0, 0, 0);
```

The `balance` starts at `0`.

---

# 🔹 Step 5 — Update Balance

For each cell:

```java
if (grid[r][c] == '(') {
    balance++;
} else {
    balance--;
}
```

For example:

```text
(
(
)
```

balances become:

```text
1
2
1
```

---

# 🔥 Pruning 1 — Negative Balance

If:

```java
balance < 0
```

the path can never become valid.

Therefore:

```java
if (balance < 0) {
    return false;
}
```

Example:

```text
)
```

Balance:

```text
0 → -1
```

Immediately invalid.

---

# 🔥 Pruning 2 — Not Enough Remaining Cells

We calculate the number of cells still remaining:

```java
int remaining =
    (m - 1 - r) + (n - 1 - c);
```

Suppose:

```text
balance = 5
remaining = 2
```

Even if both remaining cells are `)`, the balance can decrease by only `2`:

```text
5 → 4 → 3
```

It can never reach `0`.

Therefore:

```java
if (balance > remaining) {
    return false;
}
```

This is an important pruning optimization.

---

# 🔹 Step 6 — Destination

When we reach:

```java
r == m - 1 && c == n - 1
```

the entire path has been processed.

A valid parentheses string requires:

```text
balance == 0
```

Therefore:

```java
if (r == m - 1 && c == n - 1) {
    return balance == 0;
}
```

---

# 🔹 Step 7 — Memoization

Before calculating a state again:

```java
if (dp[r][c][balance] != null) {
    return dp[r][c][balance];
}
```

This avoids solving the same state repeatedly.

---

# 🔹 Step 8 — Try Both Directions

From every cell, there are at most two possible moves:

```text
Down
Right
```

Therefore:

```java
boolean down = dfs(grid, r + 1, c, balance);

boolean right = dfs(grid, r, c + 1, balance);
```

If either path works:

```java
down || right
```

the state is valid.

---

# 🧪 Dry Run

Consider a path:

```text
( ( ) ( ) )
```

Process the characters:

| Character | Balance |
|---|---:|
| `(` | 1 |
| `(` | 2 |
| `)` | 1 |
| `(` | 2 |
| `)` | 1 |
| `)` | 0 |

At no point does balance become negative.

At the end:

```text
balance = 0
```

Therefore, this path forms a valid parentheses string.

---

# 🌳 DFS State

Suppose we are at:

```text
(r, c, balance)
```

There are two possible transitions:

```text
                 (r,c,balance)
                   /        \
                  /          \
             Down              Right
               /                  \
      (r+1,c,balance')       (r,c+1,balance')
```

where:

```text
balance'
```

depends on the character in the next cell.

Memoization ensures that the same state is not recalculated.

---

# 💻 Java Solution

```java
class Solution {

    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int len = m + n - 1;

        // Valid parentheses string must have even length
        if ((len & 1) == 1) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] != '(') {
            return false;
        }

        // Last character must be ')'
        if (grid[m - 1][n - 1] != ')') {
            return false;
        }

        dp = new Boolean[m][n][len + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {

        int m = grid.length;
        int n = grid[0].length;

        // Out of bounds
        if (r >= m || c >= n) {
            return false;
        }

        // Update balance using current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid prefix
        if (balance < 0) {
            return false;
        }

        // Not enough remaining cells to close all open brackets
        int remaining = (m - 1 - r) + (n - 1 - c);

        if (balance > remaining) {
            return false;
        }

        // Destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Already calculated
        if (dp[r][c][balance] != null) {
            return dp[r][c][balance];
        }

        // Move down or right
        boolean down = dfs(grid, r + 1, c, balance);
        boolean right = dfs(grid, r, c + 1, balance);

        return dp[r][c][balance] = down || right;
    }
}
```

---

# 🔍 Code Explanation

## 1. DP Array

```java
Boolean[][][] dp;
```

Three dimensions represent:

```text
row
column
balance
```

So:

```text
dp[r][c][balance]
```

means:

> From cell `(r,c)`, with the current balance, is there a valid path to the destination?

---

## 2. Why `Boolean` Instead of `boolean`?

We use:

```java
Boolean
```

because we need three possible states:

```text
null  → not calculated yet
true  → valid path exists
false → no valid path
```

A primitive `boolean` cannot distinguish:

```text
not calculated
```

from:

```text
false
```

---

## 3. Why Balance Is Enough?

We don't need to remember the complete path.

For validating parentheses, the only important information about the prefix is:

```text
current balance
```

Therefore, two different paths that reach the same:

```text
(row, column, balance)
```

have the same future possibilities.

This is exactly why memoization works.

---

# 🔥 Why Pruning Is Correct

## Negative Balance

If:

```text
balance < 0
```

we already have more closing brackets than opening brackets.

No future characters can repair an invalid prefix.

Therefore, return `false`.

---

## Balance Greater Than Remaining

Suppose:

```text
balance = 4
remaining = 2
```

At most two `)` characters remain.

The best possible balance is:

```text
4 - 2 = 2
```

So reaching zero is impossible.

Therefore:

```java
if (balance > remaining)
    return false;
```

---

# ⏱️ Complexity Analysis

Let:

```text
m = number of rows
n = number of columns
L = m + n - 1
```

The balance can range from:

```text
0 to L
```

Therefore, the number of possible DP states is:

```text
O(m × n × L)
```

Each state performs only constant work apart from two recursive transitions.

### Time Complexity

```text
O(m × n × (m + n))
```

### Space Complexity

The memoization table stores:

```text
O(m × n × (m + n))
```

states.

The recursion stack is at most:

```text
O(m + n)
```

Therefore, overall space is:

```text
O(m × n × (m + n))
```

---

# 🧩 Pattern

This problem combines several important patterns:

### Dynamic Programming

```text
(row, column, balance)
```

is the DP state.

### DFS + Memoization

We explore paths recursively and cache repeated states.

### Matrix Traversal

Movement is restricted to:

```text
Down
Right
```

### Parentheses / Balance

The current number of unmatched opening parentheses is tracked using `balance`.

### Pruning

Impossible states are rejected early.

---

# 🎯 Key Learning

1. A valid parentheses string can be represented using a balance counter.
2. The balance must never become negative.
3. The final balance must be zero.
4. In a grid path problem, the path itself does not always need to be stored.
5. The state `(row, column, balance)` contains enough information for the future.
6. Memoization converts repeated DFS states into DP.
7. Strong pruning can eliminate many impossible states.
8. Always look for mathematical conditions that can reject a problem before DFS.

---

# 🗣️ Interview Explanation

> "I model the problem using DFS with memoization. The important state is the current row, column, and parentheses balance. When I visit an opening parenthesis I increase the balance, and for a closing parenthesis I decrease it. A state becomes invalid if the balance becomes negative. I also prune states where the current balance is greater than the number of remaining cells because there aren't enough closing parentheses to bring the balance to zero. At the destination, the balance must be zero. Since the same `(row, column, balance)` state can be reached through multiple paths, I memoize it."

---

# ⚠️ Common Mistakes

### 1. Only checking the final balance

This is not enough.

For example:

```text
)(
```

has final balance:

```text
0
```

but it is not valid because the balance becomes negative at the first character.

So we must check:

```text
balance >= 0
```

throughout the path.

---

### 2. Forgetting path length parity

A valid parentheses string must contain equal numbers of:

```text
(
)
```

Therefore, its length must be even.

The path length is:

```text
m + n - 1
```

If it is odd, the answer is immediately false.

---

### 3. Using only `(row, column)` as DP state

Two paths can reach the same cell with different balances.

For example:

```text
(r, c, 1)
(r, c, 3)
```

have different future possibilities.

Therefore, `balance` must be part of the state.

---

### 4. Forgetting the starting cell condition

If:

```text
grid[0][0] == ')'
```

the path is immediately invalid.

---

## 📌 Complexity Summary

| Metric | Complexity |
|---|---|
| DP States | O(m × n × (m+n)) |
| Time | O(m × n × (m+n)) |
| Space | O(m × n × (m+n)) |
| Pattern | DFS + Memoization + Pruning |

---

## 🔗 LeetCode

[Check if There Is a Valid Parentheses String Path - LeetCode 2267](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/)

---

## ✅ Status

- [x] Problem Solved
- [x] DFS
- [x] Dynamic Programming
- [x] Memoization
- [x] Matrix Traversal
- [x] Balance Tracking
- [x] Pruning
- [x] Dry Run
- [x] Complexity Analysis
- [x] Interview Explanation
- [x] Key Learning
