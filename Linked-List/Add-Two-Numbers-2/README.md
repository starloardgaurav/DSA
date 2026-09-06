# Add Two Numbers

**LeetCode Problem:** 2  
**Difficulty:** Medium  
**Topic:** Linked List, Math  
**Language:** Java

---

## Problem

You are given two **non-empty linked lists** representing two non-negative integers.

The digits are stored in **reverse order**, and each node contains a single digit.

Add the two numbers and return the sum as a linked list.

You may assume that the two numbers do not contain any leading zeros, except for the number `0` itself.

---

## Important Observation

The digits in the linked lists are stored in **reverse order**.

For example:

```text
l1 = [2, 4, 3]
```

represents:

```text
342
```

Similarly:

```text
l2 = [5, 6, 4]
```

represents:

```text
465
```

Therefore:

```text
342 + 465 = 807
```

The result is stored in reverse order:

```text
[7, 0, 8]
```

---

# Example 1

**Input:**

```text
l1 = [2,4,3]
l2 = [5,6,4]
```

**Output:**

```text
[7,0,8]
```

**Explanation:**

```text
342 + 465 = 807
```

Since the digits are stored in reverse order:

```text
807 → [7,0,8]
```

---

# Example 2

**Input:**

```text
l1 = [0]
l2 = [0]
```

**Output:**

```text
[0]
```

**Explanation:**

```text
0 + 0 = 0
```

---

# Example 3

**Input:**

```text
l1 = [9,9,9,9,9,9,9]
l2 = [9,9,9,9]
```

**Output:**

```text
[8,9,9,9,0,0,0,1]
```

This example demonstrates how the `carry` continues even after one linked list becomes `null`.

---

# Approach

## Intuition

This problem is similar to normal addition that we perform digit by digit.

For example:

```text
  342
+ 465
-----
  807
```

Starting from the rightmost digit:

```text
2 + 5 = 7
```

Then:

```text
4 + 6 = 10
```

So we store `0` and carry `1`.

Then:

```text
3 + 4 + 1 = 8
```

Therefore:

```text
807
```

The linked lists already store the digits in reverse order, so we can simply traverse them from left to right and perform the addition.

---

# Key Idea - Carry

The most important part of this problem is handling the **carry**.

For every pair of digits:

```text
sum = digit1 + digit2 + carry
```

The current digit is:

```text
digit = sum % 10
```

The carry for the next position is:

```text
carry = sum / 10
```

For example:

```text
9 + 8 = 17
```

Therefore:

```text
digit = 17 % 10 = 7
carry = 17 / 10 = 1
```

So we store:

```text
7
```

and carry:

```text
1
```

to the next position.

---

# Handling Different Lengths

The two linked lists may have different lengths.

For example:

```text
l1 = [9,9,9]
l2 = [1]
```

After processing the first node of `l2`, it becomes `null`.

But `l1` still contains nodes.

Therefore, we need to continue processing while either list still has nodes.

This is why we use:

```java
while(l1 != null || l2 != null || carry != 0)
```

The condition also includes:

```text
carry != 0
```

because there may be a remaining carry after both lists have ended.

---

# Dummy Node

To construct the answer linked list, we create a dummy node:

```java
ListNode l3 = new ListNode(0);
```

Then we maintain a pointer:

```java
ListNode curr = l3;
```

Whenever we calculate a new digit:

```java
curr.next = new ListNode(digit);
curr = curr.next;
```

At the end, the dummy node itself is not part of the answer.

Therefore, we return:

```java
return l3.next;
```

---

# Algorithm

1. Initialize `carry = 0`.
2. Create a dummy node `l3`.
3. Create a pointer `curr` pointing to the dummy node.
4. Continue while:
   - `l1 != null`, or
   - `l2 != null`, or
   - `carry != 0`.
5. Initialize `sum` with the current `carry`.
6. If `l1` is not `null`:
   - Add `l1.val` to `sum`.
   - Move `l1` to the next node.
7. If `l2` is not `null`:
   - Add `l2.val` to `sum`.
   - Move `l2` to the next node.
8. Calculate the current digit:

```text
digit = sum % 10
```

9. Calculate the carry:

```text
carry = sum / 10
```

10. Create a new node containing `digit`.
11. Attach it to the result list.
12. Move `curr` to the newly created node.
13. Continue until both lists are exhausted and there is no carry.
14. Return `l3.next`.

---

# Dry Run

Consider:

```text
l1 = [2,4,3]
l2 = [5,6,4]
```

These represent:

```text
342 + 465 = 807
```

---

## Step 1

Current values:

```text
l1 = 2
l2 = 5
carry = 0
```

Calculate:

```text
sum = 2 + 5 + 0
    = 7
```

Current digit:

```text
digit = 7 % 10
      = 7
```

Carry:

```text
carry = 7 / 10
      = 0
```

Result:

```text
7
```

---

## Step 2

Current values:

```text
l1 = 4
l2 = 6
carry = 0
```

Calculate:

```text
sum = 4 + 6 + 0
    = 10
```

Current digit:

```text
digit = 10 % 10
      = 0
```

Carry:

```text
carry = 10 / 10
      = 1
```

Result:

```text
7 → 0
```

---

## Step 3

Current values:

```text
l1 = 3
l2 = 4
carry = 1
```

Calculate:

```text
sum = 3 + 4 + 1
    = 8
```

Current digit:

```text
digit = 8 % 10
      = 8
```

Carry:

```text
carry = 8 / 10
      = 0
```

Result:

```text
7 → 0 → 8
```

Both linked lists are now finished and:

```text
carry = 0
```

So the loop ends.

Final result:

```text
[7,0,8]
```

---

# Visual Representation

Initial linked lists:

```text
l1:

2 → 4 → 3 → null

l2:

5 → 6 → 4 → null
```

After addition:

```text
2 + 5 = 7

7
```

```text
4 + 6 = 10

7 → 0
      carry = 1
```

```text
3 + 4 + 1 = 8

7 → 0 → 8
```

Final:

```text
7 → 0 → 8 → null
```

---

# Java Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;

        ListNode l3 = new ListNode(0);
        ListNode curr = l3;

        while(l1 != null || l2 != null || carry != 0){

            int sum = carry;

            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }

            int digit = sum % 10;
            carry = sum / 10;

            curr.next = new ListNode(digit);
            curr = curr.next;
        }

        return l3.next;
    }
}
```

---

# Complexity Analysis

## Time Complexity

**O(max(n, m))**

Where:

- `n` = number of nodes in `l1`
- `m` = number of nodes in `l2`

We process each node at most once.

Therefore, the total time complexity is:

```text
O(max(n, m))
```

---

## Space Complexity

**O(max(n, m))**

The result linked list contains at most:

```text
max(n, m) + 1
```

nodes because there can be one additional node due to the final carry.

The extra variables used by the algorithm require only:

```text
O(1)
```

auxiliary space.

If we include the output linked list, total space is:

```text
O(max(n, m))
```

---

# Important Learning

## 1. Linked List Traversal

A linked list is traversed using:

```java
node = node.next;
```

We cannot access a linked-list element using an index like an array.

---

## 2. Carry Handling

For digit addition:

```text
digit = sum % 10
carry = sum / 10
```

This is the core mathematical logic of the problem.

---

## 3. Different Length Linked Lists

We cannot assume both lists have the same length.

We need to handle cases where:

```text
l1 != null
```

but:

```text
l2 == null
```

or vice versa.

---

## 4. Final Carry

Even after both lists become `null`, there may still be a carry.

For example:

```text
9 + 1 = 10
```

So the loop condition includes:

```java
carry != 0
```

---

## 5. Dummy Node

A dummy node makes linked-list construction easier.

Instead of handling the first node separately, we always add new nodes using:

```java
curr.next = new ListNode(digit);
curr = curr.next;
```

At the end:

```java
return l3.next;
```

---

# Key Pattern

```text
Linked List 1 ─────┐
                   ↓
                 Add
                   ↑
Linked List 2 ─────┘
                   +
                 Carry
                   ↓
              Calculate Sum
                   ↓
        ┌──────────┴──────────┐
        ↓                     ↓
   digit = sum % 10      carry = sum / 10
        ↓                     ↓
   Create Node            Next Iteration
        ↓
   Move curr
```

---

# Edge Cases

### Case 1 - Both numbers are zero

```text
l1 = [0]
l2 = [0]

Output = [0]
```

### Case 2 - Different lengths

```text
l1 = [9,9,9]
l2 = [1]

Output = [0,0,0,1]
```

### Case 3 - Final carry

```text
l1 = [5]
l2 = [5]

5 + 5 = 10

Output = [0,1]
```

### Case 4 - One list becomes null before the other

The remaining nodes of the other list still need to be processed along with the carry.

---

# Interview Explanation

A concise way to explain this solution in an interview:

> Since the digits are stored in reverse order, I can traverse both linked lists from the head and add corresponding digits directly. I maintain a `carry` for sums greater than 9. For each position, I calculate the digit using `sum % 10` and update the carry using `sum / 10`. I use a dummy node to simplify construction of the result linked list. I continue while either list has nodes or a carry remains.

---

# LeetCode Result

**Status:** Accepted ✅

**Problem:** 2. Add Two Numbers

**Difficulty:** Medium

**Primary Pattern:** Linked List + Carry

---

# Status

- [x] Problem Solved
- [x] Linked List Traversal
- [x] Carry Logic
- [x] Dummy Node
- [x] Different Length Lists
- [x] Final Carry Handling
- [x] Dry Run Completed
- [x] Time Complexity: O(max(n, m))
- [x] Space Complexity: O(max(n, m)) including output
- [x] Understood `sum % 10`
- [x] Understood `sum / 10`
