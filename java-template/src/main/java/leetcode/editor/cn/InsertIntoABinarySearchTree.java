package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class InsertIntoABinarySearchTree {

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
         * 思路：利用BST性质
         * 1. 根据val与root.val的大小，选择将val插入到root的左子树还是右子树
         * 2. 插入完后更新root的左右子树
         * 3. 若root为空，则说明找到了插入点
         */
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null)
                return new TreeNode(val);
            if (val < root.val)
                root.left = insertIntoBST(root.left, val);
            if (val > root.val)
                root.right = insertIntoBST(root.right, val);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new InsertIntoABinarySearchTree().new Solution();
        // put your test code here

    }
}