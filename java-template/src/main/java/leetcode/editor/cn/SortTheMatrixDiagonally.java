package leetcode.editor.cn;

import java.util.*;

public class SortTheMatrixDiagonally {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：遍历每条对角线，获取相同对角线上的元素
         * 1. 遍历所有对角线
         * 1.1 先遍历列首元素开头的对角线
         * 1.2 再遍历行首元素开头的对角线（有重复，需从第二行开始）
         * 2. 遍历对角线上的元素：横纵坐标+1即为下一个元素
         * 3. 将对角线上的所有元素加入大顶堆
         * 4. 再次逆序遍历对角线，将堆顶赋值给对角线上的元素
         * 时间复杂度：O(m * n * log(min(m, n)))
         * 空间复杂度：O(min(m, n))
         *
         * @param mat 矩阵
         * @return 排序后的矩阵
         */
//        public int[][] diagonalSort(int[][] mat) {
//            int rows = mat.length;
//            int cols = mat[0].length;
//            Queue<Integer> nums = new PriorityQueue<>(Comparator.reverseOrder());
//            for (int i = 0; i < cols; i++)
//                sortByDiagonal(mat, 0, i, rows, cols, nums);
//            for (int i = 1; i < rows; i++)
//                sortByDiagonal(mat, i, 0, rows, cols, nums);
//            return mat;
//        }
//
//        private void sortByDiagonal(int[][] mat, int row, int col, int rows, int cols, Queue<Integer> nums) {
//            int i = row, j = col;
//            while (i < rows && j < cols)
//                nums.offer(mat[i++][j++]);
//            while (i > row && j > col)
//                mat[--i][--j] = nums.poll();
//        }
//    }

        /**
         * 思路二：利用规律——对角线上的元素横纵坐标之差唯一且相等
         * 1. 遍历数组，将横纵坐标之差作为key，对角线元素集合作为value，存入HashMap
         * 2. 遍历结束后，对HashMap中的每个value进行降序排序
         * 3. 再次遍历数组，根据key将value中的末尾元素赋值到数组中，并在value中删除该元素（末尾删除效率高）
         * 时间复杂度：O(m * n * log(min(m, n)))
         * 空间复杂度：O(m * n)
         * @param mat 矩阵
         * @return 排序后的矩阵
         */
        public int[][] diagonalSort(int[][] mat) {
            int rows = mat.length;
            int cols = mat[0].length;
            HashMap<Integer, ArrayList<Integer>> diagonals = new HashMap<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int diagonalID = i - j;
                    diagonals.putIfAbsent(diagonalID, new ArrayList<>());
                    diagonals.get(diagonalID).add(mat[i][j]);
                }
            }
            for (ArrayList<Integer> list : diagonals.values())
                list.sort(Collections.reverseOrder());
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    ArrayList<Integer> list = diagonals.get(i - j);
                    mat[i][j] = list.remove(list.size() - 1);
                }
            }
            return mat;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SortTheMatrixDiagonally().new Solution();
        // put your test code here
        solution.diagonalSort(new int[][]{{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}});
    }
}