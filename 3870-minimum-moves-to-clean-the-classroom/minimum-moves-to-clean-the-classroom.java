class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();
        
        int sr = -1, sc = -1;
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) java.util.Arrays.fill(row, -1);
        int litterCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    sr = i; sc = j;
                } else if (grid[i][j] == 'L') {
                    litterIdx[i][j] = litterCount++;
                }
            }
        }
        
        int fullMask = (1 << litterCount) - 1;
        if (litterCount == 0) return 0;
        
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litterCount];
        
        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();
        queue.offer(new int[]{sr, sc, energy, 0, 0});
        visited[sr][sc][energy][0] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], e = curr[2], mask = curr[3], moves = curr[4];
            
            if (mask == fullMask) return moves;
            if (e == 0) continue;
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (grid[nr][nc] == 'X') continue;
                
                int newE = (grid[nr][nc] == 'R') ? energy : e - 1;
                
                int newMask = mask;
                if (grid[nr][nc] == 'L' && litterIdx[nr][nc] != -1) {
                    newMask |= (1 << litterIdx[nr][nc]);
                }
                
                if (!visited[nr][nc][newE][newMask]) {
                    visited[nr][nc][newE][newMask] = true;
                    queue.offer(new int[]{nr, nc, newE, newMask, moves + 1});
                }
            }
        }
        
        return -1;
    }
}