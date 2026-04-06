package leetcode.editor.cn;

public class BestTimeToBuyAndSellStock {

    //leetcode submit region begin(Prohibit modification and deletion)


    /**
     * 1. 定义状态：
     * 1.1. 状态1: 天数i, i∈[0,n], n为prices.length, i==0表示还没有开始，i==1表示第1天，股票价格为prices[i-1]
     * 1.2. 状态2: 最大买入次数k, k∈[0,K], K为题目限制条件买入次数上限
     * 1.3. 状态3: 当前股票持有状态j, j∈[0,1], 0表示未持有,1表示持有
     * 2. base case:
     * 2.1. dp[0][..][0] = 0,           第0天还没开始卖股票，且未持有股票，利润为0
     * 2.2. dp[0][..][1] = -infinity,   第0天还没开始卖股票，且持有股票，不可能有利润，题目求大最值，这里设为最小值
     * 2.3. dp[..][0][0] = 0,           k==0，买入次数上限为0，不可能买入任何股票，利润为0
     * 2.4. dp[..][0][1] = -infinity,   k==0，买入次数上限为0，不可能持有股票，不可能有利润
     * 3. 状态转移方程：根据今天是否持有股票转移状态
     * 3.1. dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1]+prices[i-1]) 今天未持有股票的最大利润 = max(昨天未持有，昨天持有今天卖出)
     * 3.2. dp[i][k][1] = max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i-1]) 今天持有股票的最大利润 = max(昨天持有，昨天未持有今天买入)
     * 注意：今天最大买入k次的限制依赖昨天最大买入k-1次的限制，因为今天如果是要买入，昨天的k-1次最大买入可能都买了，则最大买入次数需要+1
     */
    class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][][] dp = new int[n + 1][2][2];
            // base case
            for (int i = 0; i <= n; i++)
                for (int l = 0; l <= 1; l++)
                    for (int j = 0; j < 2; j++)
                        if (i == 0 && j == 1 || l == 0 && j == 1)
                            dp[i][l][j] = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i - 1]);
                dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i - 1]);
            }
            // dp[n][k][0]（不持有）一定比dp[n][k][1]（持有）大
            return dp[n][1][0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
        // put your test code here

    }
}