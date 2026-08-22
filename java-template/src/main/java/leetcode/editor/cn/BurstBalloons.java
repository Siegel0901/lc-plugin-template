package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BurstBalloons {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：DP Table
     * 1. 逆向思维：不要想“第一个戳哪个气球”，想“最后一个戳哪个气球”。因为最后戳破的气球，左右邻居是固定的，就是边界添加的1。
     * 2. 定义dp数组：dp[i][j] 表示开区间 (i, j) 能获得的最大硬币数。
     * 3. 补全数组：在原数组的两端各添加一个元素1。即vals[0] = 1，vals[n + 1] = 1。最终答案为dp(0, n+1)。
     * 4. 状态转移方程：dp[i][j] = max(dp[i][k] + dp[k][j] + vals[i] * vals[k] * vals[j])，其中i < k < j
     * 5. base case：当j - i <= 1时，表示区间(i, j)内没有气球可戳，dp[i][j] = 0。
     *
     * 复杂度分析：
     * - 时间复杂度：O(n^3)。状态总数为 O(n^2)（即所有可能的开区间 (i, j)），对于每个状态需要枚举最后戳破的气球 k，最多有 O(n) 种选择，因此总时间复杂度为 O(n^3)。
     * - 空间复杂度：O(n^2)。主要开销是二维备忘录数组 memo，大小为 (n+2) x (n+2)，递归调用栈的最大深度为 O(n)，综合空间复杂度为 O(n^2)。
     * */
    class Solution {
        public int maxCoins(int[] nums) {
            List<Integer> vals = new ArrayList<>();
            vals.add(1);
            for (int num : nums)
                vals.add(num);
            vals.add(1);
            int n = nums.length;
            int[][] dp = new int[n + 2][n + 2];
            /*
            * 第三层：k的范围是[i + 1, j - 1]
            *   k是开区间(i, j)内最后一个被戳破的气球
            *   所以k必须满足 i < k < j
            * 第二层：j的范围是[i + 2, n + 1]
            *   j = i + 2起步：j - i <= 1是base case，第一个需要算的区间就是 j - i = 2，即区间里恰好有1个气球。
            *   j < n + 2：vals数组长度为n + 2，即vals[n + 1]是边界元素。
            * 第一层：i的范围是n - 1 -> 0
            *   区间DP要求：算大区间之前，小区间必须先算完。
            *   dp[i][j]依赖于dp[i][k]和dp[k][j]，他们都比区间(i, j)小。
            *   所以i必须从后向前遍历，j必须从i + 2开始遍历。
            * 一句话总结：i从右往左 + j从左往右 = 区间长度从小到大递增，保证大区间依赖的小区间已经算过
            * */
            for (int i = n - 1; i >= 0; i--)
                for (int j = i + 2; j < n + 2; j++)
                    for (int k = i + 1; k < j; k++)
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + vals.get(i) * vals.get(k) * vals.get(j));
            return dp[0][n + 1];
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：递归DP（记忆化搜索）
     * 1. 逆向思维：不要想“第一个戳哪个气球”，想“最后一个戳哪个气球”。因为最后戳破的气球，左右邻居是固定的，就是边界添加的1。
     * 2. 定义dp函数：dp(i, j) 表示开区间 (i, j) 能获得的最大硬币数。
     * 3. 补全数组：在原数组的两端各添加一个元素1。即vals[0] = 1，vals[n + 1] = 1。最终答案为dp(0, n+1)。
     * 4. 状态转移方程：dp(i, j) = max(dp(i, k) + dp(k, j) + vals[i] * vals[k] * vals[j])，其中i < k < j
     * 5. base case：当j - i <= 1时，表示区间(i, j)内没有气球可戳，dp(i, j) = 0。
     *
     * 复杂度分析：
     * - 时间复杂度：O(n^3)。状态总数为 O(n^2)（即所有可能的开区间 (i, j)），对于每个状态需要枚举最后戳破的气球 k，最多有 O(n) 种选择，因此总时间复杂度为 O(n^3)。
     * - 空间复杂度：O(n^2)。主要开销是二维备忘录数组 memo，大小为 (n+2) x (n+2)，递归调用栈的最大深度为 O(n)，综合空间复杂度为 O(n^2)。
     * */
    class Solution1 {
        List<Integer> vals = new ArrayList<>();
        int[][] memo;

        public int maxCoins(int[] nums) {
            vals.add(1);
            for (int num : nums)
                vals.add(num);
            vals.add(1);
            int n = nums.length;
            memo = new int[n + 2][n + 2];
            for (int[] rows : memo)
                Arrays.fill(rows, -1);
            return dp(0, n + 1);
        }

        public int dp(int i, int j) {
            if (j - i <= 1)
                return 0;
            if (memo[i][j] != -1)
                return memo[i][j];
            /*
             * 1. k是区间(i, j)内最后一个被戳破的气球，当k被戳破时，区间里其他气球都已经戳破了。
             * 2. 所以k的邻居是边界i和j，最后戳破k的收益是vals[i] * vals[k] * vals[j]。
             * 3. k把区间(i, j)分成了两个独立的子区间(i, k)和(k, j)。
             * 4. 为什么独立？因为k是最后戳的，左边戳气球时k还在，右边戳气球时k也在。左右互不影响。
             * 5. 左边子区间的最大硬币数是dp(i, k)，右边子区间的最大硬币数是dp(k, j)。
             * 6. 区间(i,j)中任何一个位置都有可能最后被戳破，所以要遍历所有位置，取最大值。
             * */
            for (int k = i + 1; k < j; k++)
                memo[i][j] = Math.max(memo[i][j], dp(i, k) + dp(k, j) + vals.get(i) * vals.get(k) * vals.get(j));
            return memo[i][j];
        }

    }


    public static void main(String[] args) {
        Solution solution = new BurstBalloons().new Solution();
        // put your test code here

    }
}