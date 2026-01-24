package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class LongestPalindromicSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 判断字符串是否为回文串
        public boolean isPalindrome(String s) {
            int left = 0, right = s.length() - 1;
            while (left < right)
                if (s.charAt(left++) != s.charAt(right--))
                    return false;
            return true;
        }

        /**
         * 思路一：双指针从后向前遍历判断回文
         * 1. 定义left，right指针，最长回文串sub
         * 2. right初值为s.length() - 1，left为s.indexOf(s.charAt(right))
         * 3. 若right != left，则判断[left,right]是否为回文串
         * 3.1. 若为回文串，则判断长度是否为最长，更新sub
         * 3.2. 接着寻找left + 1往后与s.charAt(right)值相等的left,直至left == right
         * 4. right--，直至right为0
         * 时间复杂度：O(n^3)【三层循环】
         * 空间复杂度：O(n)【存储sub】
         *
         * @param s 字符串
         * @return 最长回文串
         */
//        public String longestPalindrome(String s) {
//            int left, right = s.length() - 1;
//            String sub = "";
//            while (right > 0) {
//                // 找到第一个与right字符值相等的left
//                left = s.indexOf(s.charAt(right));
//                while (left != right) {
//                    // 判断是否为回文串
//                    if (isPalindrome(s.substring(left, right + 1)))
//                        // 判断是否为最长回文串
//                        if (right - left + 1 > sub.length())
//                            // 更新最长回文串
//                            sub = s.substring(left, right + 1);
//                    // 寻找left后与right字符值相等的left
//                    left = s.indexOf(s.charAt(right), left + 1);
//                }
//                right--;
//            }
//            // 若sub为空，则说明最长回文串长度为1
//            if (sub.isEmpty())
//                // 选第一个字符作为最长回文串
//                sub = s.substring(0, 1);
//            return sub;
//        }

        /**
         * 思路二：中心扩展
         * 1. 遍历s，每个字符i向两边扩展判断回文
         * 2. 若i的后续字符均与i相等，则以相等的字符块作为中心，向两边扩展
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(1)
         *
         * @param s 字符串
         * @return 最长回文串
         */
//        public String longestPalindrome(String s) {
//            int max = 0;
//            int[] sub = new int[]{0, 1};
//            for (int i = 0; i < s.length(); i++) {
//                int left = i, right = i, len = 1;
//                // 判断后续字符是否与i字符值相等
//                while (right + 1 < s.length()) {
//                    if (s.charAt(right + 1) != s.charAt(left))
//                        break;
//                    right++;
//                    len++;
//                }
//                // 以i或与i字符值相等的字符块为中心，向两边扩展
//                while (left - 1 >= 0 && right + 1 < s.length() && s.charAt(left - 1) == s.charAt(right + 1)) {
//                    // 长度+2
//                    len += 2;
//                    left--;
//                    right++;
//                }
//                // 更新最长回文串
//                if (len > max) {
//                    max = len;
//                    sub = new int[]{left, right + 1};
//                }
//            }
//            return s.substring(sub[0], sub[1]);
//        }

        /**
         * 思路三：中心扩展（s[i]为中心、s[i]和s[i + 1]为中心）
         * 1. 遍历s，对每个字符s[i]，判断以s[i]为中心（长度为奇数）、s[i]和s[i + 1]为中心（长度为偶数）的回文串
         * 2. 更新最长回文子串
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(1)
         *
         * @param s 字符串
         * @return 最长回文串
         */
        public String longestPalindrome(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                // 以s[i]为中心的最长回文子串
                String s1 = palindrome(s, i, i);
                // 以s[i]、s[i+1]为中心的最长回文子串
                String s2 = palindrome(s, i, i + 1);
                // res = longest(res, s1, s2)
                res = res.length() > s1.length() ? res : s1;
                res = res.length() > s2.length() ? res : s2;
            }
            return res;
        }

        public String palindrome(String s, int left, int right) {
            // 中心扩展
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            // 返回[left + 1, right - 1]子串
            return s.substring(left + 1, right);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        // put your test code here
        String s = "aacabdkacaa";
        String s1 = solution.longestPalindrome(s);
        System.out.println(s1);
    }
}