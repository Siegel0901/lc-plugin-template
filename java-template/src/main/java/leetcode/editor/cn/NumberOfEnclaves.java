package leetcode.editor.cn;

public class NumberOfEnclaves {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int numEnclaves(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1)
                        // 淹掉边界上的岛屿
                        dfs(grid, i, j);
            int num = 0;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 1)
                        // 统计剩余的岛屿格子数量
                        // num += dfs(grid, i, j);
                        // 这里不用再dfs遍历淹掉了，剩余的所有等于1的格子都是岛屿
                        num++;
            return num;
        }

        int dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return 0;
            if (grid[i][j] == 0)
                return 0;
            grid[i][j] = 0;
            int num = 1;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                num += dfs(grid, next_i, next_j);
            }
            return num;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NumberOfEnclaves().new Solution();
        // put your test code here

    }
}