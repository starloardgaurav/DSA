class Solution {

    String s;
    int idx;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;
        return new ArrayList<>(parseExpression());
    }

    private Set<String> parseExpression() {
        Set<String> result = parseConcatenation();

        while (idx < s.length() && s.charAt(idx) == ',') {
            idx++;
            result.addAll(parseConcatenation());
        }

        return result;
    }

    private Set<String> parseConcatenation() {
        Set<String> result = new TreeSet<>();
        result.add("");

        while (idx < s.length()
                && s.charAt(idx) != ','
                && s.charAt(idx) != '}') {

            Set<String> next = parseFactor();
            Set<String> temp = new TreeSet<>();

            for (String a : result) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    private Set<String> parseFactor() {

        Set<String> result;

        if (s.charAt(idx) == '{') {

            idx++;
            result = parseExpression();
            idx++;

        } else {

            result = new TreeSet<>();
            result.add(String.valueOf(s.charAt(idx)));
            idx++;
        }

        return result;
    }
}
