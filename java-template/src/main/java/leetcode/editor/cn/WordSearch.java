package leetcode.editor.cn;

public class WordSearch {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean exist = false;

        public boolean exist(char[][] board, String word) {
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (exist)
                        return true;
                    dfs(board, i, j, word, 0);
                }
            }
            return exist;
        }

        void dfs(char[][] board, int i, int j, String word, int p) {
            if (p == word.length()) {
                exist = true;
                return;
            }
            if (exist)
                return;
            int m = board.length, n = board[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return;
            if (board[i][j] != word.charAt(p))
                return;
            board[i][j] = (char) -board[i][j];
            for (int[] dir : dirs)
                dfs(board, i + dir[0], j + dir[1], word, p + 1);
            board[i][j] = (char) -board[i][j];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new WordSearch().new Solution();
        // put your test code here
        solution.exist(new char[][]{
                {'a', 'b', 'c'},
                {'a', 'e', 'd'},
                {'a', 'f', 'g'},
        }, "abcdefg");
    }
}