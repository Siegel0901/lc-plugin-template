package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {

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
         * 【遍历思维】
         * */
//        private final List<Integer> preorderTraversal = new ArrayList<>();
//
//        private void traverse(TreeNode root) {
//            if (root == null)
//                return;
//            preorderTraversal.add(root.val);
//            traverse(root.left);
//            traverse(root.right);
//        }
//
//        public List<Integer> preorderTraversal(TreeNode root) {
//            preorderTraversal.clear();
//            traverse(root);
//            return preorderTraversal;
//        }
        /*
         * 【分解问题思维】
         * 1. 求根节点的前序遍历结果，如何分解为左右子树的前序遍历组合？
         * 2. 前序遍历访问节点顺序就是根左右
         * 2.1. 获得根节点的值加上左子树的前序遍历结果
         * 2.2. 再加上右子树的前序遍历结果，即可得到根节点前序遍历结果
         * */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> preorder = new ArrayList<>();
            if (root == null)
                return preorder;
            List<Integer> left = preorderTraversal(root.left);
            List<Integer> right = preorderTraversal(root.right);
            preorder.add(root.val);
            preorder.addAll(left);
            preorder.addAll(right);
            return preorder;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
        // put your test code here
        System.out.println(solution.preorderTraversal(TreeNode.createRoot(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9})));

    }
}