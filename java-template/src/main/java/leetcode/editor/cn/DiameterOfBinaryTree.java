package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class DiameterOfBinaryTree {

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
    class Solution {
        /*
         * 【路径长度】：由从顶点到左子树走的最长路径【左深】 + 从顶点到右子树走的最长路径【右深】【分解问题思维】
         * 【直径】：所有【路径】的最大值【遍历思维】
         * 【深度】：左右子树的最大深度 + 1【分解问题思维】
         * */
        public int diameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return diameter;
        }

        int maxDepth(TreeNode root) {
            if (root == null)
                return 0;
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            int length = left + right;
            diameter = Math.max(diameter, length);
            return Math.max(left, right) + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new DiameterOfBinaryTree().new Solution();
        // put your test code here

    }
}