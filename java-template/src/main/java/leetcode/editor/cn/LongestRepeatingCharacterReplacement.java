package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：找到最多元素为x，其余元素个数<=k的最长子串
         * 1. 什么时候扩大窗口？其余元素个数 <= k
         * 2. 什么时候缩小窗口？其余元素个数 > k
         * 3. 什么时候更新结果？其余元素个数 <= k
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param s 字符串
         * @param k 替换次数
         * @return 最长子串长度
         */
//        public int characterReplacement(String s, int k) {
//            Map<Character, Integer> window = new HashMap<>();
//            int left = 0, right = 0;
//            int max = Integer.MIN_VALUE;
//            while (right < s.length()) {
//                char r = s.charAt(right);
//                right++;
//                window.put(r, window.getOrDefault(r, 0) + 1);
//                while (getOtherCharCount(window) > k) {
//                    char l = s.charAt(left);
//                    left++;
//                    window.put(l, window.get(l) - 1);
//                }
//                max = Math.max(max, right - left);
//            }
//            return max;
//        }
//
//        public int getOtherCharCount(Map<Character, Integer> map) {
//            int max = Integer.MIN_VALUE, sum = 0;
//            for (Integer value : map.values()) {
//                max = Math.max(max, value);
//                sum += value;
//            }
//            return sum - max;
//        }

        /**
         * 思路二：用windowMaxCount变量记录滑动过程中所有窗口中某字符出现的最大频次
         * 为什么窗口缩小后不用更新windowMaxCount？
         * windowMaxCount记录滑动过程中的所有窗口里出现最大频次字符的个数。
         * right - left - windowMaxCount > k时收缩窗口，则窗口大小可以认为满足right - left <= k + windowMaxCount。
         * k是不变的，要使窗口right - left（可能的结果）尽可能大
         * windowMaxCount可以保持历史最大（当前窗口虽不等于实际窗口，但不会超过已记录的最大值），窗口内出现频次更高的字符更新windowMaxCount即可。
         * 若窗口缩小时更新windowMaxCount，算法时间复杂度由O(n)变为O(26n)，影响虽然不大，但也算是优化。
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param s 字符串
         * @param k 替换次数
         * @return 最长子串长度
         */
        public int characterReplacement(String s, int k) {
            int[] windowCharCount = new int[26];
            int left = 0, right = 0;
            int max = 0;
            int windowMaxCount = 0;
            while (right < s.length()) {
                int r = s.charAt(right++) - 'A';
                windowCharCount[r]++;
                windowMaxCount = Math.max(windowMaxCount, windowCharCount[r]);
                while (right - left - windowMaxCount > k)
                    windowCharCount[s.charAt(left++) - 'A']--;
                max = Math.max(max, right - left);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestRepeatingCharacterReplacement().new Solution();
        // put your test code here

    }
}