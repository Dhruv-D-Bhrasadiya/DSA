class Solution {
    public String maxSumOfSquares(int num, int sum) {
        int n = 9;
        StringBuilder s = new StringBuilder();

        for(int i = 1; i <= num; i++){
            if(sum - n > 0){
                s.append(n);
                sum = sum - n;
            }
            else{
                // if(s.length() == num){
                //     return "";
                // }
                // else{
                    s.append(sum);
                    sum = 0;
                // }
            }
        }

        if(sum != 0){
            return "";
        }
        return s.toString();
    }
}