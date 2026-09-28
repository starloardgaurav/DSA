# 📚 Maximum Nesting Depth of the Parentheses

## 📌 Problem

Given a valid parentheses string `s`, return the **maximum nesting depth** of the parentheses.

The nesting depth is the maximum number of open parentheses that are active at the same time.

---

## 🧠 Example

### Example 1

```text
Input:
s = "(1+(2*3)+((8)/4))+1"

Output:
3
```

The deepest nested part contains:

```text
((8)/4)
```

At that point, there are three open parentheses.

Therefore:

```text
Maximum Depth = 3
```

---

## 💡 Key Observation

We only need to track the number of currently open parentheses.

Use:

```text
count = current depth
nd    = maximum depth
```

When we see:

```text
(
```

increase the current depth:

```text
count++
```

When we see:

```text
)
```

decrease the current depth:

```text
count--
```

After every update, maintain the maximum:

```text
nd = max(nd, count)
```

---

# 🚀 Approach

Traverse the string from left to right.

### If character is `(`

```java
count++;
```

A new level of nesting starts.

### If character is `)`

```java
count--;
```

One nesting level ends.

### Update maximum

```java
if(count > nd) {
    nd = count;
}
```

At the end:

```text
nd = maximum nesting depth
```

---

# 🧪 Dry Run

Consider:

```text
s = "(1+(2*3)+((8)/4))+1"
```

We only focus on parentheses.

| Character | Current Depth | Maximum Depth |
|---|---:|---:|
| `(` | 1 | 1 |
| `(` | 2 | 2 |
| `)` | 1 | 2 |
| `(` | 2 | 2 |
| `(` | 3 | 3 |
| `)` | 2 | 3 |
| `)` | 1 | 3 |
| `)` | 0 | 3 |

Therefore:

```text
Answer = 3
```

---

# 💻 Java Solution

```java
class Solution {
    public int maxDepth(String s) {

        int nd = 0;
        int count = 0;

        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);

            if(ch == '(')
                count++;

            if(ch == ')')
                count--;

            if(count > nd){
                nd = count;
            }
        }

        return nd;
    }
}
```

---

# 🔍 Code Explanation

## 1. Current Depth

```java
int count = 0;
```

This stores the number of currently open parentheses.

For example:

```text
(((
```

means:

```text
count = 3
```

---

## 2. Maximum Depth

```java
int nd = 0;
```

This stores the largest value reached by `count`.

---

## 3. Opening Parenthesis

```java
if(ch == '(')
    count++;
```

Every opening parenthesis increases the nesting depth by one.

---

## 4. Closing Parenthesis

```java
if(ch == ')')
    count--;
```

Every closing parenthesis decreases the nesting depth by one.

---

## 5. Update Maximum

```java
if(count > nd){
    nd = count;
}
```

Whenever the current depth becomes larger than the previous maximum, update `nd`.

---

# 🔥 Why Don't We Need a Stack?

A stack is useful when we need to know **which specific parenthesis matches which closing parenthesis**.

Here, we only need:

```text
How many parentheses are currently open?
```

Therefore, a simple counter is enough.

This makes the solution more memory efficient than using a stack.

---

# ⏱️ Complexity Analysis

Let:

```text
n = length of s
```

We scan every character exactly once.

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

Only two integer variables are used.

---

# 🧩 Pattern

### Counter / Parentheses

This problem demonstrates a common technique:

```text
Opening bracket → +1
Closing bracket → -1
```

The maximum prefix value gives the maximum nesting depth.

---

# 🎯 Key Learning

1. Not every parentheses problem requires a stack.
2. If only the current depth matters, use a counter.
3. `(` increases depth.
4. `)` decreases depth.
5. Track the maximum depth while traversing.
6. This gives an optimal `O(n)` time and `O(1)` space solution.

---

# 🗣️ Interview Explanation

> "I maintain a counter representing the current nesting depth. Whenever I encounter an opening parenthesis, I increment the counter, and whenever I encounter a closing parenthesis, I decrement it. After each character, I update the maximum depth seen so far. Since I only need the number of currently open parentheses and not their individual identities, a stack is unnecessary."

---

# ⚠️ Common Mistakes

### 1. Counting total parentheses

The answer is **not** the total number of `(` characters.

For:

```text
()()
```

there are two opening parentheses, but the maximum depth is:

```text
1
```

---

### 2. Using a stack unnecessarily

A stack would work for tracking parentheses, but it is unnecessary because we only need the depth.

---

### 3. Updating maximum before increasing depth

For:

```text
(
```

the depth becomes `1`.

So the maximum should be checked after processing the opening parenthesis.

---

## 📌 Complexity Summary

| Metric | Complexity |
|---|---|
| Time | O(n) |
| Space | O(1) |
| Pattern | Counter / Parentheses |

---

## 🔗 LeetCode

[Maximum Nesting Depth of the Parentheses - LeetCode 1614](https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/)

---

## ✅ Status

- [x] Problem Solved
- [x] Counter Approach
- [x] Dry Run
- [x] Complexity Analysis
- [x] Interview Explanation
- [x] Key Learning
