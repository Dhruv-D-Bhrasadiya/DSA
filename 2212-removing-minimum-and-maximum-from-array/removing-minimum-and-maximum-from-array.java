class Solution {
    public int minimumDeletions(int[] nums) {
        int madx = Integer.MIN_VALUE, midx = Integer.MAX_VALUE;
        int max_idx = -1, min_idx = -1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > madx) {
                madx = nums[i];
                max_idx = i;
            }

            if(nums[i] < midx) {
                midx = nums[i];
                min_idx = i;
            }
        }

        int c1 = nums.length - (Math.abs(max_idx - min_idx) - 1);

        int c2 = Math.max(max_idx, min_idx) + 1;

        int c3 = nums.length - Math.min(max_idx, min_idx);

        return Math.min(c1, Math.min(c2, c3));
    }
}