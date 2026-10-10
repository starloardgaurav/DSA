class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];

        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (k >= Arrays.stream(diff).asLongStream().sum()) {
            return 0L;
        }

        int left = 0, right = maxDiff;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int target = left;
        long remaining = k;
        long answer = 0;

        for (int d : diff) {
            if (d > target) {
                remaining -= d - target;
                d = target;
            }
            answer += (long) d * d;
        }

        for (int d : diff) {
            if (remaining == 0) break;
            if (d >= target && target > 0) {
                answer -= (long) target * target
                        - (long) (target - 1) * (target - 1);
                remaining--;
            }
        }

        return answer;
    }
}
