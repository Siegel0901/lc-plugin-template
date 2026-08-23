package leetcode.editor.cn;

import java.util.*;
import leetcode.editor.common.*;

public class SearchA2dMatrixIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
    * 思路：二分
    * 1. 从矩阵的某个角出发，每一步都能确定性地缩小搜索范围，直到找到目标值或搜索范围为空
    * 1.1. 左上角 (0, 0)？→ 右和下都是增大，无法判断往哪走 ❌
    * 1.2. 右下角 (m-1, n-1)？→ 左和上都是减小，无法判断 ❌
    * 1.3. 右上角 (0, n-1)？→ 左是减小，下是增大 ✅
    * 1.4. 左下角 (m-1, 0)？→ 右是增大，上是减小 ✅
    * 2. 从右上角出发，如果当前元素大于目标值，则左移；如果当前元素小于目标值，则下移
    * 2.1. matrix[r][c] == target → 找到 ✅
    * 2.2. matrix[r][c] < target → 当前行都比 target 小，排除整行，r++
    * 2.3. matrix[r][c] > target → 当前列都比 target 大，排除整列，c--
    * 时间复杂度：O(m + n)
    * 空间复杂度：O(1)
    * */
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;
            int r = 0, c = n - 1;
            while (r < m && c >= 0) {
                if (matrix[r][c] == target) {
                    return true;
                } else if (matrix[r][c] < target) {
                    r++;
                } else {
                    c--;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrixIi().new Solution();
        // put your test code here
        
    }
}