class Solution {
    
    static boolean safe(int i, int j, int n, int m){
        return 0 <= i && i < n && 0 <= j && j < m;
    }
    
    public int orangesRotting(int[][] grid) {
        int ans = 0;
        
        int n = grid.length;
        int m = grid[0].length;

        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        Queue<int[]> q = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2){
                    q.offer(new int[]{i, j}); 
                }
            }
        }

        while(!q.isEmpty()){
            int size = q.size();
            boolean change = false;

            for(int i = 0; i < size; i++){
                int cell[] = q.poll();

                int x = cell[0];
                int y = cell[1];

                for(int[] d : dir){
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if(safe(nx, ny, n, m) && grid[nx][ny] == 1){
                        grid[nx][ny] = 2;
                        q.offer(new int[]{nx, ny});
                        change = true;
                    }
                }
            }
            if(change){
                ans++;
            }
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