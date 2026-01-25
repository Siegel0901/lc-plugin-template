package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class TransposeMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：直接遍历到新数组
         * 时间复杂度：O(m * n)
         * 空间复杂度：O(n * m)
         *
         * @param matrix
         * @return
         */
        public int[][] transpose(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] res = new int[n][m];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    res[j][i] = matrix[i][j];
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new TransposeMatrix().new Solution();
        // put your test code here

    }
}