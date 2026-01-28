package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class MaxConsecutiveOnesIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：寻找0个数<=k的最长子串
         * 1. 什么时候扩大窗口？窗口子串中0的个数 < k
         * 2. 什么时候缩小窗口？窗口子串中0的个数 > k
         * 3. 什么时候更新结果？窗口子串中0的个数 <= k
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         * @param k    最多可以翻转0的个数
         * @return 最长子串的长度
         */
        public int longestOnes(int[] nums, int k) {
            int zero = 0, max = -1;
            int left = 0, right = 0;
            while (right < nums.length) {
                int r = nums[right++];
                if (r == 0)
                    zero++;
                while (zero > k) {
                    int l = nums[left++];
                    if (l == 0)
                        zero--;
                }
                if (zero <= k)
                    max = Math.max(max, right - left);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MaxConsecutiveOnesIii().new Solution();
        // put your test code here

    }
}