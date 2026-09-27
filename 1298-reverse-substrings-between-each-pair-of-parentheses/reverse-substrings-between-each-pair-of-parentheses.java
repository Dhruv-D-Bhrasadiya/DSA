class Solution {
    public String reverseParentheses(String s) {
        if(s.length() == 1){
            return s;
        }

        Deque<Character> stack = new ArrayDeque<>();

        
        for(int i = 0; i < s.length(); i++){
            char temp = s.charAt(i);
            if(temp == ')'){
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }

                if(!stack.isEmpty()){                    
                    stack.pop();
                }

                for(int j = 0; j < sb.length(); j++){
                    stack.push(sb.charAt(j));
                }
            }
            else{
                stack.push(temp);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        return sb.toString();
    }
}