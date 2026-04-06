package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

public class TargetSum {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路五：转化为0-1背包子集问题 + 空间压缩
     * 问题转化：
     * 1. 原问题：从nums中获取一些数构成正数集sum(P),其余数构成负数集sum(N),相减得到target
     * 2. 即sum(P)-sum(N)=target
     * 3. 又sum(P)+sum(N)=nums_sum
     * 4. 故2sum(P) = target+nums_sum -> sum(P) = (target+nums_sum)/2
     * 5. 问题转化为：从nums中获取子集，使得子集的和为(target+nums_sum)/2
     * 时间复杂度：O(N*S)
     * 空间复杂度：O(S)
     */
    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums)
                sum += num;
            /*
             * 剪枝：
             * 1. target的绝对值大于sum，取全正全负都不行(题目保证）
             * 2. 由2sum(P) = target+nums_sum得target+nums_sum必为偶数
             * 3. 由于sum(P)为正数，故target+nums_sum必为正数
             * */
            if (((target + sum) & 1) == 1 || target + sum < 0)
                return 0;
            int S = (target + sum) / 2;
            // dp[i][j]表示nums[0,i-1]的子集和为j的子集数
            // dp[n][S]表示nums[0,n-1]的子集和为S的子集数
            int[] dp = new int[S + 1];
            /*
             * base case
             * i==0,j!=0,dp[0][j]=0
             * j==0,dp[i][0]=1
             * */
            dp[0] = 1;
            /*
             * 状态转移：
             * nums[i-1]>j, dp[i][j] = dp[i-1][j]
             * nums[i-1]<=j, dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]]
             * i/j   j-nums[i-1]             j
             * i-1   dp[i-1][j-nums[i-1]]    dp[i-1][j]
             * i                             dp[i][j]
             * 遍历顺序：从上往下，从右往左
             * dp[j]先由dp[i-1][j]更新为dp[i][j]
             * 再由dp[i-1][j-nums[i-1]]更新为dp[i][j-nums[i-1]]
             * i∈[1,n],j∈[0,S]
             * */
            for (int num : nums)
                for (int j = S; j >= num; j--)
                    dp[j] += dp[j - num];
            return dp[S];
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路四：转化为0-1背包子集问题
     * 问题转化：
     * 1. 原问题：从nums中获取一些数构成正数集sum(P),其余数构成负数集sum(N),相减得到target
     * 2. 即sum(P)-sum(N)=target
     * 3. 又sum(P)+sum(N)=nums_sum
     * 4. 故2sum(P) = target+nums_sum -> sum(P) = (target+nums_sum)/2
     * 5. 问题转化为：从nums中获取子集，使得子集的和为(target+nums_sum)/2
     * 时间复杂度：O(N*S)
     * 空间复杂度：O(N*S)
     */
    class Solution4 {
        public int findTargetSumWays(int[] nums, int target) {
            int n = nums.length;
            int sum = 0;
            for (int num : nums)
                sum += num;
            /*
             * 剪枝：
             * 1. target的绝对值大于sum，取全正全负都不行（题目保证）
             * 2. 由2sum(P) = target+nums_sum得target+nums_sum必为偶数
             * 3. 由于sum(P)为正数，故target+nums_sum必为正数
             * */
//            if (Math.abs(target) > sum)
//                return 0;
            if (((target + sum) & 1) == 1 || target + sum < 0)
                return 0;
            int S = (target + sum) / 2;
            // dp[i][j]表示nums[0,i-1]的子集和为j的子集数
            // dp[n][S]表示nums[0,n-1]的子集和为S的子集数
            int[][] dp = new int[n + 1][S + 1];
            /*
             * base case
             * i==0,j!=0,dp[0][j]=0
             * j==0,dp[i][0]=1
             * */
            for (int j = 0; j <= S; j++)
                dp[0][j] = 0;
            for (int i = 0; i <= n; i++)
                dp[i][0] = 1;
            /*
             * 状态转移：
             * nums[i-1]>j, dp[i][j] = dp[i-1][j]
             * nums[i-1]<=j, dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]]
             * i/j   j-nums[i-1]             j
             * i-1   dp[i-1][j-nums[i-1]]    dp[i-1][j]
             * i                             dp[i][j]
             * 遍历顺序：从上往下，从左往右
             * i∈[1,n],j∈[0,S]
             * */
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= S; j++) {
                    if (nums[i - 1] > j)
                        dp[i][j] = dp[i - 1][j];
                    else
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
            return dp[n][S];
        }
    }

    /**
     * 思路三：递归DP + 数组备忘录
     * 1. 时间复杂度：O(N*S)
     * 2. 空间复杂度：O(N*S)
     */
    class Solution3 {
        // 备忘录
        int[][] memo;
        // 偏移量处理负数索引
        int offset;

        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums)
                sum += num;
            // target不在[-sum,sum]中
            if (Math.abs(target) > sum)
                return 0;
            // remain的范围：[-sum,sum],映射到[0,2*sum]
            offset = sum;
            memo = new int[nums.length][2 * sum + 1];
            for (int[] row : memo)
                Arrays.fill(row, Integer.MIN_VALUE);
            return dp(nums, 0, target);
        }


        // dp(nums,i,remain)表示nums[i..]中能组合成为remain的方法数
        int dp(int[] nums, int i, int remain) {
            // base case
            if (i == nums.length)
                return remain == 0 ? 1 : 0;
            // 越界检查
            if (Math.abs(remain) > offset)
                return 0;
            int j = remain + offset;
            // 查备忘录
            if (memo[i][j] != Integer.MIN_VALUE)
                return memo[i][j];
            memo[i][j] = dp(nums, i + 1, remain - nums[i]) + dp(nums, i + 1, remain + nums[i]);
            return memo[i][j];
        }
    }

    /**
     * 思路二：递归DP + HashMap备忘录
     * 1. 时间复杂度：O(N*S)
     * N为nums.length，S为nums中所有元素之和sum
     * DP的时间复杂度=状态数*dp函数时间复杂度
     * dp函数时间复杂度为O(1)
     * 状态数 = i的状态数 * remain的状态数
     * i的状态数为nums.length
     * remain的状态数为[target-sum,target+sum]
     * 故时间复杂度为O(N*S)
     * 2. 空间复杂度：O(N*S)
     * 备忘录memo：O(N*S)
     * 递归深度：O(N)
     */
    class Solution2 {
        public int findTargetSumWays(int[] nums, int target) {
            return dp(nums, 0, target);
        }

        // 备忘录
        HashMap<String, Integer> memo = new HashMap<>();

        // dp(nums,i,remain)表示nums[i..]中能组合成为remain的方法数
        int dp(int[] nums, int i, int remain) {
            // base case
            if (i == nums.length) {
                if (remain == 0)
                    return 1;
                return 0;
            }
            // 设置键
            String key = i + "," + remain;
            // 查备忘录
            if (memo.containsKey(key))
                return memo.get(key);
            int count = dp(nums, i + 1, remain - nums[i]) + dp(nums, i + 1, remain + nums[i]);
            memo.put(key, count);
            return count;
        }
    }

    /**
     * 思路一：回溯
     * 时间复杂度：O(2^N)
     * 空间复杂度：O(N)
     */
    class Solution1 {
        int count = 0;

        public int findTargetSumWays(int[] nums, int target) {
            backtrack(nums, 0, target);
            return count;
        }

        void backtrack(int[] nums, int idx, int remain) {
            if (idx == nums.length) {
                if (remain == 0)
                    count++;
                return;
            }
            backtrack(nums, idx + 1, remain + nums[idx]);
            backtrack(nums, idx + 1, remain - nums[idx]);
        }
    }


    public static void main(String[] args) {
        Solution solution = new TargetSum().new Solution();
        // put your test code here

    }
}