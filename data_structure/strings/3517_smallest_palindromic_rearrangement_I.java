package data_structure.strings;

/*
Approach 1: Brute Force/ Process the everything
class Solution {
    public String smallestPalindrome(String s) {
        
        int n = s.length();
        if(n == 1){
            return s;
        }

        int[] freq = new int[26];
        for(int i = 0; i < n; i++){
            freq[s.charAt(i) - 'a']++;
        }
        int i = 0;
        int j = n - 1;
        int p = 0;
        char character = 'a';
        char[] ch = new char[n];
        while(i <= j && p < 26){
            int c = freq[p++];
            while(c > 0 && c != 1){
                ch[i] = character;
                ch[j] = character;
                c = c - 2;
                i++;
                j--;
            }
            if(c == 1){
                ch[n / 2] = character;
            }
            character++;
        }

        return new String(ch);
    }
}
*/

class Solution {
    public String smallestPalindrome(String s) {
        
        int n = s.length();
        if(n == 1){
            return s;
        }

        String middle = (n % 2 != 0) ? String.valueOf(s.charAt(n / 2)) : "";
        int[] freq = new int[26];

        n >>= 1;
        for(int i = 0; i < n; i++){
            freq[s.charAt(i) - 'a']++;
        }

        char character = 'a';
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 26; i++){
            sb.append(String.valueOf((char)(i + 'a')).repeat(freq[i]));
        }

        return sb.toString() + middle + sb.reverse().toString();
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.smallestPalindrome("babab")); // Expected output: "abbba"
        System.out.println(solution.smallestPalindrome("daccad")); // Expected output: "acddca"
    }
}