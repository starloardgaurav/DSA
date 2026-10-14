# 3723. Maximize Sum of Squares of Digits

**LeetCode:** [3723. Maximize Sum of Squares of Digits](https://leetcode.com/problems/maximize-sum-of-squares-of-digits/)  
**Difficulty:** Medium  
**Topics:** Greedy, Math, String  
**Language:** Java  
**Status:** Solved ✅

---

## Problem Statement

You are given two positive integers, `num` and `sum`.

A positive integer is considered good if:

- It has exactly `num` digits.
- The sum of its digits equals `sum`.

The score of a good integer is the sum of the squares of its digits.

Return a string representing the good integer with the maximum possible score. If multiple integers achieve the same score, return the numerically largest one. If no valid integer exists, return an empty string.

## Examples

### Example 1

```text
Input: num = 2, sum = 3
Output: "30"
```

Explanation:

The possible good integers include `12`, `21`, and `30`.

- Score of `12` = \(1^2 + 2^2 = 5\)
- Score of `21` = \(2^2 + 1^2 = 5\)
- Score of `30` = \(3^2 + 0^2 = 9\)

Therefore, `"30"` is the optimal answer.

### Example 2

```text
Input: num = 2, sum = 17
Output: "98"
```

Explanation:

The possible good integers are `89` and `98`. Both have the same score, so return the larger integer, `"98"`.

### Example 3

```text
Input: num = 1, sum = 10
Output: ""
```

Explanation:

A single digit cannot have a digit sum of `10`, so no valid answer exists.

---

## Approach: Greedy

### Intuition

To maximize the sum of squared digits, we should concentrate the digit sum into as few digits as possible.

For example, distributing a sum of `10` as `9 + 1` produces a larger squared sum than `5 + 5`:

- \(9^2 + 1^2 = 82\)
- \(5^2 + 5^2 = 50\)

Therefore, we place as many `9`s as possible at the beginning, followed by the remaining digit, if any. We fill the remaining positions with zeros.

Placing larger digits first also ensures that the resulting number is as large as possible when scores are tied.

### Algorithm

1. If `sum > 9 * num`, return an empty string because the available digits cannot reach the required sum.
2. Calculate the number of complete nines using `sum / 9`.
3. Calculate the remaining digit using `sum % 9`.
4. Append the required number of `9`s.
5. If the remainder is greater than zero, append it as the next digit.
6. Append zeros until the string has exactly `num` digits.
7. Return the resulting string.

---

## Java Solution

```java
class Solution {
    public String maxSumOfSquares(int num, int sum) {

        if (sum > num * 9) {
            return "";
        }

        int nines = sum / 9;
        int remain = sum % 9;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < nines; i++) {
            result.append('9');
        }

        if (remain > 0) {
            result.append(remain);
        }

        while (result.length() < num) {
            result.append('0');
        }

        return result.toString();
    }
}
```

---

## Dry Run

**Input:** `num = 2`, `sum = 17`

### Step 1: Check feasibility

The maximum possible digit sum is:

`9 * num = 9 * 2 = 18`

Since `17 <= 18`, a valid answer exists.

### Step 2: Calculate nines and remainder

- `nines = 17 / 9 = 1`
- `remain = 17 % 9 = 8`

### Step 3: Construct the answer

| Operation | Result |
|---|---|
| Start with an empty string | `""` |
| Append one `9` | `"9"` |
| Append the remainder `8` | `"98"` |
| Check length | 2 digits, so no zeros needed |

**Output:** `"98"`

Verification:

- Number of digits = `2`
- Digit sum = `9 + 8 = 17`
- Score = \(9^2 + 8^2 = 145\)

---

## Complexity Analysis

Let `num` be the required number of digits.

- **Time Complexity:** \(O(num)\) — the algorithm constructs a string of length `num`.
- **Auxiliary Space Complexity:** \(O(num)\) — the `StringBuilder` stores the resulting number.

---

## Key Learnings

- Greedy algorithms can maximize the sum of squared digits by concentrating the digit sum into larger digits.
- Every digit is at most `9`.
- Integer division determines how many complete nines can be used.
- The modulo operator determines the remaining digit.
- Zeros fill unused positions without changing the digit sum or score.
- When scores are equal, placing larger digits earlier produces the largest number.

## Interview Explanation

"I use a greedy strategy because concentrating the digit sum into larger digits maximizes the sum of squares. First, I check whether the required sum exceeds the maximum possible sum, `9 * num`. Then I append as many nines as possible, append the remainder if it exists, and fill the remaining positions with zeros. This constructs the maximum-score number in linear time."

---

## Pattern

**Greedy + Mathematical Observation + String Construction**

## Folder Structure

```text
Greedy/
└── Maximize-Sum-of-Squares-of-Digits-3723/
    ├── README.md
    └── Solution.java
```

## Tags

`Java` `LeetCode` `Greedy` `Math` `String` `Medium`
