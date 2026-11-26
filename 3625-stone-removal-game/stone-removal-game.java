class Solution {
    public boolean canAliceWin(int n) {
        if(n < 10){
            return false;
        }

        int count = 10;
        int i = 0;
        while(n >= count){
            n = n - count;
            count--;
            i++;
        }
        return i % 2 == 1;
    }
}