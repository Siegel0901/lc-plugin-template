package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class SpiralMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：用四个变量确定未遍历元素的边界，右->下->左->上顺序遍历
         * 时间复杂度:O(m * n)
         * 空间复杂度:O(1)
         *
         * @param matrix 矩阵
         * @return 矩阵的元素
         */
//        public List<Integer> spiralOrder(int[][] matrix) {
//            int n = matrix[0].length;
//            int m = matrix.length;
//            int left = 0, right = n;
//            int upper = 0, lower = m;
//            List<Integer> res = new ArrayList<>();
//            while (res.size() < m * n) {
//                // 向➡️遍历
//                if (upper < lower) {
//                    for (int i = left; i < right; i++)
//                        res.add(matrix[upper][i]);
//                    // 上边界下移
//                    upper++;
//                }
//                // 向⬇️遍历
//                if (left < right) {
//                    for (int i = upper; i < lower; i++)
//                        res.add(matrix[i][right - 1]);
//                    // 右边界左移
//                    right--;
//                }
//                // 向⬅️遍历
//                if (upper < lower) {
//                    for (int i = right - 1; i >= left; i--)
//                        res.add(matrix[lower - 1][i]);
//                    // 下边界上移
//                    lower--;
//                }
//                // 向⬆️遍历
//                if (left < right) {
//                    for (int i = lower - 1; i >= upper; i--)
//                        res.add(matrix[i][left]);
//                    // 左边界右移
//                    left++;
//                }
//            }
//            return res;
//        }

        /**
         * 思路二：用方向数组控制遍历方向
         * 1. 定义四个方向右,下,左,上
         * 2. 从(0,0)开始向右走,每走一步,需要加上当前方向判断下一个位置
         * 3. 若下一个位置越界或已被访问,则改变方向
         * 4. 当结果数组中元素个数等于矩阵元素个数时,结束循环
         * 时间复杂度:O(n^2)
         * 空间复杂度：O(1)
         *
         * @param matrix 矩阵
         * @return 矩阵的元素
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            // 获取矩阵行数和列数
            int rows = matrix.length;
            int cols = matrix[0].length;
            // 结果数组
            List<Integer> res = new ArrayList<>();
            // 当前行列
            int row = 0, col = 0;
            // 当前方向
            int directionIndex = 0;

            while (res.size() < rows * cols) {
                // 将元素加入结果数组
                res.add(matrix[row][col]);
                // 获取下一个位置
                int nextRow = row + direction[directionIndex][0];
                int nextCol = col + direction[directionIndex][1];

                // 判断下一个位置是否合法
                // 1. 行列是否越界
                // 2. 是否已被访问
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || matrix[nextRow][nextCol] == VISITED) {
                    // 切换方向
                    directionIndex = (directionIndex + 1) % 4;
                    // 重新计算下一个位置
                    nextRow = row + direction[directionIndex][0];
                    nextCol = col + direction[directionIndex][1];
                }

                // 将当前位置标记为已访问
                matrix[row][col] = VISITED;

                // 移动到下一个位置
                row = nextRow;
                col = nextCol;
            }
            return res;
        }

        // 题目保证-100 <= matrix[i][j] <= 100
        public final int VISITED = 101;

        // 定义四个方向：右0 → 下1 → 左2 → 上3（顺时针螺旋顺序）
        public int[][] direction = new int[][]{
                {0, 1},     // 向右：行不变，列+1
                {1, 0},     // 向下：行+1，列不变
                {0, -1},    // 向左：行不变，列-1
                {-1, 0}     // 向上：行-1，列不变
        };
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();
        // put your test code here
        solution.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}