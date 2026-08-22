package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：同余定理+前缀和+哈希表
     * 1. 同余定理：(preSum[j] - preSum[i]) % k = 0 <==> preSum[j] % k - preSum[i] % k = 0 <==> preSum[j] % k = preSum[i] % k
     * 2. 转化为有没有两个前缀和对k取模的余数相等，且结尾元素构成的区间的长度>=2
     * 2. 求前缀和的过程中使用哈希表记录前缀和以及余数第一次出现的索引
     * 2. 每计算一个前缀和，都查一遍哈希表找是否存在相同余数的索引
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 0);
            int preSum = 0;
            for (int i = 1; i < nums.length + 1; i++) {
                preSum += nums[i - 1];
                int remainder = preSum % k;
                if (map.containsKey(remainder)) {
                    if (i - map.get(remainder) >= 2)
                        return true;
                } else
                    map.put(remainder, i);
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ContinuousSubarraySum().new Solution();
        // put your test code here

    }
}