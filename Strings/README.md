# Strings

This folder contains my solutions to **String problems** as part of my DSA and placement preparation.

The goal is to understand different string problem-solving patterns, improve time and space complexity, and maintain a structured revision list.

---

## 📚 Problems

| # | Problem | Difficulty | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| 1 | [Maximum Number of Non-overlapping Palindrome Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/) | Hard | Greedy + Two Pointers | O(n³) | O(1) | [View](./Maximum-Number-of-Non-overlapping-Palindrome-Substrings-2472/) |
| 2 | [Reverse Degree of a String](https://leetcode.com/problems/reverse-degree-of-a-string/) | Easy | Character Arithmetic / Math | O(n) | O(1) | [View](./Reverse-Degree-of-a-String-3498/) |
| 3 | [Brace Expansion II](https://leetcode.com/problems/brace-expansion-ii/) | Hard | Recursive Descent Parsing + Set | Depends on output size | Depends on output size | [View](./Brace-Expansion-II-1096/) |
| 4 | [Evaluate the Bracket Pairs of a String](https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/) | Medium | HashMap + String Parsing | O(n + k) | O(k + m) | [View](./Evaluate-the-Bracket-Pairs-of-a-String-1807/) |
| 5 | [Reverse Substrings Between Each Pair of Parentheses](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/) | Medium | Stack + String Manipulation | O(n²) | O(n) | [View](./Reverse-Substrings-Between-Each-Pair-of-Parentheses-1190/) |
| 6 | [Maximum Nesting Depth of the Parentheses](https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/) | Easy | Counter / Parentheses | O(n) | O(1) | [View](./Maximum-Nesting-Depth-of-the-Parentheses-1614/) |
| 7 | [Maximum Nesting Depth of Two Valid Parentheses Strings](https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/) | Medium | Depth / Greedy | O(n) | O(1) | [View](./Maximum-Nesting-Depth-of-Two-Valid-Parentheses-Strings-1111/) |
| 8 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | Stack / Bracket Matching | O(n) | O(n) | [View](./Valid-Parentheses-20/) |
| 9 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | Backtracking / Recursion | O(C(n) × n) | O(n) | [View](./Generate-Parentheses-22/) |
| 10 | [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) | Hard | Stack / Parentheses Matching | O(n) | O(n) | [View](./Longest-Valid-Parentheses-32/) |
| 11 | [Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/) | Medium | Greedy / Range Tracking | O(n) | O(1) | [View](./Valid-Parenthesis-String-678/) |
| 12 | [Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/) | Medium | Depth Tracking + Math | O(n) | O(1) | [View](./Score-of-Parentheses-856/) |
| 13 | [Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) | Medium | Greedy / Balance Tracking | O(n) | O(1) | [View](./Minimum-Add-to-Make-Parentheses-Valid-921/) |
| 14 | [Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/) | Hard | Backtracking + Pruning + HashSet | O(2^n × n) | O(2^n × n) | [View](./Remove-Invalid-Parentheses-301/) |
| 15 | [Remove Outermost Parentheses](https://leetcode.com/problems/remove-outermost-parentheses/) | Easy | Depth / Balance Tracking | O(n) | O(n) | [View](./Remove-Outermost-Parentheses-1021/) |

---

## 🧠 Patterns Covered

### Greedy
- [x] Maximum Number of Non-overlapping Palindrome Substrings
- [x] Maximum Nesting Depth of Two Valid Parentheses Strings
- [x] Valid Parenthesis String
- [x] Minimum Add to Make Parentheses Valid

### Two Pointers
- [x] Maximum Number of Non-overlapping Palindrome Substrings

### Character Arithmetic
- [x] Reverse Degree of a String

### Math
- [x] Reverse Degree of a String
- [x] Score of Parentheses

### Recursive Descent Parsing
- [x] Brace Expansion II

### Set / Cartesian Product
- [x] Brace Expansion II

### HashMap / String Parsing
- [x] Evaluate the Bracket Pairs of a String

### HashSet / Duplicate Elimination
- [x] Remove Invalid Parentheses

### Stack
- [x] Reverse Substrings Between Each Pair of Parentheses
- [x] Valid Parentheses
- [x] Longest Valid Parentheses

### Counter / Parentheses
- [x] Maximum Nesting Depth of the Parentheses

### Depth / Balance Tracking
- [x] Maximum Nesting Depth of Two Valid Parentheses Strings
- [x] Score of Parentheses
- [x] Minimum Add to Make Parentheses Valid

### Backtracking
- [x] Generate Parentheses
- [x] Remove Invalid Parentheses

### Recursion
- [x] Generate Parentheses
- [x] Remove Invalid Parentheses

### Bracket Matching
- [x] Valid Parentheses

### Parentheses Matching
- [x] Longest Valid Parentheses
- [x] Minimum Add to Make Parentheses Valid

### Greedy Range Tracking
- [x] Valid Parenthesis String

### Pruning
- [x] Remove Invalid Parentheses

---

## 📊 Progress

| Status | Count |
|---|---:|
| Solved | 14 |
| In Progress | 0 |
| Total Tracked | 14 |

**Progress: 14 / 14 solved**

---

## 📁 Folder Structure

Each solved problem has its own folder:

```text
Strings/
├── README.md
│
├── Brace-Expansion-II-1096/
│   ├── README.md
│   └── Solution.java
│
├── Evaluate-the-Bracket-Pairs-of-a-String-1807/
│   ├── README.md
│   └── Solution.java
│
├── Generate-Parentheses-22/
│   ├── README.md
│   └── Solution.java
│
├── Longest-Valid-Parentheses-32/
│   ├── README.md
│   └── Solution.java
│
├── Maximum-Nesting-Depth-of-Two-Valid-Parentheses-Strings-1111/
│   ├── README.md
│   └── Solution.java
│
├── Maximum-Nesting-Depth-of-the-Parentheses-1614/
│   ├── README.md
│   └── Solution.java
│
├── Maximum-Number-of-Non-overlapping-Palindrome-Substrings-2472/
│   ├── README.md
│   └── Solution.java
│
├── Minimum-Add-to-Make-Parentheses-Valid-921/
│   ├── README.md
│   └── Solution.java
│
├── Remove-Invalid-Parentheses-301/
│   ├── README.md
│   └── Solution.java
│
├── Reverse-Degree-of-a-String-3498/
│   ├── README.md
│   └── Solution.java
│
├── Reverse-Substrings-Between-Each-Pair-of-Parentheses-1190/
│   ├── README.md
│   └── Solution.java
│
├── Score-of-Parentheses-856/
│   ├── README.md
│   └── Solution.java
│
├── Valid-Parentheses-20/
│   ├── README.md
│   └── Solution.java
│
└── Valid-Parenthesis-String-678/
    ├── README.md
    └── Solution.java
```

---

## 📝 Each Problem README Contains

- Problem statement
- Examples
- Approach
- Intuition
- Algorithm
- Dry run
- Java solution
- Time complexity
- Space complexity
- Brute-force vs optimized approach
- Key learning
- Interview explanation
- Important pattern

---

## 🚀 Goal

Build strong understanding of **String patterns** rather than memorizing individual solutions.

### Target

- [ ] 25 String Problems
- [ ] 50 String Problems
- [ ] 75 String Problems
- [ ] 100 String Problems

---

## 🔗 Main Repository

[← Back to DSA Placement Preparation](../README.md)
