class Solution {
    
    public static boolean isSafe(char[][] g, int i, int j, boolean[][] v, int n, int m){
        return i >= 0 && j >= 0 && i < n && j < m && g[i][j] == '1' && !v[i][j];
    }

    public static void dfs(char[][] g, int i, int j, boolean[][] v, int n, int m){
        v[i][j] = true;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for(int k = 0; k < 4; k++){
            int x = i + dr[k];
            int y = j + dc[k];
            if(isSafe(g, x, y, v, n, m)){
                dfs(g, x, y, v, n, m);
            }
        }
    }

    public int numIslands(char[][] g) {
        int n = g.length;
        int m = g[0].length;

        boolean[][] v = new boolean[n][m];

        int islands = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(g[i][j] == '1' && !v[i][j]){
                    dfs(g, i, j, v, n, m);
                    islands++;
                }
            }
        }

        return islands;
    }
}