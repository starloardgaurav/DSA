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
