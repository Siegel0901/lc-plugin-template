package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            backtrack(n, k, 1);
            return res;
        }

        void backtrack(int n, int k, int start) {
            if (track.size() == k){
                res.add(new ArrayList<>(track));
                return;
            }
            for (int i = start; i <= n; i++) {
                track.add(i);
                backtrack(n, k, i + 1);
                track.remove(track.size() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
        // put your test code here

    }
}