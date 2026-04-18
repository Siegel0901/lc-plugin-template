package leetcode.editor.cn;

public class ContainerWithMostWater {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：贪心 + 双指针
     * 1. 每次移动较矮的指针，因为较矮的一侧是当前面积的瓶颈
     * 2. 舍弃的高度不可能产生更大的面积（宽度减小，高度不增）
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int maxArea(int[] height) {
            int area = 0;
            int l = 0, r = height.length - 1;
            while (l < r) {
                // 较矮的一侧是当前面积的瓶颈
                int minH = Math.min(height[l], height[r]);
                // 记录面积
                area = Math.max(area, minH * (r - l));
                // 找到比minH大的高度
                while (height[l] <= minH && l < r) l++;
                while (height[r] <= minH && l < r) r--;
            }
            return area;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：暴力求解
     * 1. 容器的盛水量为min(l_h,r_h)*(r_i-l_i)
     * 2. 遍历每条左垂线的右垂线记录最大值
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     */
    class Solution1 {
        public int maxArea(int[] height) {
            int max = 0;
            for (int i = 0; i < height.length; i++)
                for (int j = i; j < height.length; j++)
                    max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            return max;
        }
    }


    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
        // put your test code here

    }
}