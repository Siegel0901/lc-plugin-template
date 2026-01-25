package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseWordsInAString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：获取每个单词组成数组，翻转数组
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param s 字符串
         * @return 翻转后的字符串
         */
//        public String reverseWords(String s) {
//            // 先去除前导空格和尾随空格,再以" "为分割符获得分割后的字符串数组
//            String[] words = s.trim().split(" ");
//            // 去除单词之间的空格
//            List<String> list = Arrays.stream(words)
//                    .map(String::trim)
//                    .filter(word -> !word.isEmpty())
//                    .collect(Collectors.toList());
//            // 翻转
//            int left = 0, right = list.size() - 1;
//            while (left < right) {
//                String temp = list.get(left);
//                list.set(left++, list.get(right));
//                list.set(right--, temp);
//            }
//            // 用" "拼接
//            s = String.join(" ", list);
//            return s;
//        }

        /**
         * 思路二：先翻转整个字符串，再翻转每个单词
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param s 字符串
         * @return 翻转后的字符串
         */
        public String reverseWords(String s) {
            // 去除前导空格和尾随空格
            s = s.trim();
            StringBuilder sb = new StringBuilder();

            // 添加单词并筛去多余空格
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (' ' != c)
                    // 若不为空格则添加
                    sb.append(c);
                else if (' ' != s.charAt(i + 1))
                    // 若c为空格，且c后面不为空格，说明即将遇到新单词，添加空格
                    sb.append(c);
            }
            // 先翻转整个字符串
            char[] chars = sb.toString().toCharArray();
            reverse(chars, 0, chars.length - 1);

            // begin记录单词翻转起始位置
            int begin = 0;
            for (int i = 0; i < chars.length; i++) {
                if (' ' == chars[i]) {
                    // 若i为空格，翻转[begin, i - 1]
                    reverse(chars, begin, i - 1);
                    // 更新begin
                    begin = i + 1;
                }
                if (i + 1 == chars.length)
                    // 若遍历到结尾，则翻转最后一个单词[begin, i]
                    reverse(chars, begin, i);
            }
            return new String(chars);
        }

        public void reverse(char[] chars, int i, int j) {
            while (i < j) {
                char c = chars[i];
                chars[i++] = chars[j];
                chars[j--] = c;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAString().new Solution();
        // put your test code here

    }
}