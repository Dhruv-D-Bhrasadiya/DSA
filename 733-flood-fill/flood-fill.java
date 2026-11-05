class Solution {
    
    public boolean isSafe(int[][] image, int sr, int sc, int px){
        return sr >= 0 && sc >= 0 && sr < image.length && sc < image[0].length && image[sr][sc] == px;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color){
            return image;
        }

        int px = image[sr][sc];

        image[sr][sc] = color;

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while(!q.isEmpty()){
            int curr[] = q.poll();
            for(int i = 0; i < 4; i++){
                int x = curr[0] + dir[i][0];
                int y = curr[1] + dir[i][1];

                if(isSafe(image, x, y, px)){
                    image[x][y] = color;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return image;
    }
}