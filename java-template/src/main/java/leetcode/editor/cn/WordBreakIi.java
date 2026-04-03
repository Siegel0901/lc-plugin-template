package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class WordBreakIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> res = new ArrayList<>();
        List<String> track = new ArrayList<>();

        public List<String> wordBreak(String s, List<String> wordDict) {
            backtrack(s, wordDict, 0);
            return res;
        }

        void backtrack(String s, List<String> wordDict, int idx) {
            if (idx == s.length()) {
                res.add(String.join(" ", track));
                return;
            }
            for (String word : wordDict) {
                if (s.startsWith(word, idx)) {
                    track.add(word);
                    backtrack(s, wordDict, idx + word.length());
                    track.remove(track.size() - 1);
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new WordBreakIi().new Solution();
        // put your test code here

    }
}