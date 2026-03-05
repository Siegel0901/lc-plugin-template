package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class LowestCommonAncestorOfABinarySearchTree {

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
        /**
         * 利用二叉搜索树的性质：左 < 中 < 右
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return null;
            // 保证p.val <= q.val
            if (p.val > q.val)
                return lowestCommonAncestor(root, q, p);
            // 若root.val满足p.val <= root.val <= q.val，则root为LCA
            if (p.val <= root.val && root.val <= q.val)
                return root;
            if (root.val > p.val)
                // 若root.val满足p.val < q.val < root.val，则p和q都在root的左子树
                return lowestCommonAncestor(root.left, p, q);
            else
                // 只剩一种情况：root.val满足root.val < p.val < q.val，则p和q都在root的右子树
                return lowestCommonAncestor(root.right, p, q);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinarySearchTree().new Solution();
        // put your test code here

    }
}