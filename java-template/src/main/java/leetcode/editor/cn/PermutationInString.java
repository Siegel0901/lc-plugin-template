package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class PermutationInString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：滑动窗口
         * 1. 使用双指针[left,right)作为滑动窗口遍历字符串s2
         * 2. left和right初值为0
         * 3. right右移至[left,right)中包含s1中的所有字符种类及个数时停止
         * 4. left开始右移至[left,right)中不包含s1中的所有字符种类及个数时停止
         * 5. 若left右移过程中窗口子串的长度等于s1的长度，则返回true
         * 5. 什么时候元素加入窗口？当前窗口子串不含s1中的所有字符种类及个数
         * 5.1. 加入窗口后的更新操作？更新窗口子串中包含于s1中的字符种类及个数
         * 6. 什么时候元素移出窗口？当前窗口子串包含s1中的所有字符种类及个数
         * 6.1. 移出窗口后的更新操作？更新窗口子串中包含于s1中的字符种类及个数
         * 7. 什么时候更新结果？
         * 7.1. 元素移出窗口过程中，每个子串都包含s1中的所有字符种类及个数，窗口子串长度等于s1的长度时没有多余字符，返回true
         * 时间复杂度：O(s1.length + s2.length)
         * 空间复杂度：O(1)
         *
         * @param s1 子串
         * @param s2 源字符串
         * @return 是否包含子串
         */
//        public boolean checkInclusion(String s1, String s2) {
//            // window用于存放窗口中的字符种类以及个数
//            Map<Character, Integer> window = new HashMap<>();
//            // need用于存放s1中的字符种类以及个数
//            Map<Character, Integer> need = new HashMap<>();
//            for (char c : s1.toCharArray())
//                need.put(c, need.getOrDefault(c, 0) + 1);
//
//            // 初始化滑动窗口指针
//            int left = 0, right = 0;
//            // valid记录window中符合need中字符种类及个数的有效字符数
//            int valid = 0;
//            while (right < s2.length()) {
//                // r是即将加入窗口的字符
//                char r = s2.charAt(right);
//                // 窗口扩大
//                right++;
//                // 更新window中的字符种类及个数
//                if (need.containsKey(r)) {
//                    window.put(r, window.getOrDefault(r, 0) + 1);
//                    if (window.get(r).equals(need.get(r)))
//                        valid++;
//                }
//                // valid == need.size()说明窗口子串包含s1中所有字符
//                while (valid == need.size()) {
//                    // 判断窗口子串的长度,若与s1相等,则说明没有多余字符,返回true
//                    if (right - left == s1.length())
//                        return true;
//                    // l是将要移出窗口的字符
//                    char l = s2.charAt(left);
//                    // 窗口缩小
//                    left++;
//                    // 更新window中的字符种类及个数
//                    if (need.containsKey(l)) {
//                        if (window.get(l).equals(need.get(l)))
//                            valid--;
//                        window.put(l, window.get(l) - 1);
//                    }
//                }
//            }
//            // 未找到s1的排列,返回false
//            return false;
//        }

        /**
         * 思路二:定长滑动窗口
         * 1. 固定滑动窗口的长度,当窗口长度等于s1.length时，left右移
         * 2. 什么时候扩大窗口？窗口长度 < s1.length 时
         * 3. 什么时候缩小窗口？窗口长度 == s1.length 时
         * 4. 什么时候更新结果？窗口缩小时，若当前窗口是s1的排列，则返回true
         * 时间复杂度：O(s1.length + s2.length)
         * 空间复杂度：O(1)
         *
         * @param s1 子串
         * @param s2 源字符串
         * @return 是否包含子串
         */
        public boolean checkInclusion(String s1, String s2) {
            // window用于存放窗口中的字符种类以及个数
            Map<Character, Integer> window = new HashMap<>();
            // need用于存放s1中的字符种类以及个数
            Map<Character, Integer> need = new HashMap<>();
            for (char c : s1.toCharArray())
                need.put(c, need.getOrDefault(c, 0) + 1);

            // 初始化滑动窗口指针
            int left = 0, right = 0;
            // valid记录window中符合need中字符种类及个数的有效字符数
            int valid = 0;
            while (right < s2.length()) {
                // r是即将加入窗口的字符
                char r = s2.charAt(right);
                // 窗口扩大
                right++;
                // 更新window中的字符种类及个数
                if (need.containsKey(r)) {
                    window.put(r, window.getOrDefault(r, 0) + 1);
                    if (window.get(r).equals(need.get(r)))
                        valid++;
                }
                // 若窗口子串的长度与s1相等,则需要判断是否为s1排列，并缩小窗口
                // 定长窗口这里可以用if
                if (right - left == s1.length()) {
                    // valid == need.size()说明窗口子串包含s1中所有字符，且长度相等，是s1的排列
                    if (valid == need.size())
                        return true;
                    // l是将要移出窗口的字符
                    char l = s2.charAt(left);
                    // 窗口缩小
                    left++;
                    // 更新window中的字符种类及个数
                    if (need.containsKey(l)) {
                        if (window.get(l).equals(need.get(l)))
                            valid--;
                        window.put(l, window.get(l) - 1);
                    }
                }
            }
            // 未找到s1的排列,返回false
            return false;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PermutationInString().new Solution();
        // put your test code here

    }
}