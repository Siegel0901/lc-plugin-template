package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class InvertBinaryTree {

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
         * 1. 二叉树 = 左子树 + 右子树 -> 翻转二叉树=  翻转右子树 + 翻转左子树
         * 2. 递归函数有返回值，表示的是翻转后的根节点
         * 3. 需要得到左右子树翻转后的根节点，故采用后序遍历
         *
         * @param root 根节点
         * @return 翻转后的根节点
         */
//        public TreeNode invertTree(TreeNode root) {
//            if (root == null)
//                return null;
//            TreeNode left = invertTree(root.left);
//            TreeNode right = invertTree(root.right);
//            root.left = right;
//            root.right = left;
//            return root;
//        }

        /**
         * 思路二：【遍历】
         * 1. 对于每个节点，只需要交换左右子树即可得到翻转后的二叉树
         * 2. 递归函数无返回值
         * 3. 前中后序遍历都可以
         * 4. 中序位置交换完左右子树后，应继续遍历左子树（原来的右子树）
         *
         * @param root 根节点
         * @return 翻转后的根节点
         */
        public TreeNode invertTree(TreeNode root) {
            traverse(root);
            return root;
        }

        void traverse(TreeNode root) {
            if (root == null)
                return;
            // 前序遍历
//            TreeNode t = root.left;
//            root.left = root.right;
//            root.right = t;
//            traverse(root.left);
//            traverse(root.right);
            // 中序遍历
//            traverse(root.left);
//            TreeNode t = root.left;
//            root.left = root.right;
//            root.right = t;
//            // 遍历交换后的左子树（原来的右子树）
//            traverse(root.left);
            // 后序遍历
            traverse(root.left);
            traverse(root.right);
            TreeNode t = root.left;
            root.left = root.right;
            root.right = t;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new InvertBinaryTree().new Solution();
        // put your test code here

    }
}