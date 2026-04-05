package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIii {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    /**
     * 思路二：递归DP + 不用备忘录
     * 时间复杂度：O(N)
     * 空间复杂度：O(H)
     * N为节点数，H为递归深度，即树高
     */
    class Solution {
        public int rob(TreeNode root) {
            int[] res = dp(root);
            return Math.max(res[0], res[1]);
        }

        // dp(root)表示从root出发，在不触动警报的情况下，小偷不打劫root和打劫root能够盗取的最高金额
        int[] dp(TreeNode root) {
            if (root == null)
                return new int[]{0, 0};
            // left存放不打劫root.left和打劫root.left能盗取的最高金额
            int[] left = dp(root.left);
            // right存放不打劫root.right和打劫root.right能盗取的最高金额
            int[] right = dp(root.right);
            // 打劫root,则不能打劫root.left和root.right
            int rob = root.val + left[0] + right[0];
            // 不打劫root，则可以选择打劫or不打劫root.left和root.right
            int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            return new int[]{notRob, rob};
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：递归DP + 备忘录
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * N为节点数
     */
    class Solution1 {
        Map<TreeNode, Integer> memo = new HashMap<>();

        public int rob(TreeNode root) {
            return dp(root);
        }

        // dp(root)表示从root出发，在不触动警报的情况下，小偷能够盗取的最高金额
        int dp(TreeNode root) {
            if (root == null)
                return 0;
            if (memo.containsKey(root))
                return memo.get(root);
            int rob = root.val
                    + (root.left == null ? 0 : dp(root.left.left) + dp(root.left.right))
                    + (root.right == null ? 0 : dp(root.right.left) + dp(root.right.right));
            int notRob = (root.left == null ? 0 : dp(root.left))
                    + (root.right == null ? 0 : dp(root.right));
            int res = Math.max(rob, notRob);
            memo.put(root, res);
            return res;
        }

    }


    public static void main(String[] args) {
        Solution solution = new HouseRobberIii().new Solution();
        // put your test code here
        TreeNode root = TreeNode.createRoot(new Integer[]{3, 2, 3, null, 3, null, 1});
        System.out.println(solution.rob(root));
    }
}