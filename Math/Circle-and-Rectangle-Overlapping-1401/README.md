# 1401. Circle and Rectangle Overlapping

**LeetCode:** [Circle and Rectangle Overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping/)

**Difficulty:** Medium

**Topics:** Math, Geometry

---

## Problem

You are given a circle and an axis-aligned rectangle.

The circle is represented by:

```text
radius, xCenter, yCenter
```

The rectangle is represented by:

```text
x1, y1, x2, y2
```

where:

- `(x1, y1)` is the bottom-left corner.
- `(x2, y2)` is the top-right corner.

Return `true` if the circle and rectangle overlap.

---

## Key Idea

The easiest way to solve this problem is:

> Find the point on the rectangle that is closest to the center of the circle.

Then calculate the distance between:

```text
Circle Center
      ↓
Closest Point on Rectangle
```

If this distance is less than or equal to the radius, the circle overlaps the rectangle.

---

# Finding the Closest Point

The rectangle boundaries are:

```text
x1 <= x <= x2
y1 <= y <= y2
```

For the X-coordinate, we clamp the circle center between `x1` and `x2`.

```java
int nx = Math.max(x1, Math.min(xCenter, x2));
```

Similarly for Y:

```java
int ny = Math.max(y1, Math.min(yCenter, y2));
```

Therefore:

```text
(nx, ny)
```

is the closest point on the rectangle to the circle center.

---

# Understanding `Math.min` and `Math.max`

Consider:

```text
x1 = 2
x2 = 8
xCenter = 5
```

The center is already inside the range:

```text
2 <= 5 <= 8
```

So:

```java
Math.min(5, 8) = 5
Math.max(2, 5) = 5
```

Therefore:

```text
nx = 5
```

---

### Center Outside the Rectangle

Suppose:

```text
x1 = 2
x2 = 8
xCenter = 10
```

Then:

```java
Math.min(10, 8) = 8
Math.max(2, 8) = 8
```

Therefore:

```text
nx = 8
```

The closest point on the rectangle is its right boundary.

---

Suppose:

```text
xCenter = 0
```

Then:

```java
Math.min(0, 8) = 0
Math.max(2, 0) = 2
```

Therefore:

```text
nx = 2
```

The closest point is the left boundary.

---

# Distance Calculation

After finding the closest point:

```text
(nx, ny)
```

calculate the difference:

```java
int dx = xCenter - nx;
int dy = yCenter - ny;
```

Using the Euclidean distance formula:

```text
distance² = dx² + dy²
```

We don't actually need to calculate the square root.

Instead, compare squared distances:

```text
dx² + dy² <= radius²
```

In Java:

```java
return dx * dx + dy * dy <= radius * radius;
```

---

# Why Compare Squared Distance?

Normally:

```text
distance = √(dx² + dy²)
```

We need:

```text
distance <= radius
```

Instead of calculating the square root, square both sides:

```text
dx² + dy² <= radius²
```

This is mathematically equivalent and avoids unnecessary computation.

---

# Three Possible Cases

## Case 1: Circle Center Inside Rectangle

Example:

```text
Rectangle:
(0,0) → (10,10)

Circle center:
(5,5)
```

The closest point to the center is the center itself:

```text
nx = 5
ny = 5
```

Therefore:

```text
dx = 0
dy = 0
```

So:

```text
distance² = 0
```

The circle definitely overlaps the rectangle.

---

## Case 2: Circle Outside but Close Enough

Example:

```text
Circle center = (12,5)
Rectangle = [0,0,10,10]
```

Closest point:

```text
(10,5)
```

Therefore:

```text
dx = 12 - 10 = 2
dy = 5 - 5 = 0
```

So:

```text
distance² = 2² + 0²
           = 4
```

If:

```text
radius = 3
```

then:

```text
radius² = 9
```

Since:

```text
4 <= 9
```

the circle overlaps the rectangle.

---

## Case 3: Circle Too Far Away

Suppose:

```text
distance² = 25
radius² = 9
```

Then:

```text
25 > 9
```

Therefore, the circle does not overlap the rectangle.

---

# Dry Run

Consider:

```text
radius = 2
xCenter = 5
yCenter = 5

rectangle:
x1 = 7
y1 = 3
x2 = 10
y2 = 8
```

### Step 1: Find Closest X

```text
nx = max(7, min(5, 10))
```

```text
min(5,10) = 5
max(7,5) = 7
```

Therefore:

```text
nx = 7
```

### Step 2: Find Closest Y

```text
ny = max(3, min(5, 8))
```

```text
min(5,8) = 5
max(3,5) = 5
```

Therefore:

```text
ny = 5
```

Closest point:

```text
(7,5)
```

### Step 3: Calculate Distance

```text
dx = 5 - 7 = -2
dy = 5 - 5 = 0
```

Therefore:

```text
dx² + dy²
= (-2)² + 0²
= 4
```

Radius:

```text
r² = 2² = 4
```

Check:

```text
4 <= 4
```

Therefore:

```text
true
```

The circle touches the rectangle boundary, so it counts as overlap.

---

# Algorithm

1. Clamp `xCenter` between `x1` and `x2`.
2. Clamp `yCenter` between `y1` and `y2`.
3. This gives the closest point `(nx, ny)` on the rectangle.
4. Calculate:
   ```text
   dx = xCenter - nx
   dy = yCenter - ny
   ```
5. Compare:
   ```text
   dx² + dy² <= radius²
   ```
6. Return the result.

---

# Java Solution

```java
class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter,
                                int x1, int y1, int x2, int y2) {

        int nx = Math.max(x1, Math.min(xCenter, x2));
        int ny = Math.max(y1, Math.min(yCenter, y2));

        int dx = xCenter - nx;
        int dy = yCenter - ny;

        return dx * dx + dy * dy <= radius * radius;
    }
}
```

---

# Complexity Analysis

### Time Complexity

```text
O(1)
```

Only a fixed number of mathematical operations are performed.

### Space Complexity

```text
O(1)
```

Only a few integer variables are used.

---

# Why This Works

The closest point on the rectangle determines the minimum possible distance between the circle center and any point inside the rectangle.

Therefore:

```text
Minimum distance <= radius
```

means the circle intersects or touches the rectangle.

So the condition:

```java
dx * dx + dy * dy <= radius * radius
```

correctly determines whether they overlap.

---

# Important Learning

## 1. Clamping

This pattern is extremely useful:

```java
Math.max(low, Math.min(value, high))
```

It forces `value` to remain inside:

```text
[low, high]
```

This is called **clamping**.

---

## 2. Closest Point Technique

For geometry problems involving:

```text
Circle + Rectangle
```

a useful approach is:

```text
Find closest point
        ↓
Calculate distance
        ↓
Compare with radius
```

---

## 3. Avoid Square Root

Instead of:

```text
distance = sqrt(dx² + dy²)
```

use:

```text
dx² + dy² <= radius²
```

This is a common competitive-programming optimization.

---

# Pattern Recognition

When you see:

```text
Circle
+
Rectangle
+
Overlap / Intersection
```

think:

```text
Closest Point
      ↓
Squared Distance
      ↓
Compare with Radius
```

---

# Interview Explanation

> I first find the point on the rectangle that is closest to the circle center. I do this by clamping the circle center's X-coordinate between the rectangle's left and right boundaries, and similarly clamping the Y-coordinate. Then I calculate the squared Euclidean distance between the circle center and this closest point. If that squared distance is less than or equal to the squared radius, the circle overlaps or touches the rectangle. The solution runs in O(1) time and O(1) space.

---

# Key Pattern

**Geometry + Closest Point + Clamping + Squared Distance**

---

# LeetCode

- Problem: **1401. Circle and Rectangle Overlapping**
- Difficulty: **Medium**
- Status: **Solved**
- Approach: **Closest Point on Rectangle**
- Time Complexity: **O(1)**
- Space Complexity: **O(1)**
