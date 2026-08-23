package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class ProductOfArrayExceptSelf {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：前缀积+空间压缩
     * 1. 计算所有位置的左右乘积存入数组leftProduct和rightProduct
     * 2. i位置的乘积为leftProduct[i] * rightProduct[i]
     * 3. 复用结果数组res，作为leftProduct数组
     * 4. 用变量rp表示rightProduct数组，加入结果数组res
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            // 构建左乘积数组
            res[0] = 1;
            for (int i = 1; i < n; i++)
                res[i] = res[i - 1] * nums[i - 1];
            // 加入右乘积
            int rp = 1;
            for (int i = n - 2; i >= 0; i--) {
                rp *= nums[i + 1];
                res[i] *= rp;
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路：前缀积
     * 1. 计算所有位置的左右乘积存入数组leftProduct和rightProduct
     * 2. i位置的乘积为leftProduct[i] * rightProduct[i]
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution2 {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            // 构建左乘积数组
            int[] l = new int[n];
            l[0] = 1;
            for (int i = 1; i < n; i++)
                l[i] = l[i - 1] * nums[i - 1];
            // 构建右乘积数组
            int[] r = new int[n];
            r[n - 1] = 1;
            for (int i = n - 2; i >= 0; i--)
                r[i] = r[i + 1] * nums[i + 1];
            // 构建结果数组
            for (int i = 0; i < n; i++)
                nums[i] = l[i] * r[i];
            return nums;
        }
    }

    /*
     * 思路：暴力求解
     * 1. 对于每个位置i，计算i左边所有元素的乘积leftProduct
     * 2. 计算i右边所有元素的乘积rightProduct
     * 3. i位置的乘积为leftProduct * rightProduct
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     * */
    class Solution1 {
        public int[] productExceptSelf(int[] nums) {
            List<Integer> res = new ArrayList<>();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                int lp = 1;
                for (int j = 0; j < i; j++)
                    lp *= nums[j];
                int rp = 1;
                for (int j = i + 1; j < n; j++)
                    rp *= nums[j];
                res.add(lp * rp);
            }
            return res.stream().mapToInt(Integer::intValue).toArray();
        }
    }


    public static void main(String[] args) {
        Solution solution = new ProductOfArrayExceptSelf().new Solution();
        // put your test code here

    }
}