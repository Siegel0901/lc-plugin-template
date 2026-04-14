package leetcode.editor.cn;

public class NimGame {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：数学规律
     * n = 1, 2, 3：先手直接拿走所有石子，赢 ✓
     * n = 4：无论先手拿 1/2/3 个，后手都能拿走剩余的，先手输 ✗
     * n = 5, 6, 7：先手可以分别拿 1/2/3 个，让后手面对 4 个石子（必输局面），先手赢 ✓
     * n = 8：无论先手拿几个(1,2,3)，后手(5,6,7)都能让先手再次面对 4 的倍数，先手输 ✗
     * 以此类推，4 的倍数是必输态。
     */
    class Solution {
        public boolean canWinNim(int n) {
            return n % 4 != 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：DP table + 空间压缩
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     */
    class Solution2 {
        public boolean canWinNim(int n) {
            if (n <= 3)
                return true;
            boolean dp_n_1 = true;
            boolean dp_n_2 = true;
            boolean dp_n_3 = true;
            for (int i = 4; i <= n; i++) {
                boolean dp_n = !dp_n_1 || !dp_n_2 || !dp_n_3;
                dp_n_3 = dp_n_2;
                dp_n_2 = dp_n_1;
                dp_n_1 = dp_n;
            }
            return dp_n_1;
        }
//        public boolean canWinNim(int n) {
//            if (n <= 3)
//                return true;
//            boolean[] dp = new boolean[n + 1];
//            dp[1] = true;
//            dp[2] = true;
//            dp[3] = true;
//            for (int i = 4; i <= n; i++)
//                dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i - 3];
//            return dp[n];
//        }
    }

    /**
     * 思路一：递归DP
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * 【Memory Limit Exceeded】
     */
    class Solution1 {
        Boolean[] memo;

        public boolean canWinNim(int n) {
            memo = new Boolean[n + 1];
            return dp(n);
        }

        // 定义：dp(n)表示当前玩家面对n个石头时的输赢
        boolean dp(int n) {
            if (n <= 3)
                return true;
            if (memo[n] != null)
                return memo[n];
            // dp(n-i),i∈[1,3]表示下一个玩家的输赢情况，只要下一个玩家有一种情况是输的!dp(n-i)，当前玩家就能赢。
            boolean res = !dp(n - 1) || !dp(n - 2) || !dp(n - 3);
            memo[n] = res;
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new NimGame().new Solution();
        // put your test code here
        solution.canWinNim(8);
    }
}