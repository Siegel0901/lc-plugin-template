package leetcode.editor.cn;

public class ClimbingStairs {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            if (n == 1 || n == 2)
                return n;
            int dp_i_1 = 2;
            int dp_i_2 = 1;
            for (int i = 3; i <= n; i++) {
                int dp_i = dp_i_1 + dp_i_2;
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i_1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        // put your test code here

    }
}