# 🔤 Evaluate the Bracket Pairs of a String

## 📌 Problem

You are given a string `s` that contains lowercase English letters, spaces, and bracket pairs.

You are also given a list `knowledge`, where each pair contains:

```text
[key, value]
```

For every bracket pair:

```text
(key)
```

replace the key with its corresponding value from `knowledge`.

If the key does not exist in `knowledge`, replace it with:

```text
?
```

Return the resulting string.

---

## 🧠 Example

### Example 1

```text
Input:

s = "(name)is(age)yearsold"

knowledge = [
    ["name", "bob"],
    ["age", "two"]
]
```

Replace:

```text
(name) → bob
(age)  → two
```

Result:

```text
"bobistwoyearsold"
```

---

## 💡 Key Observation

The problem has two simple operations:

### 1. Store knowledge in a HashMap

Convert:

```text
[
    ["name", "bob"],
    ["age", "two"]
]
```

into:

```text
name → bob
age  → two
```

This allows us to find a value in:

```text
O(1)
```

average time.

---

### 2. Parse the string

Traverse `s` from left to right.

If the current character is not `(`:

```text
append it directly
```

If the current character is `(`:

```text
1. Read characters until ')'
2. Build the key
3. Search the key in the HashMap
4. Append its value
5. If not found, append '?'
```

---

# 🚀 Approach

### Step 1 — Build HashMap

```java
Map<String, String> map = new HashMap<>();
```

For every pair:

```java
map.put(pair.get(0), pair.get(1));
```

Example:

```text
name → bob
age  → two
```

---

### Step 2 — Traverse the String

Use:

```java
for(int i = 0; i < s.length(); i++)
```

If:

```java
s.charAt(i) == '('
```

we know a key starts here.

---

### Step 3 — Extract the Key

Skip the opening bracket:

```java
i++;
```

Then keep collecting characters until `)`:

```java
while(s.charAt(i) != ')') {
    key.append(s.charAt(i));
    i++;
}
```

For:

```text
(name)
```

the extracted key is:

```text
name
```

---

### Step 4 — Replace the Key

Use:

```java
map.getOrDefault(key.toString(), "?")
```

If key exists:

```text
name → bob
```

If key doesn't exist:

```text
unknown → ?
```

---

# 🧪 Dry Run

Consider:

```text
s = "hi(name)(age)"

knowledge = [
    ["name", "bob"],
    ["age", "20"]
]
```

### HashMap

```text
name → bob
age  → 20
```

---

### Traverse

```text
h → append
i → append
```

Current answer:

```text
hi
```

---

### `(name)`

Extract:

```text
key = "name"
```

Lookup:

```text
map["name"] = "bob"
```

Answer:

```text
hibob
```

---

### `(age)`

Extract:

```text
key = "age"
```

Lookup:

```text
map["age"] = "20"
```

Final answer:

```text
hibob20
```

---

# 🧪 Unknown Key Example

```text
s = "(name)is(unknown)"

knowledge = [
    ["name", "bob"]
]
```

For:

```text
(name)
```

we get:

```text
bob
```

For:

```text
(unknown)
```

the key is not present.

Therefore:

```text
?
```

Final result:

```text
"bobis?"
```

---

# 💻 Java Solution

```java
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        // Store key -> value
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                StringBuilder key = new StringBuilder();

                i++; // Skip '('

                // Collect characters until ')'
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                // Replace key with its value
                // If key doesn't exist, use '?'
                ans.append(map.getOrDefault(key.toString(), "?"));

            } else {

                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}
```

---

# 🔍 Code Explanation

## 1. Create HashMap

```java
Map<String, String> map = new HashMap<>();
```

The map stores:

```text
key → value
```

This gives fast lookup.

---

## 2. Insert Knowledge

```java
for (List<String> pair : knowledge) {
    map.put(pair.get(0), pair.get(1));
}
```

For:

```text
["name", "bob"]
```

we store:

```text
name → bob
```

---

## 3. Use StringBuilder

```java
StringBuilder ans = new StringBuilder();
```

Instead of repeatedly creating new strings, we append characters and values efficiently.

---

## 4. Detect Opening Bracket

```java
if (s.charAt(i) == '(')
```

This tells us that a key starts.

---

## 5. Extract Key

```java
StringBuilder key = new StringBuilder();

i++;

while (s.charAt(i) != ')') {
    key.append(s.charAt(i));
    i++;
}
```

This extracts everything between:

```text
(
)
```

---

## 6. Lookup Value

```java
map.getOrDefault(key.toString(), "?")
```

This is useful because we don't need an explicit:

```java
if(map.containsKey(...))
```

check.

---

# ⏱️ Complexity Analysis

Let:

- `n` = length of string `s`
- `k` = number of knowledge pairs

### Building the HashMap

```text
O(k)
```

---

### Processing the String

Every character of `s` is processed once.

```text
O(n)
```

HashMap lookup is `O(1)` average.

Therefore:

```text
Time Complexity = O(n + k)
```

assuming average `O(1)` HashMap operations.

### Space Complexity

The HashMap stores all knowledge pairs:

```text
O(k)
```

The output and temporary key builder also require space proportional to the input/output.

Auxiliary space excluding the returned output:

```text
O(k + m)
```

where `m` is the maximum key length.

---

# 🧩 Pattern

### HashMap + String Parsing

Important techniques:

- HashMap lookup
- String traversal
- StringBuilder
- Parsing bracketed content
- `getOrDefault()`

---

# 🎯 Key Learning

1. Use a `HashMap` when a problem asks for repeated key-value lookups.
2. Traverse the string once instead of repeatedly using substring searches.
3. Use `StringBuilder` for efficient string construction.
4. `getOrDefault()` simplifies missing-key handling.
5. When encountering a delimiter such as `(` and `)`, parse the content between them.

---

# 🗣️ Interview Explanation

> "I first store all knowledge pairs in a HashMap for constant average-time lookup. Then I scan the input string from left to right. Normal characters are appended directly to the result. When I encounter an opening bracket, I collect the key until the closing bracket, look it up in the map, and append its value. If the key doesn't exist, I append '?'. Since each character is processed once, the solution runs in O(n + k) average time."

---

# ⚠️ Common Mistakes

### 1. Forgetting to skip `(`

After detecting:

```java
s.charAt(i) == '('
```

we need:

```java
i++;
```

Otherwise `(` would become part of the key.

---

### 2. Not advancing past `)`

The `while` loop stops when:

```text
s.charAt(i) == ')'
```

The outer `for` loop then increments `i`, so the closing bracket is skipped.

---

### 3. Using `containsKey()` unnecessarily

Instead of:

```java
if(map.containsKey(key)) {
    ans.append(map.get(key));
} else {
    ans.append("?");
}
```

we can simply use:

```java
ans.append(map.getOrDefault(key, "?"));
```

---

## 📌 Complexity Summary

| Operation | Complexity |
|---|---|
| Build HashMap | O(k) |
| Traverse String | O(n) |
| HashMap Lookup | O(1) average |
| Total Time | O(n + k) |
| Auxiliary Space | O(k + m) |

---

## 🔗 LeetCode

[Evaluate the Bracket Pairs of a String - LeetCode 1807](https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/)

---

## ✅ Status

- [x] Problem Solved
- [x] HashMap Approach
- [x] String Parsing
- [x] StringBuilder
- [x] Dry Run
- [x] Complexity Analysis
- [x] Interview Explanation
- [x] Key Learning
