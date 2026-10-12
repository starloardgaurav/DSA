# 2897. Apply Operations on Array to Maximize Sum of Squares

- **Platform:** LeetCode
- **Difficulty:** Hard
- **Topics:** Greedy, Bit Manipulation, Array
- **Language:** Java

## Problem Statement

You are given a 0-indexed integer array `nums` and a positive integer `k`.

You can repeatedly choose two distinct indices `i` and `j` and simultaneously update:

- `nums[i] = nums[i] & nums[j]`
- `nums[j] = nums[i] | nums[j]`

Choose `k` elements from the final array to maximize the sum of their squares. Return the maximum sum modulo `10^9 + 7`.

## Approach: Greedy + Bit Manipulation

Instead of simulating the operations, count how many numbers contain each set bit.

The allowed operations preserve the total number of occurrences of every set bit across the array. Therefore, we can construct `k` numbers greedily by including every bit that is still available.

### Algorithm

1. Create an array `bitCount` to count the occurrences of each bit.
2. Iterate through every number and count its set bits.
3. Construct `k` numbers one by one.
4. For each number, include every bit whose remaining count is greater than zero.
5. Decrease the count of each used bit.
6. Add the square of each constructed number to the answer, taking modulo `10^9 + 7`.

## Java Solution

```java
import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bitCount = new int[31];

        // Count occurrences of each set bit
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long sum = 0;

        // Construct k maximum values greedily
        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (bitCount[bit] > 0) {
                    value |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            sum = (sum + value * value) % MOD;
        }

        return (int) sum;
    }
}
```

## Example

**Input:**
```text
nums = [2, 6, 5, 8]
k = 2
```

**Output:**
```text
261
```

**Explanation:**

The greedy strategy constructs the values `15` and `6`.

```text
15² + 6² = 225 + 36 = 261
```

## Complexity Analysis

Let `n` be the length of `nums`, `k` the number of selected elements, and `B = 31` the number of bits considered.

- **Time Complexity:** `O(B × (n + k))`, equivalent to `O(n + k)` for fixed-width integers.
- **Auxiliary Space:** `O(B)`, equivalent to `O(1)` for fixed-width integers.

## Key Concepts Learned

- Counting set bits using bitwise AND (`&`).
- Constructing numbers using bitwise OR (`|`).
- Greedy optimization by concentrating available set bits.
- Using `long` to avoid overflow when squaring values.
- Applying modulo arithmetic to large results.

## Pattern Recognition

When a problem allows bitwise operations and asks you to maximize a sum of squares, consider whether counting the occurrences of each bit and distributing those bits greedily can eliminate the need to simulate the operations.

## Tags

`#LeetCode` `#Java` `#DSA` `#Greedy` `#BitManipulation` `#Hard`
