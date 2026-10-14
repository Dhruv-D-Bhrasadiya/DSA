class Solution {
    public String maxSumOfSquares(int num, int sum) {

        if(sum > num * 9){
            return "";
        }

        int n = sum / 9;
        int rem = sum % 9;
        StringBuilder s = new StringBuilder("9".repeat(n));

        if(rem > 0){
            s.append((char) (rem + '0'));
        }

        s.append("0".repeat(num - s.length()));

        return s.toString();
    }
}