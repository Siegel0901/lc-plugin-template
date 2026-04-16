package leetcode.editor.cn;

public class TrappingRainWater {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路三：双指针
     * 1. 使用双指针代替l_max和r_max两个数组，降低空间复杂度
     * 2. 每次移动较矮一侧的指针，因为较矮一侧决定了当前位置的瓶颈(min(l_max,r_max))
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int trap(int[] height) {
            int left = 0, right = height.length - 1;
            int l_max = height[left], r_max = height[right];
            int res = 0;
            // 双指针遍历数组
            while (left < right) {
                // 更新l_max和r_max
                l_max = Math.max(l_max, height[left]);
                r_max = Math.max(r_max, height[right]);
                // res+=min(l_max,r_max)-height[i]
                if (l_max < r_max) {
                    // l_max < r_max,说明left位置的瓶颈是l_max(右边至少有r_max这么高)
                    res += l_max - height[left];
                    left++;
                } else {
                    // l_max >= r_max,说明right位置的瓶颈是r_max(左边至少有l_max这么高)
                    res += r_max - height[right];
                    right--;
                }
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路二：备忘录
     * 1. height[i]能接的雨水量water[i] = min(l_max,r_max) - height[i]
     * 2. 声明l_max和r_max两个数组，表示位置i的l_max和r_max
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution2 {
        public int trap(int[] height) {
            int n = height.length;
            int res = 0;
            int[] l_max = new int[n];
            int[] r_max = new int[n];
            for (int i = 1; i < n; i++)
                l_max[i] = Math.max(l_max[i - 1], height[i - 1]);
            for (int i = n - 2; i >= 0; i--)
                r_max[i] = Math.max(r_max[i + 1], height[i + 1]);
            for (int i = 1; i < n - 1; i++)
                res += Math.max(Math.min(l_max[i], r_max[i]) - height[i], 0);
            return res;
        }
    }

    /*
     * 思路一：暴力求解
     * 1. height[i]能接的雨水量water[i] = min(l_max,r_max) - height[i]
     * 2. 遍历每个位置，找到其左边最大和右边最大再减去当前高度即可得到当前位置能接的雨水量，累加得到结果
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     * */
    class Solution1 {
        public int trap(int[] height) {
            int n = height.length;
            int res = 0;
            for (int i = 0; i < n - 1; i++) {
                int l_max = height[0];
                int r_max = height[n - 1];
                for (int j = 0; j < i; j++)
                    l_max = Math.max(l_max, height[j]);
                for (int j = i + 1; j < n; j++)
                    r_max = Math.max(r_max, height[j]);
                res += Math.max(Math.min(l_max, r_max) - height[i], 0);
            }
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
        // put your test code here
        solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }
}