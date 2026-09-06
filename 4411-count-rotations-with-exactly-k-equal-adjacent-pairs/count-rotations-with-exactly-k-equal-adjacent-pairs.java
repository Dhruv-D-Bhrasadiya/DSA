class Solution {
    public int countRotations(String s, int k) {
        int count = 0;
        int n = s.length();

        String d = s + s;

        for(int i = 0; i < n; i++){
            String rotate = d.substring(i, i + n);

            int score = 0;
            for(int j = 0; j < n - 1; j++){
                if(rotate.charAt(j) == rotate.charAt(j + 1)){
                    score++;
                }
            }

            if(score == k){
                count++;
            }
        }
        return count;
    }
}