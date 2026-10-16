# 2916. Subarrays Distinct Element Sum of Squares II

**LeetCode:** [2916. Subarrays Distinct Element Sum of Squares II](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/)  
**Difficulty:** Hard  
**Topics:** Array, Segment Tree, Lazy Propagation, Prefix Processing  
**Language:** Java  
**Status:** Solved ✅

---

## Problem Statement

Given an integer array `nums`, consider every non-empty contiguous subarray.

For each subarray, calculate the number of distinct elements and square that count. Return the sum of these squared counts over all subarrays, modulo \(10^9+7\).

Mathematically,

\[
\text{answer}=\sum_{S}|\operatorname{distinct}(S)|^2
\]

where \(S\) represents every non-empty contiguous subarray.

### Example

```text
Input: nums = [1, 2, 1]
Output: 15
```

Explanation:

| Subarray | Distinct count | Squared count |
|---|---:|---:|
| `[1]` | 1 | 1 |
| `[1, 2]` | 2 | 4 |
| `[1, 2, 1]` | 2 | 4 |
| `[2]` | 1 | 1 |
| `[2, 1]` | 2 | 4 |
| `[1]` | 1 | 1 |

Total = `1 + 4 + 4 + 1 + 4 + 1 = 15`.

---

## Approach: Segment Tree + Lazy Propagation

### Why brute force is inefficient

In the previous problem, **2913 — Subarrays Distinct Element Sum of Squares I**, I enumerated all subarrays using two nested loops and maintained a `HashSet`.

That approach takes \(O(n^2)\) expected time and is unsuitable for the larger constraints of this problem.

For problem 2916, we process the array from left to right and maintain the distinct-element counts of all subarrays ending at the current index.

### Key observation

Suppose we are processing `nums[i] = x`, and the previous occurrence of `x` was at index `last[x]`.

Before adding `x`, the distinct count increases by one for every subarray ending at `i` whose starting index is greater than `last[x]`.

Therefore, update the range:

`[last[x] + 1, i]`

by adding `1` to every value in that range.

If `x` has never appeared before, `last[x] = -1`, so the updated range is `[0, i]`.

### What the segment tree stores

Each segment-tree node maintains two values:

- `sum[node]`: Sum of the distinct-element counts represented by that node.
- `squareSum[node]`: Sum of the squares of those distinct-element counts.

The root represents all starting positions. Positions beyond the current index have value zero and do not contribute.

After processing index `i`, `squareSum[1]` equals the sum of squared distinct counts for every subarray ending at `i`.

Adding this root value to the global answer after each update gives the required answer.

### Lazy propagation formula

Suppose every value in a segment is increased by `val`, and the segment contains `len` values.

For each value \(c\),

\[
(c+val)^2=c^2+2c\cdot val+val^2
\]

Summing across the segment gives:

\[
\text{newSquareSum}
=\text{oldSquareSum}
+2\cdot val\cdot\text{oldSum}
+len\cdot val^2
\]

The sum is updated as:

\[
\text{newSum}=\text{oldSum}+len\cdot val
\]

All calculations are performed modulo \(10^9+7\).

### Algorithm

1. Initialize the segment-tree arrays `sum`, `squareSum`, and `lazy`.
2. Initialize `last[x] = -1` for every possible value.
3. Process each array element from left to right.
4. Find its previous occurrence using `last[x]`.
5. Increment the segment-tree range `[last[x] + 1, i]` by one.
6. Add `squareSum[1]` to the global answer modulo \(10^9+7\).
7. Update `last[x] = i`.
8. Return the final answer.

---

## Java Solution

```java
class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] squareSum;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int left = last[x] + 1;

            update(1, 0, n - 1, left, i, 1);

            ans = (ans + squareSum[1]) % MOD;

            last[x] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, long val) {
        long len = r - l + 1L;

        squareSum[node] = (
            squareSum[node]
            + 2L * val % MOD * sum[node] % MOD
            + len % MOD * val % MOD * val % MOD
        ) % MOD;

        sum[node] = (sum[node] + len * val) % MOD;

        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = l + (r - l) / 2;

        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }

    private void pull(int node) {
        sum[node] = (
            sum[node * 2] + sum[node * 2 + 1]
        ) % MOD;

        squareSum[node] = (
            squareSum[node * 2] + squareSum[node * 2 + 1]
        ) % MOD;
    }

    private void update(
        int node, int l, int r,
        int ql, int qr, long val
    ) {
        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }

        push(node, l, r);

        int mid = l + (r - l) / 2;

        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);

        pull(node);
    }
}
```

---

## Dry Run

**Input:** `nums = [1, 2, 1]`

Initially, all segment-tree values are zero.

| Index `i` | Value | Previous occurrence | Updated range | Root square sum | Running answer |
|---:|---:|---:|---|---:|---:|
| 0 | 1 | -1 | `[0, 0]` | 1 | 1 |
| 1 | 2 | -1 | `[0, 1]` | 5 | 6 |
| 2 | 1 | 0 | `[1, 2]` | 9 | 15 |

At index `2`, the updated distinct counts for the subarrays ending there are:

- `[1, 2, 1]` → 2 distinct elements
- `[2, 1]` → 2 distinct elements
- `[1]` → 1 distinct element

Their squared sum is \(2^2+2^2+1^2=9\).

The final answer is:

`1 + 5 + 9 = 15`

---

## Complexity Analysis

Let `n` be the length of the array.

- **Time Complexity:** \(O(n\log n)\) — each element requires one range update, which takes \(O(\log n)\) time.
- **Auxiliary Space Complexity:** \(O(n+V)\), where \(V\) is the value-domain size used by the `last` array. The segment tree uses \(O(n)\) space, and this implementation allocates `last` with 100001 entries.

---

## Key Learnings

- Processing the array from left to right lets us maintain all subarrays ending at the current position.
- The previous occurrence of a value determines which starting positions gain a new distinct element.
- A segment tree supports efficient range updates and aggregate queries.
- Lazy propagation avoids updating every element individually.
- Maintaining both the sum and sum of squares allows the square aggregate to be updated algebraically.
- Modular arithmetic prevents the accumulated answer from growing too large.

## Pattern

**Segment Tree + Lazy Propagation + Range Update + Sum of Squares**

## Related Problem

- [2913. Subarrays Distinct Element Sum of Squares I](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/) — Brute force + `HashSet`.

## Folder Structure

```text
Arrays/
├── README.md
├── Subarrays-Distinct-Element-Sum-of-Squares-I-2913/
│   ├── README.md
│   └── Solution.java
└── Subarrays-Distinct-Element-Sum-of-Squares-II-2916/
    ├── README.md
    └── Solution.java
```

## Tags

`Java` `LeetCode` `Array` `Segment Tree` `Lazy Propagation` `Range Update` `Hard`
