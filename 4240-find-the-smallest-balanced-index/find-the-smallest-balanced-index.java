public class Solution {
    public int smallestBalancedIndex(int[] nums) {
        int n = nums.length;
        
        long leftSum = 0;
        for (int num : nums) {
            leftSum += num;
        }
        
        long rightProduct = 1;
        int resultIndex = -1;
        
        long MAX_CAP = 100_000_000_000_001L; 

        for (int i = n - 1; i >= 0; i--) {
            leftSum -= nums[i]; 
            
            if (leftSum == rightProduct) {
                resultIndex = i; 
            }
            
            if (rightProduct <= MAX_CAP / nums[i]) {
                rightProduct *= nums[i];
            } else {
                rightProduct = MAX_CAP;
            }
        }
        
        return resultIndex;
    }
}
