# 🧠 Dynamic Programming — DSA Placement Preparation

This folder contains **Dynamic Programming (DP)** problems solved during my DSA placement preparation.

The main focus is on understanding:

- DP State Design
- Memoization
- Tabulation
- DFS + DP
- Matrix DP
- Optimization
- State Transitions
- Pruning
- Time and Space Complexity

---

## 📚 Problems Solved

| # | Problem | Difficulty | Approach | Time | Space | Solution |
|---|---|---|---|---|---|---|
| 1 | [Check if There Is a Valid Parentheses String Path](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/) | Hard | DFS + Memoization + Matrix DP | O(m × n × (m+n)) | O(m × n × (m+n)) | [View](./Check-if-There-Is-a-Valid-Parentheses-String-Path-2267/) |

---

## 🔥 Problem Details

### 1️⃣ Check if There Is a Valid Parentheses String Path

**LeetCode:** [2267](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/)

**Difficulty:** Hard

**Pattern:**
- DFS
- Memoization
- Matrix DP
- Parentheses Balance
- Pruning

**Idea:**

We start from `(0,0)` and can move only:

- Down
- Right

For every path, we maintain the current parentheses `balance`.

```text
'(' → balance + 1
')' → balance - 1
```

A valid parentheses string must satisfy:

```text
balance never becomes negative
final balance = 0
```

The DP state is:

```text
dp[row][col][balance]
```

which represents whether it is possible to reach the destination from the current cell with the given balance.

### Important Pruning

If:

```text
balance < 0
```

the current path is invalid.

Also, if the number of remaining cells is smaller than the current balance:

```text
balance > remaining
```

then there are not enough `)` characters left to close all open parentheses.

Therefore, we can immediately stop exploring that path.

### Complexity

Let:

```text
m = number of rows
n = number of columns
L = m + n - 1
```

Possible DP states:

```text
m × n × L
```

Therefore:

```text
Time Complexity:  O(m × n × (m+n))
Space Complexity: O(m × n × (m+n))
```

---

## 🧩 DP Patterns

### DFS + Memoization

- [x] Check if There Is a Valid Parentheses String Path

### Matrix DP

- [x] Check if There Is a Valid Parentheses String Path

### Balance / State Tracking

- [x] Parentheses Balance DP

### Pruning

- [x] Invalid balance pruning
- [x] Remaining cells pruning

---

## 📈 Progress

| Category | Solved |
|---|---:|
| Dynamic Programming | **1** |
| Total DP Problems | **1** |

### 🎯 Goals

- [ ] 10 DP Problems
- [ ] 25 DP Problems
- [ ] 50 DP Problems
- [ ] 75 DP Problems
- [ ] 100 DP Problems

---

## 📂 Folder Structure

```text
Dynamic-Programming/
│
├── README.md
│
└── Check-if-There-Is-a-Valid-Parentheses-String-Path-2267/
    ├── README.md
    └── Solution.java
```

---

## 🧠 Key DP Concepts to Learn

```text
1. State Definition
2. State Transition
3. Base Case
4. Memoization
5. Tabulation
6. DFS + DP
7. Matrix DP
8. Space Optimization
9. Pruning
10. Identifying Overlapping Subproblems
```

---

## 🚀 Placement Preparation Strategy

For every DP problem, focus on these questions:

### 1. What is the state?

Ask:

```text
What information is required to uniquely describe a subproblem?
```

### 2. What is the transition?

Ask:

```text
How can the current state be obtained from previous states?
```

### 3. What is the base case?

Ask:

```text
When does the recursion/DP stop?
```

### 4. Can states repeat?

If yes, memoization or tabulation may be useful.

### 5. Can impossible states be eliminated?

Use pruning wherever possible.

---

## ⭐ Key Learning

> In Dynamic Programming, the most important part is not writing the DP array.  
> The most important part is correctly identifying the **state and transition**.

---

## 📌 Repository

Main DSA Repository:

[DSA Placement Preparation](https://github.com/starloardgaurav/DSA)
