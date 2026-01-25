package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class SpiralMatrixIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：设置上下左右四个边界，按右下左上的顺序遍历数组
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(1)
         *
         * @param n 矩阵的行数和列数
         * @return 生成的矩阵
         */
        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            int left = 0, right = n;
            int upper = 0, lower = n;
            int num = 1;
            while (left < right || upper < lower) {
                if (upper < lower) {
                    for (int i = left; i < right; i++)
                        matrix[upper][i] = num++;
                    upper++;
                }
                if (left < right) {
                    for (int i = upper; i < lower; i++)
                        matrix[i][right - 1] = num++;
                    right--;
                }
                if (upper < lower) {
                    for (int i = right - 1; i >= left; i--)
                        matrix[lower - 1][i] = num++;
                    lower--;
                }
                if (left < right) {
                    for (int i = lower - 1; i >= upper; i--)
                        matrix[i][left] = num++;
                    left++;
                }
            }
            return matrix;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SpiralMatrixIi().new Solution();
        // put your test code here

    }
}