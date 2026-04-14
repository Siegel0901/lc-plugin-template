package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

public class SingleNumber {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：位运算(异或)
     * 异或运算的性质：
     * 1. 任何数与0异或等于自身：a^0=a
     * 2. 相同数异或等于0:a^a=0
     * 3. 异或满足交换律和结合律:a^b^a = b^(a^a) = b^0 = b
     * 将所有数进行异或运算,成对的数相互抵消为0,最后剩下的数为只出现一次的数字
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int num : nums)
                res ^= num;
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：排序后查找
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    class Solution2 {
        public int singleNumber(int[] nums) {
            Arrays.sort(nums);
            // 记录上次的数字
            Integer last = null;
            // 记录上次数字出现的次数，到达2清零
            int count = 0;
            for (int num : nums) {
                // 遇到新数字
                if (count == 0 && (last == null || num != last)) {
                    last = num;
                    count++;
                    continue;
                }
                // 已经记录了一个数字，看当前数字跟上一个数字是否相同
                if (count == 1) {
                    if (last == num) count = 0; // 相同则重置计数
                    else return last;   // 不相同则找到了唯一的数字
                }
            }
            // 出现一次的元素排序后在最后一个位置
            if (count == 1)
                return last;
            return -1;
        }
    }

    /**
     * 思路一：HashMap
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    class Solution1 {
        public int singleNumber(int[] nums) {
            HashMap<Integer, Integer> count = new HashMap<>();
            for (int num : nums)
                count.put(num, count.getOrDefault(num, 0) + 1);
            for (int num : nums)
                if (count.get(num) == 1)
                    return num;
            return -1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new SingleNumber().new Solution();
        // put your test code here
        solution.singleNumber(new int[]{4, 1, 2, 1, 2});
    }
}