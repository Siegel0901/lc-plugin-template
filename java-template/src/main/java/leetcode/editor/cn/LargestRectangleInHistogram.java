package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：单调栈
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
    class Solution {
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

    /**
     * 思路一：暴力枚举
     * 1. 遍历数组，将每个数组作为最低高度，看他能向左右扩展多远
     * 2. 遇到比当前高度低的即停止
     * 3. 记录元素总数即为宽度
     * 4. 以当前元素为最低高度的面积即为最低高度*宽度
     * 5. 记录最大面积
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     */
    class Solution1 {
        public int largestRectangleArea(int[] heights) {
            int area = heights[0];
            for (int i = 0; i < heights.length; i++) {
                int count = 1;
                for (int r = i + 1; r < heights.length; r++) {
                    if (heights[i] > heights[r])
                        break;
                    else
                        count++;
                }
                for (int l = i - 1; l >= 0; l--) {
                    if (heights[l] < heights[i])
                        break;
                    else
                        count++;
                }
                area = Math.max(area, heights[i] * count);
            }
            return area;
        }
    }


    public static void main(String[] args) {
        Solution solution = new LargestRectangleInHistogram().new Solution();
        // put your test code here
        solution.largestRectangleArea(new int[]{2, 4});
    }
}