class Solution {

    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int len = m + n - 1;

        if ((len & 1) == 1) {
            return false;
        }

        if (grid[0][0] != '(') {
            return false;
        }

        if (grid[m - 1][n - 1] != ')') {
            return false;
        }

        dp = new Boolean[m][n][len + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {

        int m = grid.length;
        int n = grid[0].length;

        if (r >= m || c >= n) {
            return false;
        }

        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        int remaining = (m - 1 - r) + (n - 1 - c);

        if (balance > remaining) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (dp[r][c][balance] != null) {
            return dp[r][c][balance];
        }

        boolean down = dfs(grid, r + 1, c, balance);
        boolean right = dfs(grid, r, c + 1, balance);

        return dp[r][c][balance] = down || right;
    }
}
