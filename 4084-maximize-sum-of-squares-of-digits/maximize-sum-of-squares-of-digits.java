class Solution {
    public String maxSumOfSquares(int num, int sum) {

        if(sum > num * 9){
            return "";
        }

        int n = sum / 9;
        int rem = sum % 9;
        StringBuilder s = new StringBuilder();

        while(n > 0){
            s.append('9');
            n--;
        }

        if(rem > 0){
            s.append((char) (rem + '0'));
        }

        while(s.length() < num){
            s.append('0');
        }

        return s.toString();
    }
}