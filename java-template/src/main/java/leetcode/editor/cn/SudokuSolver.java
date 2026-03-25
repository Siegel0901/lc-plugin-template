package leetcode.editor.cn;

public class SudokuSolver {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用于标识是否找到解
        boolean found = false;

        public void solveSudoku(char[][] board) {
            // 从第一个格子开始 backtrack
            backtrack(board, 0);
        }

        // 回溯算法
        void backtrack(char[][] board, int index) {
            // 如果找到解则返回
            if (found)
                return;
            // 如果遍历完所有格子则找到解
            if (index == 9 * 9) {
                found = true;
                return;
            }
            // 计算当前格子的行和列
            int i = index / 9;
            int j = index % 9;
            // 如果当前格子有数字则跳过
            if (board[i][j] != '.') {
                backtrack(board, index + 1);
                return;
            }
            // 尝试填入数字 1-9
            for (char ch = '1'; ch <= '9'; ch++) {
                // 如果当前数字不能填入则跳过
                if (!isValid(board, i, j, ch))
                    continue;
                // 选择数字
                board[i][j] = ch;
                // 选择下一个位置
                backtrack(board, index + 1);
                // 如果找到解则返回
                if (found)
                    return;
                // 撤销选择
                board[i][j] = '.';
            }
        }

        boolean isValid(char[][] board, int row, int col, char target) {
            for (int i = 0; i < 9; i++) {
                // row check
                if (board[row][i] == target)
                    return false;
                // col check
                if (board[i][col] == target)
                    return false;
                // 3*3 block check
                if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == target)
                    return false;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SudokuSolver().new Solution();
        // put your test code here
        solution.solveSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '.', '2', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '7', '8', '4', '.', '.', '9'}
        });
    }
}