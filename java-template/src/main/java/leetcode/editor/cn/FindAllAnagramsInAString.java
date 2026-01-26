package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class FindAllAnagramsInAString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：滑动窗口
         * 1. 什么时候扩大窗口？窗口子串长度 < p.length
         * 2. 什么时候缩小窗口？窗口子串长度 == p.length
         * 3. 什么时候更新结果？窗口子串长度 == p.length
         * 时间复杂度：O(s.length + p.length)
         * 空间复杂度：O(1)
         *
         * @param s 源字符串
         * @param p 子串
         * @return 子串的起始索引
         */
        public List<Integer> findAnagrams(String s, String p) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> need = new HashMap<>();
            for (char c : p.toCharArray())
                need.put(c, need.getOrDefault(c, 0) + 1);

            int left = 0, right = 0;
            int valid = 0;
            List<Integer> res = new ArrayList<>();
            while (right < s.length()) {
                char r = s.charAt(right);
                right++;
                if (need.containsKey(r)) {
                    window.put(r, window.getOrDefault(r, 0) + 1);
                    if (window.get(r).equals(need.get(r)))
                        valid++;
                }

                if (right - left == p.length()) {
                    if (valid == need.size())
                        res.add(left);
                    char l = s.charAt(left);
                    left++;
                    if (need.containsKey(l)) {
                        if (window.get(l).equals(need.get(l)))
                            valid--;
                        window.put(l, window.get(l) - 1);
                    }
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsInAString().new Solution();
        // put your test code here

    }
}