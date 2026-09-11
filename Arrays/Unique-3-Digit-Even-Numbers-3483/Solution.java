class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] used = new boolean[digits.length];
        List<Integer> comb = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        isEven(digits,comb,set,used);
        return set.size();
    }

    void isEven(int[] digits, List<Integer> comb,Set<Integer> set, boolean[] used){

        if(comb.size() == 3){
            if(comb.get(2) % 2 == 0){
                int num = comb.get(0)*100 + comb.get(1)*10 + comb.get(2);
                set.add(num);
            }
            return ;
        }


        for(int i = 0 ; i < digits.length ; i++){
            if(used[i])
                continue;
            
            if(comb.size() == 0 && digits[i] == 0)
                continue;

            
            used[i] = true;
            comb.add(digits[i]);

            isEven(digits,comb,set,used);

            comb.remove(comb.size()-1);
            used[i] = false;
        }
        
    }
}
