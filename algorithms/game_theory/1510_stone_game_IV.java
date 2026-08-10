package algorithms.game_theory;

class Solution {
    public boolean winnerSquareGame(int n) {

        int[] ar = new int[n + 1];
        ar[1] = 1;
        int i;
        for(i = 2; i <= n; i++){
            if(ar[i - 1] == 0){
                ar[i] = 1;
                continue;
            }
    
            int ind;
            ind = (int)Math.sqrt(i);
            
            while(ind > 0){
                
                if(ar[i - ind*ind] == 0){
                    ar[i] = 1;
                    break;
                }
                ind--;
            }

        }

        return ar[n] == 1;


    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4; // Example input
        boolean result = solution.winnerSquareGame(n);
        System.out.println("Winner for n = " + n + ": " + result);
    }
}