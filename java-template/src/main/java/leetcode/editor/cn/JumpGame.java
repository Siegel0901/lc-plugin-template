package leetcode.editor.cn;

public class JumpGame {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 判断是否能够从数组的第一个位置跳跃到最后一个位置
         * <p>
         * 使用贪心算法，维护当前能够到达的最远位置。遍历数组，不断更新最远可达位置，
         * 如果在某个位置发现最远可达位置小于当前位置，说明无法继续前进，返回false。
         * 
         * @param nums 非负整数数组，每个元素代表在该位置可以跳跃的最大长度
         * @return 如果能够到达最后一个位置返回true，否则返回false
         */
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int farthest = 0;
            for (int i = 0; i < n - 1; i++) {
                // 更新当前能够到达的最远位置
                farthest = Math.max(farthest, i + nums[i]);
                // 如果最远位置小于当前位置，说明无法继续前进
                if (farthest <= i)
                    return false;
            }
            // 判断最远位置是否能够到达或超过最后一个位置
            return farthest >= n - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new JumpGame().new Solution();
        // put your test code here
        solution.canJump(new int[]{2, 3, 1, 1, 4});
    }
}