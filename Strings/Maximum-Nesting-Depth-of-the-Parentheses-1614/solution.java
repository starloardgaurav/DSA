class Solution {
    public int maxDepth(String s) {
        int nd = 0;
        int count = 0;

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '(')
                count++;

            if(ch == ')')
                count--;

            if(count > nd){
                nd = count;
            }
        }

        return nd;
    }
}
