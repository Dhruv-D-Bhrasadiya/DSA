class Solution {
    public int scoreOfParentheses(String s) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                list.add(count);
                count = 0;
            }
            else{
                if(s.charAt(i - 1) == '('){
                    count = (list.get(list.size() - 1) + 1);
                }   
                else{
                    count = list.get(list.size() - 1) + (count * 2);
                }
                list.remove(list.size() - 1);
            }
        }
        return count;
    }
}