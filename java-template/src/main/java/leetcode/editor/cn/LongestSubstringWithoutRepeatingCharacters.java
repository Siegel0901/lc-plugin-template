package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：滑动窗口
         * 1. 什么时候扩大窗口？窗口子串不含重复字符
         * 2. 什么时候缩小窗口？下一个字符加入窗口后含有重复字符
         * 3. 什么时候更新结果？下一个字符加入窗口后含有重复字符，更新结果
         *
         * @param s 字符串
         * @return 最长子串长度
         */
        public int lengthOfLongestSubstring(String s) {
            // window记录窗口中字符种类
            Set<Character> window = new HashSet<>();
            // 初始化滑动窗口指针
            int left = 0, right = 0;
            // len记录最长无重复字符子串长度
            int len = 0;
            while (right < s.length()) {
                // r是即将加入窗口的字符
                char r = s.charAt(right);
                // 窗口扩大
                right++;
                // 判断window中是否包含r
                while (window.contains(r)) {
                    // window此时是无重复子串,记录长度
                    len = Math.max(window.size(), len);
                    // l为即将移出窗口的字符
                    char l = s.charAt(left);
                    // 缩小窗口
                    left++;
                    // 移出窗口
                    window.remove(l);
                }
                // 加入窗口
                window.add(r);
            }
            // right走到结尾时，window是无重复子串，且字符个数还没有比较过，需要再更新一次结果
            return Math.max(window.size(), len);
        }

        /**
         * 思路二：滑动窗口
         * 1. 什么时候扩大窗口？窗口子串不含重复字符
         * 2. 什么时候缩小窗口？窗口子串含有重复字符
         * 3. 什么时候更新结果？窗口子串不含重复字符时，更新结果
         *
         * @param s 字符串
         * @return 最长子串长度
         */
//        public int lengthOfLongestSubstring(String s) {
//            // window记录窗口中字符以及个数
//            Map<Character, Integer> window = new HashMap<>();
//            // 初始化滑动窗口指针
//            int left = 0, right = 0;
//            // len记录最长无重复字符子串长度
//            int len = 0;
//            while (right < s.length()) {
//                // r是即将加入窗口的字符
//                char r = s.charAt(right);
//                // 窗口扩大
//                right++;
//                // 更新窗口中字符以及个数
//                window.put(r, window.getOrDefault(r, 0) + 1);
//                // 若字符r的个数为2,则表明r为重复字符
//                while (window.get(r) == 2) {
//                    // l为即将移出窗口的字符
//                    char l = s.charAt(left);
//                    // 缩小窗口
//                    left++;
//                    // 更新窗口中字符以及个数
//                    window.put(l, window.get(l) - 1);
//                }
//                // 此时窗口无重复字符,更新结果
//                len = Math.max(right - left, len);
//            }
//            return len;
//        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        // put your test code here

    }
}