# Two Sum II - Input Array Is Sorted

**LeetCode Problem:** 167  
**Difficulty:** Medium  
**Topic:** Array, Two Pointers  
**Language:** Java

---

## Problem

Given a **1-indexed** array of integers `numbers` that is already sorted in **non-decreasing order**, find two numbers such that they add up to a given `target`.

Return the indices of the two numbers as `[index1, index2]`.

### Conditions

- `1 <= index1 < index2 <= numbers.length`
- The array is sorted in non-decreasing order.
- The same element cannot be used twice.
- There is exactly one solution.
- The solution must use **constant extra space**.

---

## Examples

### Example 1

**Input:**

```text
numbers = [2, 7, 11, 15]
target = 9
```

**Output:**

```text
[1, 2]
```

**Explanation:**

```text
2 + 7 = 9
```

Therefore, the answer is `[1, 2]`.

---

### Example 2

**Input:**

```text
numbers = [2, 3, 4]
target = 6
```

**Output:**

```text
[1, 3]
```

**Explanation:**

```text
2 + 4 = 6
```

Therefore, the answer is `[1, 3]`.

---

### Example 3

**Input:**

```text
numbers = [-1, 0]
target = -1
```

**Output:**

```text
[1, 2]
```

**Explanation:**

```text
-1 + 0 = -1
```

Therefore, the answer is `[1, 2]`.

---

# Approach - Two Pointers

## Intuition

The most important observation in this problem is that the array is already **sorted**.

Because the array is sorted, we can use two pointers:

- `i` → starts from the beginning of the array.
- `j` → starts from the end of the array.

Initially:

```text
i = 0
j = numbers.length - 1
```

At every step, calculate:

```text
sum = numbers[i] + numbers[j]
```

Then compare the `sum` with the `target`.

### Case 1: Sum equals target

If:

```text
numbers[i] + numbers[j] == target
```

we have found the required pair.

Since the problem requires **1-based indexing**, return:

```text
[i + 1, j + 1]
```

### Case 2: Sum is greater than target

If:

```text
numbers[i] + numbers[j] > target
```

the sum is too large.

Because the array is sorted, we need a smaller value.

So we move the right pointer towards the left:

```text
j--
```

### Case 3: Sum is smaller than target

If:

```text
numbers[i] + numbers[j] < target
```

the sum is too small.

Because the array is sorted, we need a larger value.

So we move the left pointer towards the right:

```text
i++
```

---

# Algorithm

1. Initialize `i = 0`.
2. Initialize `j = numbers.length - 1`.
3. Run a loop while `i < j`.
4. Calculate `numbers[i] + numbers[j]`.
5. If the sum is equal to `target`:
   - Store `i + 1` and `j + 1`.
   - Break the loop.
6. If the sum is greater than `target`:
   - Decrement `j`.
7. If the sum is smaller than `target`:
   - Increment `i`.
8. Return the answer array.

---

# Dry Run

Consider:

```text
numbers = [2, 7, 11, 15]
target = 9
```

### Initial State

```text
i = 0
j = 3
```

```text
       i           j
       ↓           ↓
[      2, 7, 11, 15]
```

### Step 1

Calculate:

```text
numbers[i] + numbers[j]
= 2 + 15
= 17
```

Since:

```text
17 > 9
```

the sum is too large.

Therefore, move the right pointer:

```text
j--
```

Now:

```text
i = 0
j = 2
```

---

### Step 2

Calculate:

```text
2 + 11 = 13
```

Since:

```text
13 > 9
```

the sum is still too large.

Therefore:

```text
j--
```

Now:

```text
i = 0
j = 1
```

---

### Step 3

Calculate:

```text
2 + 7 = 9
```

Since:

```text
9 == 9
```

we found the required pair.

Current indices:

```text
i = 0
j = 1
```

Java uses **0-based indexing**, but the problem requires **1-based indexing**.

Therefore:

```text
i + 1 = 1
j + 1 = 2
```

Final answer:

```text
[1, 2]
```

---

# Java Solution

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] arr = new int[2];

        int i = 0;
        int j = numbers.length - 1;

        while(i < j){
            if(numbers[i] + numbers[j] == target){
                arr[0] = i + 1;
                arr[1] = j + 1;
                break;
            }
            else if(numbers[i] + numbers[j] > target){
                j--;
            }
            else{
                i++;
            }
        }

        return arr;
    }
}
```

---

# Complexity Analysis

## Time Complexity

**O(n)**

Both pointers move only in one direction:

- `i` moves from left to right.
- `j` moves from right to left.

Therefore, the array is traversed at most once.

## Space Complexity

**O(1)**

Only a constant amount of extra space is used.

We use:

- `i`
- `j`
- `arr`

No additional data structure such as a `HashMap` is required.

---

# Why Does the Two Pointer Approach Work?

The Two Pointer approach works because the array is **sorted**.

Consider:

```text
[2, 7, 11, 15]
```

Suppose:

```text
2 + 15 = 17
```

and the target is `9`.

Since `17` is greater than `9`, we need a smaller value.

Because the array is sorted, moving the right pointer to the left gives us a smaller value:

```text
15 → 11
```

Similarly, if the sum is smaller than the target, moving the left pointer to the right gives us a larger value.

Therefore:

```text
sum > target  →  j--
sum < target  →  i++
sum == target →  answer found
```

This allows us to avoid checking every possible pair.

---

# Important Learning

## 1. Sorted Array → Two Pointers

Whenever a problem involves a **sorted array** and asks us to find a pair satisfying some condition, the **Two Pointer technique** should be considered.

## 2. Pointer Movement

Remember this pattern:

```text
sum > target
      ↓
    j--

sum < target
      ↓
    i++

sum == target
      ↓
  Answer Found
```

## 3. 0-Based vs 1-Based Indexing

Java arrays use **0-based indexing**.

Example:

```text
Array:   [2, 7, 11, 15]
Index:    0  1   2   3
```

But this problem requires **1-based indexing**.

Therefore:

```text
Java index + 1 = Problem index
```

So we return:

```java
i + 1
j + 1
```

## 4. Constant Extra Space

The problem specifically requires **constant extra space**.

The Two Pointer approach satisfies this requirement:

```text
Time Complexity  → O(n)
Space Complexity → O(1)
```

---

# Key Pattern

```text
Sorted Array
     ↓
Two Pointers
     ↓
Left + Right
     ↓
Compare Sum with Target
     ↓
 ┌───────────────┬───────────────┐
 ↓               ↓               ↓
sum > target   sum < target   sum == target
 ↓               ↓               ↓
j--             i++          Answer Found
```

---

# LeetCode Result

**Status:** Accepted ✅

**Problem:** 167. Two Sum II - Input Array Is Sorted

**Difficulty:** Medium

**Primary Pattern:** Two Pointers

---

# Status

- [x] Problem Solved
- [x] Two Pointer Approach
- [x] Understood Sorted Array Property
- [x] Dry Run Completed
- [x] Time Complexity: O(n)
- [x] Space Complexity: O(1)
- [x] Understood 1-Based Indexing
- [x] Understood Pointer Movement
