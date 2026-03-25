package leetcode.editor.cn;

public class NumberOfIslands {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int numIslands = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int numIslands(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1')
                        numIslands++;
                    dfs(grid, i, j);
                }
            }
            return numIslands;
        }

        void dfs(char[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return;
            if (grid[i][j] == '0')
                return;
            // 把访问过的地方标记为0，可以省去visited数组
            grid[i][j] = '0';
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                dfs(grid, next_i, next_j);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        int numIslands = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int numIslands(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j])
                        continue;
                    if (grid[i][j] == '1')
                        numIslands++;
                    dfs(grid, i, j, visited);
                }
            }
            return numIslands;
        }

        void dfs(char[][] grid, int i, int j, boolean[][] visited) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return;
            if (visited[i][j])
                return;
            if (grid[i][j] == '0')
                return;
            visited[i][j] = true;
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                dfs(grid, next_i, next_j, visited);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        // put your test code here

    }
}