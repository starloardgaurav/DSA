# Remove Outermost Parentheses — LeetCode 1021

## 🧩 Problem

A valid parentheses string can be decomposed into primitive valid parentheses strings.

For every primitive parentheses string, remove its outermost pair of parentheses and return the resulting string.

### Example 1

```text
Input: s = "(()())(())"

Output: "()()()"
```

The primitive decomposition is:

```text
"(()())" + "(())"
```

After removing the outermost parentheses:

```text
"()()" + "()"
```

Therefore:

```text
"()()()"
```

### Example 2

```text
Input: s = "(()())(())(()(()))"

Output: "()()()()(())"
```

### Example 3

```text
Input: s = "()()"

Output: ""
```

The primitive decomposition is:

```text
"()" + "()"
```

Removing the outermost parentheses from both gives an empty string.

---

## 💡 Approach

We can solve this problem using a simple **balance/depth counter**.

Maintain:

```text
count = current parentheses depth
```

### When we see `(`

If:

```text
count != 0
```

then this `(` is not the outermost opening parenthesis, so we add it to the result.

Then increase the depth:

```text
count++
```

### When we see `)`

First decrease the depth:

```text
count--
```

If:

```text
count != 0
```

then this `)` is not the outermost closing parenthesis, so we add it to the result.

This automatically removes the first `(` and last `)` of every primitive substring.

---

## 🔑 Key Observation

The outermost parentheses of a primitive string are exactly the parentheses where the depth changes between:

```text
0 → 1
```

and:

```text
1 → 0
```

We don't want to include those characters.

For example:

```text
(()())
```

Depth:

```text
( → 1
( → 2
) → 1
( → 2
) → 1
) → 0
```

The first `(` creates:

```text
0 → 1
```

and the last `)` creates:

```text
1 → 0
```

Those are the outermost parentheses, so we skip them.

The remaining string is:

```text
()()
```

---

## 🧠 Algorithm

1. Initialize `count = 0`.
2. Create a `StringBuilder` for the answer.
3. Traverse the string.
4. For `(`:
   - If `count != 0`, append it.
   - Increment `count`.
5. For `)`:
   - Decrement `count`.
   - If `count != 0`, append it.
6. Return the resulting string.

---

## 🔍 Dry Run

### Input

```text
s = "(()())(())"
```

| Character | Action | Count | Result |
|---|---|---:|---|
| `(` | Outer opening → skip | 1 | `""` |
| `(` | Append | 2 | `(` |
| `)` | Append | 1 | `()` |
| `(` | Append | 2 | `()(` |
| `)` | Append | 1 | `()()` |
| `)` | Outer closing → skip | 0 | `()()` |
| `(` | Outer opening → skip | 1 | `()()` |
| `(` | Append | 2 | `()()(` |
| `)` | Append | 1 | `()()` |
| `)` | Outer closing → skip | 0 | `()()` |

Final answer:

```text
"()()()"
```

---

## 💻 Java Solution

```java
class Solution {
    public String removeOuterParentheses(String s) {
        int count = 0;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                if (count != 0) {
                    result.append(ch);
                }
                count++;
            } else {
                count--;

                if (count != 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
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
O(n)
```

The `StringBuilder` stores the resulting string.

The extra variables themselves use:

```text
O(1)
```

auxiliary space.

---

## 🎯 Pattern

**Balance Tracking / Parentheses Depth**

Important concepts:

- Parentheses depth
- Balance counter
- Primitive parentheses strings
- StringBuilder
- One-pass traversal

---

## 📌 Key Learning

For many parentheses problems, we don't necessarily need a stack.

If we only care about the **current nesting depth**, a simple counter is enough.

The important observation is:

```text
count == 0
```

means we are currently outside a primitive parentheses group.

Therefore:

```text
Opening bracket:
append if count != 0
then count++

Closing bracket:
count--
append if count != 0
```

This lets us remove the outermost parentheses in one pass.

---

## 🆚 Stack vs Counter

A stack is unnecessary here because we don't need to know which particular opening parenthesis matches a closing parenthesis.

We only need the current nesting depth.

Therefore:

```text
Stack → unnecessary
Counter → sufficient
```

This gives a simple `O(n)` solution.

---

## 🗣️ Interview Explanation

> I maintain the current nesting depth using a counter. For every opening parenthesis, I append it only when the current depth is greater than zero, because depth zero means it is the outermost opening parenthesis. For every closing parenthesis, I first decrease the depth and append it only if the new depth is greater than zero. This skips the outermost pair of every primitive substring and solves the problem in one pass.

---

## 📂 Folder Structure

```text
Strings/
└── Remove-Outermost-Parentheses-1021/
    ├── README.md
    └── Solution.java
```

---

## 🏷️ Tags

`Java` `LeetCode` `Strings` `Parentheses` `Balance Tracking` `Depth` `StringBuilder` `O(n)`
