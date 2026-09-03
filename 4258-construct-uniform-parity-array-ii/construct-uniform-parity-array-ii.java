class Solution {
    public boolean uniformArray(int[] nums1) {
        int minEle = Integer.MAX_VALUE;
        for(int i = 0; i < nums1.length; i++){
            minEle = Math.min(minEle, nums1[i]);
        }
        if(minEle % 2 == 1){
            return true;
        }

        for(int num : nums1){
            if(num % 2 == 1){
                return false;
            } 
        }

        return true;
    }
}