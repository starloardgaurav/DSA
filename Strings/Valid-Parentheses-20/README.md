# 20. Valid Parentheses

## 🧩 Problem Statement

Given a string `s` containing only:

```text
( ) [ ] { }
```

determine whether the string is valid.

A valid parentheses string must satisfy:

1. Every opening bracket is closed by the same type of bracket.
2. Brackets are closed in the correct order.
3. Every closing bracket has a corresponding opening bracket.

---

## 📌 LeetCode

**Problem:** [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

**Difficulty:** Easy

**Topics:**

- String
- Stack
- Parentheses
- Bracket Matching

---

## 💡 Intuition

The natural data structure for matching brackets is a **Stack**.

Whenever we see an opening bracket, instead of storing the opening bracket itself, we store the **closing bracket that we expect to see later**.

For example:

```text
'(' → ')'
'[' → ']'
'{' → '}'
```

This makes the matching operation very simple.

When a closing bracket appears:

- The stack must not be empty.
- The closing bracket must match the top of the stack.
- If it does not match, the string is invalid.

---

## 🔑 Key Idea

Instead of:

```text
Opening bracket → push opening bracket
```

we use:

```text
Opening bracket → push expected closing bracket
```

So:

```java
'(' → push ')'
'[' → push ']'
'{' → push '}'
```

Then for a closing bracket:

```java
stack.pop() == currentCharacter
```

means the brackets match.

---

## 🛠️ Approach

1. Create a stack.
2. Traverse every character of the string.
3. If the character is an opening bracket:
   - Push its corresponding closing bracket.
4. If the character is a closing bracket:
   - If the stack is empty, return `false`.
   - Pop the expected closing bracket.
   - If it does not match the current character, return `false`.
5. After processing the complete string:
   - If the stack is empty, return `true`.
   - Otherwise, return `false`.

---

## 🔄 Dry Run

### Example 1

```text
Input:
s = "()"
```

Processing:

```text
'(' → push ')'

Stack:
[ ')' ]

')' → pop ')' → matches
```

Stack becomes empty.

```text
Output: true
```

---

### Example 2

```text
Input:
s = "([{}])"
```

| Character | Operation | Stack |
|---|---|---|
| `(` | push `)` | `)` |
| `[` | push `]` | `), ]` |
| `{` | push `}` | `), ], }` |
| `}` | pop `}` | `), ]` |
| `]` | pop `]` | `)` |
| `)` | pop `)` | empty |

Final stack:

```text
empty
```

Therefore:

```text
Output: true
```

---

### Example 3

```text
Input:
s = "([)]"
```

Processing:

```text
'(' → push ')'
'[' → push ']'
')' → expected ']'
```

But:

```text
']' != ')'
```

Therefore:

```text
Output: false
```

---

## 💻 Java Solution

```java
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(')');
            } 
            else if (ch == '[') {
                stack.push(']');
            } 
            else if (ch == '{') {
                stack.push('}');
            } 
            else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

---

## ⏱️ Complexity Analysis

Let:

```text
n = s.length()
```

### Time Complexity

Every character is processed once.

```text
O(n)
```

### Space Complexity

In the worst case, all characters can be opening brackets.

For example:

```text
"((((((("
```

The stack can contain `n` elements.

Therefore:

```text
O(n)
```

### Final Complexity

```text
Time:  O(n)
Space: O(n)
```

---

## ⚖️ Why Stack?

Parentheses must be closed in **reverse order of opening**.

For example:

```text
([{}])
```

The last opening bracket is:

```text
{
```

so it must be closed first:

```text
}
```

This is exactly **LIFO — Last In, First Out**, which is the fundamental property of a Stack.

---

## 🧠 Important Pattern

### Stack for Matching

Whenever a problem asks you to:

- Match opening and closing brackets
- Validate nested structures
- Process nested expressions
- Reverse nested structures
- Maintain the most recently opened item

consider using a **Stack**.

---

## 🎯 Key Learning

- Parentheses matching is a classic Stack problem.
- Stack follows LIFO order.
- Store expected closing brackets to simplify matching.
- Always check `stack.isEmpty()` before accessing the top.
- The final stack must be empty for a valid sequence.
- Early return can avoid unnecessary processing.

---

## 🗣️ Interview Explanation

> I use a stack to validate the bracket sequence because brackets must close in reverse order of opening. For every opening bracket, I push its corresponding closing bracket onto the stack. When I encounter a closing bracket, I check whether the stack is non-empty and whether its top matches the current character. If not, I immediately return false. After processing the entire string, the stack must be empty for the sequence to be valid. The solution takes O(n) time and O(n) space.

---

## 🚫 Common Mistakes

### 1. Forgetting the Empty Stack Check

Incorrect:

```java
if (stack.pop() != ch)
```

If the stack is empty, this can cause an exception.

Correct:

```java
if (stack.isEmpty() || stack.pop() != ch)
```

---

### 2. Checking Only the Number of Brackets

A string can have equal numbers of opening and closing brackets and still be invalid.

Example:

```text
([)]
```

The order is incorrect.

---

### 3. Forgetting the Final Stack Check

Consider:

```text
"((("
```

No mismatched closing bracket exists, but the string is still invalid.

Therefore:

```java
return stack.isEmpty();
```

is necessary.

---

## 📂 File Structure

```text
Valid-Parentheses-20/
│
├── README.md
└── Solution.java
```

---

## ⭐ Pattern Summary

```text
Parentheses
     ↓
Need Matching
     ↓
LIFO Order
     ↓
Stack
     ↓
Push Expected Closing Bracket
     ↓
Match While Traversing
     ↓
Check Stack Is Empty
     ↓
O(n) Time / O(n) Space
```
