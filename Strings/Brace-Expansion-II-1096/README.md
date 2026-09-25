# 🔤 Brace Expansion II

## 📌 Problem

Given a string `expression` representing a brace expression, return all possible strings that can be generated from the expression.

The result must contain:

- All possible expanded strings
- No duplicates
- Strings in lexicographical order

The expression can contain:

- Lowercase English letters
- Commas `,` representing **union**
- Braces `{}` representing grouped expressions
- Adjacent expressions representing **concatenation**

---

## 🧠 Core Idea

This problem has two main operations:

### 1. Union

For:

```text
{a,b}
```

we get:

```text
a
b
```

So:

```text
{a,b} → {a, b}
```

---

### 2. Concatenation

For:

```text
{a,b}c
```

we combine every string from the left set with every string from the right set:

```text
a + c = ac
b + c = bc
```

Result:

```text
{ac, bc}
```

This is essentially a **Cartesian product** of two sets.

---

# 🚀 Approach

We use **Recursive Descent Parsing**.

The expression is divided into three levels:

```text
Expression
    ↓
Concatenation
    ↓
Factor
```

### Grammar

Conceptually:

```text
Expression    → Concatenation (',' Concatenation)*
Concatenation → Factor*
Factor        → letter | '{' Expression '}'
```

This grammar directly matches the structure of the problem.

---

# 🔹 1. `parseExpression()`

```java
private Set<String> parseExpression()
```

This handles the **comma / union** operation.

Example:

```text
a,b,c
```

It first parses:

```text
a
```

then sees `,` and parses:

```text
b
```

then:

```text
c
```

Finally it combines everything using:

```java
result.addAll(...)
```

So:

```text
a,b,c → {a,b,c}
```

---

# 🔹 2. `parseConcatenation()`

```java
private Set<String> parseConcatenation()
```

This handles expressions placed next to each other.

Example:

```text
{a,b}{c,d}
```

First set:

```text
{a,b}
```

Second set:

```text
{c,d}
```

We need all combinations:

```text
a + c = ac
a + d = ad
b + c = bc
b + d = bd
```

Therefore:

```text
{ac,ad,bc,bd}
```

The important part is:

```java
for (String a : result) {
    for (String b : next) {
        temp.add(a + b);
    }
}
```

This performs the Cartesian product.

---

# 🔹 3. `parseFactor()`

```java
private Set<String> parseFactor()
```

A factor can be either:

```text
letter
```

or:

```text
{expression}
```

### Case 1: Letter

For:

```text
a
```

return:

```text
{a}
```

### Case 2: Braces

For:

```text
{a,b}
```

we:

1. Skip `{`
2. Parse the inside expression
3. Skip `}`

```java
idx++;
result = parseExpression();
idx++;
```

---

# 🔥 Why Do We Use `idx`?

We maintain a global pointer:

```java
int idx;
```

It tells us where we currently are in the expression.

For example:

```text
{a,b}c
 ^
 idx
```

When a character is processed:

```java
idx++;
```

moves to the next character.

This allows all recursive functions to work on the same expression without creating substrings repeatedly.

---

# 🧪 Dry Run

Consider:

```text
expression = "{a,b}c"
```

Initially:

```text
idx = 0
```

---

### Step 1

We encounter:

```text
{
```

So `parseFactor()` enters the braces.

```text
idx = 1
```

Now we parse:

```text
a,b
```

---

### Step 2

`parseExpression()` calls:

```text
parseConcatenation()
```

It processes:

```text
a
```

Result:

```text
{a}
```

---

### Step 3

We encounter:

```text
,
```

So we parse the next concatenation:

```text
b
```

Result:

```text
{b}
```

Union gives:

```text
{a,b}
```

---

### Step 4

After `}` the next factor is:

```text
c
```

So:

```text
{a,b} × {c}
```

Cartesian product:

```text
a + c = ac
b + c = bc
```

Final result:

```text
[ac, bc]
```

---

# 🧪 Another Example

```text
expression = "{{a,z},a{b,c},{ab,z}}"
```

The parser recursively evaluates every nested expression.

The use of `TreeSet` automatically:

1. Removes duplicates
2. Keeps strings sorted lexicographically

Final result:

```text
[a, ab, ac, z]
```

---

# 💻 Java Solution

```java
class Solution {

    String s;
    int idx;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;

        return new ArrayList<>(parseExpression());
    }

    private Set<String> parseExpression() {

        Set<String> result = parseConcatenation();

        while (idx < s.length() && s.charAt(idx) == ',') {

            idx++;

            result.addAll(parseConcatenation());
        }

        return result;
    }

    private Set<String> parseConcatenation() {

        Set<String> result = new TreeSet<>();

        result.add("");

        while (idx < s.length()
                && s.charAt(idx) != ','
                && s.charAt(idx) != '}') {

            Set<String> next = parseFactor();

            Set<String> temp = new TreeSet<>();

            for (String a : result) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    private Set<String> parseFactor() {

        Set<String> result;

        if (s.charAt(idx) == '{') {

            idx++;

            result = parseExpression();

            idx++;

        } else {

            result = new TreeSet<>();

            result.add(String.valueOf(s.charAt(idx)));

            idx++;
        }

        return result;
    }
}
```

---

# 🔍 Important Code Details

## `result.add("")`

Inside `parseConcatenation()`:

```java
result.add("");
```

This represents the **empty string**.

Why is it needed?

Suppose the first factor is:

```text
{a,b}
```

Initially:

```text
result = {""}
```

After multiplying with:

```text
{a,b}
```

we get:

```text
"" + "a" = "a"
"" + "b" = "b"
```

Therefore:

```text
result = {a,b}
```

Without the empty string, the first factor could not be initialized naturally.

---

# 🌳 Recursive Structure

For:

```text
{a,b}c
```

the recursion looks conceptually like:

```text
parseExpression
       |
       v
parseConcatenation
       |
   +---+---+
   |       |
   v       v
Factor   Factor
   |       |
 {a,b}     c
   |
parseExpression
   |
   +---+
   |   |
   a   b
```

The results are then combined during the return phase.

---

# ⏱️ Complexity Analysis

Let:

- `N` = length of the expression
- `K` = number of distinct strings generated
- `L` = maximum length of a generated string

The algorithm must generate the output strings, so complexity depends heavily on the size of the resulting set.

The Cartesian-product operations can generate many intermediate strings.

A practical bound can be expressed in terms of generated output size as approximately:

```text
O(total generated strings × string length × log K)
```

because `TreeSet` insertion costs logarithmic time and string construction/comparison depends on string length.

Space complexity is approximately:

```text
O(total generated strings × string length)
```

because the generated strings are stored in sets.

---

# 🆚 Why `TreeSet`?

We use:

```java
TreeSet<String>
```

instead of `HashSet<String>`.

### `HashSet`

```text
✓ Removes duplicates
✗ Does not maintain sorted order
```

### `TreeSet`

```text
✓ Removes duplicates
✓ Maintains lexicographical order
```

Since the problem requires sorted output, `TreeSet` is convenient.

---

# 🧩 Pattern

### Recursive Descent Parsing

The expression has a grammar-like structure:

```text
Expression
    ↓
Concatenation
    ↓
Factor
```

This makes recursion a natural solution.

### Other Patterns Used

- Recursion
- Parsing
- Set
- Cartesian Product
- String Manipulation
- TreeSet / Ordered Set

---

# 🎯 Key Learning

1. Complex string expressions can often be solved by defining a grammar.
2. Separate different operators into different parsing functions.
3. Comma represents **union**.
4. Adjacent expressions represent **concatenation**.
5. Concatenation of sets is a Cartesian product.
6. A global index can simplify recursive parsing.
7. `TreeSet` can simultaneously provide uniqueness and sorted order.
8. The empty string is useful for initializing concatenation.

---

# 🗣️ Interview Explanation

> "I solve the expression using recursive descent parsing. I divide the grammar into expression, concatenation, and factor. An expression handles comma-separated unions, while concatenation combines adjacent factors using a Cartesian product. A factor is either a single character or a recursively parsed brace expression. I use a global index to track the current position in the expression and TreeSet to remove duplicates and maintain lexicographical order."

---

# ⚠️ Common Mistakes

### 1. Treating concatenation as union

```text
{a,b}c
```

is not:

```text
{a,b,c}
```

It is:

```text
{ac,bc}
```

---

### 2. Forgetting nested braces

Expressions can contain nested expressions:

```text
{{a,b},c}
```

So recursion is necessary.

---

### 3. Not removing duplicates

For example:

```text
{a,a}
```

should produce only:

```text
[a]
```

Using a Set handles this automatically.

---

### 4. Forgetting lexicographical ordering

The final result must be sorted.

`TreeSet` handles this automatically.

---

## 📌 Complexity Summary

| Metric | Complexity |
|---|---|
| Parsing | Depends on expression |
| String Generation | Depends on number of generated strings |
| Set Operations | O(log K) per TreeSet insertion |
| Space | O(total generated output size) |

---

## 🔗 LeetCode

[Brace Expansion II - LeetCode 1096](https://leetcode.com/problems/brace-expansion-ii/)

---

## ✅ Status

- [x] Problem Solved
- [x] Recursive Parsing
- [x] Cartesian Product
- [x] Set for Duplicate Removal
- [x] Lexicographical Ordering
- [x] Dry Run
- [x] Complexity Analysis
- [x] Interview Explanation
