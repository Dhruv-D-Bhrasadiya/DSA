class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> knowledgeMap = knowledge.stream().
        collect(Collectors.toMap(
            pair -> pair.get(0),
            pair -> pair.get(1),
            (existing, replacement) -> replacement)
        );


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                int idx1 = s.indexOf(')', i + 1);
                String key = s.substring(i + 1, idx1);

                sb.append(knowledgeMap.getOrDefault(key, "?"));
                i = idx1;
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}