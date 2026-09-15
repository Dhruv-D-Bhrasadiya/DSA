class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;

        for(int i = 0; i <= n - k; i++){

            if(check(s, i, i + k - 1)){
                count++;
                i += k - 1;
            }
            else if(i < n - k && check(s, i, i + k)){
                count++;
                i += k;
            }
            
        }
        return count;
    }      

    boolean check(String s, int l, int r){
        for(; l < r; l++, r--){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
        }
        return true;
    }  
}