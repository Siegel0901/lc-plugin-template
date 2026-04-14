package leetcode.editor.cn;

public class StoneGame {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：数学规律
     * 为什么先手一定赢？
     * 题目的关键信息：1️⃣有偶数堆石子 2️⃣没有平局
     * 由1️⃣得奇偶数位置的石子堆各有n/2堆
     * 由2️⃣得sum(奇数堆石子) != sum(偶数堆石子)
     * 先手第一次选择奇数或偶数位置后，可以让后手一直选择另一种位置
     * 如(E,O,E,O)
     * 先选E，剩余(O,E,O),后手只能选O
     * 先选O，剩余(E,O,E),后手只能选E
     * 故先手只要根据max(sum(奇数堆石子),sum(偶数堆石子))选择对应位置就能赢
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    class Solution {
        public boolean stoneGame(int[] piles) {
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：DP Table + 空间压缩
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N)
     */
    class Solution2 {
        public boolean stoneGame(int[] piles) {
            int n = piles.length;
            // 总和
            int totalSum = 0;
            int[] dp = new int[n];
            System.arraycopy(piles, 0, dp, 0, n);
            /*
             * i/j   j-1         j
             * i     dp[i][j-1]  dp[i][j]
             * i+1               dp[i+1][j]
             * 遍历顺序：从下往上，从左往右
             * 压缩原理：
             * 原二维：dp[i][j] 依赖 dp[i+1][j] 和 dp[i][j-1]
             * 压缩后：dp[j] 在更新前保存的是上一轮(i+1)的 dp[i+1][j]
             *        从左往右遍历时，dp[j-1] 已经是当前轮(i)的 dp[i][j-1]
             * */
            for (int i = n - 1; i >= 0; i--) {
                // 区间[i,j]的和
                int sumIJ = piles[i];
                for (int j = i + 1; j < n; j++) {
                    sumIJ += piles[j];
                    // 记录总和
                    if (i == 0 && j == n - 1)
                        totalSum = sumIJ;
                    // dp[j] 更新前是 dp[i+1][j]，dp[j-1] 已是 dp[i][j-1]
                    dp[j] = Math.max(
                            piles[i] + sumIJ - piles[i] - dp[j],
                            piles[j] + sumIJ - piles[j] - dp[j - 1]
                    );
                }
            }
            return dp[n - 1] > totalSum - dp[n - 1];
        }
    }
//        public boolean stoneGame(int[] piles) {
//            int n = piles.length;
//            // 计算前缀和
//            int[] prefixSum = new int[n + 1];
//            for (int i = 1; i < n + 1; i++)
//                // prefixSum[i]表示piles[0..i-1]的前缀和
//                prefixSum[i] = prefixSum[i - 1] + piles[i - 1];
//            int[][] dp = new int[n][n];
//            for (int i = 0; i < n; i++)
//                dp[i][i] = piles[i];
//            /*
//             * i/j   j-1         j
//             * i     dp[i][j-1]  dp[i][j]
//             * i+1               dp[i+1][j]
//             * 遍历顺序：从下往上，从左往右
//             * */
//            for (int i = n - 1; i >= 0; i--) {
//                for (int j = i + 1; j < n; j++) {
//                    int sumIJ = prefixSum[j + 1] - prefixSum[i];
//                    dp[i][j] = Math.max(
//                            piles[i] + sumIJ - piles[i] - dp[i + 1][j],
//                            piles[j] + sumIJ - piles[j] - dp[i][j - 1]
//                    );
//                }
//            }
//            return dp[0][n - 1] > prefixSum[n] - dp[0][n - 1];
//        }
//    }

    /**
     * 思路一：递归DP + 备忘录
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N^2)
     */
    class Solution1 {
        Integer[][] memo;
        int[] prefixSum;

        public boolean stoneGame(int[] piles) {
            int n = piles.length;
            memo = new Integer[n][n];
            // 计算前缀和
            prefixSum = new int[n + 1];
            for (int i = 1; i < n + 1; i++)
                // prefixSum[i]表示piles[0..i-1]的前缀和
                prefixSum[i] = prefixSum[i - 1] + piles[i - 1];
            int alice = dp(piles, 0, n - 1);
            int bob = prefixSum[n] - alice;
            return alice > bob;
        }

        // dp(piles,i,j)表示面对piles[i..j]时先手最多能拿多少分
        int dp(int[] piles, int i, int j) {
            // base case
            if (i == j)
                return piles[i];
            if (memo[i][j] != null)
                return memo[i][j];
            // piles[i..j] = piles[0..j] - piles[0..i-1]
            int sumIJ = prefixSum[j + 1] - prefixSum[i];
            // 选择左端：拿piles[i]，对手在(i+1,j)中作为先手最多能拿dp(piles, i+1, j)分
            int pickLeft = piles[i] + (sumIJ - piles[i] - dp(piles, i + 1, j));
            // 选择右端：拿piles[j]，对手在(i,j-1)中作为先手最多能拿dp(piles, i, j-1)分
            int pickRight = piles[j] + (sumIJ - piles[j] - dp(piles, i, j - 1));
            memo[i][j] = Math.max(pickLeft, pickRight);
            return memo[i][j];
        }
    }

    public static void main(String[] args) {
        Solution solution = new StoneGame().new Solution();
        // put your test code here

    }
}