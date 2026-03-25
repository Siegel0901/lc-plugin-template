package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        int trackSum = 0;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtrack(candidates, target, 0);
            return res;
        }

        void backtrack(int[] candidates, int target, int start) {
            if (trackSum == target) {
                res.add(new ArrayList<>(track));
                return;
            }
            if (trackSum > target)
                return;
            for (int i = start; i < candidates.length; i++) {
                track.add(candidates[i]);
                trackSum += candidates[i];
                backtrack(candidates, target, i);
                trackSum -= candidates[i];
                track.remove(track.size() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new CombinationSum().new Solution();
        // put your test code here

    }
}