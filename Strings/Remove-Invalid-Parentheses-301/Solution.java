class Solution {
    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int left = 0;
        int right = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                left++;
            } 
            else if (ch == ')') {
                if (left > 0) {
                    left--;
                } 
                else {
                    right++;
                }
            }
        }

        backtrack(s, 0, left, right);

        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int left, int right) {

        if (index == s.length()) {
            if (left == 0 && right == 0 && isValid(s)) {
                result.add(s);
            }
            return;
        }

        char ch = s.charAt(index);

        if (ch == '(' && left > 0) {
            backtrack(
                s.substring(0, index) + s.substring(index + 1),
                index,
                left - 1,
                right
            );
        }

        if (ch == ')' && right > 0) {
            backtrack(
                s.substring(0, index) + s.substring(index + 1),
                index,
                left,
                right - 1
            );
        }

        backtrack(s, index + 1, left, right);
    }

    private boolean isValid(String s) {
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            } 
            else if (ch == ')') {
                count--;

                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}
