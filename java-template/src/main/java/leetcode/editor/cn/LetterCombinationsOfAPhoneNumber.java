package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            backtrack(digits, 0);
            return res;
        }

        void backtrack(String digits, int pos) {
            if (sb.length() == digits.length()){
                res.add(sb.toString());
                return;
            }
            int digit = digits.charAt(pos) - '0';
            for (char c : mapping[digit].toCharArray()) {
                sb.append(c);
                backtrack(digits, pos + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        // put your test code here

    }
}