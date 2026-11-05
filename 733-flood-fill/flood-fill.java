class Solution {
    
    public boolean isSafe(int[][] image, int sr, int sc, int px){
        return sr >= 0 && sc >= 0 && sr < image.length && sc < image[0].length && image[sr][sc] == px;
    }

    public int[][] dfs(int[][] image, int sr, int sc, int color, int px){
        
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        image[sr][sc] = color;
        for(int i = 0; i < 4; i++){
            int x = sr + dir[i][0];
            int y = sc + dir[i][1];

            if(isSafe(image, x, y, px)){
                dfs(image, x, y, color, px);
            }
        }
        return image;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color){
            return image;
        }
        int px = image[sr][sc];
        return dfs(image, sr, sc, color, px);
    }
}