package data_structure.strings;

class Solution {
    public String rearrangeString(String s, char x, char y) {
        if(s.indexOf(x) == -1 || s.indexOf(y) == -1 || s.length() <= 1){
            return s;
        }

        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i++){
            freq[s.charAt(i) - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();

        while(freq[y - 'a'] > 0){
            sb.append(y);
            freq[y - 'a']--;
        }

        while(freq[x - 'a'] > 0){
            sb.append(x);
            freq[x - 'a']--;
        }
        char temp = 'a';
        for(int i = 0; i < 26; i++){
            while(freq[i] > 0){
                sb.append(temp);
                freq[i]--;
            }
            temp++;
        }

        return sb.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aabc";
        char x = 'a';
        char y = 'c';
        String result = solution.rearrangeString(s, x, y);
        System.out.println("Rearranged string: " + result);
    }   
}
