package leetcode.editor.cn;

import java.util.TreeSet;

public class ContainsDuplicateIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：滑动窗口
         * 1. 窗口中存放当前元素最近的最多indexDiff个元素，判断当前元素与窗口元素的valueDiff
         * 2. 什么时候扩大窗口？窗口大小小于indexDiff
         * 3. 什么时候缩小窗口？窗口大小大于indexDiff
         * 4. 什么时候返回结果？窗口大小小于等于indexDiff，且当前元素与窗口元素的差值小于等于valueDiff
         * 5. TreeSet方法:
         * 5.1. floor(x) 找<=x的最大元素
         * 5.2. ceiling(x) 找>=x的最小元素
         * 时间复杂度：O(nlog(min(n,k)))
         * 空间复杂度：O(min(n,k))
         *
         * @param nums      数组
         * @param indexDiff 距离
         * @param valueDiff 差值
         * @return 存在距离小于等于indexDiff且差值小于等于valueDiff的元素
         */
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            TreeSet<Integer> window = new TreeSet<>();
            int left = 0, right = 0;
            while (right < nums.length) {
                // r为当前元素
                int r = nums[right++];
                // 下界
                int lower = r - valueDiff;
                // 上界
                int upper = r + valueDiff;
                // num为窗口中<=upper小于等于上界的最大值
                Integer num = window.floor(upper);
                // 判断num是否>=lower大于等于下界lower，num ∈ [r - valueDiff, r + valueDiff]返回true
                if (num != null && num >= lower)
                    return true;
                window.add(r);
                while (right - left > indexDiff)
                    window.remove(nums[left++]);
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ContainsDuplicateIii().new Solution();
        // put your test code here

    }
}