# 836. Rectangle Overlap

**LeetCode:** [Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/)

**Difficulty:** Easy

**Topics:** Math, Geometry

---

## Problem

You are given two axis-aligned rectangles `rec1` and `rec2`.

Each rectangle is represented as:

```text
[x1, y1, x2, y2]
```

where:

- `(x1, y1)` is the bottom-left corner.
- `(x2, y2)` is the top-right corner.

Return `true` if the two rectangles overlap.

If they only touch at the boundary or corner, they do **not** overlap.

---

## Example 1

```text
Input:
rec1 = [0,0,2,2]
rec2 = [1,1,3,3]

Output:
true
```

The rectangles have a common area:

```text
    3
    ┌───────┐
    │       │
    │   ┌───┼───┐
    │   │   │   │
    └───┼───┘   │
        │       │
        └───────┘
```

Therefore, they overlap.

---

## Example 2

```text
Input:
rec1 = [0,0,1,1]
rec2 = [1,0,2,1]

Output:
false
```

The rectangles only touch at their boundary.

There is no common area.

Therefore:

```text
false
```

---

## Key Observation

For two rectangles to overlap, they must overlap on **both axes**:

```text
X-axis overlap
        AND
Y-axis overlap
```

If either axis has no common length, the rectangles do not overlap.

---

## X-Axis Overlap

For:

```text
rec1 = [x1, y1, x2, y2]
rec2 = [a1, b1, a2, b2]
```

The overlapping X-range is:

```text
[max(x1, a1), min(x2, a2)]
```

For this range to have a positive length:

```text
min(x2, a2) > max(x1, a1)
```

In Java:

```java
Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
```

---

## Y-Axis Overlap

Similarly, the overlapping Y-range is:

```text
[max(y1, b1), min(y2, b2)]
```

For positive overlap:

```text
min(y2, b2) > max(y1, b1)
```

In Java:

```java
Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1])
```

---

## Final Condition

Both X and Y axes must overlap:

```java
X-axis overlap && Y-axis overlap
```

Therefore:

```java
return Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
    && Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
```

---

## Why `>` Instead of `>=`?

This is very important.

Suppose:

```text
rec1 = [0,0,1,1]
rec2 = [1,0,2,1]
```

For the X-axis:

```text
min(1,2) = 1
max(0,1) = 1
```

So:

```text
1 > 1 → false
```

The rectangles only touch at `x = 1`.

They have **zero common area**, so the answer must be:

```text
false
```

Using `>=` would incorrectly consider boundary-touching rectangles as overlapping.

---

## Dry Run

Consider:

```text
rec1 = [0,0,2,2]
rec2 = [1,1,3,3]
```

### X-axis

```text
min(rec1[2], rec2[2])
= min(2,3)
= 2
```

```text
max(rec1[0], rec2[0])
= max(0,1)
= 1
```

Check:

```text
2 > 1
```

True.

---

### Y-axis

```text
min(rec1[3], rec2[3])
= min(2,3)
= 2
```

```text
max(rec1[1], rec2[1])
= max(0,1)
= 1
```

Check:

```text
2 > 1
```

True.

---

### Final Result

```text
true && true
= true
```

Therefore, the rectangles overlap.

---

## Algorithm

1. Find the overlapping length on the X-axis.
2. Find the overlapping length on the Y-axis.
3. Check whether both lengths are positive.
4. Return `true` only when both axes overlap.

---

## Java Solution

```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
            && Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
    }
}
```

---

## Complexity Analysis

### Time Complexity

```text
O(1)
```

Only a fixed number of comparisons and arithmetic operations are performed.

### Space Complexity

```text
O(1)
```

No extra data structure is used.

---

## Alternative Way to Think About It

Instead of directly checking overlap, we can think about when rectangles **do not overlap**.

Two rectangles do not overlap if:

```text
rec1 is completely to the left of rec2
OR
rec1 is completely to the right of rec2
OR
rec1 is completely above rec2
OR
rec1 is completely below rec2
```

The current solution directly checks the opposite:

```text
There must be positive overlap on X
AND
There must be positive overlap on Y
```

This makes the final condition concise.

---

## Key Learning

### 1. Break 2D Problems Into 1D Problems

A rectangle overlap problem becomes much easier when separated into:

```text
X-axis
Y-axis
```

Then combine the conditions using `&&`.

---

### 2. Positive Length Matters

For actual overlap:

```text
overlap > 0
```

not:

```text
overlap >= 0
```

because zero means the rectangles only touch.

---

### 3. `min(right) > max(left)`

A useful interval-overlap pattern is:

```text
min(right1, right2) > max(left1, left2)
```

The same idea can be applied to the Y-axis.

---

## Interview Explanation

> I treat the rectangles as intervals on the X and Y axes. For the rectangles to have a common area, their X intervals must overlap with positive length and their Y intervals must also overlap with positive length. The X overlap condition is `min(x2) > max(x1)`, and similarly for Y. I use `>` rather than `>=` because rectangles touching only at a boundary do not count as overlapping. This gives an O(1) time and O(1) space solution.

---

## Key Pattern

**Interval Overlap + Geometry**

```text
2D Rectangle
     ↓
X-axis overlap
     +
Y-axis overlap
     ↓
Rectangle overlap
```

---

## LeetCode

- Problem: **836. Rectangle Overlap**
- Difficulty: **Easy**
- Status: **Solved**
- Approach: **Interval Overlap**
- Complexity: **O(1) Time, O(1) Space**
