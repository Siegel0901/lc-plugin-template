package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    //leetcode submit region begin(Prohibit modification and deletion)
    // 元素视角
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            backtrack(nums, 0);
            return res;
        }

        // i是当前元素的索引
        void backtrack(int[] nums, int i) {
            // 所有元素都做出了选择
            if (i == nums.length){
                res.add(new ArrayList<>(track));
                return;
            }
            // 选择1：加入集合
            track.add(nums[i]);
            backtrack(nums, i + 1);
            // 撤销选择
            track.remove(track.size() - 1);

            // 选择2：不加入集合
            backtrack(nums, i + 1);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // 集合视角
    class Solution1 {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            backtrack(nums, 0);
            return res;
        }

        void backtrack(int[] nums, int start) {
            // 集合的每种状态都是子集
            res.add(new ArrayList<>(track));
            // 以集合视角回溯，start之前的元素都是被集合选择or不选择过的
            for (int i = start; i < nums.length; i++) {
                // 集合选择该元素
                track.add(nums[i]);
                backtrack(nums, i + 1);
                // 撤销选择
                track.remove(track.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
        // put your test code here

    }
}