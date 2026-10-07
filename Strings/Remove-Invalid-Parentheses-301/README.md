# Remove Invalid Parentheses — LeetCode 301

## 🧩 Problem

Given a string `s` containing parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return all unique valid strings that can be obtained using the minimum number of removals.

The answer can be returned in any order.

---

## 💡 Approach

This solution uses:

- **Backtracking**
- **Pruning**
- **HashSet**
- **Parentheses balance checking**

The important observation is that we first calculate exactly how many unmatched `(` and `)` need to be removed.

Then we use backtracking to try removing only those required parentheses.

A `HashSet` is used to avoid duplicate results.

---

## 🔑 Step 1 — Calculate Required Removals

We scan the original string once.

Maintain:

```text
left  = unmatched '('
right = unmatched ')'
```

For every `(`:

```text
left++
```

For every `)`:

- If there is an unmatched `(`, match it:

```text
left--
```

- Otherwise, this `)` is unmatched:

```text
right++
```

After the scan:

```text
left = number of '(' that must be removed
right = number of ')' that must be removed
```

This ensures that we remove the **minimum possible number of parentheses**.

---

## 🌳 Step 2 — Backtracking

We recursively process every character.

For an opening parenthesis:

```text
(
```

if `left > 0`, we have the option to remove it.

For a closing parenthesis:

```text
)
```

if `right > 0`, we have the option to remove it.

We also have another choice:

```text
Keep the current character
```

So at every parenthesis we explore:

```text
Remove
   OR
Keep
```

Letters are always kept.

---

## ✂️ Pruning

We never remove more parentheses than necessary.

For example, if:

```text
left = 0
```

there is no reason to remove another `(`.

Similarly, if:

```text
right = 0
```

we cannot remove another `)`.

This significantly reduces unnecessary branches.

---

## ✅ Step 3 — Validate Final String

When we reach the end of the string, we only accept the result when:

```text
left == 0
right == 0
```

and the resulting string is actually valid.

The `isValid()` method maintains a balance:

```text
'(' → count++
')' → count--
```

If at any point:

```text
count < 0
```

then there are more closing parentheses than opening parentheses, so the string is invalid.

At the end:

```text
count == 0
```

means all parentheses are properly matched.

---

## 🧠 Why HashSet?

Different backtracking paths can produce the same resulting string.

For example, if multiple identical parentheses exist, removing different copies can lead to the same final string.

Therefore:

```java
Set<String> result = new HashSet<>();
```

automatically removes duplicate answers.

---

## 🔍 Dry Run

### Input

```text
s = "()())()"
```

First calculate the required removals.

```text
(
)
(
)
)
(
)
```

After matching pairs, there is:

```text
left = 0
right = 1
```

Therefore, we must remove exactly one `)`.

Possible valid results include:

```text
(())()
()()()
```

Both use the minimum number of removals.

---

## 🧠 Backtracking State

The recursive function is:

```java
backtrack(String s, int index, int left, int right)
```

Where:

| Parameter | Meaning |
|---|---|
| `s` | Current string |
| `index` | Current position |
| `left` | Number of `(` still allowed to remove |
| `right` | Number of `)` still allowed to remove |

At each index:

```text
Remove current parenthesis
        OR
Keep current character
```

---

## 💻 Java Solution

```java
class Solution {
    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int left = 0;
        int right = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                left++;
            } 
            else if (ch == ')') {
                if (left > 0) {
                    left--;
                } 
                else {
                    right++;
                }
            }
        }

        backtrack(s, 0, left, right);

        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int left, int right) {

        if (index == s.length()) {
            if (left == 0 && right == 0 && isValid(s)) {
                result.add(s);
            }
            return;
        }

        char ch = s.charAt(index);

        if (ch == '(' && left > 0) {
            backtrack(
                s.substring(0, index) + s.substring(index + 1),
                index,
                left - 1,
                right
            );
        }

        if (ch == ')' && right > 0) {
            backtrack(
                s.substring(0, index) + s.substring(index + 1),
                index,
                left,
                right - 1
            );
        }

        backtrack(s, index + 1, left, right);
    }

    private boolean isValid(String s) {
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            } 
            else if (ch == ')') {
                count--;

                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}
```

---

## ⏱️ Complexity

This backtracking solution has exponential worst-case behavior because it explores different removal choices.

A simple upper-bound description is:

```text
Time: O(2^n × n)
```

where `n` is the length of the string, with additional cost from creating strings using `substring()` and concatenation.

Space usage is also dependent on the recursion tree and stored result strings:

```text
Space: O(2^n × n)
```

The actual search space is reduced by calculating the exact number of required removals before backtracking.

---

## 🎯 Pattern

**Backtracking + Pruning + HashSet + Parentheses Validation**

Important concepts:

- Backtracking
- Pruning
- Minimum removals
- Parentheses balance
- HashSet for duplicate elimination
- Recursive search
- Validation

---

## 📌 Key Learning

The most important trick is:

> Don't blindly try removing every parenthesis.

First calculate:

```text
left = extra '('
right = extra ')'
```

Then backtrack while removing exactly those required parentheses.

This guarantees that we search only for solutions with the **minimum number of removals**.

---

## 🆚 Stack vs Backtracking

Unlike simple parentheses problems such as Valid Parentheses, this problem requires generating **all possible valid answers** after the minimum number of removals.

Therefore, a simple stack or balance counter is not enough.

We need:

```text
Minimum removals
        ↓
Backtracking
        ↓
Generate candidates
        ↓
Validate
        ↓
Store unique answers
```

---

## 🗣️ Interview Explanation

> First, I scan the string to determine how many unmatched opening and closing parentheses must be removed. Then I use backtracking to explore removing exactly those required parentheses. At each parenthesis, I either remove it if removal is still required or keep it. Once the entire string is processed, I validate the resulting string and store it in a HashSet to avoid duplicates. This guarantees that the returned strings use the minimum number of removals.

---

## 📂 Folder Structure

```text
Strings/
└── Remove-Invalid-Parentheses-301/
    ├── README.md
    └── Solution.java
```

---

## 🏷️ Tags

`Java` `LeetCode` `Strings` `Backtracking` `Pruning` `HashSet` `Parentheses` `Recursion` `Hard`
