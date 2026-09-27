# 🔄 Reverse Substrings Between Each Pair of Parentheses

## 📌 Problem

Given a string `s` containing lowercase English letters and balanced parentheses, reverse the strings inside each matching pair of parentheses.

Parentheses should not appear in the final answer.

If parentheses are nested, the innermost parentheses are processed first.

---

## 🧠 Example

### Example 1

```text
Input:
s = "(abcd)"

Inside parentheses:
abcd

Reverse:
dcba

Output:
"dcba"
```

---

### Example 2

```text
Input:
s = "(u(love)i)"

First process:
(love) → evol

Now:
(u(evoli))

Then reverse:
uevoli → iloveu

Output:
"iloveu"
```

---

## 💡 Key Observation

A stack is useful because we need to process the **most recently opened parenthesis first**.

When we encounter:

```text
)
```

we pop characters until we find:

```text
(
```

Because stack follows **LIFO**:

```text
Last In → First Out
```

the characters are automatically extracted in reverse order.

---

# 🚀 Approach

We use:

```java
Stack<Character>
```

### When the character is not `)`

Simply push it:

```java
stack.push(ch);
```

This includes:

- letters
- `(`

---

### When we encounter `)`

We create a temporary `StringBuilder`:

```java
StringBuilder sr = new StringBuilder();
```

Then pop characters until `(`:

```java
while(stack.peek() != '('){
    sr.append(stack.pop());
}
```

Because of the stack's LIFO behavior, the popped characters are already reversed.

Then remove the opening parenthesis:

```java
stack.pop();
```

Finally, push the reversed characters back:

```java
for(int j = 0; j < sr.length(); j++){
    stack.push(sr.charAt(j));
}
```

---

# 🧪 Dry Run

Consider:

```text
s = "(abc)"
```

### Step 1

Read:

```text
(
```

Stack:

```text
[(]
```

---

### Step 2

Read:

```text
a
```

Stack:

```text
[(, a]
```

---

### Step 3

Read:

```text
b
```

Stack:

```text
[(, a, b]
```

---

### Step 4

Read:

```text
c
```

Stack:

```text
[(, a, b, c]
```

---

### Step 5

Read:

```text
)
```

Pop until `(`:

```text
pop c → sr = "c"
pop b → sr = "cb"
pop a → sr = "cba"
```

Now:

```text
stack = [(]
```

Remove `(`:

```text
stack = []
```

Push:

```text
c
b
a
```

Stack:

```text
[c, b, a]
```

---

### Step 6

End of string.

Pop the stack:

```text
a
b
c
```

The popped result is:

```text
"abc"
```

So we reverse it one final time:

```text
"cba"
```

Final answer:

```text
"cba"
```

---

# 🧪 Nested Parentheses

Consider:

```text
s = "(u(love)i)"
```

The important idea is that the inner pair:

```text
(love)
```

is completed before the outer pair:

```text
(u...i)
```

is processed.

This naturally happens because the stack processes the most recently opened parentheses first.

---

# 💻 Java Solution

```java
class Solution {
    public String reverseParentheses(String s) {

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);

            if(ch == ')'){

                StringBuilder sr = new StringBuilder();

                while(stack.peek() != '('){
                    sr.append(stack.pop());
                }

                // Remove '('
                stack.pop();

                // Push reversed characters back
                for(int j = 0; j < sr.length(); j++){
                    stack.push(sr.charAt(j));
                }

            } else {

                stack.push(ch);
            }
        }

        StringBuilder rs = new StringBuilder();

        while(!stack.isEmpty()){
            rs.append(stack.pop());
        }

        return rs.reverse().toString();
    }
}
```

---

# 🔍 Code Explanation

## 1. Create Stack

```java
Stack<Character> stack = new Stack<>();
```

The stack stores characters that have not yet been finalized.

---

## 2. Traverse the String

```java
for(int i = 0; i < s.length(); i++)
```

We process every character once.

---

## 3. Normal Character

If the character is not `)`:

```java
stack.push(ch);
```

So letters and opening parentheses are stored.

---

## 4. Closing Parenthesis

When:

```java
ch == ')'
```

we know that the current parenthesized section needs to be reversed.

---

## 5. Pop Until Opening Parenthesis

```java
while(stack.peek() != '('){
    sr.append(stack.pop());
}
```

For:

```text
(abc)
```

the stack contains:

```text
( a b c
```

Popping gives:

```text
c b a
```

Therefore:

```text
sr = "cba"
```

---

## 6. Remove Opening Parenthesis

```java
stack.pop();
```

This removes:

```text
(
```

from the stack.

---

## 7. Push Reversed Characters Back

```java
for(int j = 0; j < sr.length(); j++){
    stack.push(sr.charAt(j));
}
```

This makes the reversed substring part of the current stack content.

---

# 🔥 Why Does This Work?

The key is **LIFO**:

```text
Last In → First Out
```

Suppose:

```text
(abc)
```

Before `)`:

```text
Stack:

(
a
b
c
```

Popping gives:

```text
c
b
a
```

which is exactly the required reversed order.

For nested parentheses, the inner `)` is encountered first, so the inner substring is reversed before the outer substring.

---

# ⏱️ Complexity Analysis

Let:

```text
n = length of the input string
```

Every character is pushed and popped from the stack.

However, when a substring is reversed, its characters are also pushed back onto the stack.

Therefore, with the direct stack implementation, the worst-case time complexity can be:

```text
O(n²)
```

for deeply nested structures, because the same characters can participate in multiple reversal operations.

### Space Complexity

The stack and temporary `StringBuilder` can hold characters from the input:

```text
O(n)
```

---

# 🧩 Pattern

### Stack

This problem is a classic application of:

```text
Stack + Parentheses + String Manipulation
```

Important stack concept:

```text
LIFO
```

---

# 🎯 Key Learning

1. Use a stack when the most recently opened structure must be processed first.
2. A closing parenthesis tells us to process the latest opening parenthesis.
3. Stack popping naturally reverses characters.
4. Nested parentheses are naturally handled by LIFO behavior.
5. `StringBuilder` is useful for constructing the temporary reversed substring.
6. Always remove the matching `(` after processing its contents.

---

# 🗣️ Interview Explanation

> "I use a character stack to process the expression. Normal characters and opening parentheses are pushed onto the stack. Whenever I encounter a closing parenthesis, I pop characters until the matching opening parenthesis is found. Since a stack follows LIFO order, the popped characters are already reversed. I remove the opening parenthesis and push the reversed characters back. After processing the entire string, I pop the remaining characters and reverse the result to obtain the final answer."

---

# ⚠️ Common Mistakes

### 1. Forgetting to remove `(`

After processing the substring:

```java
stack.pop();
```

must remove the matching opening parenthesis.

---

### 2. Processing nested parentheses incorrectly

For:

```text
(a(bc)d)
```

the inner pair must be processed first.

The stack automatically provides this behavior.

---

### 3. Forgetting the final reversal

At the end:

```java
while(!stack.isEmpty()){
    rs.append(stack.pop());
}
```

Since we are again popping from a stack, the entire remaining result is reversed.

Therefore:

```java
return rs.reverse().toString();
```

is needed.

---

## 📌 Complexity Summary

| Metric | Complexity |
|---|---|
| Time | O(n²) worst case |
| Space | O(n) |
| Main Pattern | Stack |

---

## 🔗 LeetCode

[Reverse Substrings Between Each Pair of Parentheses - LeetCode 1190](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/)

---

## ✅ Status

- [x] Problem Solved
- [x] Stack Approach
- [x] Nested Parentheses
- [x] String Manipulation
- [x] Dry Run
- [x] Complexity Analysis
- [x] Interview Explanation
- [x] Key Learning
