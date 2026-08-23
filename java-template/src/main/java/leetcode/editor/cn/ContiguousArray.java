package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：前缀和+哈希表
     * 1. 0和1数量相同的最长连续子数组，如果把0视为-1，实际上就是求和为0的最长连续子数组
     * 2. 区间和通过前缀和求得
     * 3. 在求前缀和的过程中，用哈希表记录前缀和以及前缀和第一次出现的索引（记录第一次保证最长）
     * */
    class Solution {
        public int findMaxLength(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 0);
            int preSum = 0;
            int res = 0;
            /*
            * preSum数组的长度：n+1
            * preSum[0]=0
            * 前缀和为0的下标为0
            * preSum[right+1] - preSum[left]表示闭区间[left,right]的区间和，区间长度为right - left + 1
            * preSum[j] - preSum[i]表示闭区间[i,j-1]的区间和，区间长度为j - 1 - i + 1 = j - i
            * */
            for (int i = 1; i < nums.length + 1; i++) {
                preSum += (nums[i - 1] == 0 ? -1 : 1);
                /*
                 * need = 0 = preSum[i] - preSum[j] <==> preSum[i] = preSum[j]
                 * 故需要寻找与perSum相等的前缀和下标
                 * */
                if (map.containsKey(preSum))
                    res = Math.max(res, i - map.get(preSum));
                else
                    map.put(preSum, i);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ContiguousArray().new Solution();
        // put your test code here

    }
}