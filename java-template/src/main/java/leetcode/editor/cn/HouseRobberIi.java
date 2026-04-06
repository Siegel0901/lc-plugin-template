package leetcode.editor.cn;

public class HouseRobberIi {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：DP Table + 空间压缩
     * 首尾相邻的情况：
     * 1. 首和尾都不抢 -> 0 (1 2 3) 4
     * 2. 首抢尾不抢 -> (0 1 2 3) 4
     * 3. 首不抢尾抢 -> 0 (1 2 3 4)
     * 情况二和情况三包含情况一
     * 故只需取情况二和情况三的最大值
     * 本质上是破坏环形数组的条件，复用之前的解决方案
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1)
                return nums[0];
            return Math.max(
                    robRange(nums, 0, n - 2),
                    robRange(nums, 1, n - 1)
            );
        }

        int robRange(int[] nums, int start, int end) {
            int dp_i = 0;
            int dp_i_1 = 0;
            int dp_i_2 = 0;
            for (int i = start; i <= end; i++) {
                dp_i = Math.max(dp_i_1, dp_i_2 + nums[i]);
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
        // put your test code here
        solution.rob(new int[]{1});
    }
}