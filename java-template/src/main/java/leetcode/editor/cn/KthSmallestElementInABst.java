package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class KthSmallestElementInABst {

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
        public int kthSmallest(TreeNode root, int k) {
            this.k = k;
            dfs(root);
            return target;
        }

        int count = 0;
        int k = 0;
        int target = 0;

        void dfs(TreeNode root) {
            if (root == null)
                return;
            dfs(root.left);
            if (++count == k)
                target = root.val;
            dfs(root.right);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new KthSmallestElementInABst().new Solution();
        // put your test code here

    }
}