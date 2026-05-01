package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：Boyer-Moore 投票算法
     * 1. 用多数元素与其余元素一对一抵消
     * 2. 最后剩下的一定是多数元素
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int majorityElement(int[] nums) {
            int count = 0;
            int candidate = Integer.MIN_VALUE;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                    count++;
                } else if (candidate == num)
                    count++;
                else
                    count--;
            }
            return candidate;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：HashMap计数
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        public int majorityElement(int[] nums) {
            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums)
                map.put(num, map.getOrDefault(num, 0) + 1);
            for (int num : map.keySet())
                if (map.get(num) > n / 2)
                    return num;
            return Integer.MIN_VALUE;
        }
    }


    public static void main(String[] args) {
        Solution solution = new MajorityElement().new Solution();
        // put your test code here

    }
}