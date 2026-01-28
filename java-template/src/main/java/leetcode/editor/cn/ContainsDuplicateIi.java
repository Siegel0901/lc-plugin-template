package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicateIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：哈希表记录相同元素位置索引
         * 1. 将数组元素加入哈希表，key为元素值，value为索引集合
         * 2. 当key的value集合更新时，判断是否有索引距离<=k的两个索引，有则返回true
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         * @param k    距离
         * @return 是否存在距离小于等于k的重复元素
         */
//        public boolean containsNearbyDuplicate(int[] nums, int k) {
//            Map<Integer, List<Integer>> map = new HashMap<>();
//            for (int i = 0; i < nums.length; i++) {
//                if (map.containsKey(nums[i])) {
//                    List<Integer> indexList = map.get(nums[i]);
//                    for (Integer index : indexList)
//                        if (i - index <= k)
//                            return true;
//                    indexList.add(i);
//                } else {
//                    map.put(nums[i], new ArrayList<>(Collections.singletonList(i)));
//                }
//            }
//            return false;
//        }

        /**
         * 思路二：滑动窗口
         * 窗口中维护当前元素之前的最多k个元素，判断当前元素在窗口内是否有重复
         * 1. 什么时候扩大窗口？窗口大小小于k
         * 2. 什么时候缩小窗口？窗口大小大于k
         * 3. 什么时候返回结果？窗口大小小于等于k，且当前元素在窗口内有重复
         * 时间复杂度：O(n)
         * 空间复杂度：O(min(n,k))
         *
         * @param nums 数组
         * @param k    距离
         * @return 存在距离小于等于k的元素
         */
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Set<Integer> window = new HashSet<>();
            int left = 0, right = 0;
            while (right < nums.length) {
                int r = nums[right++];
                if (window.contains(r))
                    return true;
                window.add(r);
                if (right - left > k)
                    window.remove(nums[left++]);
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ContainsDuplicateIi().new Solution();
        // put your test code here

    }
}