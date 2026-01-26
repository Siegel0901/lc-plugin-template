package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：使用滑动窗口算法
         * 1. 使用[left, right)滑动窗口寻找最短窗口子串
         * 2. right不断右移，当窗口子串包含t中的每一个字符时停止
         * 3. 此时left右移，直至窗口子串不满足包含t中的每一个字符
         * 4. left右移时，每个窗口子串都可能是结果，需要更新最短窗口子串
         * 5. 什么时候扩大窗口？窗口内的字符不包括子串中的字符 或 重复字符个数不等于子串中的重复字符个数
         * 5.1. 扩大窗口后需要更新什么？更新窗口的字符种类以及个数
         * 6. 什么时候缩小窗口？窗口子串包含t中的每一个字符时需要缩小窗口
         * 6.1. 缩小窗口后需要更新什么？更新窗口的字符种类以及个数
         * 7. 什么时候更新结果？缩小窗口时，每个窗口都是结果，找出最短结果
         * 时间复杂度：O(m + n)，left和right指针都只走一遍s，HashMap操作为O(1)
         * 空间复杂度：O(1)，HashMap索引空间最大为字符集大小
         *
         * @param s 源字符串
         * @param t 子串
         * @return 最短窗口子串
         */
        public String minWindow(String s, String t) {
            // window存放窗口中的字符种类以及个数
            Map<Character, Integer> window = new HashMap<>();
            // need存放字符t中的字符种类以及个数
            Map<Character, Integer> need = new HashMap<>();
            // t中的字符种类以及个数存入need
            for (char c : t.toCharArray())
                need.put(c, need.getOrDefault(c, 0) + 1);

            // 定义滑动窗口区间指针
            int left = 0, right = 0;
            // 定义最短窗口子串的起始索引和长度
            int start = 0, len = Integer.MAX_VALUE;
            // 定义valid用于记录window中满足need要求的字符种类数
            int valid = 0;
            // 开始滑动窗口
            while (right < s.length()) {
                // r是要移动到窗口的字符
                char r = s.charAt(right);
                // 增大窗口
                right++;
                // 更新窗口中的数据
                if (need.containsKey(r)) {  // 如果need中包含r
                    // 将r加入window中
                    window.put(r, window.getOrDefault(r, 0) + 1);
                    // 如果字符r在window中的数量等于r在need中的数量
                    if (window.get(r).equals(need.get(r)))
                        // 有效字符数+1
                        valid++;
                }

                // 当有效字符数与need中的字符数相等时，说明当前窗口子串包含了t中的所有字符（包括重复字符）
                while (valid == need.size()) {
                    // 记录当前窗口子串的起始位置以及长度
                    if (right - left < len) {
                        start = left;
                        len = right - left;
                    }
                    // l是要移出窗口的字符
                    char l = s.charAt(left);
                    // 缩小窗口
                    left++;
                    // 更新窗口中的数据
                    if (need.containsKey(l)) {
                        // 如果字符l在window中的数量等于l在need中的数量
                        if (window.get(l).equals(need.get(l)))
                            // 有效字符数-1
                            valid--;
                        // 将l移出窗口
                        window.put(l, window.get(l) - 1);
                    }
                }
            }
            // 判断len是否更新，返回最短窗口子串
            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
        // put your test code here

    }
}