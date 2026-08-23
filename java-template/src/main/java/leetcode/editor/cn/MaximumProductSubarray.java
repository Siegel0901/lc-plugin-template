package leetcode.editor.cn;

public class MaximumProductSubarray {


    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：双状态动态规划 + 空间压缩
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int maxProduct(int[] nums) {
            int n = nums.length;
            int maxDP = nums[0];
            int minDP = nums[0];
            int max = nums[0];
            for (int i = 1; i < n; i++) {
                int maxPrev = maxDP;
                int minPrev = minDP;
                maxDP = Math.max(nums[i], Math.max(maxPrev * nums[i], minPrev * nums[i]));
                minDP = Math.min(nums[i], Math.min(maxPrev * nums[i], minPrev * nums[i]));
                max = Math.max(max, maxDP);
            }
            return max;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路：双状态动态规划
     * 1. 为什么要两个状态？
     * 因为遇到负数，乘积可能变大也可能变小（正负得负，负负得正）
     * 2. 同时维护两个值
     * 2.1. maxDP[i]：以nums[i]结尾的子数组的最大乘积
     * 2.2. minDP[i]：以nums[i]结尾的子数组的最小乘积
     * 3. 状态转移：每个位置有三种选择
     * 3.1. 从当前位置重新开始：nums[i]
     * 3.2. 延续之前的最大值：maxDP[i-1] * nums[i]
     * 3.3. 延续之前的最小值：minDP[i-1] * nums[i]
     * 4. base case
     * 4.1. maxDP[0] = nums[0]
     * 4.2. minDP[0] = nums[0]
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        public int maxProduct(int[] nums) {
            int n = nums.length;
            int[] maxDP = new int[n];
            int[] minDP = new int[n];
            maxDP[0] = nums[0];
            minDP[0] = nums[0];
            int max = maxDP[0];
            for (int i = 1; i < n; i++) {
                maxDP[i] = Math.max(nums[i], Math.max(maxDP[i - 1] * nums[i], minDP[i - 1] * nums[i]));
                minDP[i] = Math.min(nums[i], Math.min(maxDP[i - 1] * nums[i], minDP[i - 1] * nums[i]));
                max = Math.max(max, maxDP[i]);
            }
            return max;
        }
    }


    public static void main(String[] args) {
        Solution solution = new MaximumProductSubarray().new Solution();
        // put your test code here
        solution.maxProduct(new int[]{0, 2});
    }
}