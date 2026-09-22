# 3525. Find X Value of Array II

**LeetCode:** [3525. Find X Value of Array II](https://leetcode.com/problems/find-x-value-of-array-ii/)

**Difficulty:** Hard

**Pattern:** Segment Tree + Modular Arithmetic

**Language:** Java

---

## 📌 Problem

You are given an array `nums`, an integer `k`, and a list of queries.

Each query is:

```text
[index, value, start, x]
```

For every query:

1. Update `nums[index]` to `value`.
2. Remove the prefix `nums[0 ... start - 1]`.
3. Then remove any suffix while keeping the array non-empty.
4. Count the number of possible remaining arrays whose product has remainder `x` modulo `k`.

The updates are persistent, meaning an update remains applied for all subsequent queries.

---

## 💡 Key Observation

After removing the prefix:

```text
nums[0 ... start - 1]
```

we are left with:

```text
nums[start ... n - 1]
```

Now we can remove any suffix.

Therefore, every possible remaining array is a **prefix** of:

```text
nums[start ... n - 1]
```

So the query becomes:

> Count prefixes of `nums[start ... n - 1]` whose product modulo `k` equals `x`.

---

## 🤔 Why Not Use the DP From 3524?

In **3524. Find X Value of Array I**, there are no updates.

We can process the array once using remainder DP:

```text
O(n × k)
```

But here:

```text
nums[index] = value
```

changes the array after every query.

Recomputing the entire range after every update would be too expensive.

Therefore, we need a data structure that supports:

- Point updates
- Range queries

This leads to a **Segment Tree**.

---

## 🧠 Segment Tree State

Each segment tree node stores:

```java
class Node {
    long[] pref;
    int product;
}
```

### `product`

```text
product =
product of all elements in the segment % k
```

### `pref[r]`

```text
pref[r] =
number of prefixes of this segment
whose product % k == r
```

Since `k <= 5`, we only need `k` remainder states.

---

## 🔹 Leaf Node

For a single element:

```text
[value]
```

There is exactly one prefix:

```text
[value]
```

If:

```text
value % k = r
```

then:

```java
pref[r] = 1;
product = r;
```

This is implemented as:

```java
tree[node] = new Node(k);

int rem = nums[l] % k;

tree[node].product = rem;
tree[node].pref[rem] = 1;
```

---

## 🔥 Merge Operation

Suppose:

```text
LEFT  = [a, b]
RIGHT = [c, d]
```

The prefixes of the combined segment are:

```text
[a]
[a,b]
[a,b,c]
[a,b,c,d]
```

There are two types of prefixes.

### Type 1: Prefix lies completely inside LEFT

These are already stored in:

```text
left.pref
```

So:

```java
for (int rem = 0; rem < k; rem++) {
    res.pref[rem] += left.pref[rem];
}
```

---

### Type 2: Prefix enters RIGHT

Any prefix that enters RIGHT must contain the entire LEFT segment.

Suppose:

```text
product(LEFT) % k = p
```

and a prefix of RIGHT has:

```text
product % k = r
```

Then the combined prefix has:

```text
(p × r) % k
```

Therefore:

```java
int newRem =
    (int) ((long) left.product * rem % k);

res.pref[newRem] += right.pref[rem];
```

---

## 🔹 Product of Combined Segment

The product of the complete segment is:

```text
product(LEFT) × product(RIGHT)
```

modulo `k`.

Therefore:

```java
res.product =
    (int) ((long) left.product * right.product % k);
```

---

## 🔄 Update Operation

For every query:

```text
nums[index] = value
```

we update the corresponding leaf.

At the leaf:

```java
tree[node] = new Node(k);

tree[node].product = value;
tree[node].pref[value] = 1;
```

Then we rebuild all affected ancestors:

```java
tree[node] =
    merge(tree[node * 2], tree[node * 2 + 1]);
```

This takes:

```text
O(k log n)
```

---

## 🔎 Range Query

After the update, the query asks about:

```text
[start ... n - 1]
```

We query this range in the segment tree:

```java
Node res =
    query(1, 0, n - 1, start, n - 1);
```

The required answer is directly stored in:

```java
res.pref[x]
```

Therefore:

```java
ans[q] = (int) res.pref[x];
```

---

## 🔄 Dry Run

Consider:

```text
nums = [1, 2, 3]
k = 3
```

Suppose we query:

```text
[start = 0, x = 2]
```

The possible remaining arrays are prefixes of:

```text
[1, 2, 3]
```

They are:

```text
[1]
[1,2]
[1,2,3]
```

Their products are:

```text
[1]       → 1 % 3 = 1
[1,2]     → 2 % 3 = 2
[1,2,3]   → 6 % 3 = 0
```

Therefore:

```text
pref[0] = 1
pref[1] = 1
pref[2] = 1
```

So for:

```text
x = 2
```

the answer is:

```text
1
```

---

## 🧩 Example of Merge

Consider:

```text
LEFT  = [1]
RIGHT = [2,3]
k = 3
```

LEFT:

```text
product = 1

pref:
remainder 1 → 1
```

RIGHT prefixes:

```text
[2]     → 2
[2,3]   → 6 % 3 = 0
```

So:

```text
right.pref[2] = 1
right.pref[0] = 1
```

When merging:

```text
[1]       → 1
[1,2]     → 2
[1,2,3]   → 0
```

Final:

```text
pref[0] = 1
pref[1] = 1
pref[2] = 1
```

---

## 💻 Java Solution

```java
class Solution {

    class Node {
        long[] pref;
        int product;

        Node(int k) {
            pref = new long[k];
            product = 1 % k;
        }
    }

    int n;
    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            update(1, 0, n - 1, index, value % k);

            Node res =
                query(1, 0, n - 1, start, n - 1);

            ans[q] = (int) res.pref[x];
        }

        return ans;
    }

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            tree[node] = new Node(k);

            int rem = nums[l] % k;

            tree[node].product = rem;
            tree[node].pref[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] =
            merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Prefixes completely inside LEFT
        for (int rem = 0; rem < k; rem++) {
            res.pref[rem] += left.pref[rem];
        }

        // Prefixes that extend from LEFT into RIGHT
        for (int rem = 0; rem < k; rem++) {

            int newRem =
                (int) ((long) left.product * rem % k);

            res.pref[newRem] += right.pref[rem];
        }

        // Product of the complete segment
        res.product =
            (int) ((long) left.product * right.product % k);

        return res;
    }

    void update(int node, int l, int r,
                int index, int value) {

        if (l == r) {

            tree[node] = new Node(k);

            tree[node].product = value;
            tree[node].pref[value] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] =
            merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int l, int r,
               int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1,
                         mid + 1, r, ql, qr);
        }

        Node left =
            query(node * 2, l, mid, ql, qr);

        Node right =
            query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}
```

---

## ⏱️ Complexity

Let:

```text
n = nums.length
q = number of queries
k = modulus
```

### Build

```text
O(n × k)
```

### Update

Each update visits `O(log n)` nodes and each merge costs `O(k)`:

```text
O(k log n)
```

### Query

Each range query visits `O(log n)` nodes and each merge costs `O(k)`:

```text
O(k log n)
```

### Total

```text
O(nk + qk log n)
```

Since:

```text
k <= 5
```

this is efficient for the given constraints.

---

## 💾 Space Complexity

The segment tree contains `O(n)` nodes.

Each node stores an array of size `k`.

Therefore:

```text
O(n × k)
```

Space complexity.

---

## ⚖️ Brute Force vs Optimized

### Brute Force

For every query:

1. Apply the update.
2. Start from `start`.
3. Calculate every possible prefix product.
4. Count the required remainder.

This would require repeatedly processing many elements and becomes too expensive.

---

### Segment Tree

The segment tree maintains information about every range.

Each node stores:

```text
Complete segment product
+
Number of prefixes for every remainder
```

Therefore:

```text
Point Update → O(k log n)

Range Query  → O(k log n)
```

---

## 🔑 Key Learning

The main idea is to combine:

```text
Segment Tree
      +
Modular Arithmetic
      +
State Compression
```

Because `k <= 5`, we don't need to store actual products.

We only store:

```text
product % k
```

and counts for each possible remainder.

---

## 🧠 Important DP-Like State

For each segment:

```text
pref[r]
```

means:

> Number of prefixes of this segment whose product modulo `k` equals `r`.

The merge transition is:

```text
left.pref
+
left.product × right.pref
```

modulo `k`.

This is the core of the entire solution.

---

## 🎯 Interview Explanation

> The key observation is that after removing the prefix up to `start - 1`, removing any suffix leaves a prefix of the remaining range. So each query asks for the number of prefixes of `[start, n-1]` having a particular product remainder. Because the array is modified by point updates, I use a segment tree. Each node stores the product of its complete segment modulo `k`, along with the count of prefixes for every possible remainder. When merging two nodes, prefixes entirely inside the left segment are copied directly, while prefixes extending into the right segment are combined with the complete product of the left segment. Since there are only `k` remainder states, each merge costs `O(k)`, giving `O(k log n)` per update and query.

---

## 🔥 Pattern

**Segment Tree + Modular Arithmetic + State Compression**

Reusable structure:

```text
Segment
   │
   ├── Complete Product % k
   │
   └── Prefix Count for each remainder
                │
                ▼
             merge()
                │
        ┌───────┴───────┐
        ▼               ▼
  Left Prefixes    Left Product
                   × Right Prefix
```

---

## 📌 Complexity Summary

| Operation | Complexity |
|---|---:|
| Build | O(nk) |
| Update | O(k log n) |
| Query | O(k log n) |
| Total | O(nk + qk log n) |
| Space | O(nk) |

---

## 🚀 Takeaway

The important transition from the previous problem is:

```text
3524
↓
Simple remainder DP
↓
O(nk)
```

to:

```text
3525
↓
Point Updates + Range Queries
↓
Segment Tree
↓
O(nk + qk log n)
```

The reusable lesson is:

> When a DP/state-compression problem gains **point updates and range queries**, consider storing the state inside a **segment tree node** and defining a correct `merge()` operation.
