class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char ch[] = new char[2 * n];
        func(ans, ch, 0, n , n);
        return ans;
    }

    public static void func(List<String> ans, char[] ch, int idx, int open, int close){
        if(open == 0 && close == 0){
            ans.add(new String(ch));
            return;
        }

        if(open > 0){
            ch[idx] = '(';
            func(ans, ch, idx + 1, open - 1 , close);
        }

        if(close > 0 && close > open){
            ch[idx] = ')';
            func(ans, ch, idx + 1, open , close - 1);
        }
    }
}