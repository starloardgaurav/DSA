# Minimum Add to Make Parentheses Valid — LeetCode 921

## 🧩 Problem

Given a parentheses string `s` containing only `(` and `)`, return the minimum number of parentheses that must be added to make the resulting parentheses string valid.

A parentheses string is valid when:

- Every opening parenthesis `(` has a matching closing parenthesis `)`.
- Parentheses are properly nested.

### Example 1

```text
Input: s = "())"
Output: 1
```

We need to add one `(` or `)` to make it valid.

For example:

```text
(())
```

### Example 2

```text
Input: s = "((("
Output: 3
```

Three closing parentheses are required.

### Example 3

```text
Input: s = "()"
Output: 0
```

The string is already valid.

---

## 💡 Approach

We use a **Stack** to keep track of unmatched parentheses.

### For `(`

Push it into the stack because it needs a future `)`.

### For `)`

There are three cases:

1. Stack is empty:
   - There is no `(` available to match this `)`.
   - Push `)` because it is unmatched.

2. Stack top is `(`:
   - The pair `()` is valid.
   - Remove the `(` using `pop()`.

3. Stack top is `)`:
   - There is still no unmatched `(` available.
   - Push this `)` as another unmatched parenthesis.

At the end, every character remaining in the stack represents one unmatched parenthesis.

Therefore:

```text
answer = stack.size()
```

---

## 🔑 Key Idea

Instead of actually adding parentheses, we simply count how many parentheses remain unmatched.

For example:

```text
s = "())"
```

Processing:

```text
( → push
) → pop
) → unmatched → push
```

At the end:

```text
stack = [)]
```

Therefore:

```text
answer = 1
```

---

## 🧠 Algorithm

1. Create an empty stack.
2. Traverse the string.
3. If the character is `(`, push it.
4. If the character is `)`:
   - If the stack is empty, push `)`.
   - Otherwise, if the top is `(`, pop it.
   - Otherwise, push `)`.
5. Return the size of the stack.

---

## 🔍 Dry Run

### Input

```text
s = "()))(("
```

| Character | Action | Stack |
|---|---|---|
| `(` | Push | `(` |
| `)` | Match and pop | Empty |
| `)` | Unmatched, push | `)` |
| `)` | Unmatched, push | `))` |
| `(` | Push | `))(` |
| `(` | Push | `))((` |

At the end:

```text
Stack size = 4
```

Therefore:

```text
Answer = 4
```

---

## 💻 Java Solution

```java
import java.util.Stack;

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(ch);
            }

            if (ch == ')') {
                if (stack.isEmpty()) {
                    stack.push(ch);
                }
                else if (stack.peek() == '(') {
                    stack.pop();
                }
                else {
                    stack.push(ch);
                }
            }
        }

        return stack.size();
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse the string once.

### Space Complexity

```text
O(n)
```

In the worst case, all parentheses can remain unmatched and be stored in the stack.

---

## 🚀 Approach 2 — Optimized Greedy / Balance

The stack solution works, but we don't actually need to store the parentheses.

We only need to know:

- How many unmatched `(` are currently present.
- How many unmatched `)` we have encountered.

So we maintain two variables:

```text
open = unmatched '('
add  = number of '(' that need to be added
```

### For `(`

Increase `open`:

```text
open++
```

### For `)`

If there is an unmatched `(`:

```text
open--
```

because the `(` and `)` form a valid pair.

Otherwise:

```text
add++
```

because this `)` has no matching `(`, so we must add one `(` before it.

### At the end

If `open > 0`, those unmatched opening parentheses need closing `)`.

Therefore:

```text
answer = open + add
```

---

## 🔍 Dry Run — Optimized Approach

### Input

```text
s = "()))(("
```

| Character | Action | `open` | `add` |
|---|---|---:|---:|
| `(` | `open++` | 1 | 0 |
| `)` | Match `(` → `open--` | 0 | 0 |
| `)` | No `(` → `add++` | 0 | 1 |
| `)` | No `(` → `add++` | 0 | 2 |
| `(` | `open++` | 1 | 2 |
| `(` | `open++` | 2 | 2 |

At the end:

```text
open = 2
add = 2
```

Therefore:

```text
answer = open + add
       = 2 + 2
       = 4
```

---

## 💻 Optimized Java Solution

```java
class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int add = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    add++;
                }
            }
        }

        return open + add;
    }
}
```

---

## ⏱️ Complexity — Optimized Approach

### Time Complexity

```text
O(n)
```

The string is traversed once.

### Space Complexity

```text
O(1)
```

Only two integer variables are used.

---

## ⚖️ Approach Comparison

| Approach | Idea | Time | Space |
|---|---|---|---|
| Stack | Store unmatched parentheses | O(n) | O(n) |
| Greedy / Balance | Store only counts | O(n) | O(1) |

### Preferred Approach

The **Greedy / Balance approach** is preferred because it achieves the same `O(n)` time complexity while reducing auxiliary space from `O(n)` to `O(1)`.

---

## 🎯 Key Learning

We don't always need a stack for parentheses problems.

If the actual identity of the unmatched parentheses is not important and we only need their **count**, we can replace the stack with a simple counter.

Here:

```text
Stack → counts of unmatched parentheses
```

becomes:

```text
open + add
```

This reduces the extra space from:

```text
O(n) → O(1)
```

---

## 🎯 Pattern

**Stack + Parentheses Matching**

Important concepts:

- Stack
- Matching parentheses
- Unmatched opening brackets
- Unmatched closing brackets
- Balance tracking

---

## 📌 Key Learning

For parentheses problems, always think about:

```text
What is currently unmatched?
```

For this problem:

- Unmatched `(` needs a `)`.
- Unmatched `)` needs a `(`.

The total number of unmatched parentheses is exactly the minimum number of additions required.

---

## 🗣️ Interview Explanation

> I use a stack to keep track of unmatched parentheses. Whenever I see an opening parenthesis, I push it. For a closing parenthesis, if the top is an opening parenthesis, I pop it because they form a valid pair. Otherwise, I push the closing parenthesis as unmatched. At the end, the number of elements remaining in the stack is the minimum number of parentheses that need to be added.

---

## 📂 Folder Structure

```text
Strings/
└── Minimum-Add-to-Make-Parentheses-Valid-921/
    ├── README.md
    └── Solution.java
```

---

## 🏷️ Tags

`Java` `LeetCode` `Strings` `Stack` `Parentheses` `Greedy` `Matching` `O(n)`
