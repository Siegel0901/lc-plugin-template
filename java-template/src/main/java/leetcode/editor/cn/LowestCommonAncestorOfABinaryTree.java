package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class LowestCommonAncestorOfABinaryTree {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {

        TreeNode lca = null;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return find(root, p, q);
        }

        public TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;
            // 优化的点：已经找到lca，就不继续遍历了
            if (lca != null)
                return null;
            // 当前节点为p或q，则当前节点就是LCA
            if (root == p || root == q)
                return root;
            // 当前节点不是p或q，则在当前节点的左右子树中找p或q
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // 左p右q或者左q右p，则当前节点为LCA
            if (left != null && right != null) {
                // 记录lca
                lca = root;
                return root;
            }
            // 返回子树的查找结果为LCA
            return left != null ? left : right;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
        // put your test code here

    }
}