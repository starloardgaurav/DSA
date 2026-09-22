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

            Node res = query(1, 0, n - 1, start, n - 1);

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

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node left, Node right) {

        Node res = new Node(k);

        for (int rem = 0; rem < k; rem++) {
            res.pref[rem] += left.pref[rem];
        }

        for (int rem = 0; rem < k; rem++) {

            int newRem =
                (int) ((long) left.product * rem % k);

            res.pref[newRem] += right.pref[rem];
        }

        res.product =
            (int) ((long) left.product * right.product % k);

        return res;
    }

    void update(int node, int l, int r, int index, int value) {

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

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left =
            query(node * 2, l, mid, ql, qr);

        Node right =
            query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}
