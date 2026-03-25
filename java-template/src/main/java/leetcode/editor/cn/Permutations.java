package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        // 用于记录位置是否被使用
        boolean[] used;
        // 用于记录有多少个元素已经找到了位置
        int count = 0;

        /**
         * 思路：以元素视角进行回溯
         * 1. 每个元素都要选择一个位置
         * 元素 A → 选择位置 0
         * 元素 B → 选择位置 1
         * 元素 C → 选择位置 2
         * 核心：每个元素找位置
         */
        public List<List<Integer>> permute(int[] nums) {
            used = new boolean[nums.length];
            backtrack(nums);
            return res;
        }

        void backtrack(int[] nums) {
            // 结束条件：所有元素都选择了位置
            if (count == nums.length) {
                List<Integer> list = new ArrayList<>();
                for (int num : nums)
                    list.add(num);
                res.add(list);
                return;
            }

            int originIndex = -1;

            // 遍历所有位置，让当前元素选择
            for (int i = 0; i < nums.length; i++) {
                // i位置已经被占了
                if (used[i])
                    continue;
                // originIndex记录元素的原始位置
                if (originIndex == -1)
                    originIndex = i;
                // nums[originIndex]元素选择位置i
                swap(nums, originIndex, i);
                used[i] = true;
                count++;
                // 进入下一层
                backtrack(nums);
                // 撤销选择
                count--;
                used[i] = false;
                swap(nums, originIndex, i);
            }
        }

        void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        List<List<Integer>> res = new ArrayList<>();

        /**
         * 思路：以位置视角进行回溯
         * 时间复杂度：O(n!)
         * 空间复杂度：O(n)
         */
        public List<List<Integer>> permute(int[] nums) {
            List<Integer> track = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            backtrack(nums, track, used);
            return res;
        }

        void backtrack(int[] nums, List<Integer> track, boolean[] used) {
            // 结束条件：所有元素都包含在track中
            if (track.size() == nums.length) {
                res.add(new ArrayList<>(track));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                // 用used数组实现选择列表
                if (used[i])
                    continue;
                // 做选择
                track.add(nums[i]);
                used[i] = true;
                // 进入下一层决策树
                backtrack(nums, track, used);
                // 撤销选择
                used[i] = false;
                track.remove(track.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
        // put your test code here

    }
}