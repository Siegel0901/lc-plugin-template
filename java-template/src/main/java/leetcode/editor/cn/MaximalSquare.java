package leetcode.editor.cn;

import java.util.Arrays;

public class MaximalSquare {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路四：dp Table + 空间压缩
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(n)
     * */
    class Solution {
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            // dp table
            int[] dp = new int[n];
            if (matrix[0][0] == '1')
                dp[0] = 1;
            // max记录最大边长
            int max = 0;
            /*
             * i/j  j-1     j               j+1
             * i-1  prev    temp(prev)     temp
             * i    dp[j-1]   dp[j](dp[j-1])    dp[j]
             * 遍历方向：从左到右，从上到下
             * base case：i == 0 || j == 0，dp[i][j] = matrix[i][j] - '0'
             * */
            for (int i = 0; i < m; i++) {
                // prev 记录 dp[i-1][j-1]
                int prev = dp[0];
                for (int j = 0; j < n; j++) {
                    // temp 记录 dp[i-1][j]
                    int temp = dp[j];
                    // 如果当前元素为 '0'，则 dp[i][j] = 0
                    if (matrix[i][j] == '0') {
                        dp[j] = 0;
                    } else {
                        // 如果当前元素为 '1'，则 dp[i][j] = 1
                        dp[j] = 1;
                        // 如果当前元素为 '1'且满足 i > 0 && j > 0，则 dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
                        if (i > 0 && j > 0)
                            dp[j] = Math.min(temp, Math.min(dp[j - 1], prev)) + 1;
                    }
                    // 更新 prev 为 temp，即dp[i-1][j-1] -->(j++) dp[i-1][j]
                    prev = temp;
                    // 更新最大边长
                    max = Math.max(max, dp[j]);
                }
            }
            // 返回最大边长的平方
            return max * max;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路三：dp Table
     * 1. 定义数组：dp[i][j] 表示以 matrix[i][j] 为右下角的最大正方形的边长
     * 1.1. 因此 dp[i][j] 取决于其上方、左方和左上方的三个位置的最大正方形边长的最小值
     * 2. 状态转移
     * 2.1. 若 matrix[i][j] == '0'，则 dp[i][j] = 0
     * 2.2. 若 matrix[i][j] == '1'，则 dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
     * 3. base case：i == 0 || j == 0，dp[i][j] = matrix[i][j] - '0'
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     * */
    class Solution3 {
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            // dp table
            int[][] dp = new int[m][n];
            // max记录最大边长
            int max = 0;
            // 遍历矩阵中的每个元素
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 如果当前元素为 '0'，则 dp[i][j] = 0
                    if (matrix[i][j] == '0') {
                        dp[i][j] = 0;
                    } else {
                        // 如果当前元素为 '1'，则 dp[i][j] = 1
                        dp[i][j] = 1;
                        // 如果当前元素为 '1'，且满足 i > 0 && j > 0，则 dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
                        if (i > 0 && j > 0)
                            dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    // 更新最大边长
                    max = Math.max(max, dp[i][j]);
                }
            }
            // 返回最大边长的平方
            return max * max;
        }
    }

    /*
     * 思路二：递归dp
     * 1. 定义函数：dp(i,j) 表示以 matrix[i][j] 为右下角的最大正方形的边长
     * 1.1. 因此 dp(i,j) 取决于其上方、左方和左上方的三个位置的最大正方形边长的最小值
     * 2. 状态转移
     * 2.1. 若 matrix[i][j] == '0'，则 dp(i,j) = 0
     * 2.2. 若 matrix[i][j] == '1'，则 dp(i,j) = min(dp(i-1,j), dp(i,j-1), dp(i-1,j-1)) + 1
     * 3. base case：dp(i,j) = 0，当 i < 0 或 j < 0 时
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     * */
    class Solution2 {
        // 备忘录
        int[][] memo;

        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            // 备忘录初始化为-1
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            // max记录最大边长
            int max = 0;
            // 遍历矩阵中的每个元素
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    // 更新最大边长
                    max = Math.max(max, dp(matrix, i, j));
            // 返回最大边长的平方
            return max * max;
        }

        // 定义函数：dp(i,j) 表示以 matrix[i][j] 为右下角的最大正方形的边长
        int dp(char[][] matrix, int i, int j) {
            // base case：当 i < 0 或 j < 0 时，返回 0
            if (i < 0 || j < 0)
                return 0;
            // 如果备忘录中已经记录了结果，则直接返回
            if (memo[i][j] != -1)
                return memo[i][j];
            // 如果当前元素为 '0'，则 dp(i,j) = 0
            if (matrix[i][j] == '0') {
                memo[i][j] = 0;
                return 0;
            }
            // 否则，dp(i,j) = min(dp(i-1,j), dp(i,j-1), dp(i-1,j-1)) + 1
            int res = Math.min(Math.min(dp(matrix, i - 1, j), dp(matrix, i, j - 1)), dp(matrix, i - 1, j - 1)) + 1;
            // 将结果记录在备忘录中
            memo[i][j] = res;
            // 返回结果
            return res;
        }
    }

    /*
     * 思路一：暴力求解
     * 1. 对于每个'1'，以该'1'为左上角，向右下方扩展，直到不能扩展为止，记录最大边长
     * 2. 每次扩展，只需检查是否越界以及右列和下行是否都为'1'
     * 时间复杂度：O(m*n*min(m,n)^2)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     * */
    class Solution1 {
        public int maximalSquare(char[][] matrix) {
            // 预处理矩阵的行数和列数
            int m = matrix.length;
            int n = matrix[0].length;
            // 预处理矩阵的最大边长
            int max = 0;
            // 遍历矩阵中的每个元素
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 遍历矩阵中的每个'1'，以该'1'为左上角，向右下方扩展，直到不能扩展为止，记录最大边长
                    if (matrix[i][j] == '1') {
                        // 边长
                        int w = 0;
                        // 当前是否为正方形
                        boolean flag = true;
                        // 判断扩展后是否为正方形
                        while (flag) {
                            // 尝试扩展边长
                            w++;
                            // 判断扩展后是否越界，以及右下角是否为'1'
                            if (i + w < m && j + w < n && matrix[i + w][j + w] == '1') {
                                // 判断边长扩展后，新增的两条边是否都为'1'
                                // 右列：(i, j+w) 到 (i+w, j+w)，即 p 应从 i 到 i+w
                                // 下行：(i+w, j) 到 (i+w, j+w)，即 p 应从 j 到 j+w
                                for (int p = 0; p <= w; p++) {
                                    // 检查右列 (i 到 i+w) 和底行 (j 到 j+w)是否都为'1'
                                    if (matrix[i + p][j + w] != '1' || matrix[i + w][j + p] != '1') {
                                        flag = false;
                                        break;
                                    }
                                }
                            } else
                                flag = false;
                            // 如果不能扩展，则更新最大边长
                            if (!flag)
                                max = Math.max(max, w);
                        }
                    }
                }
            }
            return max * max;
        }
    }


    public static void main(String[] args) {
        Solution solution = new MaximalSquare().new Solution();
        // put your test code here

    }
}