class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int count = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                count++;
            }
            else{
                count--;
                if(s.charAt(i - 1) == '('){
                    score += Math.pow(2, count);
                }
            }
        }
        return score;
    }
}