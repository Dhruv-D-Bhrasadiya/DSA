class Solution {
    public String removeOuterParentheses(String s) {
        int open = 0;
        int close = 0;
        StringBuilder st = new StringBuilder();
        int ptr = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                open++;
                st.append('(');
            }
            else{
                st.append(')');
                close++;
            }

            if(open == close){
                st.deleteCharAt(st.length() - 1);
                st.deleteCharAt(ptr);
                ptr = st.length();
            }
        }
        return st.toString();
    }
}