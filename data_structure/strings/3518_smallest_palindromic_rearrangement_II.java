package data_structure.strings;

class Solution {
    static int LIMIT = 1_000_000;
    public static long comb(int n, int r){
        r = Math.min(r, n-r);
        long ans = 1;
        for(int i = 1; i <= r; i++){
            ans = ans * (n-r+i)/i;
            if(ans > LIMIT)
                return LIMIT + 1;
        }
        return ans;
    }
    public static long getWays(int n , int[] cnts){
        long ways = 1;
        for(int i = 0; i < 26; i++){
            if(cnts[i] <= 0)
                continue;
            ways *= comb(n, cnts[i]);
            if(ways > LIMIT)
                return LIMIT + 1;
            n -= cnts[i];
        }
        return ways;
    }

    public String smallestPalindrome(String s, int k) {

        int[] cnts = new int[26];
        int n = s.length();
        for(int i = 0; i < n; i++)
            cnts[s.charAt(i) - 'a']++;
        
        int oddChar = -1;
        int totalCnt = 0;
        for(int i = 0; i < 26; i++){
            if(cnts[i] % 2 == 1){
                oddChar = i;
                cnts[i]--;
            }
            cnts[i] >>= 1;
            totalCnt += cnts[i];
        }
        int totalWays = (int) getWays(totalCnt, cnts);
        if(k > totalWays)
            return "";

        StringBuilder res = new StringBuilder();
        while(totalCnt > 0){
            for(int i = 0; i < 26; i++){
                if(cnts[i] == 0)
                    continue;
                cnts[i]--;
                totalCnt--;
                int ways = (int) getWays(totalCnt, cnts);
                if(k > ways){
                    k -= ways;
                    cnts[i]++;
                    totalCnt++;
                }else{
                    res.append((char)(i + 'a'));
                    break;
                }
            }
        } 
        StringBuilder half2 = new StringBuilder(res);
        half2.reverse();
        if(oddChar != -1)
            res.append((char)(oddChar + 'a'));
        res.append(half2);

        return res.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "bacab";
        int k = 1;
        String result = sol.smallestPalindrome(s, k);
        System.out.println(result); // Output: "abcba"
    }
}       