package leetcode.editor.cn;

import java.util.*;

public class LongestSubstringWithAtLeastKRepeatingCharacters {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：自定义count变量记录子串中出现的字符种类
         * 1. 什么时候扩大窗口？当前窗口中的字符种类小于count
         * 2. 什么时候缩小窗口？当前窗口中的字符种类大于count
         * 3. 什么时候更新结果？当前窗口中的字符种类大于count，且每个字符种类出现的次数都大于等于k
         * 4. 最后遍历count从1到26，取最大结果返回
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param s 字符串
         * @param k 最小重复次数
         * @return 最长子串的长度
         */
        public int longestSubstring(String s, int k) {
            int res = 0;
            for (int i = 1; i <= 26; i++)
                res = Math.max(res, longestKLetterSubstring(s, k, i));
            return res;
        }

        public int longestKLetterSubstring(String s, int k, int count) {
            Map<Character, Integer> window = new HashMap<>();
            int res = 0;
            int validCount = 0;
            int left = 0, right = 0;
            while (right < s.length()) {
                char r = s.charAt(right++);
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r) == k)
                    validCount++;
                while (left < right && window.size() > count) {
                    char l = s.charAt(left++);
                    Integer i = window.get(l);
                    if (i - 1 == 0) {
                        window.remove(l);
                    } else {
                        window.put(l, i - 1);
                        if (i == k)
                            validCount--;
                    }
                }
                res = validCount == count ? Math.max(res, right - left) : res;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithAtLeastKRepeatingCharacters().new Solution();
        // put your test code here
//        solution.longestSubstring("bbaaacbd", 3);
        System.out.println(solution.longestSubstring("aaabb", 3));

    }
}