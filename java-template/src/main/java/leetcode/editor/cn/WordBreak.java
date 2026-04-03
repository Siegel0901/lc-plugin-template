package leetcode.editor.cn;

import java.util.*;

public class WordBreak {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：分解问题（动态规划）
     * 1. 问题：wordDict能否拼接为s[i..]
     * 2. 子问题：若s.substring(i,len)是s[i..]的前缀，问题转换为wordDict能否拼接为s[i+len..]
     * 3. 状态：s中的每一个字符位置，共有N个状态
     * 4. dp函数定义：dp(s,i)表示wordDict能否拼接为s[i..]
     * 时间复杂度：O(N*MN)
     * 递归深度：每个状态通过备忘录只计算一次：O(N)
     * 每个节点的开销：遍历字符串前缀O(MN)
     */
    class Solution {
        Map<Integer, Boolean> memo = new HashMap<>();

        public boolean wordBreak(String s, List<String> wordDict) {
            return dp(s, 0, wordDict);
        }

        boolean dp(String s, int idx, List<String> wordDict) {
            if (idx == s.length())
                return true;
            // 查找之前是否在idx这个位置分割过
            if (memo.containsKey(idx))
                return memo.get(idx);

            for (String word : wordDict) {
                if (s.startsWith(word, idx)) {
                    if (dp(s, idx + word.length(), wordDict)) {
                        memo.put(idx, true);
                        return true;
                    }
                }
            }

            // 在idx处分割找不到结果
            memo.put(idx, false);
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new WordBreak().new Solution();
        // put your test code here
//        solution.wordBreak("leetcode", new ArrayList<>(List.of("leet", "code")));
        solution.wordBreak("applepenapple", new ArrayList<>(List.of("apple", "pen")));
    }
}