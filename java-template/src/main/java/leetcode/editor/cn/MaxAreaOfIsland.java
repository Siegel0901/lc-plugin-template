package leetcode.editor.cn;

public class MaxAreaOfIsland {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：DFS带返回值
     * 1. 遍历每个点，如果该点是1，则从该点开始DFS，统计该点所在岛屿的面积
     * 2. 将岛屿淹没，省去visited数组
     * 3. 返回最大岛屿面积
     */
    class Solution {
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 1)
                        maxArea = Math.max(maxArea, dfs(grid, i, j));
            return maxArea;
        }


        // 从(i, j)开始DFS，统计岛屿面积
        int dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return 0;
            if (grid[i][j] == 0)
                return 0;
            grid[i][j] = 0;
            int area = 1;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                area += dfs(grid, next_i, next_j);
            }
            return area;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：DFS
     * 1. 遍历每个点，如果该点是1，则从该点开始DFS，统计该点所在岛屿的面积
     * 2. 将岛屿淹没，省去visited数组
     * 3. 返回最大岛屿面积
     */
    class Solution1 {
        int maxArea = 0;
        int area = 0;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int maxAreaOfIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0)
                        continue;
                    area = 0;
                    dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
            return maxArea;
        }

        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return;
            if (grid[i][j] == 0)
                return;
            grid[i][j] = 0;
            area++;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                dfs(grid, next_i, next_j);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new MaxAreaOfIsland().new Solution();
        // put your test code here

    }
}