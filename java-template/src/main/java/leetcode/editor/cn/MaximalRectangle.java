package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximalRectangle {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路三：逐行转化为柱状图问题
     * 1. 逐行处理
     * 1.1. 第 0 行：形成一个柱状图
     * 1.2. 第 1 行：在第 0 行的基础上累加高度（如果当前位置是 '1'）
     * 1.3. ...以此类推
     * 2. 用单调栈对每一行的柱状图求最大矩形面积
     * 时间复杂度：O(m × n)
     * 空间复杂度：O(n)
     * */
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int max = 0;
            int[] heights = new int[n];
            for (char[] chars : matrix) {
                for (int j = 0; j < n; j++)
                    heights[j] = chars[j] == '1' ? heights[j] + 1 : 0;
                // 求每一行高度柱状图的最大面积
                max = Math.max(max, largestRectangleArea(heights));
            }
            return max;
        }

        /**
         * 如何求柱状图的最大面积？
         * 思路：单调栈
         * 1. 维护一个单调递增栈，存放元素索引，加入哨兵索引-1，处理左边界为第0个柱子的情况
         * 2. 遍历数组，栈内只有哨兵或当前元素大于等于栈顶元素时,当前索引入栈
         * 3. 若当前元素小于栈顶元素或遍历结束，则说明找到了栈顶元素的右边界，栈顶出栈
         * 4. 出栈元素的左边界就是新栈顶（一定比出栈元素小）
         * 5. 计算面积：面积 = 长 * 宽（右边界 - 左边界 - 1） = 出栈元素高度 * (当前元素索引 - 新栈顶索引 - 1)
         * 6. 重复第3步，直至当前元素大于栈顶元素或者遇到了第0个柱子,当前元素索引入栈
         * 7. 第0个柱子的索引是-1，计算宽度 = 右边界 - 左边界 - 1 = 右边界 - (-1) - 1 = 右边界
         * 8. 数组遍历完，若栈内除了哨兵还有其余元素，则说明这些元素的右边界都为heights.length
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         */
        public int largestRectangleArea(int[] heights) {
            // 声明单调栈
            Deque<Integer> stk = new ArrayDeque<>();
            // 加入哨兵
            stk.push(-1);
            // 记录最大面积
            int max = 0;
            // 遍历所有位置
            for (int i = 0; i <= heights.length; i++) {
                /*
                 * 1. 必须确保栈内至少有哨兵元素,用while处理所有找到右边界的栈内元素
                 * 2. 当i为数组长度且栈内还有元素时,栈内元素的右边界都是数组长度i
                 * 3. 当i不是数组长度,且当前位置元素小于栈顶索引所指元素高度时,弹出栈顶,这些被弹出元素的右边界都是当前位置i
                 * */
                while (stk.size() > 1 && (i == heights.length || heights[i] < heights[stk.peek()])) {
                    int top = stk.pop();
                    int area = heights[top] * (i - stk.peek() - 1);
                    max = Math.max(max, area);
                }
                // 当前索引入栈
                stk.push(i);
            }
            return max;
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路二：以每个点为右下角扩展（较优的暴力）
     * 1. 预处理：计算每个位置向上连续 '1' 的高度
     * 1.1. 对于每个位置 (i, j)，计算从该位置向上有多少个连续的 '1'
     * 1.2. 这就是以 (i, j) 为底的高度
     * 2. 枚举每个位置作为矩形的右下角
     * 2.1. 对于位置 (i, j)，已知其高度 h
     * 2.2. 向左扫描，对于每一列，取最小高度作为当前矩形的高度
     * 2.3. 宽度逐渐增加，计算面积 = 最小高度 × 宽度
     * 3. 记录最大面积
     * 时间复杂度：O(m × n²)
     * 预处理高度：O(m × n)
     * 枚举每个位置并向左扩展：O(m × n × n)
     * 空间复杂度：O(m × n)
     * */
    class Solution2 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int max = 0;
            // 预处理每个位置1的高度
            int[][] heights = new int[m][n];
            // 处理第一行各列元素的高度
            for (int j = 0; j < n; j++)
                heights[0][j] = matrix[0][j] == '1' ? 1 : 0;
            // 处理后续几行各列元素的高度
            for (int i = 1; i < m; i++)
                for (int j = 0; j < n; j++)
                    // 当前位置为0，高度为0
                    // 当前位置为1，高度为上一个位置的高度+1
                    heights[i][j] = matrix[i][j] == '0' ? 0 : heights[i - 1][j] + 1;
            // 遍历每个位置作为矩形右下角
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 跳过0
                    if (matrix[i][j] == '0')
                        continue;
                    // 当前高度
                    int h = heights[i][j];
                    // 从当前列开始向左遍历
                    for (int k = j; k >= 0; k--) {
                        // 遇到0停止
                        if (matrix[i][k] == '0')
                            break;
                        // 记录当前连续1的宽度
                        int w = j - k + 1;
                        // 取最小高度
                        h = Math.min(h, heights[i][k]);
                        // 记录最大面积
                        max = Math.max(max, w * h);
                    }
                }
            }
            return max;
        }
    }

    /*
     * 思路一：枚举所有矩形（最暴力）
     * 1. 枚举矩形的左上角和右下角
     * 1.1. 遍历所有可能的左上角坐标 (r1, c1)
     * 1.2. 遍历所有可能的右下角坐标 (r2, c2)，其中 r2 ≥ r1，c2 ≥ c1
     * 2. 验证矩形是否全为 '1'
     * 2.1. 对于每个确定的矩形区域，检查内部所有元素是否都是 '1'
     * 2.2. 如果全是 '1'，计算面积 = (r2 - r1 + 1) × (c2 - c1 + 1)
     * 2.3. 记录最大面积
     * 时间复杂度：O(m² × n² × m × n) = O(m³ × n³)，其中 m 是行数，n 是列数
     * 枚举左上角：O(m × n)
     * 枚举右下角：O(m × n)
     * 验证矩形：O(m × n)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     * */
    class Solution1 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int max = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '0')
                        continue;
                    for (int k = i; k < m; k++) {
                        for (int l = j; l < n; l++) {
                            if (matrix[k][l] == '0')
                                continue;
                            boolean valid = true;
                            for (int r = i; r <= k; r++) {
                                for (int c = j; c <= l; c++) {
                                    if (matrix[r][c] == '0') {
                                        valid = false;
                                        break;
                                    }
                                }
                            }
                            if (valid)
                                max = Math.max(max, (k - i + 1) * (l - j + 1));
                        }
                    }
                }
            }
            return max;
        }
    }


    public static void main(String[] args) {
        Solution solution = new MaximalRectangle().new Solution();
        // put your test code here
        solution.maximalRectangle(new char[][]{
                {'1', '0', '1', '1', '1'},
                {'0', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1'},
        });
    }
}