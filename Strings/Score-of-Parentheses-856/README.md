# Score of Parentheses — LeetCode 856

## 🧩 Problem

Given a balanced parentheses string `s`, return its score.

The score is calculated using these rules:

- `()` has score `1`.
- `AB` has score `A + B`, where `A` and `B` are balanced parentheses strings.
- `(A)` has score `2 * A`, where `A` is a balanced parentheses string.

### Example 1

```text
Input: s = "()"
Output: 1
```

### Example 2

```text
Input: s = "(())"
Output: 2
```

### Example 3

```text
Input: s = "()()"
Output: 2
```

### Example 4

```text
Input: s = "(()(()))"
Output: 6
```

---

## 💡 Approach

Instead of using a stack, we can solve the problem using the **current nesting depth**.

We maintain:

```text
depth = current nesting depth
ans   = total score
```

Whenever we encounter:

```text
()
```

this primitive pair contributes:

```text
2^depth
```

where `depth` is the depth after processing the closing `)`.

This works because every extra pair of parentheses doubles the score.

---

## 🔑 Key Observation

The basic score is:

```text
() = 1
```

If it is wrapped inside one pair:

```text
(()) = 2
```

Two levels:

```text
((())) = 4
```

Therefore:

```text
score = 2^depth
```

For bit manipulation, we can calculate this as:

```java
1 << depth
```

because:

```text
1 << d = 2^d
```

---

## 🧠 Algorithm

1. Initialize `depth = 0`.
2. Initialize `ans = 0`.
3. Traverse the string character by character.
4. If the character is `(`:
   - Increase `depth`.
5. If the character is `)`:
   - Decrease `depth`.
   - If the previous character was `(`, we found a primitive `()`.
   - Add `2^depth` to `ans`.
6. Return `ans`.

---

## 🔍 Dry Run

### Input

```text
s = "(()(()))"
```

| Character | Action | Depth | Score Added | Total |
|---|---|---:|---:|---:|
| `(` | depth++ | 1 | 0 | 0 |
| `(` | depth++ | 2 | 0 | 0 |
| `)` | depth-- | 1 | `2^1 = 2` | 2 |
| `(` | depth++ | 2 | 0 | 2 |
| `(` | depth++ | 3 | 0 | 2 |
| `)` | depth-- | 2 | `2^2 = 4` | 6 |
| `)` | depth-- | 1 | 0 | 6 |
| `)` | depth-- | 0 | 0 | 6 |

Therefore:

```text
Answer = 6
```

---

## 💻 Java Solution

```java
class Solution {
    public int scoreOfParentheses(String s) {
        int depth = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;

                if (s.charAt(i - 1) == '(') {
                    ans += 1 << depth;
                }
            }
        }

        return ans;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse the string exactly once.

### Space Complexity

```text
O(1)
```

Only `depth` and `ans` variables are used.

---

## 🎯 Pattern

**Parentheses + Depth Tracking + Mathematical Observation**

Important concepts:

- Parentheses depth
- Primitive `()`
- Bit shifting
- `2^depth`
- One-pass traversal

---

## 📌 Key Learning

The main trick is recognizing that a primitive `()` contributes a score based on its nesting depth.

Instead of explicitly calculating the recursive structure using a stack, we can directly calculate:

```text
()       → 1
(())     → 2
((()))   → 4
```

So whenever we encounter `()`, we add:

```text
2^currentDepth
```

This gives an `O(n)` time and `O(1)` space solution.

---

## 🗣️ Interview Explanation

> I maintain the current nesting depth while traversing the parentheses string. Whenever I find a primitive `()`, its score is `2^depth`, because every surrounding pair doubles the score. I decrease the depth when processing `)` and add the contribution only when the previous character is `(`. This allows the problem to be solved in O(n) time and O(1) extra space without using a stack.

---

## 📂 Folder Structure

```text
Strings/
└── Score-of-Parentheses-856/
    ├── README.md
    └── Solution.java
```

---

## 🏷️ Tags

`Java` `LeetCode` `Strings` `Parentheses` `Depth Tracking` `Math` `Bit Manipulation` `O(n)`
