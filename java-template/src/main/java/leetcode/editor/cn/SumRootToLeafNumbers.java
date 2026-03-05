package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class SumRootToLeafNumbers {

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
         * 思路：遍历
         * 1. 用StringBuilder存放节点值
         * 2. 遍历到叶子节点时，将sb转换为整数，并加入res中
         */
        public int sumNumbers(TreeNode root) {
            traverse(root);
            return res;
        }

        int res = 0;
        StringBuilder sb = new StringBuilder();

        void traverse(TreeNode root) {
            if (root == null)
                return;
            sb.append(root.val);
            if (root.left == null && root.right == null)
                res += Integer.parseInt(sb.toString());
            traverse(root.left);
            traverse(root.right);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SumRootToLeafNumbers().new Solution();
        // put your test code here

    }
}