package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class ShunShiZhenDaYinJuZhenLcof {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：方向数组
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(1)
         *
         * @param array 二维数组
         * @return 一维数组
         */
        public int[] spiralArray(int[][] array) {
            int rows = array.length;
            if (rows == 0)
                return new int[]{};
            int cols = array[0].length;
            int[] res = new int[rows * cols];
            int index = 0;

            int row = 0;
            int col = 0;
            int directionIndex = 0;

            while (index < rows * cols) {
                res[index++] = array[row][col];

                int nextRow = row + direction[directionIndex][0];
                int nextCol = col + direction[directionIndex][1];

                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || array[nextRow][nextCol] == VISITED) {
                    directionIndex = (directionIndex + 1) % 4;

                    nextRow = row + direction[directionIndex][0];
                    nextCol = col + direction[directionIndex][1];
                }

                array[row][col] = VISITED;

                row = nextRow;
                col = nextCol;
            }

            return res;
        }

        public final int VISITED = 101;

        public int[][] direction = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ShunShiZhenDaYinJuZhenLcof().new Solution();
        // put your test code here
        System.out.println(Arrays.toString(solution.spiralArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }
}