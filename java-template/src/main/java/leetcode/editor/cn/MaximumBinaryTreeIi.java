package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class MaximumBinaryTreeIi {

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
        /**
         * 思路：分解问题
         * 1. 因为要满足a和b中的最大值左边为左子树，右边为右子树，b是a末尾附加val的副本
         * 2. 故val要么是最大的根节点，要么是最大值的右子树中的节点
         * 3. 如果root.val小于val，则val作为根节点，root.val作为根节点的左子树
         * 4. 如果root.val大于val，则将val插入到root的右子树中
         */
        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            if (root == null)
                return new TreeNode(val);
            if (root.val < val) {
                TreeNode temp = root;
                root = new TreeNode(val);
                root.left = temp;
            } else
                root.right = insertIntoMaxTree(root.right, val);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MaximumBinaryTreeIi().new Solution();
        // put your test code here

    }
}