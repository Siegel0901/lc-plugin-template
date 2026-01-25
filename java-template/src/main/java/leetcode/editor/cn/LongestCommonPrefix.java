package leetcode.editor.cn;

public class LongestCommonPrefix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：依次对比每个字符串的字符得到最长公共前缀
         * 时间复杂度：O(n * m)，n为字符串数组长度，m为最长公共前缀长度
         * 空间复杂度：O(1)
         *
         * @param strs
         * @return
         */
//        public String longestCommonPrefix(String[] strs) {
//            for (int i = 0; i < strs[0].length(); i++)
//                for (String str : strs)
//                    if (str.length() <= i || str.charAt(i) != strs[0].charAt(i))
//                        return strs[0].substring(0, i);
//            return strs[0];
//        }

        /**
         * 思路二：逐步缩短任意元素直至其为所有字符串前缀
         * 时间复杂度：O(S),S为所有字符串总长度
         * 空间复杂度：O(1)
         * @param strs
         * @return
         */
        public String longestCommonPrefix(String[] strs) {
            String prefix = strs[0];
            for (String str : strs) {
                while (!str.startsWith(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty())
                        return "";
                }
            }
            return prefix;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        // put your test code here
        solution.longestCommonPrefix(new String[]{"a", "a", "ab"});
    }
}