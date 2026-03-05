package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class FlattenBinaryTreeToLinkedList {

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
         * 思路一：【分解问题】
         * 根节点展平结果为：左子树为空，右子树为（原左子树展平 + 原右子树展平）
         *
         * @param root 根节点
         */
        public void flatten(TreeNode root) {
            if (root == null)
                return;
            // 记录左右子树
            TreeNode left = root.left;
            TreeNode right = root.right;
            // 对左右子树进行展平操作
            flatten(left);
            flatten(right);
            // 左子树为空
            root.left = null;
            // 右子树为原左子树展平 + 原右子树展平
            root.right = left;
            // 原右子树展平结果接到当前右子树（原左子树展平结果）的末尾
            TreeNode p = root;
            while (p.right != null)
                p = p.right;
            p.right = right;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
        // put your test code here

    }
}