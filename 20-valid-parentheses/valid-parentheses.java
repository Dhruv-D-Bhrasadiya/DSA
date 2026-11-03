class Solution {
    public boolean isValid(String s) {
        // Use Stack to keep track of brackets
        Stack<Character> st = new Stack<Character>();

        // Loop through each character in the string
        for(int i = 0; i < s.length(); i++){

            // If the character is an opening bracket, push it onto the stack
            if(s.charAt(i) == '(' ||
            s.charAt(i) == '[' ||
            s.charAt(i) == '{'){
                st.push(s.charAt(i));

            }
            // If the character is a closing bracket
            else{
                
                // If stack is empty, it means there is no matching opening bracket
                if(st.isEmpty()){
                    return false;
                }

                // Peek the top element to check if it matches the current closing bracket
                char temp = st.peek();

                // If it forms a matching pair, pop the opening bracket from the stack
                if((temp == '(' && s.charAt(i) == ')') ||
                (temp == '[' && s.charAt(i) == ']') ||
                (temp == '{' && s.charAt(i) == '}')){
                    st.pop();
                }

                // If brackets do not match, the sequence is invalid
                else{
                    return false;
                }
            }
        }

        // If the stack is empty, all brackets were matched correctly
        return st.isEmpty();
    }
}