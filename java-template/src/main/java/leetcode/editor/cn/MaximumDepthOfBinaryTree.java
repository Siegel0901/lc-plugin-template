package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class MaximumDepthOfBinaryTree {

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
         * 【遍历思维模式】
         * 1. 递归函数没有返回值
         * 2. 非叶子节点无定义
         * 3. 收集叶子节点的结果【深度】
         * */
//        int depth = 0;
//        int maxDep = 0;
//
//        public int maxDepth(TreeNode root) {
//            traverse(root);
//            return maxDep;
//        }
//
//        void traverse(TreeNode root) {
//            if (root == null)
//                return;
//            depth++;
//            if (root.left == null && root.right == null)
//                maxDep = Math.max(maxDep, depth);
//            traverse(root.left);
//            traverse(root.right);
//            depth--;
//        }

        /*
         * 【分解问题思维模式】
         * 1. 根节点的最大深度为左右子树的最大深度 + 1
         * 2. 递归函数有返回值
         * 3. 树的每个节点对应的递归函数都满足其定义：返回的是当前节点为根的树的深度
         * */
        public int maxDepth(TreeNode root) {
            if (root == null)
                return 0;
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
        // put your test code here
        System.out.println(solution.maxDepth(TreeNode.createRoot(new Integer[]{3, 9, 20, null, null, 15, 7})));
    }
}