package leetcode.editor.cn;

import java.util.HashMap;

public class SingleNumber {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            HashMap<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
            for (int num : nums) {
                if (count.get(num) == 1)
                    return num;
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SingleNumber().new Solution();
        // put your test code here

    }
}