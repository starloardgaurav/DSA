# 678. Valid Parenthesis String

## 🟡 Problem

Given a string `s` containing three types of characters:

- `'('`
- `')'`
- `'*'`

The `'*'` character can be treated as:

- `'('`
- `')'`
- an empty string `""`

Return `true` if the string can be converted into a valid parentheses string.

Otherwise, return `false`.

---

## 💡 Approach

This solution uses a **Greedy Range Tracking** approach.

Instead of deciding immediately what every `'*'` represents, we maintain two values:

```text
min = minimum possible number of unmatched '('
max = maximum possible number of unmatched '('
```

This allows us to consider all possible interpretations of `'*'` without explicitly trying every possibility.

---

## 🔑 Key Idea

### When character is `'('`

It definitely increases the number of open parentheses.

```java
min++;
max++;
```

### When character is `')'`

It decreases the number of open parentheses.

```java
min--;
max--;
```

### When character is `'*'`

`'*'` can represent either:

```text
')'  → minimum balance
'('  → maximum balance
```

Therefore:

```java
min--;
max++;
```

The empty-string possibility is automatically covered because we later make sure that `min` never stays negative.

---

## 🚨 Important Conditions

### 1. `max < 0`

```java
if(max < 0){
    return false;
}
```

If the maximum possible balance becomes negative, even the most favorable interpretation cannot make the string valid.

Therefore, we can immediately return `false`.

---

### 2. Keep `min` non-negative

```java
min = Math.max(min, 0);
```

The minimum possible number of unmatched opening parentheses cannot be negative.

For example, if `min` becomes `-1`, we can interpret some previous `'*'` as empty or `'('`, so the minimum possible balance is effectively `0`.

---

## 🧠 Algorithm

1. Initialize:
   ```text
   min = 0
   max = 0
   ```

2. Traverse the string character by character.

3. For `'('`:
   ```text
   min++
   max++
   ```

4. For `')'`:
   ```text
   min--
   max--
   ```

5. For `'*'`:
   ```text
   min--
   max++
   ```

6. If:
   ```text
   max < 0
   ```
   return `false`.

7. Make sure:
   ```text
   min = max(min, 0)
   ```

8. After processing the complete string:
   ```text
   min == 0
   ```
   means a valid interpretation exists.

---

## 💻 Java Solution

```java
class Solution {
    public boolean checkValidString(String s) {
        int min = 0;
        int max = 0;

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '('){
                min++;
                max++;
            }
            else if(ch == ')'){
                min--;
                max--;
            }
            else{
                // '*' can be ')' for min
                // or '(' for max
                min--;
                max++;
            }

            // Even the maximum possible balance is negative
            if(max < 0){
                return false;
            }

            // Minimum balance cannot be negative
            min = Math.max(min, 0);
        }

        return min == 0;
    }
}
```

---

## 🧪 Dry Run

Consider:

```text
s = "(*))"
```

### Initial

```text
min = 0
max = 0
```

### Index 0 → `'('`

```text
min = 1
max = 1
```

### Index 1 → `'*'`

`'*'` can be:

```text
')' → min = 0
'(' → max = 2
```

So:

```text
min = 0
max = 2
```

### Index 2 → `')'`

```text
min = -1
max = 1
```

Since `min` cannot be negative:

```text
min = 0
```

So:

```text
min = 0
max = 1
```

### Index 3 → `')'`

```text
min = -1
max = 0
```

Again:

```text
min = 0
```

Final:

```text
min = 0
max = 0
```

Therefore:

```text
true
```

One valid interpretation is:

```text
(*))
 ↓
(())
```

which is valid.

---

## ❌ Why `max < 0` Means False

Consider:

```text
s = "())"
```

Process:

```text
'(' → min = 1, max = 1
')' → min = 0, max = 0
')' → min = -1, max = -1
```

Now:

```java
max < 0
```

is true.

There is no possible interpretation that can make this string valid.

Therefore:

```text
false
```

---

## ⏱️ Complexity

Let `n` be the length of the string.

### Time Complexity

```text
O(n)
```

We traverse the string exactly once.

### Space Complexity

```text
O(1)
```

Only two integer variables are used:

```text
min
max
```

---

## 🎯 Pattern

- Greedy
- Range Tracking
- Parentheses
- Balance Tracking
- Constraint Handling

---

## 🧠 Key Learning

The main trick is to avoid explicitly deciding what every `'*'` represents.

Instead, maintain a range:

```text
[min, max]
```

where:

```text
min = minimum possible open brackets
max = maximum possible open brackets
```

This converts what could be an exponential search into an `O(n)` greedy solution.

The two most important lines are:

```java
if(max < 0){
    return false;
}
```

and:

```java
min = Math.max(min, 0);
```

---

## ⚠️ Common Mistakes

### 1. Treating `'*'` as only one character

`'*'` can represent:

```text
'('
')'
empty
```

It should not be treated as only one fixed character.

### 2. Using only one balance variable

A single balance cannot properly represent all possibilities created by `'*'`.

We need:

```text
min
max
```

### 3. Forgetting `max < 0`

If:

```text
max < 0
```

the string can never become valid.

Return immediately:

```java
return false;
```

### 4. Allowing `min` to remain negative

Use:

```java
min = Math.max(min, 0);
```

---

## 🗣️ Interview Explanation

> I use a greedy range approach. I maintain the minimum and maximum possible number of unmatched opening parentheses. For `'('`, both values increase. For `')'`, both decrease. For `'*'`, it can act as a closing bracket for the minimum balance or an opening bracket for the maximum balance. If the maximum balance becomes negative, the string cannot be valid. After every step I keep the minimum balance at least zero. At the end, if the minimum balance is zero, a valid interpretation exists.

---

## 📌 Problem Information

| Property | Value |
|---|---|
| LeetCode | 678 |
| Difficulty | Medium |
| Pattern | Greedy / Range Tracking |
| Language | Java |
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

## 📁 GitHub Structure

```text
Strings/
└── Valid-Parenthesis-String-678/
    ├── README.md
    └── Solution.java
```

---

## 🔗 LeetCode

[Valid Parenthesis String - LeetCode 678](https://leetcode.com/problems/valid-parenthesis-string/)
