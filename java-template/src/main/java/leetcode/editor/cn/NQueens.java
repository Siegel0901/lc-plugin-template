package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> res = new ArrayList<>();
        char[][] board;
        int n;

        /**
         * 思路二：对每行中在哪个列放皇后进行回溯
         * 时间复杂度分析：
         * 1. 搜索树结构：
         * 第 0 行：有 n 种选择
         * 第 1 行：最多 n-1 种选择（至少 1 列被占用）
         * 第 2 行：最多 n-2 种选择
         * ...
         * 第 n-1 行：最多 1 种选择
         * 2. 搜索树节点数：
         * 总节点数 ≈ n × (n-1) × (n-2) × ... × 1 = n!
         * 3. 每个节点的代价：
         * isValid() 检查：O(n)
         * 检查列：O(n)
         * 检查两个对角线：O(n)
         * 4. 总时间复杂度：
         * T(n) = n! × O(n) = O(n! × n)
         * 空间复杂度分析：
         * char[][] board;           // O(n²) - 棋盘
         * List<List<String>> res;   // O(n! × n²) - 存储所有解
         * recursion stack;          // O(n) - 递归深度
         * 1. 关键点：
         * 棋盘存储：O(n²)
         * 递归栈深度：O(n)（最多 n 层递归）
         * 结果存储：O(n! × n²)（N 皇后问题有 n! 个解，每个解 n×n 大小）
         * 2. 通常不计入结果存储，所以：
         * 空间复杂度 = O(n²) + O(n) = O(n²)
         * 按行剪枝策略：
         * 第 0 行：n 种选择
         * 第 1 行：≤ n-1 种选择（排除第 0 行的列和对角线）
         * 第 2 行：≤ n-2 种选择
         * ...
         * 自动保证：
         * ✅ 每行恰好 1 个皇后
         * ✅ 总共恰好 n 个皇后
         * ✅ 大量无效分支被提前剪掉
         */
        public List<List<String>> solveNQueens(int n) {
            // 初始化棋盘
            board = new char[n][n];
            for (char[] chars : board)
                Arrays.fill(chars, '.');
            this.n = n;
            // 从第一行开始回溯
            backtrack(0);
            return res;
        }

        void backtrack(int row) {
            // 如果遍历完所有行
            if (row == n) {
                // 添加一个解
                List<String> temp = new ArrayList<>();
                for (char[] chars : board)
                    temp.add(new String(chars));
                res.add(temp);
                return;
            }
            // 遍历当前行的每一列
            for (int col = 0; col < n; col++) {
                // 如果当前格子可以放皇后
                if (isValid(row, col)) {
                    // 放皇后
                    board[row][col] = 'Q';
                    // 继续回溯下一行
                    backtrack(row + 1);
                    // 撤销选择
                    board[row][col] = '.';
                }
            }
        }

        boolean isValid(int row, int col) {
            // 检查列
            for (int i = 0; i < n; i++)
                if (board[i][col] == 'Q') return false;
            // 检查左上角
            for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
                if (board[i][j] == 'Q')
                    return false;
            // 检查右上角
            for (int i = row, j = col; i >= 0 && j < n; i--, j++)
                if (board[i][j] == 'Q')
                    return false;
            return true;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        List<List<String>> res = new ArrayList<>();
        char[][] board;
        int n;

        /**
         * 思路一：每个格子是否放皇后进行回溯
         * 时间复杂度分析
         * 1. 搜索树结构：
         * 每个格子有 2 种选择：放 Q 或 不放
         * 总共有 n² 个格子
         * <p>
         * 搜索树大小 = 2 × 2 × 2 × ... × 2 (n²次) = 2^(n²)
         * 2. 每个节点的代价：
         * isValid() 检查：O(n)
         * 终止时数皇后：O(n²)
         * 3. 总时间复杂度：
         * T(n) = O(2^(n²))
         * 空间复杂度分析：
         * char[][] board;           // O(n²) - 棋盘
         * List<List<String>> res;   // O(n! × n²) - 存储所有解
         * recursion stack;          // O(n²) - 递归深度
         * 1. 关键点：
         * 棋盘存储：O(n²)
         * 递归栈深度：O(n²)（需要遍历 n² 个格子）
         * 结果存储：O(n! × n²)
         * 2. 通常不计入结果存储，所以：
         * 空间复杂度 = O(n²) + O(n²) = O(n²)
         * 遍历所有格子的问题：
         * 格子 (0,0): 放 or 不放 (2 种)
         * 格子 (0,1): 放 or 不放 (2 种)
         * 格子 (0,2): 放 or 不放 (2 种)
         * ...
         * 格子 (n-1,n-1): 放 or 不放 (2 种)
         * 导致：
         * ❌ 大量分支会放少于 n 个皇后（无效）
         * ❌ 大量分支会放多于 n 个皇后（无效）
         * ❌ 同一行可能放多个皇后（需要在 isValid 中检查行）
         * ❌ 直到最后才知道是否有效
         */
        public List<List<String>> solveNQueens(int n) {
            // 初始化棋盘
            board = new char[n][n];
            for (char[] chars : board)
                Arrays.fill(chars, '.');
            this.n = n;
            // 从第一个格子开始回溯
            backtrack(0);
            return res;
        }

        void backtrack(int index) {
            // 如果遍历完所有格子
            if (index == n * n) {
                // 检查是否放了n个皇后
                int count = 0;
                for (char[] chars : board)
                    for (char c : chars)
                        if (c == 'Q')
                            count++;
                // 放了n个皇后则找到一个解
                if (count == n) {
                    List<String> temp = new ArrayList<>();
                    for (char[] chars : board)
                        temp.add(new String(chars));
                    res.add(temp);
                }
                return;
            }
            // 获取当前格子的行和列
            int i = index / n;
            int j = index % n;

            // 情况1:这个格子不放皇后
            backtrack(index + 1);

            // 情况2:这个格子放皇后（如果可以的话）
            if (board[i][j] != 'Q' && isValid(i, j)) {
                board[i][j] = 'Q';
                backtrack(index + 1);
                board[i][j] = '.';
            }
        }

        boolean isValid(int row, int col) {
            for (int i = 0; i < n; i++) {
                // 检查列
                if (board[i][col] == 'Q') return false;
                // 检查行
                if (board[row][i] == 'Q') return false;
            }
            // 检查左上角
            for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
                if (board[i][j] == 'Q')
                    return false;
            // 检查右上角
            for (int i = row, j = col; i >= 0 && j < n; i--, j++)
                if (board[i][j] == 'Q')
                    return false;
            return true;
        }
    }


    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
        // put your test code here
    }
}