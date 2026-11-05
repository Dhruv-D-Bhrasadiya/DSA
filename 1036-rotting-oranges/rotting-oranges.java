class Solution {
    
    private boolean safe(int i, int j, int n, int m){
        return 0 <= i && i < n && 0 <= j && j < m;
    }
    
    public int orangesRotting(int[][] grid) {
        int ans = 0;
        
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        boolean change;

        while(true){
            change = false;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(grid[i][j] == ans + 2){
                        for(int[] d : dir){
                            int x = i + d[0];
                            int y = j + d[1];

                            if(safe(x, y, n, m) && grid[x][y] == 1){
                                grid[x][y] = grid[i][j] + 1;
                                change = true;
                            }
                        }
                    }
                }
            }
            if(!change){
                break;
            }
            ans++;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }

        return ans;
    }

}