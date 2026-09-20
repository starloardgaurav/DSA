# 3498. Reverse Degree of a String

**LeetCode:** [Reverse Degree of a String](https://leetcode.com/problems/reverse-degree-of-a-string/)

**Difficulty:** Easy

**Topics:** String, Math

---

## Problem

Given a string `s`, calculate its **reverse degree**.

The reverse alphabet assigns values to letters in reverse order:

```text
a → 26
b → 25
c → 24
...
y → 2
z → 1
```

For each character:

```text
Reverse Degree = position × reverse alphabet value
```

where the position starts from `1`.

The answer is the sum of these values for all characters.

---

## Example

For:

```text
s = "abc"
```

Reverse alphabet values are:

```text
a → 26
b → 25
c → 24
```

Positions:

```text
a → position 1
b → position 2
c → position 3
```

Therefore:

```text
1 × 26 = 26
2 × 25 = 50
3 × 24 = 72
```

Total:

```text
26 + 50 + 72 = 148
```

---

# Approach 1: Reverse Alphabet String

The first solution uses a manually created reverse alphabet:

```java
String revAlpha = "_zyxwvutsrqponmlkjihgfedcba";
```

The underscore is added at index `0`, so:

```text
index 1 → z → reverse value 1
index 2 → y → reverse value 2
...
index 26 → a → reverse value 26
```

Then:

```java
int index = revAlpha.indexOf(ch);
```

finds the reverse value of the current character.

### Code

```java
class Solution {
    public int reverseDegree(String s) {
        String revAlpha = "_zyxwvutsrqponmlkjihgfedcba";
        int result = 0;

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            int index = revAlpha.indexOf(ch);

            result += (i + 1) * index;
        }

        return result;
    }
}
```

### Complexity

`indexOf()` searches through the string.

Since the alphabet contains only 26 characters, its maximum cost is constant:

```text
O(26) = O(1)
```

For a string of length `n`:

```text
Time: O(n)
Space: O(1)
```

Although this is already efficient enough, we can calculate the reverse value directly using a formula.

---

# Approach 2: Mathematical Formula

Instead of searching for the character in a reverse alphabet string, we can use the ASCII/Unicode character difference.

For lowercase English letters:

```text
'a' - 'a' = 0
'b' - 'a' = 1
'c' - 'a' = 2
...
'z' - 'a' = 25
```

Therefore, the normal alphabet position starting from `0` is:

```java
ch - 'a'
```

The reverse alphabet value is:

```text
26 - (ch - 'a')
```

---

# Reverse Value Examples

### Character `a`

```text
'a' - 'a' = 0

26 - 0 = 26
```

Therefore:

```text
a → 26
```

### Character `b`

```text
'b' - 'a' = 1

26 - 1 = 25
```

Therefore:

```text
b → 25
```

### Character `c`

```text
'c' - 'a' = 2

26 - 2 = 24
```

Therefore:

```text
c → 24
```

### Character `z`

```text
'z' - 'a' = 25

26 - 25 = 1
```

Therefore:

```text
z → 1
```

---

# Formula

The reverse value of character `ch` is:

```text
reverseValue = 26 - (ch - 'a')
```

Then multiply it by the 1-based position:

```text
result += (i + 1) × reverseValue
```

---

# Dry Run

Consider:

```text
s = "abc"
```

Initial:

```text
result = 0
```

---

### i = 0

Character:

```text
ch = 'a'
```

Reverse value:

```text
26 - ('a' - 'a')
= 26 - 0
= 26
```

Position:

```text
i + 1 = 1
```

Contribution:

```text
1 × 26 = 26
```

Result:

```text
26
```

---

### i = 1

Character:

```text
ch = 'b'
```

Reverse value:

```text
26 - ('b' - 'a')
= 26 - 1
= 25
```

Position:

```text
2
```

Contribution:

```text
2 × 25 = 50
```

Result:

```text
26 + 50 = 76
```

---

### i = 2

Character:

```text
ch = 'c'
```

Reverse value:

```text
26 - ('c' - 'a')
= 26 - 2
= 24
```

Position:

```text
3
```

Contribution:

```text
3 × 24 = 72
```

Final result:

```text
76 + 72 = 148
```

Therefore:

```text
Answer = 148
```

---

# Algorithm

1. Initialize `result = 0`.
2. Traverse the string.
3. For every character:
   - Calculate its reverse alphabet value:
     ```text
     26 - (ch - 'a')
     ```
   - Multiply it by its 1-based position:
     ```text
     (i + 1) × reverseValue
     ```
   - Add the contribution to `result`.
4. Return `result`.

---

# Java Solution

```java
class Solution {
    public int reverseDegree(String s) {
        int result = 0;

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            int reverseValue = 26 - (ch - 'a');

            result += (i + 1) * reverseValue;
        }

        return result;
    }
}
```

---

# Complexity Analysis

Let:

```text
n = s.length()
```

We traverse the string exactly once.

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# Approach Comparison

| Approach | Time | Space | Comment |
|---|---:|---:|---|
| Reverse Alphabet + `indexOf()` | O(n) | O(1) | Simple but performs searching |
| Mathematical Formula | O(n) | O(1) | Direct calculation |

The mathematical approach is cleaner because it avoids maintaining a reverse alphabet string and avoids searching for every character.

---

# Important Learning

## 1. Character Arithmetic

For lowercase English letters:

```java
ch - 'a'
```

gives the zero-based alphabet index.

Example:

```text
a → 0
b → 1
c → 2
...
z → 25
```

This is a very useful technique for string problems.

---

## 2. Reverse Mapping

Normal zero-based mapping:

```text
ch - 'a'
```

Reverse mapping:

```text
26 - (ch - 'a')
```

This pattern can be used whenever a problem asks for reverse alphabet positions.

---

## 3. 0-Based vs 1-Based Index

Java string indexing starts from:

```text
0
```

But the problem uses positions starting from:

```text
1
```

Therefore:

```java
i + 1
```

must be used.

---

# Common Mistake

Do not use:

```java
i
```

directly as the position.

For the first character:

```text
i = 0
```

but its problem position is:

```text
1
```

Therefore:

```java
(i + 1)
```

is required.

---

# Interview Explanation

> I traverse the string once. For each character, I calculate its reverse alphabet value using `26 - (ch - 'a')`. Since Java uses zero-based string indexing but the problem uses one-based positions, I multiply the reverse value by `i + 1`. I accumulate these contributions and return the result. This gives O(n) time and O(1) space.

---

# Key Pattern

**Character Arithmetic + Mathematical Mapping**

```text
Character
    ↓
ch - 'a'
    ↓
Zero-based alphabet index
    ↓
26 - index
    ↓
Reverse alphabet value
```

---

# LeetCode

- Problem: **3498. Reverse Degree of a String**
- Difficulty: **Easy**
- Status: **Solved**
- Preferred Approach: **Mathematical Formula**
- Time Complexity: **O(n)**
- Space Complexity: **O(1)**
