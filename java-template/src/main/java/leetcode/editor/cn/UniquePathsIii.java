package leetcode.editor.cn;

public class UniquePathsIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int paths = 0;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited;
        int total = 0;
        int count = 0;


        public int uniquePathsIII(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            visited = new boolean[m][n];

            int startI = 0, startJ = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        startI = i;
                        startJ = j;
                    }
                    if (grid[i][j] == 0 || grid[i][j] == 1)
                        total++;
                }
            }

            dfs(grid, startI, startJ);

            return paths;
        }

        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n)
                return;
            if (grid[i][j] == -1 || visited[i][j])
                return;
            if (grid[i][j] == 2){
                if (count == total)
                    paths++;
                return;
            }
            visited[i][j] = true;
            count++;
            for (int[] dir : dirs)
                dfs(grid, i + dir[0], j + dir[1]);
            count--;
            visited[i][j] = false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new UniquePathsIii().new Solution();
        // put your test code here
        solution.uniquePathsIII(new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        });

    }
}