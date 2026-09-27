class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);

            if(ch == ')'){

                StringBuilder sr = new StringBuilder();

                while(stack.peek() != '('){
                    sr.append(stack.pop());
                }

                stack.pop();

                for(int j = 0; j < sr.length(); j++){
                    stack.push(sr.charAt(j));
                }

            } else {
                stack.push(ch);
            }
        }

        StringBuilder rs = new StringBuilder();

        while(!stack.isEmpty()){
            rs.append(stack.pop());
        }

        return rs.reverse().toString();
    }
}
