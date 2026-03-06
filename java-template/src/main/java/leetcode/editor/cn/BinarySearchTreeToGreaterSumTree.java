package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class BinarySearchTreeToGreaterSumTree {

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
        public TreeNode bstToGst(TreeNode root) {
            dfs(root);
            return root;
        }

        int sum = 0;

        void dfs(TreeNode root) {
            if (root == null)
                return;
            dfs(root.right);
            sum += root.val;
            root.val = sum;
            dfs(root.left);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinarySearchTreeToGreaterSumTree().new Solution();
        // put your test code here

    }
}