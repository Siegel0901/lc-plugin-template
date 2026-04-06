package leetcode.editor.cn;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路二：二分
         * 时间复杂度：O(nlogn)
         * 空间复杂度：O(n)
         */
        public int lengthOfLIS(int[] nums) {
            int[] top = new int[nums.length];
            int piles = 0;
            for (int poker : nums) {
                int left = 0, right = piles;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (top[mid] < poker)
                        left = mid + 1;
                    else
                        right = mid;
                }
                if (left == piles)
                    piles++;
                top[left] = poker;
            }
            return piles;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一：dp
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(n)
         */
        public int lengthOfLIS(int[] nums) {
            // dp数组定义：dp[i]是以nums[i]为结尾的最长严格递增子序列的长度
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);

            // 遍历所有状态：以nums[i]为结尾
            for (int i = 0; i < nums.length; i++)
                // 遍历所有选择：以小于nums[i]的位置为结尾
                for (int j = 0; j < i; j++)
                    if (nums[j] < nums[i])
                        // 当前状态的解为子问题的最大解+1
                        dp[i] = Math.max(dp[i], dp[j] + 1);
            int max = 0;
            for (int len : dp)
                max = Math.max(max, len);
            return max;
        }
    }


    public static void main(String[] args) {
        Solution solution = new LongestIncreasingSubsequence().new Solution();
        // put your test code here

    }
}