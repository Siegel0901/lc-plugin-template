package leetcode.editor.cn;

import java.util.Arrays;

public class JumpGameIi {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：贪心
     * 在每一步跳跃时，选择能够到达最远位置的跳法，而不是关心具体跳到哪个位置。
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int jump(int[] nums) {
            int n = nums.length;
            if (n <= 1)
                return 0;
            // 当前已经跳了多少步
            int step = 0;
            // 当前这一步能到达的边界最远位置
            int end = 0;
            // 从当前位置范围内,下一步能到达的最远位置
            int farthest = 0;
            for (int i = 0; i < n - 1; i++) {
                // 不断更新"下一步能到达的最远位置"
                farthest = Math.max(farthest, i + nums[i]);
                // 如果到达了当前步的边界,必须再跳一步
                if (i == end) {
                    step++;         // 步数+1
                    end = farthest; // 更新边界到最远位置
                    // 如果当前这一步能达到的边界已经超过了n-1，直接返回step
                    if (farthest >= n - 1)
                        return step;
                }
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：递归DP
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N)
     */
    class Solution1 {
        int[] memo;

        public int jump(int[] nums) {
            memo = new int[nums.length];
            Arrays.fill(memo, Integer.MAX_VALUE);
            return dp(nums, 0);
        }

        // dp(nums,idx)表示从idx到n-1的最小步数
        int dp(int[] nums, int idx) {
            // base case
            if (idx >= nums.length - 1)
                return 0;
            if (memo[idx] != Integer.MAX_VALUE)
                return memo[idx];
            int step = nums[idx];
            for (int i = 1; i <= step; i++) {
                int next = dp(nums, idx + i);
                // 只有当下一个位置可达时才更新，否则会整型溢出
                if (next != Integer.MAX_VALUE)
                    memo[idx] = Math.min(memo[idx], next + 1);
            }
            return memo[idx];
        }
    }


    public static void main(String[] args) {
        Solution solution = new JumpGameIi().new Solution();
        // put your test code here

    }
}