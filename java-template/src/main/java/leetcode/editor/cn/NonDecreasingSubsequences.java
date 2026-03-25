package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NonDecreasingSubsequences {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            backtrack(nums, 0);
            return res;
        }

        /**
         * 以集合的视角出发
         * 让集合的每个位置去选择数字
         */
        void backtrack(int[] nums, int start) {
            // 若集合当前至少有两个元素,则加入答案
            if (track.size() >= 2)
                res.add(new ArrayList<>(track));
            // 用hashset记录当前位置选择过哪些数字
            Set<Integer> used = new HashSet<>();
            // 当前位置选择数字
            for (int i = start; i < nums.length; i++) {
                // 判断该数字是否满足递增条件
                if (!track.isEmpty() && nums[i] < track.get(track.size() - 1))
                    continue;
                // 判断当前位置是否使用过该数字
                if (used.contains(nums[i]))
                    continue;
                // 做选择
                track.add(nums[i]);
                // 标记当前位置使用过该数字
                used.add(nums[i]);
                // 下一个位置选择数字
                backtrack(nums, i + 1);
                // 撤销选择
                track.remove(track.size() - 1);
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NonDecreasingSubsequences().new Solution();
        // put your test code here

    }
}