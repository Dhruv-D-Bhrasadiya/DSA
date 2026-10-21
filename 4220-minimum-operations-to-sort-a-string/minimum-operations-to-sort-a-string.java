class Solution {
    public int minOperations(String s) {
        int n = s.length();

        if(n == 1){
            return 0;
        }
        if(n == 2){
            if(s.charAt(0) > s.charAt(1)){
                return -1;
            }
        }

        boolean sorted = true;
        char[] ch = s.toCharArray();

        char min = ch[0];
        char max = ch[0];

        for(int i = 1; i < n; i++){
            min = (char) Math.min(ch[i], min);
            max = (char) Math.max(ch[i], max);

            if(ch[i] < ch[i - 1]){
                sorted = false;
            }
        }
    
        if(sorted){
            return 0;
        }
        if(ch[0] == min || ch[n - 1] == max){
            return 1;
        }
        for(int i = 1; i < n - 1; i++){
            if(ch[i] == min || ch[i] == max){
                return 2;
            }
        }
        return 3;
    }
}