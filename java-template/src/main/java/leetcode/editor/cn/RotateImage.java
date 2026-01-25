package leetcode.editor.cn;

public class RotateImage {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：先将矩阵沿着↘️对角线交换位置，再将每行元素翻转
         * 例子：
         * 1 2 3    1 4 7    7 4 1
         * 4 5 6 -> 2 5 8 -> 8 5 2
         * 7 8 9    3 6 9    9 6 3
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(1)
         * 【拓展】：如果要求逆时针旋转，可以先将每行元素翻转，再沿着↘️对角线交换位置
         * 例子：
         * 1 2 3    3 2 1    3 6 9
         * 4 5 6 -> 6 5 4 -> 2 5 8
         * 7 8 9    9 8 7    1 4 7
         *
         * @param matrix 矩阵
         */
        public void rotate(int[][] matrix) {
            // 先将矩阵沿着↘️对角线交换位置
            for (int i = 0; i < matrix.length; i++)
                for (int j = i; j < matrix.length; j++)
                    if (i != j)
                        swap(matrix, i, j, j, i);
            // 对每行翻转
            for (int i = 0; i < matrix.length; i++) {
                int left = 0, right = matrix.length - 1;
                while (left < right)
                    swap(matrix, i, left++, i, right--);
            }
        }

        private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
            int temp = matrix[i1][j1];
            matrix[i1][j1] = matrix[i2][j2];
            matrix[i2][j2] = temp;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RotateImage().new Solution();
        // put your test code here
        solution.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}