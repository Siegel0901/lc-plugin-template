package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class NumbersWithSameConsecutiveDifferences {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<Integer> res = new ArrayList<>();
        int track = 0;

        public int[] numsSameConsecDiff(int n, int k) {
            backtrack(n, k, 0);
            int[] arr = new int[res.size()];
            for (int i = 0; i < res.size(); i++)
                arr[i] = res.get(i);
            return arr;
        }

        void backtrack(int n, int k, int index) {
            if (index == n) {
                res.add(track);
                return;
            }
            for (int i = 0; i < 10; i++) {
                if (index == 0 && i == 0)
                    continue;
                if (index > 0 && Math.abs(track % 10 - i) != k)
                    continue;
                track = track * 10 + i;
                backtrack(n, k, index + 1);
                track = track / 10;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NumbersWithSameConsecutiveDifferences().new Solution();
        // put your test code here

    }
}