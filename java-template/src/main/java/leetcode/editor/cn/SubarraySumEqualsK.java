package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：前缀和 + 哈希表
     * 1. 算出前缀和数组preSum
     * 2. 在计算过程中，用哈希表记录前缀和到该前缀和出现次数的映射，方便快速查找所需的前缀和
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution {
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> cnt = new HashMap<>();
            // 前缀和为0的次数为1，为了匹配preSum[i]=k的情况
            cnt.put(0, 1);
            int res = 0;
            int n = nums.length;
            int preSum = 0;
            for (int i = 1; i < n + 1; i++) {
                // 计算前缀和
                preSum += nums[i - 1];
                /*
                 * 得到了preSum这个前缀和，目标区间和是k
                 * 如果哈希表中有preSum-k这个前缀和，说明存在以nums[i-1]为结尾的子数组和为k
                 * */
                int need = preSum - k;
                if (cnt.containsKey(need))
                    res += cnt.get(need);
                // 记录前缀和
                cnt.put(preSum, cnt.getOrDefault(preSum, 0) + 1);
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：前缀和
     * 1. 算出前缀和数组preSum
     * 2. 遍历所有闭区间，计算区间和是否等于k
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        public int subarraySum(int[] nums, int k) {
            int n = nums.length;
            // 计算前缀和
            int[] preSum = new int[n + 1];
            for (int i = 1; i < preSum.length; i++)
                preSum[i] = preSum[i - 1] + nums[i - 1];
            int res = 0;
            // 判断所有闭区间[i,j]的和是否等于k
            for (int i = 0; i < n; i++)
                for (int j = i; j < n; j++)
                    if (preSum[j + 1] - preSum[i] == k)
                        res++;
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new SubarraySumEqualsK().new Solution();
        // put your test code here
        solution.subarraySum(new int[]{1, 2, 3}, 3);
    }
}