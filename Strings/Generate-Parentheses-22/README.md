# 22. Generate Parentheses

## 🟢 Problem

Given `n` pairs of parentheses, generate all combinations of **well-formed parentheses**.

### Example

**Input:**
```text
n = 3
```

**Output:**
```text
["((()))","(()())","(())()","()(())","()()()"]
```

---

## 💡 Approach

This problem is solved using **Backtracking**.

At every step, we have two choices:

1. Add an opening bracket `(`
2. Add a closing bracket `)`

But we need to maintain the condition that the generated sequence always remains valid.

### Rules

- We can add `(` while `open < n`.
- We can add `)` only when `close < open`.
- When `open == n` and `close == n`, we have generated one valid answer.

The condition:

```java
if (close < open)
```

prevents invalid sequences such as:

```text
())(
```

because we can never close more parentheses than we have opened.

---

## 🔍 Algorithm

1. Start with:
   - `open = 0`
   - `close = 0`
   - `current = ""`

2. If `open < n`, add `(` and recursively continue.

3. If `close < open`, add `)` and recursively continue.

4. When both `open` and `close` become `n`, add the current string to the result.

---

## 💻 Java Solution

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        fun(0, 0, n, "", res);

        return res;
    }

    public void fun(int o, int c, int n, String s, List<String> res) {

        // Both opening and closing brackets are used
        if (o == n && c == n) {
            res.add(s);
            return;
        }

        // Add opening bracket
        if (o < n) {
            fun(o + 1, c, n, s + "(", res);
        }

        // Add closing bracket only if it keeps the sequence valid
        if (c < o) {
            fun(o, c + 1, n, s + ")", res);
        }
    }
}
```

---

## 🧪 Dry Run

For:

```text
n = 2
```

The recursion generates:

```text
(
├── (
│   └── )
│       └── )
│
└── )
    └── (
        └── )
```

Valid combinations:

```text
(())
()()
```

Therefore:

```text
Output = ["(())", "()()"]
```

---

## ⏱️ Complexity

The number of valid parentheses combinations is the Catalan number:

```text
C(n) = 1/(n+1) * C(2n,n)
```

Each generated string has length `2n`.

### Time Complexity

```text
O(C(n) × n)
```

### Space Complexity

Auxiliary recursion space:

```text
O(n)
```

Result storage:

```text
O(C(n) × n)
```

---

## 🧠 Key Learning

The most important condition in this problem is:

```java
close < open
```

It guarantees that we never create an invalid parentheses sequence.

This is a classic example of **Backtracking with constraint pruning**.

Instead of generating all possible strings and checking them afterward, we avoid invalid choices during recursion itself.

---

## 🎯 Pattern

- Backtracking
- Recursion
- Constraint Pruning
- Catalan Number

---

## ⚠️ Common Mistakes

### 1. Allowing `close > open`

Wrong:

```java
if (close < n) {
    // add ')'
}
```

This can generate invalid parentheses.

Correct:

```java
if (close < open) {
    // add ')'
}
```

### 2. Forgetting the base case

We must add the string only when:

```java
open == n && close == n
```

### 3. Adding more than `n` opening brackets

Always check:

```java
if (open < n)
```

---

## 🗣️ Interview Explanation

> I use backtracking to generate all valid parentheses combinations.  
> I maintain two counters: `open` and `close`.  
> I can add an opening bracket while `open < n`. I can add a closing bracket only when `close < open`, which ensures that the number of closing brackets never exceeds the number of opening brackets. When both counters reach `n`, I add the generated string to the result.

---

## 📌 Problem Information

| Property | Value |
|---|---|
| LeetCode | 22 |
| Difficulty | Medium |
| Pattern | Backtracking |
| Language | Java |
| Time | O(C(n) × n) |
| Auxiliary Space | O(n) |

---

## 🔗 LeetCode

[Generate Parentheses - LeetCode 22](https://leetcode.com/problems/generate-parentheses/)
