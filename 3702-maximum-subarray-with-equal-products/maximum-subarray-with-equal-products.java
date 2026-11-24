class Solution {

    public long gcd(long a, long b){
        while(b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public long lcm(long a, long b){
        if(a == 0 || b == 0){
            return 0;
        }
        return (a / gcd(a, b)) * b;
    }

    public int maxLength(int[] nums) {
        int maxLen = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            long currProd = 1;
            long currGcd = 0;
            long currLcm = 1;

            for(int j = i; j < n; j++){
                currProd *= nums[j];
                currGcd = gcd(currGcd, nums[j]);
                currLcm = lcm(currLcm, nums[j]);

                if(currProd == currGcd * currLcm){
                    maxLen = Math.max(maxLen, j - i + 1);
                }

                if(currProd > 1000000000L){
                    break;
                }
            }
        }

        return maxLen;
    }
}