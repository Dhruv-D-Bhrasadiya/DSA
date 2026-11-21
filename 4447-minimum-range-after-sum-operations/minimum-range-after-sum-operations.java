class Solution {
    public int minRange(int a, int b, int c) {
        int ans = Integer.MAX_VALUE;

        while(true){
            int temp = ans;
            if(a > b && a > c && b + c <= a){
                a = b + c;
                ans = Math.min(ans, (Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c))));
            }
            
            if(b > a && b > c && a + c <= b){
                b = a + c;
                ans = Math.min(ans, (Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c))));
            }
            
            if(c > b && c > a && a + b <= c){
                c = a + b;
                ans = Math.min(ans, (Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c))));
            }
            
            if(temp == ans || ans == 0){
                ans = Math.min(ans, (Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c))));
                return ans;
            }
        }
    }
}