class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int close = 0;
        int n = s.length();
        int i = 0;
        int count = 0;
        while(i < n){
            if(s.charAt(i) == '('){
                open++;
            }
            else{
                if(open > 0){
                    open--;
                }
                else{
                    close++;
                }
            }
            i++;
        }

        return open + close;
    }
}