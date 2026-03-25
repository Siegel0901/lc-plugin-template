package leetcode.editor.cn;

public class CountSubIslands {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：DFS
     * 1. 遍历grid2中的格子，若grid2[i][j] == 1，grid2[i][j] == 0，则该岛屿不是子岛屿，直接淹掉
     * 2. dfs遍历grid2，剩下的岛屿就是子岛屿
     * 3. 提前剪枝，比思路一稍快
     */
    class Solution {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            int m = grid1.length, n = grid1[0].length;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    // grid2中是陆地，grid1中是水，说明该岛屿不是子岛屿
                    if (grid2[i][j] == 1 && grid1[i][j] == 0)
                        // 淹掉该岛屿
                        dfs(grid2, i, j);
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 剩下的岛屿都是子岛屿
                    if (grid2[i][j] == 1) {
                        count++;
                        dfs(grid2, i, j);
                    }
                }
            }
            return count;
        }

        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return;
            if (grid[i][j] == 0)
                return;
            grid[i][j] = 0;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                dfs(grid, next_i, next_j);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：DFS
     * 1. dfs遍历 grid2[i][j] == 1的格子，同时淹掉该岛屿
     * 2. 若淹掉岛屿过程中出现 grid1[i][j] == 0 的格子，则该岛屿不是子岛屿
     */
    class Solution1 {
        int count = 0;
        boolean isSubIsland = true;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            int m = grid1.length, n = grid1[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid2[i][j] == 0)
                        continue;
                    isSubIsland = true;
                    dfs(grid1, grid2, i, j);
                    if (isSubIsland)
                        count++;
                }
            }
            return count;
        }

        void dfs(int[][] grid1, int[][] grid2, int i, int j) {
            int m = grid1.length, n = grid1[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return;
            if (grid2[i][j] == 0)
                return;
            if (grid1[i][j] == 0)
                isSubIsland = false;
            grid2[i][j] = 0;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                dfs(grid1, grid2, next_i, next_j);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new CountSubIslands().new Solution();
        // put your test code here

    }
}