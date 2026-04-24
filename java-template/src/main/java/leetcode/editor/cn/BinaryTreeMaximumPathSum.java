package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeMaximumPathSum {

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
    /*
     * 思路二：以每个节点为“最高点”计算最大路径，同时记录最为分支的最大贡献【分解问题思维】
     * 1. 遍历每个节点
     * 2. 计算经过该节点的最大路径和 = 左子树的最大贡献 + 当前节点值 + 右子树的最大贡献
     * 3. 计算该节点作为分支的最大贡献 = 当前节点值 + max(左子树最大贡献，右子树最大贡献)
     * 4. 若左右子树的贡献为负数，则不选
     * 5. 记录所有节点作为最高点路径和的最大值
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * */
    class Solution {
        int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            getMaxContrib(root);
            return maxSum;
        }

        // 定义：返回节点作为分支的最大贡献
        int getMaxContrib(TreeNode root) {
            if (root == null)
                return 0;
            // 获取左子树的最大贡献（小于0则舍去）
            int leftCtb = Math.max(getMaxContrib(root.left), 0);
            // 获取右子树的最大贡献（小于0则舍去）
            int rightCtb = Math.max(getMaxContrib(root.right), 0);
            // 记录作为最高点路径和的最大值
            maxSum = Math.max(maxSum, root.val + leftCtb + rightCtb);
            // 返回作为分支的最大贡献
            return root.val + Math.max(leftCtb, rightCtb);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：以每个节点为“最高点”计算最大路径，同时记录最为分支的最大贡献【遍历思维】
     * 1. 遍历每个节点
     * 2. 计算经过该节点的最大路径和 = 左子树的最大贡献 + 当前节点值 + 右子树的最大贡献
     * 3. 计算该节点作为分支的最大贡献 = 当前节点值 + max(左子树最大贡献，右子树最大贡献)
     * 4. 若左右子树的贡献为负数，则不选
     * 5. 记录所有节点作为最高点路径和的最大值
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        Map<TreeNode, Integer> map = new HashMap<>();
        int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            traverse(root);
            return maxSum;
        }

        void traverse(TreeNode root) {
            if (root == null)
                return;
            traverse(root.left);
            traverse(root.right);

            int left = Math.max(map.getOrDefault(root.left, 0), 0);
            int right = Math.max(map.getOrDefault(root.right, 0), 0);
            int pathSum = root.val + Math.max(left, right);
            maxSum = Math.max(maxSum, root.val + left + right);
            map.put(root, pathSum);
        }
    }


    public static void main(String[] args) {
        Solution solution = new BinaryTreeMaximumPathSum().new Solution();
        // put your test code here

    }
}