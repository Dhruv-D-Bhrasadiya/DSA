class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        visited[source] = true;
        queue.offer(source);

        while(!queue.isEmpty()){
            int curr = queue.poll();
            if(curr == destination){
                return true;
            }
            List<Integer> side = graph.getOrDefault(curr, new ArrayList<>());
            for(int i : side){
                if(!visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }

        return false;
    }
}