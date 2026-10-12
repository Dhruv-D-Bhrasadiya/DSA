class BusBooking {
    int seats[][];
    int time = 0;
    Set<String> set;
    int count = 0;

    public BusBooking(int n) {
        seats = new int[n][4];
        set = new HashSet<>();
    }

    public void toggle(String seat) {
        int r = Integer.parseInt(seat.substring(1, seat.length())) - 1;
        int c = seat.charAt(0) - 'A';
        
        if(seats[r][c] > 0){
            time -= seats[r][c];
            seats[r][c] = 0;
            count--;
        }
        else{
            
            int cost = 1;
            if (c == 0) {
                if(seats[r][1] > 0){
                    cost = 3;
                }
            } 
            else if(c == 3) {
                if(seats[r][2] > 0){
                    cost = 3;
                }
            }

            seats[r][c] = cost;
            time += cost;
            count++;
        }
    }

    public int getTotalTime() {
        return time;
    }

    public int getMinTime() {
        return count;
    }
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * BusBooking obj = new BusBooking(n);
 * obj.toggle(seat);
 * int param_2 = obj.getTotalTime();
 * int param_3 = obj.getMinTime();
 */