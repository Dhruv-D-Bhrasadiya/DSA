class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length, minElement = Integer.MAX_VALUE;
        int[] prefixMin = new int[n];
        for (int i = n-1; i >= 0; --i) {
            minElement = Math.min(minElement, nums[i]);
            prefixMin[i] = minElement;
        }

        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            maxElement = Math.max(maxElement, nums[i]);
            int stabilityScore = maxElement - prefixMin[i];
            if (stabilityScore <= k) {
                return i;
            }
        }

        return -1;
    }
}