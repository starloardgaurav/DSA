class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        fun(0, 0, n, "", res);
        return res;
    }

    public void fun(int o, int c, int n, String s, List<String> res) {
        if (o == n && c == n) {
            res.add(s);
            return;
        }

        if (o < n) {
            fun(o + 1, c, n, s + "(", res);
        }

        if (c < o) {
            fun(o, c + 1, n, s + ")", res);
        }
    }
}
