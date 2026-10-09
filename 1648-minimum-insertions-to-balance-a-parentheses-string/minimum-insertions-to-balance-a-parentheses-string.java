class Solution {
    public int minInsertions(String s) {
        int add = 0;
        int cnt = 0;
        int n = s.length();
        int idx = 0;
        while (idx < n) {
            char c = s.charAt(idx);
            if (c == '(') {
                cnt++;
                idx++;
            } else {
                if (cnt > 0) {
                    cnt--;
                } else {
                    add++;
                }
                if (idx < n - 1 && s.charAt(idx + 1) == ')') {
                    idx += 2;
                } else {
                    add++;
                    idx++;
                }
            }
        }
        add += cnt * 2;
        return add;
    }
}