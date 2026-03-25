package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        int trackSum = 0;

        public List<List<Integer>> combinationSum3(int k, int n) {
            backtrack(k, n, 1);
            return res;
        }

        void backtrack(int k, int n, int start) {
            if (track.size() == k && trackSum == n) {
                res.add(new ArrayList<>(track));
                return;
            }
            if (trackSum > n || track.size() > k)
                return;
            for (int i = start; i < 10; i++) {
                track.add(i);
                trackSum += i;
                backtrack(k, n, i + 1);
                trackSum -= i;
                track.remove(track.size() - 1);
            }

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new CombinationSumIii().new Solution();
        // put your test code here

    }
}