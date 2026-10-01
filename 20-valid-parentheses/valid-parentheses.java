class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' ||
            s.charAt(i) == '[' ||
            s.charAt(i) == '{'){
                st.push(s.charAt(i));

            }
            else{
                if(st.isEmpty()){
                    return false;
                }
                char temp = st.peek();
                if((temp == '(' && s.charAt(i) == ')') ||
                (temp == '[' && s.charAt(i) == ']') ||
                (temp == '{' && s.charAt(i) == '}')){
                    st.pop();
                }
                else{
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}