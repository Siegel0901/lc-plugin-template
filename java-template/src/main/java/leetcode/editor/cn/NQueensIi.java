package leetcode.editor.cn;

import java.util.Arrays;

public class NQueensIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        char[][] board;
        int n;
        int res = 0;

        public int totalNQueens(int n) {
            board = new char[n][n];
            for (char[] chars : board)
                Arrays.fill(chars, '.');
            this.n = n;
            backtrack(0);
            return res;
        }

        void backtrack(int row) {
            if (row == n) {
                res++;
                return;
            }
            for (int col = 0; col < n; col++) {
                if (isValid(row, col)) {
                    board[row][col] = 'Q';
                    backtrack(row + 1);
                    board[row][col] = '.';
                }
            }
        }

        boolean isValid(int row, int col) {
            for (int i = 0; i < n; i++)
                if (board[i][col] == 'Q')
                    return false;
            for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
                if (board[i][j] == 'Q')
                    return false;
            for (int i = row, j = col; i >= 0 && j < n; i--, j++)
                if (board[i][j] == 'Q')
                    return false;
            return true;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NQueensIi().new Solution();
        // put your test code here

    }
}