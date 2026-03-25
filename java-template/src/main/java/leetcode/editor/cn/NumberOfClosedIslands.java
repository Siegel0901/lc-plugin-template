package leetcode.editor.cn;

public class NumberOfClosedIslands {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int closedIsland = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        /**
         * 思路二：dfs
         * 1. 边界的岛屿不是封闭岛屿
         * 2. dfs将边界岛屿淹没
         * 3. 剩下的岛屿就是封闭岛屿
         */
        public int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            // 将边界岛屿淹没
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1)
                        if (grid[i][j] == 0)
                            dfs(grid, i, j);
            // 剩下的岛屿就是封闭岛屿
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        closedIsland++;
                        dfs(grid, i, j);
                    }
                }
            }
            return closedIsland;
        }

        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] == 1)
                return;
            // 将岛屿淹没，省去visited数组
            grid[i][j] = 1;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                dfs(grid, next_i, next_j);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        int closedIsland = 0;
        boolean isClosed = true;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        /**
         * 思路一：dfs
         * 1. dfs遍历grid，找到一个岛屿，判断是否是封闭岛屿
         * 2. 走到边界，说明不是封闭岛屿
         * 3. 将岛屿淹没，省去visited数组
         */
        public int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1)
                        continue;
                    // 走到这说明找到了一个岛屿，需要判断是否是封闭岛屿
                    isClosed = true;
                    dfs(grid, i, j);
                    if (isClosed)
                        closedIsland++;
                }
            }
            return closedIsland;
        }

        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                // 如果走到边界，说明不是封闭岛屿
                isClosed = false;
                return;
            }
            if (grid[i][j] == 1)
                return;
            // 将岛屿淹没，省去visited数组
            grid[i][j] = 1;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                dfs(grid, next_i, next_j);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new NumberOfClosedIslands().new Solution();
        // put your test code here

    }
}