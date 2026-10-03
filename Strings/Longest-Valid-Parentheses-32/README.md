# 32. Longest Valid Parentheses

## 🔴 Problem

Given a string containing only `'('` and `')'`, return the length of the longest valid (well-formed) parentheses substring.

### Example 1

**Input:**
```text
s = "(()"
```

**Output:**
```text
2
```

Explanation:

```text
"()"
```

is the longest valid parentheses substring.

### Example 2

**Input:**
```text
s = ")()())"
```

**Output:**
```text
4
```

The longest valid substring is:

```text
"()()"
```

### Example 3

**Input:**
```text
s = ""
```

**Output:**
```text
0
```

---

## 💡 Approach

This solution uses a **Stack of indices**.

The stack stores indices of unmatched opening brackets and a special boundary index.

Initially:

```java
stack.push(-1);
```

The `-1` acts as a base index for calculating the length of a valid substring starting from index `0`.

---

## 🔑 Key Idea

### When we see `'('`

Push its index into the stack:

```java
stack.push(i);
```

This index represents an unmatched opening bracket.

### When we see `')'`

First remove the matching opening bracket:

```java
stack.pop();
```

Then there are two possibilities.

### Case 1: Stack becomes empty

```java
if(stack.isEmpty()){
    stack.push(i);
}
```

This means the current `')'` does not have a matching `'('`.

Therefore, this index becomes a new boundary.

### Case 2: Stack is not empty

```java
maax = Math.max(maax, i - stack.peek());
```

The index at the top of the stack represents the position immediately before the current valid substring.

Therefore:

```text
length = currentIndex - stack.peek()
```

---

## 🧠 Algorithm

1. Create a stack of integers.
2. Push `-1` initially.
3. Traverse the string from left to right.
4. If the current character is `'('`, push its index.
5. If the current character is `')'`:
   - Pop the stack.
   - If the stack becomes empty, push the current index as a new boundary.
   - Otherwise, calculate the current valid substring length.
6. Keep track of the maximum length.
7. Return the maximum length.

---

## 💻 Java Solution

```java
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int maax = 0;

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '('){
                stack.push(i);
            }
            else {
                stack.pop();

                if(stack.isEmpty()){
                    stack.push(i);
                }
                else{
                    maax = Math.max(maax, i - stack.peek());
                }
            }
        }

        return maax;
    }
}
```

---

## 🧪 Dry Run

Consider:

```text
s = ")()())"
```

Indexes:

```text
Index:  0 1 2 3 4 5
String: ) ( ) ( ) )
```

Initially:

```text
Stack = [-1]
max = 0
```

### Index 0 → `)`

Pop `-1`.

Stack becomes empty, so push `0`.

```text
Stack = [0]
max = 0
```

### Index 1 → `(`

Push `1`.

```text
Stack = [0, 1]
```

### Index 2 → `)`

Pop `1`.

```text
Stack = [0]
```

Calculate:

```text
2 - 0 = 2
```

```text
max = 2
```

### Index 3 → `(`

Push `3`.

```text
Stack = [0, 3]
```

### Index 4 → `)`

Pop `3`.

```text
Stack = [0]
```

Calculate:

```text
4 - 0 = 4
```

```text
max = 4
```

### Index 5 → `)`

Pop `0`.

Stack becomes empty.

Push current index:

```text
Stack = [5]
```

Final answer:

```text
4
```

---

## 📊 Why `-1` Is Used

The initial:

```java
stack.push(-1);
```

acts as a boundary before the string starts.

For example:

```text
s = "()"
```

At index `1`:

```text
stack = [-1]
```

After popping the opening bracket:

```text
length = 1 - (-1)
       = 2
```

Therefore, the complete valid substring `"()"` has length `2`.

---

## ⏱️ Complexity

Let `n` be the length of the string.

### Time Complexity

```text
O(n)
```

Each character is processed once.

### Space Complexity

```text
O(n)
```

In the worst case, the stack can contain indices of many opening brackets.

---

## 🎯 Pattern

- Stack
- Parentheses Matching
- Index Tracking
- Boundary Tracking

---

## 🧠 Key Learning

The important trick is:

```java
stack.push(-1);
```

and after encountering an unmatched closing bracket:

```java
if(stack.isEmpty()){
    stack.push(i);
}
```

The stack does not just store parentheses. It stores **indices that help calculate the length of valid substrings**.

The length is calculated using:

```java
i - stack.peek()
```

---

## ⚠️ Common Mistakes

### 1. Storing characters instead of indices

We need indices because the answer requires the length of the valid substring.

Correct:

```java
Stack<Integer> stack
```

### 2. Forgetting the `-1` boundary

Without:

```java
stack.push(-1);
```

valid substrings beginning at index `0` are difficult to calculate correctly.

### 3. Not handling an empty stack

After popping:

```java
stack.pop();
```

the stack can become empty.

We must reset the boundary:

```java
if(stack.isEmpty()){
    stack.push(i);
}
```

### 4. Calculating the answer when the stack is empty

The length calculation is performed only when:

```java
!stack.isEmpty()
```

---

## 🗣️ Interview Explanation

> I use a stack of indices and initially push `-1` as a boundary. When I encounter an opening parenthesis, I push its index. For a closing parenthesis, I pop the stack. If the stack becomes empty, the current index becomes a new boundary because this closing parenthesis cannot be matched. Otherwise, the length of the current valid substring is `i - stack.peek()`. I keep updating the maximum length.

---

## 📌 Problem Information

| Property | Value |
|---|---|
| LeetCode | 32 |
| Difficulty | Hard |
| Pattern | Stack |
| Language | Java |
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

## 🔗 LeetCode

[Longest Valid Parentheses - LeetCode 32](https://leetcode.com/problems/longest-valid-parentheses/)
