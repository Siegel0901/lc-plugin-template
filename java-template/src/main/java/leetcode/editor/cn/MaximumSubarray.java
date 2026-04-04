package leetcode.editor.cn;

public class MaximumSubarray {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路五：滑动窗口
     * 1. 什么时候扩大窗口?当窗口元素和>0时扩大窗口
     * 2. 什么时候缩小窗口?当窗口元素和<0时缩小窗口
     * 3. 什么时候记录结果?扩大窗口时更新结果
     * 情况讨论：
     * 1. 全为负数：窗口内始终只有一个元素，最大和为最大的负数
     * 2. 有正有负：最大和数组一定以正数开头，若以负数开头，则去掉负数可以得到更大的和，与最大和矛盾
     * 2.1. 窗口中只有一个负数开头时，窗口和<0，会缩小窗口删去该负数
     * 2.2. 直到窗口以正数开头，此时扩大窗口，同时更新结果
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int maxSubArray(int[] nums) {
            int left = 0, right = 0;
            int windowSum = 0, maxSum = Integer.MIN_VALUE;
            while (right < nums.length) {
                // 扩大窗口
                windowSum += nums[right++];

                // 更新结果
                maxSum = Math.max(maxSum, windowSum);

                // 缩小窗口
                while (windowSum < 0)
                    windowSum -= nums[left++];
            }
            return maxSum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路四：前缀和
     * 1. 计算nums数组的前缀和preSum（索引0为占位符）
     * 2. preSum[i+1] - preSum[j]是子数组nums[j..i]之和
     * 3. 以nums[i]为结尾的最大子数组之和：preSum[i+1] - min(preSum[0..i])
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    class Solution4 {
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            // 索引0用于占位
            int[] preSum = new int[n + 1];
            // 保证preSum[i - 1]运算
            preSum[0] = 0;
            // preSum[1~n]为nums[0~n-1]的前缀和
            for (int i = 1; i <= n; i++)
                preSum[i] = preSum[i - 1] + nums[i - 1];
            // 记录最大连续子数组和
            int max = nums[0];
            // 记录遍历过程中的最小前缀和
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                min = Math.min(min, preSum[i]);
                max = Math.max(max, preSum[i + 1] - min);
            }
            return max;
        }
    }

    /**
     * 思路三：递归DP
     * 1. dp(nums,i)表示以nums[i]为结尾的最大和连续子数组的和
     * 2. nums[i]要么加入dp(nums,i-1)表示的最大和连续子数组，要么自己作为一个最大和连续子数组
     * 3. 故状态转移方程为 dp(nums,i) = Math.max(nums[i],nums[i] + dp(nums,i-1))
     * 4. base case：dp(nums,0) = nums[0];
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    class Solution3 {
        public int maxSubArray(int[] nums) {
            int max = nums[0];
            for (int i = 0; i < nums.length; i++)
                max = Math.max(dp(nums, i), max);
            return max;
        }

        int dp(int[] nums, int i) {
            if (i == 0)
                return nums[0];
            return Math.max(nums[i], dp(nums, i - 1) + nums[i]);
        }
    }

    /**
     * 思路二：DP Table + 空间压缩
     * 1. dp[i]定义：dp[i]表示以nums[i]为结尾的最大和连续子数组的和
     * 2. nums[i]要么加入dp[i-1]表示的最大和连续子数组，要么自己作为一个最大和连续子数组
     * 3. 故状态转移方程为 dp[i] = Math.max(nums[i],nums[i] + dp[i-1])
     * 4. base case：dp[0] = nums[0];
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    class Solution2 {
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            // base case
            int dp_i = nums[0];
            int max = dp_i;
            for (int i = 1; i < n; i++) {
                dp_i = Math.max(nums[i], nums[i] + dp_i);
                max = Math.max(max, dp_i);
            }
            return max;
        }
    }

    /**
     * 思路一：DP Table
     * 1. dp[i]定义：dp[i]表示以nums[i]为结尾的最大和连续子数组的和
     * 2. nums[i]要么加入dp[i-1]表示的最大和连续子数组，要么自己作为一个最大和连续子数组
     * 3. 故状态转移方程为 dp[i] = Math.max(nums[i],nums[i] + dp[i-1])
     * 4. base case：dp[0] = nums[0];
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    class Solution1 {
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            // base case
            dp[0] = nums[0];
            int max = dp[0];
            for (int i = 1; i < n; i++) {
                dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }


    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
        // put your test code here

    }
}